#include <stdio.h>

#define STOP 10

int main() {
    for(int i = STOP; i > 0; i--) {
        for(int j = 0; j < i; j++) {
            printf("*");
        }
        printf("\n");
    }

    return 0;
}