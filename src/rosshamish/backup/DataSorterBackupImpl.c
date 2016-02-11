#include <jni.h>
#include <stdio.h>
#include <stdlib.h>
#include "rosshamish_backup_DataSorterBackup.h"

void insertion_sort(int *, size_t, size_t);
int read_ints(const char*, int **);
void write_ints(const char*, int, int *);

JNIEXPORT jint JNICALL Java_rosshamish_backup_DataSorterBackup_sortData_1C(JNIEnv *e, jobject jo,
        jobject inputFilename, jobject outputFilename, jdouble failureProb, jint timeLimit) {
    const char *nativeInputFilename = (*e)->GetStringUTFChars(e, inputFilename, 0);
    const char *nativeOutputFilename = (*e)->GetStringUTFChars(e, outputFilename, 0);
    printf("From C, using inputFilename=%s, outputFilename=%s, failureProb=%.3f\n",
        nativeInputFilename, nativeOutputFilename, failureProb);
    int *arr;
    int len = read_ints(nativeInputFilename, &arr);
    insertion_sort(arr, len, 0);
    write_ints(nativeOutputFilename, len, arr);

    free(arr);
    return 0;
}

/*
 * Attribution
 *  Author: Joseph McCullough
 *  URL: https://gist.github.com/joequery/8e33be3f5e9d58cc0546
 *  Published: October 12, 2015
 *  Accessed: February 11, 2016
 *
 * This insertion sort will be done in place.
 */
void insertion_sort(int *arr, size_t n, size_t i) {
    int el,j;

    if(i == n-1){
        // Nothing to do since we're looking at the last element.
    } else {
        insertion_sort(arr, n, i+1);
        el = arr[i];

        // All elements to the right of index i are assumed to be sorted.
        // Now we just have to figure out where el fits in the sorted array
        for(j = i+1; j<n; j++){
            if(el > arr[j]){
                // el is bigger, swap so el moves to the right in the array.
                arr[j-1] = arr[j];
                arr[j] = el;
            }
        }
    }
}

/**
 * Attribution
 *  Author: Vijay Mathew
 *  URL: http://stackoverflow.com/a/4601150/1817465
 *  Published: January 5, 2011
 *  Accessed: February 11, 2016
 *
 * Modified to read integers into an array instead of printing
 * them to stdout.
 *
 * CALLER IS RESPONSIBLE FOR FREEING THE POINTER
 *
 * Usage:
 *  int *arr;
 *  int len = read_ints("filename.txt", &arr);
 *  for (int i=0; i < len; i++) {
 *    printf(arr[i]);
 *  }
 */
int read_ints(const char* filename, int **arr) {
    FILE* file = fopen(filename, "r");
    if (file == NULL) {
        printf("null file");
        return -1;
    }
    int i = 0;
    int len;

    fscanf(file, "%d", &len);
    *arr = (int *) malloc(len*sizeof(int));
    while (!feof(file) && i < len) {
        fscanf (file, "%d", &(*arr)[i]);
        i += 1;
    }

    if (i != len) {
        printf("ERROR: %d integers read, expecting to read %d", i, len);
    }
    fclose(file);

    return len;
}

void write_ints(const char* filename, int len, int ints[]) {
    printf("TODO write to %s. Ints in order:\n", filename);
    for (int i=0; i < len; i++) {
        printf("\t%d: %d\n", i, ints[i]);
    }
    printf("\n");
}
