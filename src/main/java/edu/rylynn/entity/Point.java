package edu.rylynn.entity;

/**
 * @author rylynn
 * @version 2019-01-19
 * @classname Point
 * @discription
 */

public class Point {

    private int id;
    private int clazz;
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public Point(int id, int clazz, double x, double y) {
        this.id = id;
        this.clazz = clazz;
        this.x = x;
        this.y = y;
    }

    public Point() {
        this.x = 0.0d;
        this.y = 0.0d;
    }

    public int getClazz() {
        return clazz;
    }

    public void setClazz(int clazz) {
        this.clazz = clazz;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getDistance(Point point2) {
        return Math.sqrt((this.x - point2.x) * (this.x - point2.x)
                + (this.y - point2.y) * (this.y - point2.y));
    }
}
