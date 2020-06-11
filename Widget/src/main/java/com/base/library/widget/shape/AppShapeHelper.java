package com.base.library.widget.shape;

import com.base.library.widget.shape.round.AppRoundDelegate;

public abstract class AppShapeHelper {
    /**
     * get clip rect and starting form the top left corner
     *
     * @return values of four corner radius
     */
    protected float[] getFloatWithAllCorners(int cornerType, float cornerRadius) {
        float[] rect;
        switch (cornerType) {
            case AppRoundDelegate.MODE_TOP_LEFT: // add top left
                rect = new float[]{cornerRadius, cornerRadius, 0f, 0f, 0f, 0f, 0f, 0f};
                break;
            case AppRoundDelegate.MODE_TOP_RIGHT: // add top right
                rect = new float[]{0f, 0f, cornerRadius, cornerRadius, 0f, 0f, 0f, 0f};
                break;
            case AppRoundDelegate.MODE_BOTTOM_RIGHT: // add bottom right
                rect = new float[]{0f, 0f, 0f, 0f, cornerRadius, cornerRadius, 0f, 0f};
                break;
            case AppRoundDelegate.MODE_BOTTOM_LEFT: // add bottom left
                rect = new float[]{0f, 0f, 0f, 0f, 0f, 0f, cornerRadius, cornerRadius};
                break;
            case AppRoundDelegate.MODE_LEFT: // add top lef and bottom left
                rect = new float[]{cornerRadius, cornerRadius, 0f, 0f, 0f, 0f, cornerRadius, cornerRadius};
                break;
            case AppRoundDelegate.MODE_TOP: // add top left and top right
                rect = new float[]{cornerRadius, cornerRadius, cornerRadius, cornerRadius, 0f, 0f, 0f, 0f};
                break;
            case AppRoundDelegate.MODE_RIGHT: // add top right and bottom right
                rect = new float[]{0f, 0f, cornerRadius, cornerRadius, cornerRadius, cornerRadius, 0f, 0f};
                break;
            case AppRoundDelegate.MODE_BOTTOM: // add bottom left and bottom right
                rect = new float[]{0f, 0f, 0f, 0f, cornerRadius, cornerRadius, cornerRadius, cornerRadius};
                break;
            default: // add all corners with radius or no add corners
                rect = new float[]{cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius, cornerRadius};
                break;
        }
        return rect;
    }
}
