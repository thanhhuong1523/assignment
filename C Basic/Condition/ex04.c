#include <stdio.h>

#define MIN_GRADE_A 75
#define MIN_GRADE_B 60
#define MIN_GRADE_C 45
#define MIN_GRADE_D 35

int main() {    
    int marks;
    char grade;
    scanf("%d", &marks);

    if(marks > MIN_GRADE_A) {
        grade = 'A';
    } else if(marks > MIN_GRADE_B) {
        grade = 'B';
    } else if(marks > MIN_GRADE_C) {
        grade = 'C';
    } else if(marks > MIN_GRADE_D) {
        grade = 'D';
    } else {
        grade = 'E';
    }

    printf("%c\n", grade);

    return 0;
}