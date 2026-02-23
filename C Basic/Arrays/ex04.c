#include <stdio.h>

int main() {
    int arrLength;
    scanf("%d", &arrLength);

    int arr[arrLength];
    for(int i = 0; i < arrLength; i++){
        scanf("%d", &arr[i]);
        if(arr[i] % 3 == 0 || arr[i] % 5 == 0) {
            printf("%d ", arr[i]);
        }
    }

    printf("\n");

    return 0;
}