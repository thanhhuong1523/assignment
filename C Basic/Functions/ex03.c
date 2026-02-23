#include <stdio.h>
#include <math.h>

float DB_mark, C_mark, OOP_mark, Java_mark;

int checkGrades(float grade) {
    if(grade < 0 || grade > 10) return 0;
    return 1;
}

void inputMark() {
    printf("Input your Database, C, OOP, Java marks: ");
    int flag = 1;
    do {
        scanf("%f %f %f %f", &DB_mark, &C_mark, &OOP_mark, &Java_mark);
        if(checkGrades(DB_mark) == 0 || checkGrades(C_mark) == 0 || checkGrades(OOP_mark) == 0 || checkGrades(Java_mark) == 0) {
            flag = 0;
            printf("Your marks must be between 0 and 10! Please input again!!\n");
        } else {
            flag = 1;
            break;
        }
    } while (flag == 0);
}

float calculateGPA() {
    float GPA_10_point = (DB_mark + C_mark + OOP_mark + Java_mark)/4;
    float GPA_4_point = GPA_10_point / 10 * 4;
    return GPA_4_point;
}

void displayGPA() {
    printf("GPA: %.2f\n", calculateGPA());
}

void displayRank() {
    float GPA = round(calculateGPA() * 100) / 100;
    if(GPA >= 3.60) {
        printf("Excellent!\n");
    } else if(GPA >= 3.20) {
        printf("Good!\n");
    } else if(GPA >= 2.50) {
        printf("Fair\n");
    } else if(GPA >= 2.00) {
        printf("Average\n");
    } else {
        printf("Weak\n");
    }
}

int main() {
    int choice;

    while(1) {
        printf("------ Calculate GPA ------\n");
        printf("1. Input mark\n");
        printf("2. Display GPA\n");
        printf("3. Display Rank\n");
        printf("4. Quit\n");

        printf("Your choice is: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                inputMark();
                break;
            case 2:
                displayGPA();
                break;
            case 3:
                displayRank();
                break;
            case 4:
                return 0;
        }
    }

    return 0;
}