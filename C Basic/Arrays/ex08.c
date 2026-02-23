#include <stdio.h>

int main() {
    long num;
    scanf("%ld", &num);

    int sum = 0;

    for(int i = 1; i <= 10; i++) {
        sum += (num % 10) * i;
        num /= 10;
    }

    if(sum % 11 == 0) {
        printf("Valid\n");
    } else {
        printf("Invalid\n");
    }

    return 0;
}