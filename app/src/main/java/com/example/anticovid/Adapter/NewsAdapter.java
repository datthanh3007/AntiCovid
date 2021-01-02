package com.example.anticovid.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.anticovid.Model.Countries;
import com.example.anticovid.Model.News;
import com.example.anticovid.R;
import com.example.anticovid.util.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<News> mListNews = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public NewsAdapter(ArrayList<News> mListNews, OnItemClickListener onItemClickListener) {
        this.mListNews = mListNews;
        this.onItemClickListener = onItemClickListener;
    }

    public void updateData(List<News> list) {
        this.mListNews = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        News news = mListNews.get(position);

        Glide.with(holder.imgnews.getContext()).load(news.getResourceID()).into(holder.imgnews);
        holder.txtnamenews.setText(news.getNameNews());
        holder.txttimenews.setText(news.getTimeNews());
    }

    @Override
    public int getItemCount() {
        return mListNews.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        private ImageView imgnews;
        private TextView txtnamenews;
        private TextView txttimenews;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgnews = itemView.findViewById(R.id.img_news);
            txtnamenews = itemView.findViewById(R.id.txt_news);
            txttimenews = itemView.findViewById(R.id.txt_timenews);
           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   onItemClickListener.OnitemClick(mListNews.get(getAdapterPosition()));
               }
           });

        }

    }
}
