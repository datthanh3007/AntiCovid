package com.example.anticovid.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.anticovid.Model.FAQ;
import com.example.anticovid.Model.Guide;
import com.example.anticovid.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GuideAdapter extends RecyclerView.Adapter<GuideAdapter.GuideViewHolder> {
    List<Guide> arrGuide;

    public GuideAdapter(List<Guide> arrGuide) {
        this.arrGuide = arrGuide;
    }

    @NonNull
    @Override
    public GuideViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_guide, parent, false);
        return new GuideViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GuideViewHolder holder, int position) {
        Guide guide = arrGuide.get(position);
        holder.txtTitle.setText(guide.getNameTitle());
        holder.imgGuide.setImageResource(guide.getResource());
        holder.txtAnswerguide.setText(guide.getChildGuide());
        boolean isExpandable = arrGuide.get(position).isExpandable();
        holder.relativeLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);

    }

    @Override
    public int getItemCount() {
        return arrGuide.size();
    }

    public class GuideViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgGuide;
        private TextView txtTitle;
        private TextView txtAnswerguide;
        private LinearLayout linearLayout;
        private RelativeLayout relativeLayout;

        public GuideViewHolder(@NonNull View itemView) {
            super(itemView);
            imgGuide = itemView.findViewById(R.id.img_guide);
            txtTitle = itemView.findViewById(R.id.txtnameTitle);
            txtAnswerguide = itemView.findViewById(R.id.txtAnswerguide);
            linearLayout = itemView.findViewById(R.id.lnLinearGuide);
            relativeLayout = itemView.findViewById(R.id.expandguide);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Guide guide = arrGuide.get(getAdapterPosition());
                    guide.setExpandable(!guide.isExpandable());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
