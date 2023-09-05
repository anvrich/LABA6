package Person;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private Float x;
    private Float y;

    public Coordinates(Float x, Float y) {
        this.x = x;
        this.y = y;
    }
    public Float getX() {
        return x;
    }

    public Float getY() {
        return y;
    }

    public void setX(Float x) {
        this.x = x;
    }

    public void setY(Float y) {
        this.y = y;
    }
}

