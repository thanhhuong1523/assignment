package Class_and_Object.Lab01;

public class Test {
    public static void main(String[] args) {
        Circle circle = new Circle();
        circle.setRadius(12);
        System.out.printf("Area of the circle is: %.2f", circle.getArea());
    }
}
