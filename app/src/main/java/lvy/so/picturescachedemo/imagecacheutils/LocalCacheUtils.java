package lvy.so.picturescachedemo.imagecacheutils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @author gping  email: gping.vip@gmail.com
 * @date Created by 2016/8/24.15:33
 * @filename LocalCacheUtils.class
 * @description SDCard 图片缓存
 * @TODO
 */
public class LocalCacheUtils {
    private static final String CACHE_IMAGE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + "/LocalCachePicture";

    /***
     * 获取内存中的图片
     *
     * @param imgPath
     * @return
     */
    public Bitmap getLocalBitMap(String imgPath) {

        try {
            File file = new File(CACHE_IMAGE_PATH, imgPath);
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
            if (bitmap != null) {
                return bitmap;
            } else {
                return null;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    /***
     * 把图片缓存在本地
     *
     * @param imgPath
     * @param bitMap
     */
    public void setLocalBitMap(String imgPath, Bitmap bitMap) {
        try {
            //TODO  应该判断是否有SD卡
            File file = new File(CACHE_IMAGE_PATH, imgPath);
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            bitMap.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
