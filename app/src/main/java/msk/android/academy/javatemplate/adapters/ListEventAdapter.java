package msk.android.academy.javatemplate.adapters;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import msk.android.academy.javatemplate.R;

public class ListEventAdapter extends RecyclerView.Adapter<ListEventAdapter.ViewHolder> {
    private Context context;
    private List<ResultDTO> resultDTO = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private ItemClickListener clickListener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.list_event_item, viewGroup, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onClick(View v) {
                Log.e("holder.getAdapterPosition", String.valueOf(holder.getAdapterPosition()));
//                openNewsActivity(holder.getAdapterPosition());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder vievHolder, int position) {
        final ResultDTO resultItem = resultDTO.get(position);
//        vievHolder.title.setText(resultItem.getAuthor());
//        holder.category.setText(resultItem.getSection());
//        holder.textDescription.setText(resultItem.getAbstractDescription());
//        holder.title.setText(resultItem.getPublishDate());
//        Glide.with(holder.image.getContext()).load(resultItem.getMultimedia().get(0).getNewsImageUrl()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return resultDTO.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @NonNull
        TextView title;
        ImageView image;
        TextView description;
        TextView datetime;
        TextView hashTag;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.event_big_img);
            title = itemView.findViewById(R.id.title_txt);
            description = itemView.findViewById(R.id.description_txt);
            datetime = itemView.findViewById(R.id.datetime_txt);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) clickListener.onItemClick(itemView, getAdapterPosition());
        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
