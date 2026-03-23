import java.util.ArrayList;
import java.util.Scanner;

abstract class Shape {
    protected double x, y;

    public Shape(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public abstract double area();
    public abstract double perimeter();
    public abstract String getType();

    @Override
    public String toString() {
        return "Type: " + getType() + "\n" +
               "Location: (" + x + ", " + y + ")";
    }
}

// --- 2. THE SUBCLASSES ---
class Circle extends Shape {
    private double radius;
    public Circle(double x, double y, double radius) {
        super(x, y);
        this.radius = radius;
    }
    @Override public double area() { return Math.PI * radius * radius; }
    @Override public double perimeter() { return 2 * Math.PI * radius; }
    @Override public String getType() { return "Circle"; }
    @Override public String toString() {
        return super.toString() + "\nRadius: " + radius + 
               "\nArea: " + String.format("%.2f", area()) + 
               "\nPerimeter: " + String.format("%.2f", perimeter());
    }
}

class Rectangle extends Shape {
    private double width, height;
    public Rectangle(double x, double y, double width, double height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }
    @Override public double area() { return width * height; }
    @Override public double perimeter() { return 2 * (width + height); }
    @Override public String getType() { return "Rectangle"; }
    @Override public String toString() {
        return super.toString() + "\nWidth: " + width + "\nHeight: " + height +
               "\nArea: " + String.format("%.2f", area()) + 
               "\nPerimeter: " + String.format("%.2f", perimeter());
    }
}

class Triangle extends Shape {
    private double base, height;
    public Triangle(double x, double y, double base, double height) {
        super(x, y);
        this.base = base;
        this.height = height;
    }
    @Override public double area() { return 0.5 * base * height; }
    @Override public double perimeter() { return -1; } // Required by assignment
    @Override public String getType() { return "Triangle"; }
    @Override public String toString() {
        return super.toString() + "\nBase: " + base + "\nHeight: " + height +
               "\nArea: " + String.format("%.2f", area()) + "\nPerimeter: N/A";
    }
}

// --- 3. THE MAIN APPLICATION ---
public class GeometryApp {
    public static void main(String[] args) {
        ArrayList<Shape> shapes = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        while (choice != 8) {
            printMenu();
            choice = sc.nextInt();

            switch (choice) {
                case 1: // Add Circle
                    System.out.print("Enter x, y, radius: ");
                    shapes.add(new Circle(sc.nextDouble(), sc.nextDouble(), sc.nextDouble()));
                    break;
                case 2: // Add Rectangle
                    System.out.print("Enter x, y, width, height: ");
                    shapes.add(new Rectangle(sc.nextDouble(), sc.nextDouble(), sc.nextDouble(), sc.nextDouble()));
                    break;
                case 3: // Add Triangle
                    System.out.print("Enter x, y, base, height: ");
                    shapes.add(new Triangle(sc.nextDouble(), sc.nextDouble(), sc.nextDouble(), sc.nextDouble()));
                    break;
                case 4: // List all shapes
                    for (int i = 0; i < shapes.size(); i++) {
                        System.out.println("\nShape #" + i + "\n" + shapes.get(i));
                    }
                    break;
                case 5: // Total Area
                    double total = 0;
                    for (Shape s : shapes) total += s.area();
                    System.out.printf("Total area of all shapes: %.2f\n", total);
                    break;
                case 6: // Largest Shape
                    if (!shapes.isEmpty()) {
                        Shape largest = shapes.get(0);
                        for (Shape s : shapes) if (s.area() > largest.area()) largest = s;
                        System.out.println("Largest shape details:\n" + largest);
                    }
                    break;
                case 7: // Details by Index
                    System.out.print("Enter index: ");
                    int idx = sc.nextInt();
                    if (idx >= 0 && idx < shapes.size()) System.out.println(shapes.get(idx));
                    else System.out.println("Invalid index.");
                    break;
                case 8:
                    System.out.println("Seeyah");
                    break;
            }
        }
        sc.close();
    }

    public static void printMenu() {
        System.out.print("\n1. Add Circle\n2. Add Rectangle\n3. Add Triangle\n4. List all\n5. Total area\n6. Largest\n7. Details\n8. Quit\nEnter choice: ");
    }
}