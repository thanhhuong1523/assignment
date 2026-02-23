#include <stdio.h>

int main() {
    int arrLength;
    scanf("%d", &arrLength);

    int arr[arrLength];
    for(int i = 0; i < arrLength; i++) {
        scanf("%d", &arr[i]);
    }

    int min = arr[0], max = arr[0];

    for(int i = 1; i < arrLength; i++) {
        if(arr[i] < min) {
            min = arr[i];
        }
        if(arr[i] > max) {
            max = arr[i];
        }
    }

    printf("%d %d\n", min, max);

    return 0;
}