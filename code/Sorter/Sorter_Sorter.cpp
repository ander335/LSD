#include "Sorter_Sorter.h"

void LSDSort(int * mass, int count);

JNIEXPORT void JNICALL Java_Sorter_Sorter_SortC (JNIEnv * evn, jclass jClass, jintArray jArray)
{
   jint * mass = evn->GetIntArrayElements(jArray, NULL);
   jsize len = evn->GetArrayLength(jArray);

   LSDSort((int *)mass, len);

   evn->ReleaseIntArrayElements(jArray, mass, 0);
}

void LSDSort (int * mass, int count)
{
   mass[0] = 3281584;
}
