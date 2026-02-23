#include <stdio.h>

#define NUM 1000

int main() {
    int num1, num2;
    scanf("%d %d", &num1, &num2);

    int product = num1 * num2;

    if(product >= NUM) {
        printf("True\n");
    } else {
        printf("False\n");
    }

    return 0;
}