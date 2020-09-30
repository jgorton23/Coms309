package com.example.bottonnavbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class AddScreen extends Fragment {
    private Spinner spinner;

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.add_screen, container, false);

        spinner = rootView.findViewById(R.id.spinnerCategory2);

        List<String> category = new ArrayList<>();
        category.add("--");
        category.add("Rent");
        category.add("Entertainment");
        category.add("Other");

        ArrayAdapter<String> colorAdapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, category);
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(colorAdapter);

        Button addBtn = rootView.findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddScreen();
            }
        });

        return rootView;
    }

    public void goToAddScreen() {
        EditText name = getActivity().findViewById(R.id.namePlainText);
        EditText amount = getActivity().findViewById(R.id.amountNumberText);
        EditText date = getActivity().findViewById(R.id.dateDateText);
        EditText notes = getActivity().findViewById(R.id.notesMultilineText);
        final Spinner spinner = getActivity().findViewById(R.id.spinnerCategory2);

        if(name != null && amount != null && date != null && notes != null && !spinner.getSelectedItem().toString().equals("--")){
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        }
    }
}
