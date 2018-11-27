package zyh.com.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

public class NetUtil {
    public static String getJson(String jsonUril) {

        try {
            //统一定位符
            URL url = new URL(jsonUril);
            //我们使用的是http协议,所以强转成HttpURLConnection;
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            //获取状态码,判断是否请求成功
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == 200) {
                //成功后读取数据
                InputStream stream = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(stream);
                BufferedReader bufferedReader = new BufferedReader(reader);

                String temp="";
                //自动拼接方法
                StringBuilder stringBuilder = new StringBuilder();

                while ((temp=bufferedReader.readLine())!=null) {

                    stringBuilder.append(temp);
                }

                return stringBuilder.toString();
            } else {
                //失败打印日志
                Log.e("wzq","请求json失败,状态码:"+responseCode);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static Bitmap getBimp(String jsonBimp) {

        try {
            //定义统一定位符
            URL url = new URL(jsonBimp);
            //使用http协议.强转
            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
            int responseCode = urlConnection.getResponseCode();
            //判断相应码是否正确
            if (responseCode == 200) {
                //成功后读取数据
                InputStream stream = urlConnection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(stream);
                return bitmap;
            } else {
                Log.e("wzq","请求失败,状态码"+responseCode);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
