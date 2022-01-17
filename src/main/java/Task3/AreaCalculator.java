package Task3;

public class AreaCalculator {

    public static double calcArea(Circle circle) {
        return circle.getRadius() * Math.PI;
    }

    public static double calcArea(Square square) {
        return square.getSide() * square.getSide();
    }

    public static double calcArea(Triangle triangle) {
        return triangle.getH() * triangle.getSide() / 2;
    }

}
