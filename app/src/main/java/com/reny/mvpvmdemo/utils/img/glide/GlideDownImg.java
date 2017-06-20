package com.reny.mvpvmdemo.utils.img.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.reny.mvpvmdemo.MyApplication;
import com.reny.mvpvmdemo.utils.img.glide.DownCallBack;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by admin on 2017/6/20.
 */

public class GlideDownImg {

    private static ExecutorService singleExecutor = null;
    private DownCallBack callBack;

    public void setCallBack(DownCallBack callBack) {
        this.callBack = callBack;
    }

    public void downLoad(final Context context, final String url, final String dir, final String picName) {
        if (singleExecutor == null) {
            singleExecutor = Executors.newSingleThreadExecutor();
        }
        singleExecutor.submit(new Runnable() {
            @Override
            public void run() {
                File file = null;
                try {
                    file = Glide.with(MyApplication.getContext())
                            .load(url)
                            .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                            .get();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (file != null) {
                        savePhotoToSDCard(file.getPath(), dir, picName);
                    } else {
                        onFailed();
                    }
                }
            }
        });
    }

    private void savePhotoToSDCard(String path, String dir, String picName) {
        try {
            File file = new File(path);
            if (!file.exists()) {
                onFailed();
                return;
            }
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, opts);
            opts.inJustDecodeBounds = false;
            Bitmap bitmap = BitmapFactory.decodeFile(path, opts);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int options = 100;
            bitmap.compress(Bitmap.CompressFormat.PNG, options, baos);
            File picDir = new File(dir);
            if (!picDir.exists()) {
                picDir.mkdir();
            }
            if(!picName.contains(".jpg") && !picName.contains(".png")){
                picName += ".jpg";
            }
            final File file2 = new File(picDir, picName);
            FileOutputStream fOut = new FileOutputStream(file2);
            fOut.write(baos.toByteArray());
            fOut.flush();
            fOut.close();
            baos.flush();
            baos.close();
            bitmap.recycle();
            bitmap = null;

            if(file2.length() == 0){
                onFailed();
                return;
            }
            if(null != callBack) {
                callBack.onSuccess(file2);
            }
        } catch (Exception e) {
            e.printStackTrace();
            onFailed();
        }
    }

    private void onFailed(){
        if(null != callBack){
            callBack.onFailed("下载图片失败");
        }
    }

}
