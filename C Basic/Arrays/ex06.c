#include <stdio.h>

int main() {
    int arrLength;
    scanf("%d", &arrLength);

    int arr[arrLength];
    int checkOddArr = 1;

    for(int i = 0; i < arrLength; i++){
        scanf("%d", &arr[i]);
        if(arr[i] % 2 == 0) {
            checkOddArr = 0;
            break;
        }
    }

    if(checkOddArr == 0) {
        printf("False\n");
    } else {
        printf("True\n");
    }

    return 0;
}