package Class.Object.Lab01;

public class Circle {
    private double radius;

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     * Calculate area of a circle with radius
     * @return area of the circle
     */
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }
}
