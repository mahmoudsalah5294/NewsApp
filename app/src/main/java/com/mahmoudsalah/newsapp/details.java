package com.mahmoudsalah.newsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.StringReader;

public class details extends AppCompatActivity {
    TextView titleText,descText,linkText,beforeText,afterText;
    String link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        titleText = findViewById(R.id.titleText);
        descText = findViewById(R.id.descText);
        linkText = findViewById(R.id.linkText);
        beforeText = findViewById(R.id.beforeText);
        afterText = findViewById(R.id.afterText);
        titleText.setText(getIntent().getStringExtra("title"));
        descText.setText(getIntent().getStringExtra("des"));
        linkText.setText(getIntent().getStringExtra("url"));
//        beforeText.setText(getIntent().getStringExtra("befurl"));
//        afterText.setText(getIntent().getStringExtra("afturl"));
//        link = getIntent().getStringExtra("url");
//        beforeText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                titleText.setText(getIntent().getStringExtra("befurl"));
//                descText.setText(getIntent().getStringExtra("befdes"));
//                linkText.setText(getIntent().getStringExtra("beflink"));
//            }
//        });
//        afterText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                titleText.setText(getIntent().getStringExtra("afturl"));
//                descText.setText(getIntent().getStringExtra("aftdes"));
//                linkText.setText(getIntent().getStringExtra("aftlink"));
//            }
//        });

    linkText.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(details.this,webview.class);
            intent.putExtra("url",getIntent().getStringExtra("url"));
        startActivity(intent);
        }
    });
    }
}
