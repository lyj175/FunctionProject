package com.example.administrator.function;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2018/9/13 0013.
 */

public abstract class  SingleFragmentActivity extends Activity {
     abstract Fragment getFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);
        FragmentManager manager = getFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragment_container);

        if(fragment==null){
            fragment = getFragment();

            manager.beginTransaction()
                    .add(R.id.fragment_container , fragment)
                    .commit();
        }

    }
}
