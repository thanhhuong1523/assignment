#include <stdio.h>

int main() {
    int arrLength;
    scanf("%d", &arrLength);

    int arr[arrLength];
    int cnt = 0;

    for(int i = 0; i < arrLength; i++){
        scanf("%d", &arr[i]);
        if(arr[i] < 0) {
            cnt++;
        }
    }

    printf("%d\n", cnt);

    return 0;
}