#include <jni.h>
#include <stdio.h>
#include "rosshamish_DataSorterBackup.h"

JNIEXPORT void JNICALL Java_rosshamish_DataSorterBackup_sortData_1C(JNIEnv *e, jobject jo, jdouble jd) {
     printf("From C, using HAZARD=%.3f\n", jd);
     return;
}
