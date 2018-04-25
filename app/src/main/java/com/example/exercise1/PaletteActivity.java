package com.example.exercise1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.view.PaletteView;

public class PaletteActivity extends AppCompatActivity {
    PaletteView selfView;

    TextView tvRevoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);

        selfView = findViewById(R.id.selfView);

        tvRevoke = findViewById(R.id.tvRevoke);

        tvRevoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selfView.revoke();
            }
        });
    }
}
