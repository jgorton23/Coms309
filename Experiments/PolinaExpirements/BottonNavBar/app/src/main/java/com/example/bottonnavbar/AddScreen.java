package com.example.bottonnavbar;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AddScreen extends AppCompatActivity {
//    @Nullable
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.add_screen, container, false);
//    }


    private Spinner spinner;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.add_screen);

        spinner = findViewById(R.id.spinnerCategory);

        List<String> category = new ArrayList<>();
        category.add(" ");
        category.add("Rent");
        category.add("Other");

        ArrayAdapter<String> colorAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, category);
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(colorAdapter);

//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                String yourCategory = spinner.getSelectedItem().toString();
//                if(!yourCategory.equals){
//
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

    }
}
