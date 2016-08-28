package lvy.so.picturescachedemo.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by ping on 2016-08-25.
 */
public class LoadImageUtils {
    private LoadImageUtils() {
    }

    /**
     * 获取App版本号
     *
     * @param context
     * @return
     */
    public static int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    /**
     * 创建一个独特的指定应用程序缓存目录的子目录。试图使用外部，但如果没有安装，倒回到内部存储。
     *
     * @param context
     * @param uniqueName
     * @return
     */
    public static File getDiskDir(Context context, String uniqueName) {
        // 检查是否安装或存储介质是内置的，如果是这样的话，尽量使用外部高速缓存目录，否则使用内部缓存目录
        String diskDirPath = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()
                ? Environment.getExternalStorageDirectory().getPath() : Environment.getDataDirectory().getPath();
        return new File(diskDirPath + File.separator + uniqueName);
    }

    public static String getFileMD5(String info) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(info.getBytes("UTF-8"));
            byte[] encryption = md5.digest();

            StringBuffer strBuf = new StringBuffer();
            for (int i = 0; i < encryption.length; i++) {
                if (Integer.toHexString(0xff & encryption[i]).length() == 1) {
                    strBuf.append("0").append(Integer.toHexString(0xff & encryption[i]));
                } else {
                    strBuf.append(Integer.toHexString(0xff & encryption[i]));
                }
            }

            return strBuf.toString();
        } catch (NoSuchAlgorithmException e) {
            return "";
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}
