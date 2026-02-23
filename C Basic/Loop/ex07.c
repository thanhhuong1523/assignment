#include <stdio.h>

#define CORRECT_PASSWORD 12345

int main() {
    int i = 0;
    int pass;
    do {
        i++;
        scanf("%d", &pass);
        if(pass == CORRECT_PASSWORD) {
            printf("Correct !!\n");
            break;
        } else if(i < 3) {
            printf("Invalid password! Please enter the password again!\n");
        } else {
            printf("Invalid password! Gooodbye!\n");
        }
    } while (i < 3);

    return 0;
}