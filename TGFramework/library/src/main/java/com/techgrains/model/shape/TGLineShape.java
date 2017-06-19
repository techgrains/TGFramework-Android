package com.techgrains.model.shape;

/**
 * Attributes of custom Line Shape
 */
public class TGLineShape extends TGShape {

    private int strokeColor;
    private int startX;
    private int stopX;
    private int startY;
    private int stopY;
    private int strokeDashWidth;
    private int strokeDashGap;

    /**
     * Apply line co-ordinates
     *
     * @param startX startX of line
     * @param startY startY of line
     * @param stopX  stopX  of line
     * @param stopY  stopY  of line
     */
    public void applyLineCoordinates(int startX, int startY, int stopX, int stopY) {
        this.startX = startX;
        this.startY = startY;
        this.stopX = stopX;
        this.stopY = stopY;
    }

    /**
     * Apply line color
     *
     * @param color line color
     */
    public void applyLineColor(int color) {
        this.strokeColor = color;
    }

    /**
     * Apply line stroke
     *
     * @param strokeDashWidth strokeDashWidth of line
     * @param strokeDashGap   strokeDashGap of line
     */
    public void applyDashedLine(int strokeDashWidth, int strokeDashGap) {
        this.strokeDashWidth = strokeDashWidth;
        this.strokeDashGap = strokeDashGap;
    }

    //returns strokeColor of line
    public int getLineColor() {
        return strokeColor;
    }

    //returns startX of line
    public int getStartX() {
        return startX;
    }

    //returns stopX of line
    public int getStopX() {
        return stopX;
    }

    //returns startY of line
    public int getStartY() {
        return startY;
    }

    //returns stopY of line
    public int getStopY() {
        return stopY;
    }

    //returns strokeDashWidth of line
    public int getStrokeDashWidth() {
        return strokeDashWidth;
    }

    //returns strokeDashGap of line
    public int getStrokeDashGap() {
        return strokeDashGap;
    }

}
