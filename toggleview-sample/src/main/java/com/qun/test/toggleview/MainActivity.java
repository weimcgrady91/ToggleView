package com.qun.test.toggleview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.qun.lib.toggleview.ToggleView;

public class MainActivity extends AppCompatActivity {

    private ToggleView mToggleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.view1).scrollTo(-100, -100);
            }
        });
        mToggleView = findViewById(R.id.toggle_view);
        mToggleView.setChecked(true);
        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mToggleView.isChecked()) {
                    mToggleView.setChecked(false);
                }  else {
                    mToggleView.setChecked(true);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
//        mToggleView.setChecked(true);
    }
}
