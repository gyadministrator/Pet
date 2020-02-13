package com.android.pet.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.pet.R;
import com.android.pet.activity.PetDetailActivity;
import com.android.pet.model.Pet;
import com.bumptech.glide.Glide;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    private List<Pet> list;
    private Context context;

    public MainAdapter(List<Pet> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_adapter_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Pet pet = list.get(position);
        if (pet != null) {
            if (!TextUtils.isEmpty(pet.getImage())) {
                Glide.with(context).load(pet.getImage()).into(holder.ivIcon);
            }
            holder.tvName.setText(pet.getName());
        }
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pet != null) {
                    PetDetailActivity.startActivity((Activity) context, pet.getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivIcon;
        TextView tvName;
        View view;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            ivIcon = itemView.findViewById(R.id.iv_icon);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }
}
