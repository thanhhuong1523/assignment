#include <stdio.h>

int main() {
    int arrLength;
    scanf("%d", &arrLength);

    int arr[arrLength];
    int sumOddNumbers = 0;
    for(int i = 0; i < arrLength; i++) {
        scanf("%d", &arr[i]);
        if(arr[i] % 2 == 1) {
            sumOddNumbers += arr[i];
        }
    }

    printf("%d\n", sumOddNumbers);

    return 0;
}