package com.base.library.widget.shape.state;

/**
 * @author reber
 * <p>
 * this is order to change the backgroud or textcolor when the view changed state
 */
public interface AppStateDelegate {
    /**
     * this type to get pressed or unpressed state set when it is enable.
     */
    int TYPE_ENABLE_PRESSED = 11;

    /**
     * this type to get selected or unselected state set when it is enable.
     */
    int TYPE_ENABLE_SELECTED = 21;
    int TYPE_ENABLE_UNSELECTED = 22;

    int TYPE_DISABLE = 31;

    int TYPE_ENABLE = 41;
}
