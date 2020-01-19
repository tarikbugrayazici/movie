package com.example.movies.ui.detailtabs.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.movies.R;
import com.example.movies.data.entity.Trailer;

import java.util.ArrayList;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.InfoViewHolder> {
    private Context context;
    private ArrayList<Trailer> list;

    public InfoAdapter(Context context, ArrayList<Trailer> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new InfoViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_item_trailer, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InfoViewHolder holder, int position) {
        final Trailer trailer = list.get(position);
        String url = "https://img.youtube.com/vi/";
        Glide.with(context).load(url+trailer.getKey()+"/hqdefault.jpg")
                .placeholder(new ColorDrawable(context.getResources().getColor(R.color.colorBlack)))
                .centerCrop().into(holder.imageView);
        holder.textView.setText(trailer.getName());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/watch?v="+trailer.getKey()));
                        v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class InfoViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public InfoViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_trailer);
            textView = itemView.findViewById(R.id.trailer);
        }
    }
}
