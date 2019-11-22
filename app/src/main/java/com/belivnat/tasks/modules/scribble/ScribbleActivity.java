package com.belivnat.tasks.modules.scribble;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.belivnat.tasks.R;
import com.belivnat.tasks.customviews.ScribbleView;

public class ScribbleActivity extends AppCompatActivity {
    ScribbleView scribbleView;
    Button btnResetScribble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scribble);
        scribbleView = findViewById(R.id.sv_scribble_view);
        btnResetScribble = findViewById(R.id.btn_reset_scribble);
        btnResetScribble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scribbleView.resetView();
            }
        });
    }
}
