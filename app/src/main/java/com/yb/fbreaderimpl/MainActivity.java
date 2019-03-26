package com.yb.fbreaderimpl;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.geometerplus.android.fbreader.FBReader;
import org.geometerplus.fbreader.util.TurnPageJudgeUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * desc:<br>
 * author : yuanbin<br>
 * email : binbinrd@foxmail.com<br>
 * date : 2019/3/20 21:01
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void startRead(View view) {
        read("css.epub");
    }

    public void startPinYinRead(View view) {
        read("pinyin.epub");
    }

    private void read(String name) {
        File file = openFile(name);
        if (file.exists()) {
            openBook(file.getAbsolutePath());
        } else {
            createFile(name, file);
        }
    }

    private void openBook(String path) {
        Log.e("test", "onScrollingFinished path " + path);
        //FBReader.openLog(null);
        FBReader.openBookActivity(MainActivity.this, path, new FBReader.ReaderLifeCycle() {
            @Override
            public void onCreate(FBReader context) {
                Log.e(" FBReader test", "onCreate");
            }

            @Override
            public void onStart(FBReader context) {
                Log.e("FBReader test", "onStart");
            }

            @Override
            public void onResume(FBReader context) {
                Log.e("FBReader test", "onResume");
            }

            @Override
            public void onPause(FBReader context) {
                Log.e("FBReader test", "onPause");
            }

            @Override
            public void onFinishing(FBReader context) {
                Log.e("FBReader test", "onFinishing");
            }


            @Override
            public boolean onBackClick(FBReader context) {
                Log.e("FBReader test", "onBackClick");
                context.finish();
                return false;
            }

            @Override
            public void onLoadStart(FBReader context) {
                Log.e("FBReader test", "onLoadStart");
            }

            @Override
            public void onLoadComplete(FBReader context) {
                Log.e("FBReader test", "onLoadComplete");
            }

            @Override
            public void toast(FBReader context, String msg) {
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                Log.e("FBReader test", "toast:" + msg);
            }

            @Override
            public void onTurnPage(TurnPageJudgeUtil.PageInfo info) {
                if (info != null) {
                    Log.e("test", "turn page:" + info.content + info.content.length()
                            + " start: " + info.startParagraph + "," + info.startElementIndex + "," + info.startCharIndex
                            + " end: " + info.endParagraph + "," + info.endElementIndex + "," + info.endCharIndex
                            + " read Time:" + info.readTime);
                }
            }

            @Override
            public void share(FBReader context) {
                Log.e("FBReader test", "share");
            }
        });
    }

    private File openFile(String name) {
        File file = new File(getCacheDir(), "book");
        if (!file.exists()) {
            file.mkdirs();
        }
        file = new File(file.getAbsolutePath() + File.separator + name);
        return file;
    }

    private void createFile(String assertName, final File file) {
        InputStream inputStream = null;
        try {
            inputStream = getAssets().open(assertName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (inputStream == null) {
            return;
        }
        final InputStream finalInputStream = inputStream;
        new Thread() {
            @Override
            public void run() {
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    byte[] buffer = new byte[1024];
                    int byteCount = 0;
                    while ((byteCount = finalInputStream.read(buffer)) != -1) {// 循环从输入流读取
                        // buffer字节
                        fos.write(buffer, 0, byteCount);// 将读取的输入流写入到输出流
                    }
                    fos.flush();// 刷新缓冲区
                    finalInputStream.close();
                    fos.close();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            openBook(file.getAbsolutePath());
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
