package lvy.so.picturescachedemo.imagecacheutils;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
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
    private LocalCacheUtils mLocalCache;
    private MemoryCacheUtils mMemoryCache;
    private NetWorkCacheUtils mNetWorkCache;

    private LoadImage() {
        mMemoryCache = new MemoryCacheUtils();
        mLocalCache = new LocalCacheUtils();
        mNetWorkCache = new NetWorkCacheUtils(mLocalCache, mMemoryCache);

    }

    /***
     * 单利模式保证LoadImage实例唯一性
     *
     * @return
     */
    public static LoadImage getLoadImageInstance() {
        if (instance == null) {
            synchronized (LoadImage.class) {
                if (instance == null) {
                    instance = new LoadImage();
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
        if (bitmap != null) {
            img.setImageBitmap(bitmap);
            return;
        }
        bitmap = mLocalCache.getLocalBitMap(imgPath);
        if(bitmap != null ) {
            img.setImageBitmap(bitmap);
            return;
        }
        bitmap = mNetWorkCache.getNetWorkBitMap(imgPath);
        if(bitmap != null ) {
            img.setImageBitmap(bitmap);
            return;
        }

        //TODO 加载失败
    }

}
