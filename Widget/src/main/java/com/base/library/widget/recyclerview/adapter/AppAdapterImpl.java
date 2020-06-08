package com.base.library.widget.recyclerview.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.base.library.widget.recyclerview.viewholder.AppViewHolderImpl;

public class AppAdapterImpl extends RecyclerView.Adapter<AppViewHolderImpl> {
    @NonNull
    @Override
    public AppViewHolderImpl onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AppViewHolderImpl holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
