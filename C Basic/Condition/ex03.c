#include <stdio.h>

#define ALLOWANCE_A 300
#define ALLOWANCE_B 200
#define ALLOWANCE_Others 100

int main() {
    int salary;
    char grade;
    scanf("%d %c", &salary, &grade);

    if(grade == 'A') {
        salary += ALLOWANCE_A;
        printf("%d\n", salary);
    } else if(grade == 'B') {
        salary += ALLOWANCE_B;
        printf("%d\n", salary);
    } else {
        salary += ALLOWANCE_Others;
        printf("%d\n", salary);
    }

    return 0;
}