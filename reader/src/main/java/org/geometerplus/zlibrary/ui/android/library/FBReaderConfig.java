package org.geometerplus.zlibrary.ui.android.library;

import android.app.Application;
import android.content.Context;

import org.geometerplus.android.fbreader.api.FBReaderIntents;
import org.geometerplus.android.fbreader.config.ConfigShadow;
import org.geometerplus.android.util.TTSReadUtil;
import org.geometerplus.zlibrary.ui.android.image.ZLAndroidImageManager;

/**
 * desc:<br>
 * author : yuanbin<br>
 * date : 2018/10/15 18:33
 */
public class FBReaderConfig {
    private static Context context;
    private static ZLAndroidLibrary myLibrary;
    private static ConfigShadow myConfig;

    private FBReaderConfig() {
    }

    public static void init(Application con) {
        context = con.getApplicationContext();
        // this is a workaround for strange issue on some devices:
        //    NoClassDefFoundError for android.os.AsyncTask
        try {
            Class.forName("android.os.AsyncTask");
        } catch (Throwable t) {
        }
        FBReaderIntents.DEFAULT_PACKAGE = context.getPackageName();
        myConfig = new ConfigShadow(context);
        new ZLAndroidImageManager();
        myLibrary = new ZLAndroidLibrary(con);

        TTSReadUtil.getInstance().init(context);
    }

    public static Context getContext() {
        return context;
    }

    public static final ZLAndroidLibrary library() {
        return myLibrary;
    }
}
