package zyh.com.frag;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import zyh.com.day01.R;
import zyh.com.util.NetUtil;

public class Fragment_02 extends Fragment implements View.OnClickListener {

    String jsonUril="http://api.expoon.com/AppNews/getNewsList/type/1/p/1";
    String jsonBimp="http://img.my.csdn.net/uploads/201407/26/1406383265_8550.jpg";
    private TextView textView;
    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag02_item, container, false);
        //获取控件
        Button button1 = view.findViewById(R.id.button_json);
        Button button2 = view.findViewById(R.id.button_bimp);
        textView = view.findViewById(R.id.textView_json);
        imageView = view.findViewById(R.id.imageView_tupian);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_json:
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        String json = NetUtil.getJson(jsonUril);
                        //准备handler
                        Message message = handler.obtainMessage();
                        message.what = 1;
                        message.obj = json;
                        //发送handler消息
                        handler.sendMessage(message);
                    }
                }.start();
                break;
            case R.id.button_bimp:
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        Bitmap bimp = NetUtil.getBimp(jsonBimp);
                        //准备handler
                        Message message = handler.obtainMessage();
                        message.what=2;
                        message.obj=bimp;
                        //发送handler消息
                        handler.sendMessage(message);
                    }
                }.start();
                break;
        }
    }//点击事件end

    //创建handler
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    String obj = (String) msg.obj;
                    textView.setText(obj);
                    break;
                case 2:
                    Bitmap bitmap= (Bitmap) msg.obj;
                    imageView.setImageBitmap(bitmap);
                    break;
            }

        }
    };//handler

}
