package com.tetrasoft;

public class Triangle {

    int angle1;
    int angle2;
    int angle3;

    public Triangle(int angle1, int angle2, int angle3) {
        this.angle1 = angle1;
        this.angle2 = angle2;
        this.angle3 = angle3;
    }

    public int getAngle1() {
        return angle1;
    }

    public void setAngle1(int angle1) {
        this.angle1 = angle1;
    }

    public int getAngle2() {
        return angle2;
    }

    public void setAngle2(int angle2) {
        this.angle2 = angle2;
    }

    public int getAngle3() {
        return angle3;
    }

    public void setAngle3(int angle3) {
        this.angle3 = angle3;
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "angle1=" + angle1 +
                ", angle2=" + angle2 +
                ", angle3=" + angle3 +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Triangle)) return false;

        Triangle triangle = (Triangle) o;

        if (angle1 != triangle.angle1) return false;
        if (angle2 != triangle.angle2) return false;
        return angle3 == triangle.angle3;
    }

    @Override
    public int hashCode() {
        int result = angle1;
        result = 31 * result + angle2;
        result = 31 * result + angle3;
        return result;
    }
}

