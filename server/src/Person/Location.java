package Person;

import java.io.Serializable;

public class Location implements Serializable {
    private Float x;
    private Double y;
    private Float z;

    public Location(Float x, Double y, Float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Float getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public Float getZ() {
        return z;
    }


}

