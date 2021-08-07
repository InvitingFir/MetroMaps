package com.roman.Metro;

import java.util.Objects;

public class Location {
    private int x;
    private int y;

    public Location(int X, int Y) {
        this.x = X;
        this.y = Y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return getX() == location.getX() &&
                getY() == location.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }
}
