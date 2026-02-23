#include <stdio.h>

int main() {
    int number;
    scanf("%d", &number);

    int digit1, digit2, digit3;
    digit3 = number % 10;
    number /= 10;
    digit2 = number % 10;
    digit1 = number /= 10;

    printf("%d %d %d\n", digit1, digit2, digit3);

    return 0;
}