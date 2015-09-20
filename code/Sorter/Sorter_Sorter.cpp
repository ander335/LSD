#include <string.h>
#include <stdlib.h>

#include "Sorter_Sorter.h"

int __Quick_Comparator(const void * A, const void * B)
{
   return *(int *)A - *(int *)B;
}

JNIEXPORT void JNICALL Java_Sorter_Sorter_NativeQuick(JNIEnv * evn, jclass jclass, jintArray array)
{
   jint * mass = evn->GetIntArrayElements(array, NULL);
   jsize count = evn->GetArrayLength(array);

   qsort(mass, count, sizeof(int), __Quick_Comparator);

   evn->ReleaseIntArrayElements(array, mass, 0);
}


void LSD(int * mass, int count);

JNIEXPORT void JNICALL Java_Sorter_Sorter_NativeLSD (JNIEnv * evn, jclass jclass, jintArray array)
{
   jint * mass = evn->GetIntArrayElements(array, NULL);
   jsize count = evn->GetArrayLength(array);

   LSD((int *)mass, count);

   evn->ReleaseIntArrayElements(array, mass, 0);
}

__forceinline static int __GetChar(int val, int idx) {
   return (val >> (16 * idx)) & 0xFFFF;
}

void LSD (int * mass, int count)
{
   const int maxOrder = 1;
   const int countChars = 1 << 16;

   int chars[countChars];
   int * tmpMass = new int[count];

   for (int order = 0; order <= maxOrder; ++order) {
      memset(chars, 0, countChars * sizeof(int));
      for (int i = 0; i < count; ++i) {
         chars[__GetChar(mass[i], order)]++;
      }

      for (int i = 0, pos = 0; i < countChars; ++i) {
         int tmp = chars[i];
         chars[i] = pos;
         pos += tmp;
      }

      for (int i = 0; i < count; ++i) {
         tmpMass[chars[__GetChar(mass[i], order)]++] = mass[i];
      }

      memcpy(mass, tmpMass, count * sizeof(int));
   }
}
