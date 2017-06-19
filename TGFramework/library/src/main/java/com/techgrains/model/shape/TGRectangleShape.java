package com.techgrains.model.shape;

/**
 * Attributes of custom Rectangle Shape
 */
public class TGRectangleShape extends TGShape {

    private int radius;
    private int topLeftRadius;
    private int topRightRadius;
    private int bottomLeftRadius;
    private int bottomRightRadius;
    private int solidColor;
    private int padding;
    private int leftPadding;
    private int topPadding;
    private int bottomPadding;
    private int rightPadding;
    private int strokeWidth;
    private int strokeColor;
    private int strokeDashWidth;
    private int strokeDashGap;

    /**
     * Apply rectangle radius
     *
     * @param radius radius of rectangle
     */
    public void applyRadius(int radius) {
        this.radius = radius;
    }

    /**
     * Apply rectangle radius
     *
     * @param topLeftRadius     topLeftRadius of rectangle
     * @param topRightRadius    topRightRadius of rectangle
     * @param bottomLeftRadius  bottomLeftRadius of rectangle
     * @param bottomRightRadius bottomRightRadius of rectangle
     */
    public void applyRadius(int topLeftRadius, int topRightRadius, int bottomLeftRadius, int bottomRightRadius) {
        this.topLeftRadius = topLeftRadius;
        this.topRightRadius = topRightRadius;
        this.bottomLeftRadius = bottomLeftRadius;
        this.bottomRightRadius = bottomRightRadius;
    }

    /**
     * Apply rectangle color
     *
     * @param color color of rectangle
     */
    public void applySolidColor(int color) {
        solidColor = color;
    }

    /**
     * Apply rectangle stroke
     *
     * @param strokeWidth strokeWidth of rectangle
     * @param strokeColor strokeColor of rectangle
     */
    public void applyStroke(int strokeWidth, int strokeColor) {
        this.strokeWidth = strokeWidth;
        this.strokeColor = strokeColor;
    }

    /**
     * Apply dashedStroke of rectangle
     *
     * @param strokeWidth     strokeWidth of rectangle
     * @param strokeColor     strokeColor or rectangle
     * @param strokeDashWidth strokeDashWidth of rectangle
     * @param strokeDashGap   strokeDashGap or rectangle
     */
    public void applyDashedStroke(int strokeWidth, int strokeColor, int strokeDashWidth, int strokeDashGap) {
        this.strokeWidth = strokeWidth;
        this.strokeColor = strokeColor;
        this.strokeDashWidth = strokeDashWidth;
        this.strokeDashGap = strokeDashGap;
    }

    /**
     * Apply padding of rectangle
     *
     * @param padding padding of rectangle
     */
    public void applyPadding(int padding) {
        this.padding = padding;
    }

    /**
     * Apply padding of rectangle
     *
     * @param leftPadding   leftPadding of rectangle
     * @param topPadding    topPadding of rectangle
     * @param rightPadding  rightPadding of rectangle
     * @param bottomPadding bottomPadding of rectangle
     */
    public void applyPadding(int leftPadding, int topPadding, int rightPadding, int bottomPadding) {
        this.leftPadding = leftPadding;
        this.topPadding = topPadding;
        this.bottomPadding = bottomPadding;
        this.rightPadding = rightPadding;
    }

    //returns radius of rectangle
    public int getRadius() {
        return radius;
    }

    //returns topLeftRadius of rectangle
    public int getTopLeftRadius() {
        return topLeftRadius;
    }

    //returns topRightRadius of rectangle
    public int getTopRightRadius() {
        return topRightRadius;
    }

    //returns bottomLeftRadius of rectangle
    public int getBottomLeftRadius() {
        return bottomLeftRadius;
    }

    //returns bottomRightRadius of rectangle
    public int getBottomRightRadius() {
        return bottomRightRadius;
    }

    //returns color of rectangle
    public int getSolidColor() {
        return solidColor;
    }

    //returns padding of rectangle
    public int getPadding() {
        return padding;
    }

    //returns leftPadding of rectangle
    public int getLeftPadding() {
        return leftPadding;
    }

    //returns topPadding of rectangle
    public int getTopPadding() {
        return topPadding;
    }

    //returns bottomPadding of rectangle
    public int getBottomPadding() {
        return bottomPadding;
    }

    //returns rightPadding of rectangle
    public int getRightPadding() {
        return rightPadding;
    }

    //returns strokeWidth of rectangle
    public int getStrokeWidth() {
        return strokeWidth;
    }

    //returns strokeColor of rectangle
    public int getStrokeColor() {
        return strokeColor;
    }

    //returns strokeDashWidth of rectangle
    public int getStrokeDashWidth() {
        return strokeDashWidth;
    }

    //returns strokeDashGap fo rectangle
    public int getStrokeDashGap() {
        return strokeDashGap;
    }

}
