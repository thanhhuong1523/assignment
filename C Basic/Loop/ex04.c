#include <stdio.h>

int main() {
    printf("---------- (a) ----------\n");

    for(int i = 1; i <= 5; i++) {
        for(int j = 1; j <= i; j++) {
            printf("%d", j);
        }
        printf("\n");
    }

    printf("---------- (b) ----------\n");

    for(int i = 5; i >= 1; i--) {
        for (int j = 1; j <= i; j++) {
            printf("%d", j);
        }
        printf("\n");
    }

    return 0;
}