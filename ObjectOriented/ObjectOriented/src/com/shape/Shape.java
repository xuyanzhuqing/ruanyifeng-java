package com.shape;

public sealed /* jdk15 以上支持，限定某些类可以继承当前类 */ class Shape permits Circle, Rect {
    protected int width;

    protected int height;

    public int getHeight () {
        return height;
    }

    public void setHeight (int w) {
        height = w;
    }

    @Override
    public String toString() {
        return width + ":" + height;
    }
}
