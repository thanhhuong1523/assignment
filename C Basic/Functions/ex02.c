#include <stdio.h>

long long factorial(int num) {
    if(num == 0) return 1;

    long long product = num;
    for(int i = 1; i < num; i++) {
        product *= i;
    }
    return product;
}

int main() {
    int num;
    scanf("%d", &num);

    printf("%lld\n", factorial(num));

    return 0;
}