import java.awt.Rectangle;

public class Square extends Rectangle {
    public Square(int x, int y, int l) {
        super(x - l / 2, y + l / 2, l, l);
    }

    public double getArea() {
        return Math.pow(super.getHeight(), 2);
    }

    public void setSize(int width, int height) {
        if (width == height) {
            super.setSize(width, height);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void setSize(int dim) {
        super.setSize(dim, dim);
    }
}
