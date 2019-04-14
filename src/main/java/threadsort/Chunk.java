package threadsort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;


public class Chunk {

    private ChunkSort[] chunkSorts;
    private int length, chunkCount;
    private int[] nums;
    private final int chunkSize = 1000;

    public Chunk(int[] nums){
        this.nums = nums;
        chunkCount =1+((nums.length-1)/chunkSize);
        length = nums.length;
        chunkSorts = new ChunkSort[chunkCount];
        for(int i=0;i<chunkCount;i++){
            chunkSorts[i] = new ChunkSort(nums,i* chunkSize,
                    Math.min(length-1, ((i+1)*chunkSize) -1));
        }
    }

    public void sort(){
        for(ChunkSort cs: chunkSorts)
            cs.sort();
        merge();
    }

    public void threadSort() throws InterruptedException {
        Thread[] threads = new Thread[chunkCount];
        for(int i=0;i<chunkCount;i++){
            threads[i] = new Thread(chunkSorts[i]);
            threads[i].start();
        }
        for(Thread t: threads)
            t.join();
        merge();
    }

    private void merge(){
        int[] sorted = new int[length];
        Queue<Integer[]> queue = new PriorityQueue<>(Comparator.comparingInt((Integer[] o) -> nums[o[0]]));

        for(int i=0;i<chunkCount;i++){
            queue.add(new Integer[] {chunkSorts[i].start, chunkSorts[i].end});
        }

        int index=0;
        Integer[] info;
        while(!queue.isEmpty()){
            info = queue.poll();
            sorted[index] = nums[info[0]];
            info[0]++;
            index++;
            if(info[0] <= info[1])
                queue.add(new Integer[]{info[0], info[1]});
        }
        index=0;
        for(int cur: sorted)
            nums[index++] = cur;
    }
}
