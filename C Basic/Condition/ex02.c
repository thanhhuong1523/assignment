#include <stdio.h>

int main() {
    int num1, num2;
    scanf("%d %d", &num1, &num2);

    int diff = num1 - num2;

    if(diff == num1 || diff == num2) {
        printf("Difference is equal to value %d\n", diff);
    } else {
        printf("Difference is not equal to any of the values entered\n");
    }

    return 0;
}