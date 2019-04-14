package threadsort;

import java.util.Arrays;

public class ChunkSort implements Runnable{
    int[] nums;
    int start, end;
    public ChunkSort(int[] nums, int start, int end){
        this.nums = nums;
        this.start = start;
        this.end = end;
    }

    public void merge(int left, int mid, int right) {
        if (left == right - 1) {
            if (nums[left] > nums[right]) {
                nums[left] = nums[left] + nums[right];
                nums[right] = nums[left] - nums[right];
                nums[left] = nums[left] - nums[right];
            }
        } else {
            int x = 0, y = 1, i = 0, l = right - left + 1;
            int[] tmp = new int[l];
            while (left + x <= mid && mid + y <= right) {
                if (nums[left + x] < nums[mid + y]) {
                    tmp[i] = nums[left + x];
                    x++;
                } else {
                    tmp[i] = nums[mid + y];
                    y++;
                }
                i++;
            }
            while (left + x <= mid) {
                tmp[i] = nums[left + x];
                x++;
                i++;
            }
            while (mid + y <= right) {
                tmp[i] = nums[mid + y];
                y++;
                i++;
            }
            for (i = 0; i < l; i++)
                nums[left + i] = tmp[i];
        }
    }

    public void mergesort(int left, int right){
        if(left < right){
            int mid = (left + right)/2;
            mergesort(left, mid);
            mergesort(mid+1, right);
            merge(left, mid, right);
        }
    }

    public void quicksort(int left, int right){
        if(left < right) {
            int mid = (left + right)/2;
            int pivot = nums[mid];
            int tmp;

            // swap mid element with last element
            tmp = nums[mid];
            nums[mid] = nums[right];
            nums[right] = tmp;

            int swap_index=right-1, index=left;
            while(index <= swap_index){
                if(nums[index] >= pivot){
                    tmp = nums[index];
                    nums[index] = nums[swap_index];
                    nums[swap_index] = tmp;
                    swap_index--;
                }
                else
                    index++;
            }

            tmp = nums[right];
            nums[right] = nums[swap_index+1];
            nums[swap_index+1] = tmp;

            quicksort(left, swap_index);
            quicksort(swap_index+2, right);

        }
    }

    public void sort(){
        quicksort(start, end);
    }

    public void run() {
        sort();
    }
}
