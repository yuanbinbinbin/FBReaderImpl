package com.yb.fbreaderimpl;

import android.app.Application;

import org.geometerplus.zlibrary.ui.android.library.FBReaderConfig;


/**
 * desc:application<br>
 * author : yuanbin<br>
 * email : binbinrd@foxmail.com<br>
 * date : 2019/3/20 20:52
 */
public class FBReaderImplApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FBReaderConfig.init(this);
    }
}
