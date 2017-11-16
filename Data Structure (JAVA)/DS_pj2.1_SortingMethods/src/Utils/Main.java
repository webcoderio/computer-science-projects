/*******************************************
Name: Shing Ng
Assignment id: ds2_01
Due Date: Nov. 18, 2009
********************************************/

package Utils;

/**
 *
 * @author Shing Ng
 * @desc
 * Compare the performance (running time) of Shellsort with various increment sequences
 */
public class Main {

    /**
     * @desc Generate sequence of Array, count and output time executed
     */
    public static void main(String[] args) {

        long TimeStart, TimeEnd, TimeRun;
        long Time1, Time2, Time3, Time4;

        // Set N Items of sequence for comparision
        int N = 1000000;

        /***********************  Sequence 1 *************************/
        
        System.out.println("### ( SEQUENCE S1 DATA ) Execution Time Benchmark ####");

        // Generate Random Sequences
        int[] S1 = Utils.random_serial(N);
        // Use Clone to generate array with same items for comparing
        int[] S1Clone1 = S1.clone();
        int[] S1Clone2 = S1.clone();
        int[] S1Clone3 = S1.clone();
        int[] S1Clone4 = S1.clone();

        // Shell Sort
        TimeStart = System.currentTimeMillis();    // Get Start Time
        ShellSort.shellsort(S1Clone1);
        TimeEnd = System.currentTimeMillis();      // Get End Time
        TimeRun = TimeEnd - TimeStart;             // Calculate Time Elapsed
        Time1 = TimeRun;                           // avg time count holder
        System.out.println("Shell Sort: " + TimeRun+"ms");

        // Merge Sort
        TimeStart = System.currentTimeMillis();    // Get Start Time
        MergeSort.mergeSort(S1Clone2);
        TimeEnd = System.currentTimeMillis();      // Get End Time
        TimeRun = TimeEnd - TimeStart;             // Calculate Time Elapsed
        Time2 = TimeRun;                           // avg time count holder
        System.out.println("Merge Sort: " + TimeRun+"ms");

        // Quick Sort
        TimeStart = System.currentTimeMillis();    // Get Start Time
        QuickSort.quicksort(S1Clone3);
        TimeEnd = System.currentTimeMillis();      // Get End Time
        TimeRun = TimeEnd - TimeStart;             // Calculate Time Elapsed
        Time3 = TimeRun;                           // avg time count holder
        System.out.println("Quick Sort: " + TimeRun+"ms");

        // Heap Sort
        TimeStart = System.currentTimeMillis();    // Get Start Time
        HeapSort.heapsort(S1Clone4);
        TimeEnd = System.currentTimeMillis();      // Get End Time
        TimeRun = TimeEnd - TimeStart;             // Calculate Time Elapsed
        Time4 = TimeRun;                           // avg time count holder
        System.out.println("Heap Sort: " + TimeRun+"ms");


        /***********************  Sequence S2 *************************/

        System.out.println("### ( SEQUENCE S2 DATA ) Execution Time Benchmark ####");

        // Generate Random Sequences
        int[] S2 = Utils.random_serial(1000000);
        // Use Clone to generate array with same items for comparing
        int[] S2Clone1 = S2.clone();
        int[] S2Clone2 = S2.clone();
        int[] S2Clone3 = S2.clone();
        int[] S2Clone4 = S2.clone();

        // Shell Sort
        TimeStart = System.currentTimeMillis();    // Get Start Time
        ShellSort.shellsort(S2Clone1);
        TimeEnd = System.currentTimeMillis();      // Get End Time
        TimeRun = TimeEnd - TimeStart;             // Calculate Time Elapsed
        Time1 = TimeRun + Time1;                   // avg time count holder
        System.out.println("Shell Sort: " + TimeRun+"ms");

        // Merge Sort
        TimeStart = System.currentTimeMillis();    // Get Start Time
        MergeSort.mergeSort(S2Clone2);
        TimeEnd = System.currentTimeMillis();      // Get End Time
        TimeRun = TimeEnd - TimeStart;             // Calculate Time Elapsed
        Time2 = TimeRun + Time2;                   // avg time count holder
        System.out.println("Merge Sort: " + TimeRun+"ms");

        // Quick Sort
        TimeStart = System.currentTimeMillis();    // Get Start Time
        QuickSort.quicksort(S2Clone3);
        TimeEnd = System.currentTimeMillis();      // Get End Time
        TimeRun = TimeEnd - TimeStart;             // Calculate Time Elapsed
        Time3 = TimeRun + Time3;                   // avg time count holder
        System.out.println("Quick Sort: " + TimeRun+"ms");

        // Heap Sort
        TimeStart = System.currentTimeMillis();    // Get Start Time
        HeapSort.heapsort(S2Clone4);
        TimeEnd = System.currentTimeMillis();      // Get End Time
        TimeRun = TimeEnd - TimeStart;             // Calculate Time Elapsed
        Time4 = TimeRun + Time4;                   // avg time count holder
        System.out.println("Heap Sort: " + TimeRun+"ms");

        /***********************  Sequence S3 *************************/

        System.out.println("### ( SEQUENCE S3 DATA ) Execution Time Benchmark ####");

        // Generate Random Sequences
        int[] S3 = Utils.random_serial(1000000);
        // Use Clone to generate array with same items for comparing
        int[] S3Clone1 = S3.clone();
        int[] S3Clone2 = S3.clone();
        int[] S3Clone3 = S3.clone();
        int[] S3Clone4 = S3.clone();

        // Shell Sort
        TimeStart = System.currentTimeMillis();    // Get Start Time
        ShellSort.shellsort(S3Clone1);
        TimeEnd = System.currentTimeMillis();      // Get End Time
        TimeRun = TimeEnd - TimeStart;             // Calculate Time Elapsed
        Time1 = TimeRun + Time1;                   // avg time count holder
        System.out.println("Shell Sort: " + TimeRun+"ms");

        // Merge Sort
        TimeStart = System.currentTimeMillis();    // Get Start Time
        MergeSort.mergeSort(S3Clone2);
        TimeEnd = System.currentTimeMillis();      // Get End Time
        TimeRun = TimeEnd - TimeStart;             // Calculate Time Elapsed
        Time2 = TimeRun + Time2;                   // avg time count holder
        System.out.println("Merge Sort: " + TimeRun+"ms");

        // Quick Sort
        TimeStart = System.currentTimeMillis();    // Get Start Time
        QuickSort.quicksort(S3Clone3);
        TimeEnd = System.currentTimeMillis();      // Get End Time
        TimeRun = TimeEnd - TimeStart;             // Calculate Time Elapsed
        Time3 = TimeRun + Time3;                   // avg time count holder
        System.out.println("Quick Sort: " + TimeRun+"ms");

        // Heap Sort
        TimeStart = System.currentTimeMillis();    // Get Start Time
        HeapSort.heapsort(S3Clone4);
        TimeEnd = System.currentTimeMillis();      // Get End Time
        TimeRun = TimeEnd - TimeStart;             // Calculate Time Elapsed
        Time4 = TimeRun + Time4;                   // avg time count holder
        System.out.println("Heap Sort: " + TimeRun+"ms");


        /***********************  Averge Benchmark *************************/
        System.out.println("### ( AVERGE Time ) for "+N+" items ####");
        System.out.println("Shell Sort: "+(Time1/3)+"ms"); // 3 Sequences, hence divide by 3
        System.out.println("Merge Sort: "+(Time2/3)+"ms");
        System.out.println("Quick Sort: "+(Time3/3)+"ms");
        System.out.println("Heap Sort: "+(Time4/3)+"ms");


        /***********************  CUMULATIVE Benchmark *************************/
        System.out.println("### ( Total Time ) Executed ####");
        System.out.println("Shell Sort: "+(Time1)+"ms");
        System.out.println("Merge Sort: "+(Time2)+"ms");
        System.out.println("Quick Sort: "+(Time3)+"ms");
        System.out.println("Heap Sort: "+(Time4)+"ms");


    } // main

} // class
