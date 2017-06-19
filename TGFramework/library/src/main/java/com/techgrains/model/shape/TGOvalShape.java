package com.techgrains.model.shape;

/**
 * Attributes of custom Oval Shape
 */
public class TGOvalShape extends TGShape {

    private int padding;
    private int leftPadding;
    private int topPadding;
    private int bottomPadding;
    private int rightPadding;
    private int strokeWidth;
    private int strokeColor;
    private int solidColor;
    private int strokeDashGap;
    private int strokeDashWidth;

    /**
     * Apply oval stroke
     * @param strokeWidth strokeWidth of oval
     * @param strokeColor strokeColor of oval
     */
    public void applyStroke(int strokeWidth, int strokeColor) {
        this.strokeWidth = strokeWidth;
        this.strokeColor = strokeColor;
    }

    /**
     *  Apply oval color
     * @param color color of oval
     */
    public void applySolidColor(int color) {
        solidColor = color;
    }

    /**
     * Apply oval padding
     * @param padding padding of oval
     */
    public void applyPadding(int padding) {
        this.padding = padding;
    }

    /**
     * Apply oval padding
     * @param leftPadding left padding of oval
     * @param topPadding  top padding of oval
     * @param rightPadding right padding of oval
     * @param bottomPadding bottom padding of oval
     */
    public void applyPadding(int leftPadding, int topPadding, int rightPadding, int bottomPadding) {
        this.leftPadding = leftPadding;
        this.topPadding = topPadding;
        this.bottomPadding = bottomPadding;
        this.rightPadding = rightPadding;
    }

    /**
     * Apply oval DashedStroke
     * @param strokeWidth strokeWidth of oval
     * @param strokeColor strokeColor of oval
     * @param strokeDashWidth strokeDashWidth of oval
     * @param strokeDashGap strokeDashGap of oval
     */
    public void applyDashedStroke(int strokeWidth, int strokeColor, int strokeDashWidth, int strokeDashGap) {
        this.strokeWidth = strokeWidth;
        this.strokeColor = strokeColor;
        this.strokeDashWidth = strokeDashWidth;
        this.strokeDashGap = strokeDashGap;
    }

    //returns leftPadding of oval
    public int getLeftPadding() {
        return leftPadding;
    }

    //returns topPadding of oval
    public int getTopPadding() {
        return topPadding;
    }

    //returns bottomPadding of oval
    public int getBottomPadding() {
        return bottomPadding;
    }

    //returns rightPadding of oval
    public int getRightPadding() {
        return rightPadding;
    }

    //returns padding of oval
    public int getPadding() {
        return padding;
    }

    //returns strokeDashWidth of oval
    public int getStrokeDashWidth() {
        return strokeDashWidth;
    }

    //returns strokeDashGap of oval
    public int getStrokeDashGap() {
        return strokeDashGap;
    }

    //returns strokeWidth of oval
    public int getStrokeWidth() {
        return strokeWidth;
    }

    //returns color of oval
    public int getSolidColor() {
        return solidColor;
    }

    //returns strokeColor of oval
    public int getStrokeColor() {
        return strokeColor;
    }

}
