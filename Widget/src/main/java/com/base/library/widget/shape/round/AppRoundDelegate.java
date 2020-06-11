package com.base.library.widget.shape.round;

/**
 * @author reber
 * <p>
 * this is order to add corner in view
 */
public interface AppRoundDelegate {
    /**
     * No add angles to all corners.
     */
    int MODE_NONE = 0;
    /**
     * add top left.
     */
    int MODE_TOP_LEFT = 1;
    /**
     * add top right.
     */
    int MODE_TOP_RIGHT = 2;
    /**
     * add bottom right.
     */
    int MODE_BOTTOM_RIGHT = 3;
    /**
     * add bottom left.
     */
    int MODE_BOTTOM_LEFT = 4;
    /**
     * Add top left and bottom left.
     */
    int MODE_LEFT = 5;
    /**
     * Add top left and top right.
     */
    int MODE_TOP = 6;
    /**
     * Add top right and bottom right.
     */
    int MODE_RIGHT = 7;
    /**
     * Add bottom right and bottom left.
     */
    int MODE_BOTTOM = 8;
    /**
     * Add angles to all four corners.
     */
    int MODE_ALL = 9;
}
