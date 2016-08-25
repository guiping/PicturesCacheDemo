package lvy.so.picturescachedemo.imagecacheutils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import lvy.so.picturescachedemo.utils.LoadImageUtils;
import okhttp3.internal.cache.DiskLruCache;

/**
 * @author gping  email: gping.vip@gmail.com
 * @date Created by 2016/8/24.15:33
 * @filename LocalCacheUtils.class
 * @description 磁盘缓存缓存
 * @TODO
 */
public class DiskCacheUtils {
    private final Object mDiskCacheLock = new Object();
    private static final int DISK_CACHE_SIZE = 1024 * 1024 * 10; // 10MB
    private static final String DISK_CACHE_SUBDIR = "DiskCache";
    private Context mContext;

    public DiskCacheUtils(Context context) {

    }

    /***
     * 获取内存中的图片
     *
     * @param imgPath
     * @return
     */
    public Bitmap getDiskCacheBitMap(String imgPath) {
        try {
            File file = LoadImageUtils.getDiskDir(mContext, DISK_CACHE_SUBDIR);

            if (!file.exists()) {
                file.exists();
            }

            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            if (bitmap != null) {
                return bitmap;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("-->>>路径000","====000" +e.getMessage());
        }

        return null;
    }

    /***
     * 把图片缓存在本地
     *
     * @param imgPath
     * @param bitMap
     */
    public void addBitmapToCache(String imgPath, Bitmap bitMap) {
        Log.e("----=====","=======addBitmapToCache");
        if (getDiskCacheBitMap(imgPath) != null) {
            return;
        }
        try {
            //TODO  应该判断是否有SD卡
            File file = LoadImageUtils.getDiskDir(mContext, DISK_CACHE_SUBDIR);
            if (!file.exists()) {
                file.mkdirs();
            }
//            File cacheFile = new File(file,imgPath);
            bitMap.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(file));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
