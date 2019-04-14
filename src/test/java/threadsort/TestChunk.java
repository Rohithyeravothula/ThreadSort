package threadsort;

import java.util.Arrays;
import java.util.Random;

public class TestChunk {
    private Random rand = new Random();
    int testArraySize = 100000;
    int arrayRange = 10000;

    public int[] getRandomArray(int size, int range){
        int[] nums = new int[size];
        for(int i=0;i<size;i++)
            nums[i] = rand.nextInt(range);
        return nums;
    }

    public void testTestChunk() throws InterruptedException {
        int[] nums = getRandomArray(50000, arrayRange);
//        System.out.println(Arrays.toString(nums));
        Chunk chunk = new Chunk(nums);
        chunk.threadSort();
//        System.out.println(Arrays.toString(nums));
    }

    public void repeatTest(int limit) throws InterruptedException {
        int count=0;
        while(count++<limit){
            testTestChunk();
            if(count%100==0)
                System.out.println("finished: " + count + " tests");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestChunk testChunk = new TestChunk();
//        testChunk.testTestChunk();
        testChunk.repeatTest(10000);
    }
}
