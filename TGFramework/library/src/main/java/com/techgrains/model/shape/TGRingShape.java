package com.techgrains.model.shape;

/**
 * Attributes of custom Ring Shape
 */
public class TGRingShape extends TGShape {
    private float innerRadiusRatio = 9;
    private float thicknessRatio = 3;
    private float leftX;
    private float topY;
    private float rightX;
    private float bottomY;
    private boolean useLevelForShape;
    private int innerRadius;
    private int thickness;
    private int solidColor;
    private int strokeWidth;
    private int strokeColor;
    private int strokeDashWidth;
    private int strokeDashGap;
    private float level;

    /**
     * Apply {@code true} if ring should be scaled based on
     * level, {@code false} otherwise
     *
     * @param useLevelForShape useLevel of ring
     */
    public void applyUseLevelForShape(boolean useLevelForShape) {
        this.useLevelForShape = useLevelForShape;
    }

    /**
     * Apply ring level
     *
     * @param level progress value of ring maxValue 10000f
     */
    public void applyLevel(float level) {
        this.level = level;
    }

    /**
     * Apply ring co-ordinates
     *
     * @param leftX   leftX of ring
     * @param topY    topY of ring
     * @param rightX  rightX of ring
     * @param bottomY bottomX of ring
     */
    public void applyCoordinates(float leftX, float topY, float rightX, float bottomY) {
        this.leftX = leftX;
        this.topY = topY;
        this.rightX = rightX;
        this.bottomY = bottomY;
    }

    /**
     * Apply ring innerRadius
     *
     * @param innerRadius innerRadius of ring
     */
    public void applyInnerRadius(int innerRadius) {
        this.innerRadius = innerRadius;
    }

    /**
     * Apply ring Stroke
     *
     * @param strokeWidth strokeWidth of ring
     * @param strokeColor strokeColor of ring
     */
    public void applyStroke(int strokeWidth, int strokeColor) {
        this.strokeWidth = strokeWidth;
        this.strokeColor = strokeColor;
    }

    /**
     * Apply ring dashedStroke
     *
     * @param strokeWidth     strokeWidth of ring
     * @param strokeColor     strokeColor of ring
     * @param strokeDashWidth strokeDashWidth of ring
     * @param strokeDashGap   strokeDashGap of ring
     */
    public void applyDashedStroke(int strokeWidth, int strokeColor, int strokeDashWidth, int strokeDashGap) {
        this.strokeWidth = strokeWidth;
        this.strokeColor = strokeColor;
        this.strokeDashWidth = strokeDashWidth;
        this.strokeDashGap = strokeDashGap;
    }

    /**
     * Apply ring innerRadiusRatio
     *
     * @param innerRadiusRatio innerRadiusRatio of ring
     *                         default value is 9
     */
    public void applyInnerRadiusRatio(float innerRadiusRatio) {
        this.innerRadiusRatio = innerRadiusRatio;
    }

    /**
     * Apply ring thickness
     *
     * @param thickness thickness of ring
     */
    public void applyThickness(int thickness) {
        this.thickness = thickness;
    }

    /**
     * Apply ring color
     *
     * @param color color of ring
     */
    public void applySolidColor(int color) {
        solidColor = color;
    }

    /**
     * Apply thicknessRatio of ring
     *
     * @param thicknessRatio thicknessRatio of ring
     *                       default value is 3
     */
    public void applyThicknessRatio(float thicknessRatio) {
        this.thicknessRatio = thicknessRatio;
    }

    //returns innerRadius of ring
    public int getInnerRadius() {
        return innerRadius;
    }

    //returns level should be use or not for ring
    public boolean isUseLevelForShape() {
        return useLevelForShape;
    }

    //returns thickness of ring
    public int getThickness() {
        return thickness;
    }

    //returns color of ring
    public int getSolidColor() {
        return solidColor;
    }

    //return strokeWidth of ring
    public int getStrokeWidth() {
        return strokeWidth;
    }

    //return strokeColor of ring
    public int getStrokeColor() {
        return strokeColor;
    }

    //returns strokeDashWidth of ring
    public int getStrokeDashWidth() {
        return strokeDashWidth;
    }

    //returns strokeDashGap of ring
    public int getStrokeDashGap() {
        return strokeDashGap;
    }

    //returns leftX of ring
    public float getLeftX() {
        return leftX;
    }

    //returns topY of ring
    public float getTopY() {
        return topY;
    }

    //returns rightX of ring
    public float getRightX() {
        return rightX;
    }

    //returns bottomY of ring
    public float getBottomY() {
        return bottomY;
    }

    //returns innerRadiusRatio of ring
    public float getInnerRadiusRatio() {
        return innerRadiusRatio;
    }

    //returns thicknessRatio of ring
    public float getThicknessRatio() {
        return thicknessRatio;
    }

    //returns level of ring
    public float getLevel() {
        return level;
    }

}
