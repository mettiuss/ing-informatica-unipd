public class MyRectangle {
    int posX;
    int posY;
    int w;
    int h;

    public MyRectangle(int posX, int posY, int w, int h) {
        this.posX = posX;
        this.posY = posY;
        this.w = w;
        this.h = h;
    }

    public int getX() {
        return posX;
    }

    public int getY() {
        return posY;
    }

    public int getWidth() {
        return w;
    }

    public int getHeight() {
        return h;
    }

    public void resize(int mult) {
        w = w * mult;
        h = h * mult;
    }

    public void translate(int dx, int dy) {
        posX += dx;
        posY += dy;
    }

    public String toString() {
        return "Rettangolo: x=" + posX + ", y=" + posY + ", w=" + w + ", h=" + h;
    }
}
