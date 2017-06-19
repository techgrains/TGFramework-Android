package com.techgrains.util;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;

import com.techgrains.model.shape.TGLineShape;
import com.techgrains.model.shape.TGOvalShape;
import com.techgrains.model.shape.TGRectangleShape;
import com.techgrains.model.shape.TGRingShape;
import com.techgrains.model.shape.TGShape;

/**
 * Custom ShapeDrawable to handle various views for the shapes - Line, Oval, Rectangle, Ring
 */
public class TGShapeDrawable extends ShapeDrawable {

    private Paint fillPaint, strokePaint;
    private Path path;
    private TGShape tgShape;

    /**
     * Initialize ShapeDrawable with customized attributes
     *
     * @param s Shape of Android
     * @param shape Shape of TGFramework
     * @param color int
     * @param path Path
     */
    public TGShapeDrawable(Shape s, TGShape shape, int color, Path path) {
        super(s);
        this.tgShape = shape;

        if (tgShape instanceof TGRectangleShape) {
            TGRectangleShape rectangleShape = (TGRectangleShape) shape;

            // SolidColor
            applySolidColor(color, rectangleShape.getSolidColor());

            // StrokeWidth StrokeColor
            applyStrokeColorWidth(rectangleShape.getStrokeWidth(), rectangleShape.getStrokeColor());

            // StrokeDashWidth StrokeDashGap
            applyDashedStroke(rectangleShape.getStrokeDashWidth(), rectangleShape.getStrokeDashGap());

        } else if (tgShape instanceof TGOvalShape) {
            TGOvalShape ovalShape = (TGOvalShape) shape;

            // SolidColor
            applySolidColor(color, ovalShape.getSolidColor());

            // StrokeWidth StrokeColor
            applyStrokeColorWidth(ovalShape.getStrokeWidth(), ovalShape.getStrokeColor());

            // StrokeDashWidth StrokeDashGap
            applyDashedStroke(ovalShape.getStrokeDashWidth(), ovalShape.getStrokeDashGap());

        } else if (tgShape instanceof TGLineShape) {
            TGLineShape lineShape = (TGLineShape) tgShape;
            this.path = path;

            // SolidColor
            applySolidColor(color, lineShape.getLineColor());

            // StrokeWidth StrokeColor
            applyStrokeColorWidth(lineShape.getHeight(), lineShape.getLineColor());

            // StrokeDashWidth StrokeDashGap
            applyDashedStroke(lineShape.getStrokeDashWidth(), lineShape.getStrokeDashGap());

        } else if (tgShape instanceof TGRingShape) {
            TGRingShape ringShape = (TGRingShape) shape;
            this.path = path;

            // SolidColor
            applySolidColor(ringShape.getSolidColor(), color);

            // StrokeWidth StrokeColor
            applyStrokeColorWidth(ringShape.getStrokeWidth(), ringShape.getStrokeColor());

            // StrokeDashWidth StrokeDashGap
            applyDashedStroke(ringShape.getStrokeDashWidth(), ringShape.getStrokeDashGap());

        }

        // Gradient
        if (shape.getGradientOption() != null && shape.getGradientColors() != null)
            applyGradient(tgShape);

    }

    // GradientAngle
    private static Shader applyGradientAngle(Shader shader, float angle, float centerX, float centerY, int gradientMultiplicationAngle) {
        if (angle % gradientMultiplicationAngle == 0) {
            Matrix matrix = new Matrix();
            matrix.setRotate(angle, centerX, centerY);
            if (shader != null)
                shader.setLocalMatrix(matrix);
        }
        return shader;
    }

    // ColorSet
    private static boolean isColorSet(int color) {
        return color != 0;
    }

    // LinearGradient
    private static Shader createLinearGradient(int startX, int startY, int endX, int endY, int gradientAngle, int[] gradientColors, float[] gradientPositions, int gradientMultiplicationAngle, TGShape.GradientTileMode gradientTileMode) {
        //Apply gradientAngle
        float endX1 = gradientAngle % gradientMultiplicationAngle == 0 ? (float) (Math.cos(Math.toRadians(gradientAngle) * endX)) : endX;
        float endY1 = gradientAngle % gradientMultiplicationAngle == 0 ? (float) (Math.sin(Math.toRadians(gradientAngle)) * endY) : endY;

        float[] gradientPositionValues = gradientPositions != null ? gradientPositions.length == gradientColors.length ? gradientPositions : null : null;
        switch (gradientTileMode) {
            case MIRROR:
                return new LinearGradient(startX, startY, endX1, endY1, gradientColors, gradientPositionValues, Shader.TileMode.MIRROR);

            case REPEAT:
                return new LinearGradient(startX, startY, endX1, endY1, gradientColors, gradientPositionValues, Shader.TileMode.REPEAT);

            case CLAM:
                return new LinearGradient(startX, startY, endX1, endY1, gradientColors, gradientPositionValues, Shader.TileMode.CLAMP);

            default:
                return null;
        }
    }

    // RadialGradient
    private static Shader createRadialGradient(int centerX, int centerY, int gradientRadius, int gradientAngle, int[] gradientColors, float[] gradientPositions, int gradientMultiplicationAngle, TGShape.GradientTileMode gradientTileMode) {
        Shader shader = null;
        float[] gradientPositionValues = gradientPositions != null ? gradientPositions.length == gradientColors.length ? gradientPositions : null : null;
        switch (gradientTileMode) {
            case MIRROR:
                shader = new RadialGradient(centerX, centerY, gradientRadius, gradientColors, gradientPositionValues, Shader.TileMode.MIRROR);
                break;

            case REPEAT:
                shader = new RadialGradient(centerX, centerY, gradientRadius, gradientColors, gradientPositionValues, Shader.TileMode.REPEAT);
                break;

            case CLAM:
                shader = new RadialGradient(centerX, centerY, gradientRadius, gradientColors, gradientPositionValues, Shader.TileMode.CLAMP);
                break;
        }
        return applyGradientAngle(shader, gradientAngle, centerX, centerY, gradientMultiplicationAngle);
    }

    // SweepGradient
    private static Shader createSweepGradient(int centerX, int centerY, int gradientAngle, int[] gradientColors, float[] gradientPositions, int gradientMultiplicationAngle) {
        Shader shader = new SweepGradient(centerX, centerY, gradientColors, gradientPositions != null ? gradientPositions.length == gradientColors.length ? gradientPositions : null : null);
        return applyGradientAngle(shader, gradientAngle, centerX, centerY, gradientMultiplicationAngle);
    }

    @Override
    protected void onDraw(Shape shape, Canvas canvas, Paint paint) {

        // Draw Rectangle and Oval
        if (tgShape instanceof TGRectangleShape || tgShape instanceof TGOvalShape) {
            if (isFillPaintSet())
                shape.draw(canvas, fillPaint);
            if (isStrokeSet())
                shape.draw(canvas, strokePaint);
        }

        // Draw Line
        if (tgShape instanceof TGLineShape) {
            if (isStrokeSet())
                canvas.drawPath(path, strokePaint);
        }

        // Draw Ring
        if (tgShape instanceof TGRingShape) {
            if (isStrokeSet())
                canvas.drawPath(path, strokePaint);
            if (isFillPaintSet())
                canvas.drawPath(path, fillPaint);
        }
    }

    // Gradient
    private void applyGradient(TGShape tgShape) {
        Shader shader = null;
        // gradientAngle must be a multiple of 45
        int gradientMultiplicationAngle = 45;
        switch (tgShape.getGradientOption()) {
            case LINEAR:
                shader = createLinearGradient(tgShape.getStartX(), tgShape.getStartY(), tgShape.getEndX(), tgShape.getEndY(), tgShape.getGradientAngle(), tgShape.getGradientColors(), tgShape.getGradientPositions(), gradientMultiplicationAngle, tgShape.getGradientTileMode());
                break;
            case RADIAL:
                shader = createRadialGradient(tgShape.getGradientCenterX(), tgShape.getGradientCenterY(), tgShape.getRadialGradientRadius(), tgShape.getGradientAngle(), tgShape.getGradientColors(), tgShape.getGradientPositions(), gradientMultiplicationAngle, tgShape.gradientTileMode);
                break;
            case SWEEP:
                shader = createSweepGradient(tgShape.getGradientCenterX(), tgShape.getGradientCenterY(), tgShape.getGradientAngle(), tgShape.getGradientColors(), tgShape.getGradientPositions(), gradientMultiplicationAngle);
                break;
        }
        // apply gradient to rectangle,oval and ring
        if (shader != null) {
            fillPaint.setColor(Color.BLACK);
            fillPaint.setShader(shader);
        }
        // apply gradient to lineShape
        if (shader != null && tgShape instanceof TGLineShape) {
            strokePaint.setColor(Color.BLACK);
            strokePaint.setShader(shader);
        }

    }

    // Stroke Color and Width
    private void applyStrokeColorWidth(int strokeWidth, int strokeColor) {
        strokePaint = new Paint(fillPaint);
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setStrokeWidth(strokeWidth);
        strokePaint.setColor(isColorSet(strokeColor) ? strokeColor : Color.TRANSPARENT);
    }

    // SolidColor
    private void applySolidColor(int color, int solidColor) {
        fillPaint = new Paint(this.getPaint());
        fillPaint.setColor(isColorSet(solidColor) ? solidColor : color);
    }

    // DashedStroke
    private void applyDashedStroke(int strokeDashWidth, int strokeDashGap) {
        strokePaint.setPathEffect(new DashPathEffect(new float[]{strokeDashWidth, strokeDashGap}, 0));
    }

    private boolean isStrokeSet() {
        return strokePaint != null;
    }

    private boolean isFillPaintSet() {
        return fillPaint != null;
    }
}
