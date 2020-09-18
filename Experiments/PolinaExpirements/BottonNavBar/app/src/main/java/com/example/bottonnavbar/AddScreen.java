package com.example.bottonnavbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
        category.add("Other");

        ArrayAdapter<String> colorAdapter = new ArrayAdapter<>(this.getActivity(), android.R.layout.simple_spinner_item, category);
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(colorAdapter);
        return rootView;
    }
}
