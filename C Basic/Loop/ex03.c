#include <stdio.h>

#define STOP 20

int main() {
    int num1 = 1, num2 = 1;
    int tmp;

    for(int i = 2; i <= STOP; i++) {
        printf("%d ", num1);
        tmp = num2;
        num2 += num1;
        num1 = tmp;
    }

    printf("\n");

    return 0;
}