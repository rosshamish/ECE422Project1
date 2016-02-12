#include <jni.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "rosshamish_backup_DataSorterBackup.h"

int insertion_sort(int *, size_t, size_t);
int read_ints(const char*, int **);
void write_ints(const char*, int, int *);

JNIEXPORT jint JNICALL Java_rosshamish_backup_DataSorterBackup_sortData_1C(JNIEnv *e, jobject jo,
        jobject inputFilename, jobject outputFilename, jdouble failureProb) {
    const char *nativeInputFilename = (*e)->GetStringUTFChars(e, inputFilename, 0);
    const char *nativeOutputFilename = (*e)->GetStringUTFChars(e, outputFilename, 0);
    int *arr;
    int returnCode;
    int len;
    int memoryAccesses;
    double HAZARD;
    double randDouble;
    time_t t;

    srand((unsigned long) time(&t));

    len = read_ints(nativeInputFilename, &arr);

    memoryAccesses = insertion_sort(arr, len, 0);
    HAZARD = memoryAccesses * failureProb;
    randDouble = (double)rand() / (double)RAND_MAX;
    if (randDouble > 0.5 && randDouble < (0.5+HAZARD)) {
        free(arr);
        returnCode = -1;
    } else {
        write_ints(nativeOutputFilename, len, arr);
        returnCode = 0;
    }
    return returnCode;
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
int insertion_sort(int *arr, size_t n, size_t i) {
    int el,j;
    int memoryAccesses = 0;

    if(i == n-1){
        // Nothing to do since we're looking at the last element.
    } else {
        memoryAccesses += insertion_sort(arr, n, i+1);
        el = arr[i];
        memoryAccesses += 1;

        // All elements to the right of index i are assumed to be sorted.
        // Now we just have to figure out where el fits in the sorted array
        for(j=i+1; j<n; j++){
            memoryAccesses += 1;
            if(el > arr[j]){
                // el is bigger, swap so el moves to the right in the array.
                arr[j-1] = arr[j];
                arr[j] = el;
                memoryAccesses += 3;
            }
        }
    }
    return memoryAccesses;
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
    // todo write C sorted output to file
    FILE *fp = fopen(filename, "w");

    if (!fp) {
        perror("Failed to open file");
        return;
    }

    fprintf(fp, "%d\n", len);
    for (int i=0; i < len; i++) {
        if (i > 0) {
            fprintf(fp, " ");
        }
        fprintf(fp, "%d", ints[i]);
    }

    fclose(fp);
}
