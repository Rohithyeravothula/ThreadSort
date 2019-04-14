package threadsort;

import java.util.*;

public class TestChunkSort {
    public void swap(int[] nums, int left, int right){
        nums[left] = nums[left]^nums[right];
        nums[right] = nums[left]^nums[right];
        nums[left] = nums[left]^nums[right];
    }

    public void testSwap(){
        int[] a = {0, 1,2,3,4,5};
        swap(a, 1, 1);
        System.out.println(Arrays.toString(a));
    }

    public void testArrayHeap(){
        Queue<Integer[]> queue = new PriorityQueue<>(Comparator.comparingInt((Integer[] o) -> o[0]));
        Integer[] a = {1,2};
        Integer[] b = {3,4};
        queue.add(a);
        queue.add(b);
        System.out.println(Arrays.toString(queue.poll()));

    }

    public void testHeap(){
        int[] a = {5,2,3,6,1,23};
        Queue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt((Integer i) -> a[i]));
        queue.add(0);
        queue.add(1);
        queue.add(2);
        System.out.println(queue.poll());
    }

    public static void main(String[] args) {
        TestChunkSort testChunkSort = new TestChunkSort();
//        testChunkSort.testHeap();
        int[] a = {1,2,3,4,5};
        int index=0;
        while(index<a.length)
            System.out.println(a[index++]);
    }
}
