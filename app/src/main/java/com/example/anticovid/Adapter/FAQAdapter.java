package com.example.anticovid.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.anticovid.Model.FAQ;
import com.example.anticovid.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FAQAdapter  extends RecyclerView.Adapter<FAQAdapter.ViewHolder> {
    List<FAQ> listFAQ;

    public FAQAdapter(List<FAQ> listFAQ) {
        this.listFAQ = listFAQ;
    }

    @NonNull
    @Override
    public FAQAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_faq,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FAQAdapter.ViewHolder holder, int position) {
        FAQ faq = listFAQ.get(position);
        holder.imgFAQ.setImageResource(faq.getResourceImg());
        holder.txtQuestion.setText(faq.getQuestion());
        holder.txtAnswer.setText(faq.getAnswer());
        boolean isExpandable= listFAQ.get(position).isExpandable();
        holder.expandlayout.setVisibility(isExpandable? View.VISIBLE :View.GONE);
    }
    @Override
    public int getItemCount() {
        return listFAQ.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFAQ;
        TextView txtAnswer, txtQuestion ;
        LinearLayout linearLayout;
        RelativeLayout expandlayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFAQ = itemView.findViewById(R.id.imgFAQ);
            txtAnswer=itemView.findViewById(R.id.txtAnswerfaq);
            txtQuestion=itemView.findViewById(R.id.txtQuestion);
            linearLayout=itemView.findViewById(R.id.lnLinear);
            expandlayout=itemView.findViewById(R.id.expandfaq);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FAQ faq = listFAQ.get(getAdapterPosition());
                    faq.setExpandable(!faq.isExpandable());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
