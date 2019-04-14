package benchmark;

import threadsort.Chunk;
import threadsort.ChunkSort;

import java.util.Arrays;
import java.util.Random;


public class ChunkSortBenchmark {
    Random rand = new Random();
    int size_range = 1000000, number_range=10000;



    public long[] benchmark() throws InterruptedException {
        int cur, size = rand.nextInt(size_range);
        int[] first = new int[size];
        int[] second = new int[size];

        for(int i=0;i<size;i++){
           cur = rand.nextInt(2*size_range) - size_range;
           first[i] = cur;
           second[i] = cur;
        }

        long lib_sort_start_time = System.nanoTime();
        Arrays.sort(first);
        long lib_sort_end_time = System.nanoTime();

        Chunk chunk = new Chunk(second);
        long chunk_sort_start_time = System.nanoTime();
        chunk.threadSort();
//        chunk.sort();
        long chunk_sort_end_time = System.nanoTime();

        long[] times = new long[2];
        times[0] = lib_sort_end_time-lib_sort_start_time;
        times[1] = chunk_sort_end_time - chunk_sort_start_time;
        return times;
    }

    public void getAverageTimes() throws InterruptedException {
        int test_cases = 1000, cur_test=0;
        long lib_time=0, chunk_time=0;
        while(cur_test < test_cases){
            long[] times = benchmark();
            lib_time+=times[0];
            chunk_time+=times[1];
            cur_test++;
        }
        long lib_avg = lib_time/test_cases;
        long chunk_avg = chunk_time/test_cases;
        long nano_lag = chunk_avg-lib_avg;
        double lag = nano_lag/(1e6);
        System.out.println("library sort avg: " + lib_avg);
        System.out.println("chunk sort avg: " + chunk_avg);
        System.out.println("chunk sort lags by " + lag + " seconds");
    }

    public static void main(String[] args) throws InterruptedException {
        ChunkSortBenchmark chunkSortBenchmark = new ChunkSortBenchmark();
        chunkSortBenchmark.getAverageTimes();
    }
}

