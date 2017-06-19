package com.techgrains.util;

import android.graphics.Color;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.PathShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.annotation.NonNull;
import android.view.View;
import com.techgrains.model.shape.TGLineShape;
import com.techgrains.model.shape.TGOvalShape;
import com.techgrains.model.shape.TGRectangleShape;
import com.techgrains.model.shape.TGRingShape;
import com.techgrains.model.shape.TGShape;

/**
 * Utility class to handle TGShapeDrawable.
 */
public class TGShapeDrawableUtil {

    /**
     * Apply configuration based on TGShape to the View.
     *
     * @param shape TGShape
     * @param view View
     */
    public static void apply(TGShape shape, final View view) {
        if (view == null || shape == null) return;

        // Get View Default Color
        int color = Color.TRANSPARENT;
        if (view.getBackground() instanceof ColorDrawable) color = ((ColorDrawable) view.getBackground()).getColor();

        // Shape ladder
        Drawable drawable = null;

        if (shape instanceof TGRectangleShape)
            drawable = createDrawable((TGRectangleShape) shape, color);

        if (shape instanceof TGOvalShape)
            drawable = createDrawable((TGOvalShape) shape, color);

        if (shape instanceof TGLineShape)
            drawable = createDrawable((TGLineShape) shape, color);

        if (shape instanceof TGRingShape)
            drawable = createDrawable((TGRingShape) shape, color);

        // Set drawable to view
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackgroundDrawable(drawable);
        } else {
            view.setBackground(drawable);
        }
    }

    // Rectangle
    private static Drawable createDrawable(TGRectangleShape shape, int color) {
        // Shape radius
        float[] radius;
        if (shape.getRadius() == 0) {
            radius = new float[] {
                shape.getTopLeftRadius(), shape.getTopLeftRadius(), shape.getTopRightRadius(), shape.getTopRightRadius(),
                shape.getBottomRightRadius(), shape.getBottomRightRadius(), shape.getBottomLeftRadius(), shape.getBottomLeftRadius()
            };
        } else {
            radius = new float[] {
                shape.getRadius(), shape.getRadius(), shape.getRadius(), shape.getRadius(), shape.getRadius(), shape.getRadius(), shape.getRadius(),
                shape.getRadius()
            };
        }

        // Prepare Drawable
        TGShapeDrawable drawable = new TGShapeDrawable(new RoundRectShape(radius, null, radius), shape, color, null);

        applyShapeSize(shape.getWidth(), shape.getHeight(), drawable);
        applyShapePadding(shape.getPadding(), shape.getLeftPadding(), shape.getTopPadding(), shape.getRightPadding(), shape.getBottomPadding(),drawable);

        return applyStroke(shape.getStrokeWidth(), drawable);
    }

    //Oval
    private static Drawable createDrawable(TGOvalShape shape, int color) {
        // Prepare Drawable
        TGShapeDrawable drawable = new TGShapeDrawable(new OvalShape(), shape, color, null);
        applyShapeSize(shape.getWidth(), shape.getHeight(), drawable);
        applyShapePadding(shape.getPadding(), shape.getLeftPadding(), shape.getTopPadding(), shape.getRightPadding(), shape.getBottomPadding(),
            drawable);

        return applyStroke(shape.getStrokeWidth(), drawable);
    }

    //Line
    private static Drawable createDrawable(TGLineShape shape, int color) {
        // Prepare Path
        Path path = createPath(shape);
        TGShapeDrawable tgShapeDrawable = new TGShapeDrawable(new PathShape(path, 1, 1), shape, color, path);
        applyShapeSize(shape.getWidth(), shape.getHeight(), tgShapeDrawable);
        return tgShapeDrawable;
    }

    //Ring
    private static Drawable createDrawable(TGRingShape shape, int color) {
        // Prepare Path
        Path path = createPath(shape);
        TGShapeDrawable tgShapeDrawable = new TGShapeDrawable(new PathShape(path, 1, 1), shape, color, path);
        applyShapeSize(shape.getWidth(), shape.getHeight(), tgShapeDrawable);
        return tgShapeDrawable;
    }

    //LinePath
    @NonNull private static Path createPath(TGLineShape shape) {
        Path baseline = new Path();
        baseline.moveTo(shape.getStartX(), shape.getStartY());
        baseline.lineTo(shape.getStopX(), shape.getStopY());
        return baseline;
    }

    //RingPath
    private static Path createPath(TGRingShape shape) {
        float sweep = shape.isUseLevelForShape() ? (360.0f * shape.getLevel() / 10000.0f) : 360f;
        RectF bounds = new RectF(new RectF(shape.getLeftX(), shape.getTopY(), shape.getRightX(), shape.getBottomY()));
        float x = bounds.width() / 2.0f;
        float y = bounds.height() / 2.0f;
        float thickness = shape.getThickness() != -1 ? shape.getThickness() : bounds.width() / shape.getThicknessRatio();

        // inner radius
        float radius = shape.getInnerRadius() != -1 ? shape.getInnerRadius() : bounds.width() / shape.getThicknessRatio();

        RectF innerBounds = new RectF(bounds);
        innerBounds.inset(x - radius, y - radius);
        bounds = new RectF(innerBounds);
        bounds.inset(-thickness, -thickness);

        final Path ringPath = new Path();
        // arcTo treats the sweep angle mod 360, so check for that, since we
        // think 360 means draw the entire oval
        if (sweep < 360 && sweep > -360) {
            ringPath.setFillType(Path.FillType.EVEN_ODD);
            // inner top
            ringPath.moveTo(x + radius, y);
            // outer top
            ringPath.lineTo(x + radius + thickness, y);
            // outer arc
            ringPath.arcTo(bounds, 0.0f, sweep, false);
            // inner arc
            ringPath.arcTo(innerBounds, sweep, -sweep, false);
            ringPath.close();
        } else {
            // add the entire ovals
            ringPath.addOval(bounds, Path.Direction.CW);
            ringPath.addOval(innerBounds, Path.Direction.CCW);
        }

        return ringPath;
    }

    // Applying Shape Width
    private static void applyShapeWidth(int width, TGShapeDrawable drawable) {
        drawable.setIntrinsicWidth(width);
    }

    // Applying Shape Size
    private static void applyShapeSize(int width, int height, TGShapeDrawable drawable) {
        drawable.setIntrinsicWidth(width);
        drawable.setIntrinsicHeight(height);
    }

    // Padding
    private static void applyShapePadding(int padding, int leftPadding, int topPadding, int rightPadding, int bottomPadding,
        TGShapeDrawable drawable) {
        if (padding == 0) {
            drawable.setPadding(leftPadding, topPadding, rightPadding, bottomPadding);
        } else {
            drawable.setPadding(padding, padding, padding, padding);
        }
    }

    // Stroke
    @NonNull private static LayerDrawable applyStroke(int width, TGShapeDrawable drawable) {
        Drawable[] layers = { drawable };
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        int halfStrokeWidth = width / 2;
        layerDrawable.setLayerInset(0, halfStrokeWidth, halfStrokeWidth, halfStrokeWidth, halfStrokeWidth);
        return layerDrawable;
    }
}
