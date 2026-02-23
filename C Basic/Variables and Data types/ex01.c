#include <stdio.h>

#define PI 3.1428

int main() {
    double radius;
    scanf("%lf", &radius);

    double area = PI * radius * radius;
    double perimeter = 2 * PI * radius;

    printf("%.2lf\n%.2lf\n", area, perimeter);

    return 0;
}