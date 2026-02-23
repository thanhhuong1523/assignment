#include <stdio.h>

int main() {
    int arrLength, key;
    scanf("%d", &arrLength);

    int arr[arrLength];
    for(int i = 0; i < arrLength; i++) {
        scanf("%d", &arr[i]);
    }

    scanf("%d", &key);

    int cnt = 0;

    for(int i = 0; i < arrLength; i++) {
        if(arr[i] == key) {
            cnt++;
        }
    }

    printf("%d\n", cnt);

    return 0;
}