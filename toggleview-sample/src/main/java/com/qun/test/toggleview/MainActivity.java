package com.qun.test.toggleview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.qun.lib.toggleview.ToggleView;

public class MainActivity extends AppCompatActivity {

    private ToggleView mToggleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToggleView = findViewById(R.id.toggle_view);
        mToggleView.setChecked(true);
    }
}
