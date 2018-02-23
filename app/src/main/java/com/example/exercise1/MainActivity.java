package com.example.exercise1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvMeasure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvMeasure = findViewById(R.id.tvOnMeasure);
        tvMeasure.setOnClickListener(mOnClickListener);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override public void onClick(View view) {
           switch (view.getId()){
               case R.id.tvOnMeasure:
                   Intent intent = new Intent(MainActivity.this,MeasureActivity.class);
                   startActivity(intent);
                   break;
           }
        }
    };
}
