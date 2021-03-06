package com.example.administrator.function;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.dmcbig.mediapicker.PickerActivity;
import com.dmcbig.mediapicker.PickerConfig;
import com.dmcbig.mediapicker.entity.Media;

import java.util.ArrayList;

import cn.kevin.floatingeditor.EditorCallback;
import cn.kevin.floatingeditor.EditorHolder;
import cn.kevin.floatingeditor.FloatEditorActivity;

public class HostActivity extends AppCompatActivity {
    private final int REQUEST_VIDEO_CODE = 1;
    private static final String TAG = "HostActivity";

    private Button mShare;
    private Button mDynamic;
    private Button mSelectVideo;
    private ImageView mTestImage;
    private ArrayList<Media> defaultSelect;
    private ArrayList<Media> receive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        mShare = (Button) findViewById(R.id.share);
        mDynamic = (Button) findViewById(R.id.dynamic);
        mSelectVideo = (Button) findViewById(R.id.select_video);
        mTestImage = (ImageView) findViewById(R.id.testImage);

        mShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent=new Intent(Intent.ACTION_SEND);
//                intent.setType("text/plain");
//                intent.putExtra(Intent.EXTRA_TEXT, "分享的内容");
//                startActivity(Intent.createChooser(intent,"选择应用"));
                EditorCallback editorCallback = new EditorCallback() {

                    @Override
                    public void onCancel() {
                        //onCancel callback
                    }

                    @Override
                    public void onSubmit(String content) {
                        //onSubmit callback
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("text/plain");
                        intent.putExtra(Intent.EXTRA_TEXT, content);
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
                startActivity(new Intent(HostActivity.this, Activity_Dynamic.class));
            }
        });
        mSelectVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intentPic = new Intent(
//                        Intent.ACTION_GET_CONTENT);
//                intentPic.addCategory(Intent.CATEGORY_OPENABLE);
//                intentPic.setType("video/*;image/*");
//                startActivityForResult(intentPic,
//                        REQUEST_VIDEO_CODE);
                startActivity(new Intent(HostActivity.this , PickerActivity.class));
            }
        });
    }

    void go(){
        Intent intent =new Intent(HostActivity.this, PickerActivity.class);
        intent.putExtra(PickerConfig.SELECT_MODE,PickerConfig.PICKER_IMAGE_VIDEO);//default image and video (Optional)
        long maxSize=188743680L;//long long long long类型
        intent.putExtra(PickerConfig.MAX_SELECT_SIZE,maxSize); //default 180MB (Optional)
        intent.putExtra(PickerConfig.MAX_SELECT_COUNT,9);  //default 40 (Optional)
        intent.putExtra(PickerConfig.DEFAULT_SELECTED_LIST,defaultSelect); //(Optional)默认选中的照片
        HostActivity.this.startActivityForResult(intent,200);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==200&&resultCode==PickerConfig.RESULT_CODE){
            receive=data.getParcelableArrayListExtra(PickerConfig.EXTRA_RESULT);
            mTestImage.setImageURI(Uri.parse(receive.get(0).path));
        }
    }

}
