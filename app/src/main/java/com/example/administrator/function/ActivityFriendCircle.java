package com.example.administrator.function;

import android.app.Activity;
import android.app.Fragment;

/**
 * Created by Administrator on 2018/9/13 0013.
 */

public class ActivityFriendCircle extends SingleFragmentActivity {

    @Override
    Fragment getFragment() {
        return new Fragment_FriendCircle();
    }
}
