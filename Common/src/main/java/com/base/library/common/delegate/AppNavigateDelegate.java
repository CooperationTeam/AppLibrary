package com.base.library.common.delegate;

import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * @author reber
 */
public interface AppNavigateDelegate {

    void startFragment(@NonNull FragmentManager fragmentManager, @IdRes int containerId,
                       @NonNull Fragment showFragment, Fragment hideFragment,
                       @FragmentFillingType int fillingType, String fragmentTag,
                       boolean addToBackStack);

    void startActivity(@NonNull AppCompatActivity activity, @NonNull Class<?> clazz, Bundle bundle);

    void startActivity(@NonNull Fragment fragment, @NonNull Class<?> clazz, Bundle bundle);

    void startActivityForResult(@NonNull AppCompatActivity activity, @NonNull Class<?> clazz,
                                Bundle bundle, int requestCode);

    void startActivityForResult(@NonNull Fragment fragment, @NonNull Class<?> clazz,
                                Bundle bundle, int requestCode);
}
