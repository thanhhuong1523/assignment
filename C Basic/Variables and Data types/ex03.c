#include <stdio.h>

int main () {
    int basicSalary = 12000;
    int DA = 0.12 * basicSalary;
    int HRA = 150;
    int TA = 120;
    int others = 450;
    int PF = 0.14 * basicSalary;
    int IT = 0.15 * basicSalary;

    int netSalary = basicSalary + DA + HRA + TA + others - (PF + IT);
    printf("%d\n", netSalary);

    return 0;
}