package com.example.exercise1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvMeasure,tvPath,tvPalette;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvMeasure = findViewById(R.id.tvOnMeasure);
        tvPath = findViewById(R.id.tvPath);
        tvPalette = findViewById(R.id.tvPalette);
        tvMeasure.setOnClickListener(mOnClickListener);
        tvPath.setOnClickListener(mOnClickListener);
        tvPalette.setOnClickListener(mOnClickListener);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override public void onClick(View view) {
           switch (view.getId()){
               case R.id.tvOnMeasure:
                   Intent intent = new Intent(MainActivity.this,MeasureActivity.class);
                   startActivity(intent);
                   break;
               case R.id.tvPath:
                   Intent pathIntent = new Intent(MainActivity.this,PathActivity.class);
                   startActivity(pathIntent);
                   break;
               case R.id.tvPalette:
                   Intent paletteIntent = new Intent(MainActivity.this,PaletteActivity.class);
                   startActivity(paletteIntent);
                   break;
           }
        }
    };
}
