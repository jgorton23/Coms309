package com.example.addandsubtractapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_screen);

        Button addBtn = findViewById(R.id.addBtn);
        Button subtractBtn = findViewById(R.id.subtractBtn);

        addBtn.setOnClickListener(this);
        subtractBtn.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        EditText firstNumEditText = (EditText) findViewById(R.id.firstNumEditText);
        EditText secondNumEditText = (EditText) findViewById(R.id.secondNumEditText);
        TextView resultTextView = (TextView) findViewById(R.id.resultTextView);

        int num1 = Integer.parseInt(firstNumEditText.getText().toString());
        int num2 = Integer.parseInt(secondNumEditText.getText().toString());
        int result;

        switch (view.getId()){
            case R.id.addBtn:
                result = num1 + num2;
                resultTextView.setText(""+result);
                break;
            case R.id.subtractBtn:
                result = num1 - num2;
                resultTextView.setText(""+result);
                break;
        }


    }
}