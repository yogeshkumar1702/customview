package com.example.customview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    private Button ClickMe;
    private ShapeSelectorView shapeSelectorView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ClickMe = findViewById(R.id.button_click_me);
        shapeSelectorView = findViewById(R.id.ShapeSelectorView_selector);

        ClickMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowMessage();
            }
        });

    }

    private void ShowMessage() {
        Toast.makeText(this,shapeSelectorView.getSelectedShape(),Toast.LENGTH_LONG).show();
    }
}