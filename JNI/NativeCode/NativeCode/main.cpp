#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#include <fstream>
using namespace std;

void LSD(int * mass, int count);

void FillArray(int * mass, int count)
{
   for (int i = 0; i < count; ++i) {
      mass[i] = rand();
   }
}

bool isSorted(int * mass, int count)
{
   for (int i = 0; i < count - 1; ++i) {
      if (mass[i] > mass[i + 1]) {
         return false;
      }
   }
   return true;
}

int main()
{
   srand((unsigned int)time(NULL));

   const int count = 1000000;
   int * mass = new int[count];

   time_t time = clock();
   for (int i = 0; i < 10; ++i) {
      FillArray(mass, count);
      LSD(mass, count);
   }
   time = (clock() - time) / 10;

   return 0;
}
