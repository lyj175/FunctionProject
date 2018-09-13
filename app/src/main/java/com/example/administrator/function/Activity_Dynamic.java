package com.example.administrator.function;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/9/13 0013.
 */

public class Activity_Dynamic extends Activity implements View.OnClickListener{
    private ImageView mBackView;
    private TextView mSubmitView;
    private EditText mEdit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic);
        initView();
    }

    private void initView() {
        mBackView = (ImageView)findViewById(R.id.btn_back);
        mSubmitView = (TextView)findViewById(R.id.submit);
        mEdit = (EditText)findViewById(R.id.dynamic_edit);
        mBackView.setOnClickListener(this);
        mSubmitView.setOnClickListener(this);
        mEdit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_back:
                finish();
                break;
            case R.id.submit:
                startActivity(new Intent(Activity_Dynamic.this , ActivityFriendCircle.class));
                break;
        }
    }

}
