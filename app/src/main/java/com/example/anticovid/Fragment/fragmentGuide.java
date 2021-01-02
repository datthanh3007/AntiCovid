package com.example.anticovid.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anticovid.Adapter.GuideAdapter;
import com.example.anticovid.Model.Guide;
import com.example.anticovid.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class fragmentGuide extends Fragment {
    private RecyclerView recyclerView;
    private List<Guide> guideList;
    private View view;
    private GuideAdapter guideAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_guide_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyler_guide);
        initdata();
        guideAdapter = new GuideAdapter(guideList);
        recyclerView.setAdapter(guideAdapter);
        recyclerView.setHasFixedSize(true);


    }

    private void initdata() {
        guideList = new ArrayList<>();
        guideList.add(new Guide(R.drawable.hinhnen, "name title 1", "childguide 1"));
        guideList.add(new Guide(R.drawable.hinhnen, "name title 1", "childguide 1"));
        guideList.add(new Guide(R.drawable.hinhnen, "name title 1", "childguide 1"));
        guideList.add(new Guide(R.drawable.hinhnen, "name title 1", "childguide 1"));
        guideList.add(new Guide(R.drawable.hinhnen, "name title 1", "childguide 1"));
        guideList.add(new Guide(R.drawable.hinhnen, "name title 1", "childguide 1"));
    }
}
