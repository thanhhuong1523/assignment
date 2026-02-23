#include <stdio.h>

int main() {
    char letter;
    scanf("%c", &letter);

    if(letter == 'B' || letter == 'b') {
        printf("Basic\n");
    } else if (letter == 'C' || letter == 'c') {
        printf("Cobol\n");
    } else if(letter == 'f' || letter == 'F') {
        printf("Fortran\n");
    } else if(letter == 'P' || letter == 'p') {
        printf("Pascal\n");
    } else if(letter == 'V' || letter == 'v') {
        printf("Visual C++\n");
    }

    return 0;
}