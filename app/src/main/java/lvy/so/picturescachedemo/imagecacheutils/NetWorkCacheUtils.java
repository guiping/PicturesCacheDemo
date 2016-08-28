package lvy.so.picturescachedemo.imagecacheutils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

import lvy.so.picturescachedemo.netutils.OkHttpUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author gping  email: gping.vip@gmail.com
 * @date Created by 2016/8/24.15:32
 * @filename NetWorkCacheUtils.class
 * @description 网络图片加载
 * @TODO
 */
public class NetWorkCacheUtils {
    private DiskCacheUtils mLocaCache;
    private MemoryCacheUtils mMemoryCache;
    private static final int pixelsWidth = 480, pixelsHeight = 800;   //默认手机分辨率设置为 480 * 800  后边可以更改为自动获取

    public NetWorkCacheUtils(DiskCacheUtils localCache, MemoryCacheUtils memoryCache) {
        this.mLocaCache = localCache;
        this.mMemoryCache = memoryCache;
    }


    public void getNetWorkBitMap(final String imgPath, final ImageView img) {
        Log.e("-------", "====" + imgPath);
        //下载网络图片 然后返回 为了简单起见直接用OkHttp 下载了  主要是为了写三级缓存 先不管文件下载
        OkHttpUtils._downloadAsyn(imgPath, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //错误了不做任何处理
                Log.e("出错了-->", "--->>>>出错啦出错啦");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    InputStream is = response.body().byteStream();
                    if (is == null) {
                        return;
                    }
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;   //设置为true 先不把图片读到内存中
                    BitmapFactory.decodeFile(imgPath, options);
                    //获取图片原始的宽高
                    int width = options.outWidth;
                    int height = options.outHeight;
                    int inSampleSize = 1;
                    if (height > pixelsHeight || width > pixelsWidth) {
                        int heightRatio = Math.round((float) height / (float) pixelsHeight);
                        int widthRatio = Math.round((float) width / (float) pixelsWidth);
                        inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
                    }
                    options.inSampleSize = inSampleSize;
                    options.inJustDecodeBounds = false;
                    options.inSampleSize = 2;  //将宽度和高度 压缩成原来的1/2
                    options.inPreferredConfig = Bitmap.Config.ARGB_4444;
                    final Bitmap bitmap = BitmapFactory.decodeStream(is, null, options);
                    if (bitmap != null) {
                        mLocaCache.addBitmapToCache(imgPath, bitmap);
                        mMemoryCache.setMemoryBitMap(imgPath, bitmap);

                        if (img.getTag().equals(imgPath)) {  //避免加载图片错位
                            img.post(new Runnable() {
                                @Override
                                public void run() {
                                    img.setImageBitmap(bitmap);
                                }
                            });
                        } else {
                            img.post(new Runnable() {
                                @Override
                                public void run() {
                                    img.setBackgroundColor(Color.parseColor("#FF80AB"));
                                }
                            });
                        }
                    }
                }
            }
        });
    }


}
