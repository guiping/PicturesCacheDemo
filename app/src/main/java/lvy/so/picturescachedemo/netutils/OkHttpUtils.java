package lvy.so.picturescachedemo.netutils;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @author gping  email: gping.vip@gmail.com
 * @date Created by 2016/8/24.16:14
 * @filename OkHttpUtils.class
 * @description
 * @TODO
 */
public class OkHttpUtils {
    private OkHttpUtils() {
    }

    private static OkHttpClient okHttpClient = new OkHttpClient();

    public static final String URL_PATH = "http://gank.io/api/data/福利/300/1";
    private static final MediaType JSON = MediaType.parse("application/json;chartset=utf-8"); // post

    /**
     * 异步下载文件
     */
    public static void _downloadAsyn(String url, Callback callback) {
        final Request request = new Request.Builder().url(url).build();
        final Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

    /**
     * 异步Post请求
     */
    public static void _asynchronousPost(String url, String json, Callback callback) {
        RequestBody requestBody = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        Call call = okHttpClient.newCall(request);

        call.enqueue(callback);
    }

    /***
     * 异步get请求
     * @param url
     * @param callback
     */
    public static void _asynchronousGet(String url, Callback callback) {
        Request request = new Request.Builder().url(url).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }
}
