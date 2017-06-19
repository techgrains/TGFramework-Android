package com.techgrains.model.shape;

import com.techgrains.model.TGModel;

/**
 * Common attributes of custom Shapes
 */
public abstract class TGShape extends TGModel {
    public GradientOption gradientOption;
    public GradientTileMode gradientTileMode;
    private int[] gradientColors;
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private int gradientAngle;
    private int radialGradientRadius;
    private int gradientCenterX;
    private int gradientCenterY;
    private float[] gradientPositions;
    private int height;
    private int width;

    /**
     * Apply shape LinearGradient
     *
     * @param startX            startX of linearGradient
     * @param startY            startY of linearGradient
     * @param stopX             stopX of linearGradient
     * @param stopY             stopY of linearGradient
     * @param gradientAngle     gradientAngle of linearGradient
     * @param gradientColors    gradientColor of linearGradient
     * @param gradientPositions gradientPositions of linearGradient
     * @param gradientTileMode  The Shader tiling mode of linearGradient
     */
    public void applyLinearGradient(int startX, int startY, int stopX, int stopY, int gradientAngle, int[] gradientColors, float[] gradientPositions, GradientTileMode gradientTileMode) {
        this.startX = startX;
        this.startY = startY;
        this.endX = stopX;
        this.endY = stopY;
        this.gradientColors = gradientColors;
        this.gradientTileMode = gradientTileMode;
        this.gradientPositions = gradientPositions;
        this.gradientAngle = gradientAngle;
        this.gradientOption = GradientOption.LINEAR;
    }

    /**
     * Apply shape size
     *
     * @param height height of shape
     * @param width  width of shape
     */
    public void applySize(int height, int width) {
        this.height = height;
        this.width = width;
    }

    /**
     * Apply shape RadialGradient
     *
     * @param radialGradientCenterX x-coordinate of the center of radialGradient
     * @param radialGradientCenterY y-coordinate of the center of radialGradient
     * @param gradientAngle         gradientAngle of radialGradient
     * @param radialGradientRadius  radialGradientRadius of radialGradient
     * @param gradientColors        gradientColor of radialGradient
     * @param gradientPositions     gradientPositions of radialGradient
     * @param gradientTileMode      The Shader tiling mode of radialGradient
     */

    public void applyRadialGradient(int radialGradientCenterX, int radialGradientCenterY, int gradientAngle, int radialGradientRadius, int[] gradientColors, float[] gradientPositions, GradientTileMode gradientTileMode) {
        this.gradientCenterX = radialGradientCenterX;
        this.gradientCenterY = radialGradientCenterY;
        this.radialGradientRadius = radialGradientRadius;
        this.gradientColors = gradientColors;
        this.gradientTileMode = gradientTileMode;
        this.gradientPositions = gradientPositions;
        this.gradientAngle = gradientAngle;
        this.gradientOption = GradientOption.RADIAL;
    }

    /**
     * @param sweepGradientCenterX x-coordinate of the sweepGradient
     * @param sweepGradientCenterY y-coordinate of the sweepGradient
     * @param gradientAngle        gradientAngle of sweepGradient
     * @param gradientColors       gradientColor of sweepGradient
     * @param gradientPositions    The Shader tiling mode of sweepGradient
     */
    public void applySweepGradient(int sweepGradientCenterX, int sweepGradientCenterY, int gradientAngle, int[] gradientColors, float[] gradientPositions) {
        this.gradientCenterX = sweepGradientCenterX;
        this.gradientCenterY = sweepGradientCenterY;
        this.gradientColors = gradientColors;
        this.gradientPositions = gradientPositions;
        this.gradientAngle = gradientAngle;
        this.gradientOption = GradientOption.SWEEP;
    }

    //returns gradientOptions of shape
    public GradientOption getGradientOption() {
        return gradientOption;
    }

    //returns gradientTileMode of shape
    public GradientTileMode getGradientTileMode() {
        return gradientTileMode;
    }

    //returns gradientCenterX of shape
    public int getGradientCenterX() {
        return gradientCenterX;
    }

    //returns gradientCenterY of shape
    public int getGradientCenterY() {
        return gradientCenterY;
    }

    //returns radialGradientRadius of shape
    public int getRadialGradientRadius() {
        return radialGradientRadius;
    }

    //returns gradientPositions of shape
    public float[] getGradientPositions() {
        return gradientPositions;
    }

    //returns gradientAngle of shape
    public int getGradientAngle() {
        return gradientAngle;
    }

    //returns startX of shape
    public int getStartX() {
        return startX;
    }

    //returns startY of shape
    public int getStartY() {
        return startY;
    }

    //returns endX of shape
    public int getEndX() {
        return endX;
    }

    //returns endY of shape
    public int getEndY() {
        return endY;
    }

    //returns gradientColors of shape
    public int[] getGradientColors() {
        return gradientColors;
    }

    //return height of shape
    public int getHeight() {
        return height;
    }

    //return width of shape
    public int getWidth() {
        return width;
    }

    //gradientTypes
    public enum GradientOption {
        LINEAR,
        RADIAL,
        SWEEP
    }

    //gradient TileModes
    public enum GradientTileMode {
        CLAM,
        REPEAT,
        MIRROR
    }
}


