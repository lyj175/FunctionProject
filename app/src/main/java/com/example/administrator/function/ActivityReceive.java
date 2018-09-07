package com.example.administrator.function;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/9/4 0004.
 */

public class ActivityReceive extends Activity {
    private TextView mReceive;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);
        mReceive = (TextView) findViewById(R.id.receive);

        Intent intent = getIntent();
        String type = intent.getType().toString();

        if(type.equals("text/plain")){
            if(intent.getStringExtra(Intent.EXTRA_TEXT)!=null){
                mReceive.setText(intent.getStringExtra(Intent.EXTRA_TEXT));
            }

        }

    }
}
