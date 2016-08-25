package lvy.so.picturescachedemo.imagecacheutils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

/**
 * @author gping  email: gping.vip@gmail.com
 * @date Created by 2016/8/24.15:35
 * @filename LoadImage.class
 * @description 加载图片
 * @TODO
 */
public class LoadImage {
    private static LoadImage instance;
    private DiskCacheUtils mLocalCache;
    private MemoryCacheUtils mMemoryCache;
    private NetWorkCacheUtils mNetWorkCache;

    private LoadImage(Context context) {
        mMemoryCache = new MemoryCacheUtils();
        mLocalCache = new DiskCacheUtils(context);
        mNetWorkCache = new NetWorkCacheUtils(mLocalCache, mMemoryCache);
    }

    /***
     * 单利模式保证LoadImage实例唯一性
     *
     * @return
     */
    public static LoadImage getLoadImageInstance(Context context) {
        if (instance == null) {
            synchronized (LoadImage.class) {
                if (instance == null) {
                    instance = new LoadImage(context);
                }
            }
        }
        return instance;
    }

    /***
     * 显示图片
     *
     * @param img
     * @param imgPath
     */
    public void showImg(ImageView img, String imgPath) {
        //TODO 先去缓存中读取图片
        Bitmap bitmap = null;
        bitmap = mMemoryCache.getMemoryBitMap(imgPath);
        if (bitmap != null && img.getTag().equals(imgPath)) {
            img.setImageBitmap(bitmap);
            return;
        }
        bitmap = mLocalCache.getDiskCacheBitMap(imgPath);
        if (bitmap != null && img.getTag().equals(imgPath)) {
            img.setImageBitmap(bitmap);
            return;
        }
        Log.e("------->>>哈哈哈", "hjashkdhkashdkashdkashdkasdj000000000000");
         mNetWorkCache.getNetWorkBitMap(imgPath,img);
        //TODO 加载失败
    }

}
