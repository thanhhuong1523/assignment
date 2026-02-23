#include <stdio.h>

int main() {
    int num1, num2;
    int sum = 0;

    scanf("%d %d", &num1, &num2);

    int start = num1%2 == 0 ? num1 + 1 : num1;

    for(int i = start; i<= num2; i+=2) {
        sum += i;
    }

    printf("%d\n", sum);

    return 0;
}