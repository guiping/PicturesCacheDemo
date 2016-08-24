package lvy.so.picturescachedemo.imagecacheutils;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import java.lang.ref.SoftReference;
import java.util.HashMap;

/**
 * @author gping  email: gping.vip@gmail.com
 * @date Created by 2016/8/24.15:33
 * @filename MemoryCacheUtils.class
 * @description 内存缓存
 * @TODO
 */
public class MemoryCacheUtils {
    private HashMap<String, Bitmap> memoryHashMap = new HashMap<>();  //强引用存储图片 浪费内存
    private HashMap<String, SoftReference<Bitmap>> softReferenceHashMap = new HashMap<>();   //用图片存储采取软引用 系统内存紧张的时候会回收
    private LruCache<String, Bitmap> mMemoryLruCache;

    public MemoryCacheUtils() {
        long maxMemory = Runtime.getRuntime().maxMemory() / 8;
        mMemoryLruCache = new LruCache<String, Bitmap>((int) maxMemory) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
    }

    /***
     * 读取缓存中的图片
     *
     * @param imgPath
     * @return
     */
    public Bitmap getMemoryBitMap(String imgPath) {
//        memoryHashMap.get(imgPath);
//        SoftReference<Bitmap> softReference = softReferenceHashMap.get(imgPath);
//        if (softReference != null) {
//            return softReference.get();
//        }
        Bitmap bitmap = mMemoryLruCache.get(imgPath);
        if (bitmap != null) {
            return bitmap;
        }
        return null;
    }

    /**
     * 缓存中存储图片
     *
     * @param imgPath
     * @param bitmap
     */
    public void setMemoryBitMap(String imgPath, Bitmap bitmap) {
//        memoryHashMap.put(imgPath, bitmap);
//        softReferenceHashMap.put(imgPath, new SoftReference(bitmap));
        mMemoryLruCache.put(imgPath, bitmap);
    }

}
