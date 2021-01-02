package com.example.anticovid.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.anticovid.R;


public class fragmentDialogInfected extends DialogFragment {
    private View view;
    private TextView txtnameDialog,txtNote,txtAddress,txtTime;
   private Button btnback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       view= inflater.inflate(R.layout.fragment_dialog_infected, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtnameDialog = view.findViewById(R.id.nameDialog);
        txtAddress = view.findViewById(R.id.txtAddress);
        txtTime = view.findViewById(R.id.txttime);
        txtNote = view .findViewById(R.id.txtNote);
        btnback = view. findViewById(R.id.btnback);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               dismiss();
            }
        });
        Bundle bundle =getArguments();
        if(bundle!=null){
            txtnameDialog.setText(bundle.getString("name"));
            txtAddress.setText(bundle.getString("address"));
            txtTime.setText(bundle.getString("time"));
            txtNote.setText(bundle.getString("note"));
        }
    }
}