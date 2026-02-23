#include <stdio.h>

#define PI 3.14

double areaCircle (double radius) {
    return PI * radius * radius;
}

double perimeterCircle (double radius) {
    return 2 * PI * radius;
}

int main() {
    double radius;
    scanf("%lf", &radius);

    printf("%.2lf %.2lf\n", areaCircle(radius), perimeterCircle(radius));

    return 0;
}