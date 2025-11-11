#include <stdio.h>      // for input/output
#include <omp.h>        // for OpenMP functions

int main() {
    int N, i, total = 0;                         // N = array size, total = final sum
    printf("Enter number of elements: ");        
    scanf("%d", &N);                             // take user input for N

    int a[N];                                    // declare array of size N
    printf("Enter %d elements:\n", N);
    for(i=0;i<N;i++) scanf("%d",&a[i]);          // read all elements

    int t;                                       // number of threads
    printf("Enter number of threads: ");
    scanf("%d",&t);                              // take user input for thread count

    #pragma omp parallel num_threads(t)          // create t threads for parallel execution
    {
        int id = omp_get_thread_num();           // get current thread ID
        int nT = omp_get_num_threads();          // get total threads
        int start = id*(N/nT);                   // start index for this thread
        int end = (id==nT-1)? N : start + (N/nT);// last thread may take extra elements
        int sum = 0;                             // local sum for this thread

        for(i=start;i<end;i++) sum += a[i];      // calculate local sum
        printf("Thread %d -> sum=%d\n",id,sum);  // print partial (intermediate) sum

        #pragma omp atomic                      // prevent race condition when updating total
        total += sum;                            // add local sum to total safely
    }

    printf("Total = %d\n", total);               // print final total sum
    return 0;                                    // end of program
}
