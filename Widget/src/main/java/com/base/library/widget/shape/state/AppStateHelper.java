package com.base.library.widget.shape.state;

class AppStateHelper {

    /**
     * get the combination of view state according to the stateType.
     */
    final int[] getStateCombination(int stateType) {
        switch (stateType) {
            case AppStateDelegate.TYPE_ENABLE_PRESSED: // enable and pressed
                return new int[]{
                        android.R.attr.state_enabled,
                        android.R.attr.state_pressed};


            case AppStateDelegate.TYPE_ENABLE_SELECTED: // enable and selected
                return new int[]{
                        android.R.attr.state_enabled,
                        android.R.attr.state_selected};

            case AppStateDelegate.TYPE_ENABLE_UNSELECTED: // enable and unselected
                return new int[]{
                        android.R.attr.state_enabled,
                        -android.R.attr.state_selected};

            case AppStateDelegate.TYPE_DISABLE: //disable
                return new int[]{-android.R.attr.state_enabled};

            default: // default is enable
                return new int[]{android.R.attr.state_enabled};
        }
    }
}
