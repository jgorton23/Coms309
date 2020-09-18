package com.example.bottonnavbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        Button button = (Button) rootView.findViewById(R.id.addBtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddScreen();
            }
        });
        return rootView;
    }

    public void goToAddScreen() {
       requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AddScreen()).commit();
    }

}

