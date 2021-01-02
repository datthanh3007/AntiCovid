package com.example.anticovid.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anticovid.Model.Countries;
import com.example.anticovid.R;
import com.example.anticovid.util.OnItemClickListenerCountries;

import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SeedetailCountriesAdapter extends RecyclerView.Adapter<SeedetailCountriesAdapter.ViewHolder> {
    private OnItemClickListenerCountries onItemClickListenerCountries;
    private List<Countries> mListCountries = new ArrayList<>();

    public SeedetailCountriesAdapter(OnItemClickListenerCountries onItemClickListenerCountries, List<Countries> mListCountries) {
        this.onItemClickListenerCountries = onItemClickListenerCountries;
        this.mListCountries = mListCountries;
    }

    public void updateData (List<Countries> list ){
        this.mListCountries=list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_countries,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Countries countries=mListCountries.get(position);
        holder.txtInfected.setText(countries.getTotalConfirmed()+"");
        holder.txtCountryname.setText(countries.getCountry());
        holder.txtCountryCode.setText("("+countries.getCountryCode()+")");
        holder.txtDeath.setText(countries.getTotalDeaths()+"");
        holder.txtRecovered.setText(countries.getTotalRecovered()+"");
    }

    @Override
    public int getItemCount() {
        return mListCountries.size();
    }

    public void filteredList(ArrayList<Countries> filterList) {
        mListCountries=filterList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       private TextView txtCountryname,txtCountryCode,txtInfected,txtDeath,txtRecovered;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCountryname=itemView.findViewById(R.id.textViewCountryName);
            txtCountryCode=itemView.findViewById(R.id.textViewCountryCode);
            txtInfected=itemView.findViewById(R.id.textViewInfected);
            txtDeath=itemView.findViewById(R.id.textViewDeath);
            txtRecovered= itemView.findViewById(R.id.textViewRecovered);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListenerCountries.OnitemClickCountries(mListCountries.get(getAdapterPosition()));
                }
            });
        }
    }
}
