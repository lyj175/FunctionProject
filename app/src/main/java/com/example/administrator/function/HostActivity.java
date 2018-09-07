package com.example.administrator.function;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cn.kevin.floatingeditor.EditorCallback;
import cn.kevin.floatingeditor.EditorHolder;
import cn.kevin.floatingeditor.FloatEditorActivity;

public class HostActivity extends AppCompatActivity {
    private final int REQUEST_VIDEO_CODE = 1;

    private Button mShare;
    private Button mDynamic;
    private Button mSelectVideo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        mShare = (Button)findViewById(R.id.share);
        mDynamic = (Button)findViewById(R.id.dynamic);
        mSelectVideo = (Button)findViewById(R.id.select_video);

        mShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(Intent.ACTION_SEND);
//                intent.setType("text/plain");
//                intent.putExtra(Intent.EXTRA_TEXT, "分享的内容");
//                startActivity(Intent.createChooser(intent,"选择应用"));
                EditorCallback editorCallback = new EditorCallback(){

                    @Override
                    public void onCancel() {
                        //onCancel callback
                    }

                    @Override
                    public void onSubmit(String content) {
                        //onSubmit callback
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_TEXT , content);
                        startActivity(Intent.createChooser(intent, "发送给谁？"));
                    }
                    @Override
                    public void onAttached(ViewGroup rootView) {
                        //on layout Attached callback
                    }
                };

                FloatEditorActivity.openEditor(getApplicationContext(), editorCallback,
                        new EditorHolder(R.layout.view_component_write,//Custom layout
                                R.id.tv_cancel, R.id.tv_send, R.id.et_write));


            }
        });
        mDynamic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HostActivity.this , com.zq.weixinselectpicture.MainActivity.class));
            }
        });
        mSelectVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_VIDEO_CODE);
            }
        });
    }
}
