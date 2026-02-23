#include <stdio.h>
#include <math.h>

int main() {
    int a, b, c;
    scanf("%d%d%d", &a, &b, &c);

    double delta = b*b - 4*a*c;

    if(delta < 0) {
        printf("Phuong trinh vo nghiem\n");
    } else if(delta == 0) {
        double x = -b/(2*a);
        printf("PT co nghiem kep x = %.2lf\n", x);
    } else {
        double x1 = (-b + sqrt(delta))/(2*a);
        double x2 = (-b - sqrt(delta))/(2*a);
        printf("PT co hai nghiem phan biet x1 = %.2lf; x2 = %.2lf\n", x1, x2);
    }

    return 0;
}