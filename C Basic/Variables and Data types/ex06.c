#include <stdio.h>
#include <math.h>

int main() {
    int a, b, c;
    scanf("%d %d %d", &a, &b, &c);

    double semi_p = (a + b + c)/2;
    double area = sqrt(semi_p * (semi_p - a) * (semi_p - b) * (semi_p - c));

    printf("%.2lf\n", area);

    return 0;
}