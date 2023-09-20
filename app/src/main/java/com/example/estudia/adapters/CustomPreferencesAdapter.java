package com.example.estudia.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.estudia.R;

import java.util.List;

public class CustomPreferencesAdapter extends RecyclerView.Adapter<CustomPreferencesAdapter.ViewHolder> {

    Context context;
    List<String> mList;
    List<String> mDescription;

    public CustomPreferencesAdapter(Context context, List<String> mList, List<String> mDescription) {
        this.context = context;
        this.mList = mList;
        this.mDescription = mDescription;
    }

    @NonNull
    @Override
    public CustomPreferencesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.preferences_item_recycler_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomPreferencesAdapter.ViewHolder holder, int position) {
        if (mList.size() > 0 && mList != null) {
            String title = mList.get(position);
            holder.title.setText(title);
            String description = mDescription.get(position);
            holder.description.setText(description);
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleItemPreference);
            description = itemView.findViewById(R.id.descItemPreference);
        }
    }
}
