package com.roman.Metro;

import java.util.Objects;

public class Location {
    private int X;
    private int Y;

    public Location(int X, int Y){
        this.X = X;
        this.Y = Y;
    }

    public void setX(int newX){X = newX;}
    public int getX(){return X;}

    public void setY(int newY){Y = newY;}
    public int getY(){return Y;}

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
