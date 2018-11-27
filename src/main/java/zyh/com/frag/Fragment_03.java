package zyh.com.frag;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.DefaultClientConnection;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

import zyh.com.day01.R;

public class Fragment_03 extends Fragment {

    public String urlString = "http://api.expoon.com/AppNews/getNewsList/type/1/p/1";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag03_item, container, false);

        //创建一个子线程
        new Thread(){
            @Override
            public void run() {
                super.run();
                //联网工具类,DefaultHttpClient封装了请求操作
                DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
                //初始化请求方式,并把接口传入
                HttpGet httpGet = new HttpGet(urlString);
                HttpPost httpPost = new HttpPost(urlString);

                try {
                    //执行httpget
                    HttpResponse execute = defaultHttpClient.execute(httpGet);
                    int statusCode = execute.getStatusLine().getStatusCode();
                    Log.e("zyh","statusCode:::"+statusCode);
                    if (statusCode == 200) {
                        //getEntity包含服务器返回的数据
                        HttpEntity entity = execute.getEntity();
                        //EntityUtils是专门处理操作entity中的数据的一个类；
                        String s = EntityUtils.toString(entity);
                        //Log.e("zyh","s:::"+s.toString());
                    } else {

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    //Log.e("zyh","e:"+e.toString());
                    String urlString = "http://api.expoon.com/AppNews/getNewsList/type/1/p/1";
                }

            }
        }.start();


        return view;
    }
}
