#include <stdio.h>
#include <math.h>

int checkPrimeNumber(int n) {
    if(n < 2) return 0;

    for(int i = 2; i <= sqrt(n); i++) {
        if(n % i == 0) {
            return 0;
        }
    }
    return 1;
}

int main() {
    int arrLength;
    scanf("%d", &arrLength);

    int arr[arrLength];

    for(int i = 0; i < arrLength; i++){
        scanf("%d", &arr[i]);
        if(checkPrimeNumber(arr[i]) == 1) {
            printf("%d ", arr[i]);
        }
    }

    printf("\n");

    return 0;
}