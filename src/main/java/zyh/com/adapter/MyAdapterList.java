package zyh.com.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.ArrayList;

import zyh.com.bean.JsonBean;
import zyh.com.day01.R;

public class MyAdapterList extends BaseAdapter {

    private Context context;
    private ArrayList<JsonBean.DataBean> list;

    public MyAdapterList(Context context, ArrayList<JsonBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //优化
        HandView handView = null;
        if (convertView == null) {
            handView = new HandView();
            convertView = View.inflate(context, R.layout.list_item, null);
            //获取控件
            handView.text1 = convertView.findViewById(R.id.textView_item01);
            handView.text2 = convertView.findViewById(R.id.textView02);
            handView.image1 = convertView.findViewById(R.id.imageView_item);

            convertView.setTag(handView);
        } else {
            handView = (HandView) convertView.getTag();
        }
        //获取一条信息
        JsonBean.DataBean dataBean = list.get(position);
        //给控件赋值
        handView.text1.setText(dataBean.getNews_title());
        handView.text2.setText(dataBean.getNews_summary());

        //创建DisplayImageOptions对象进行相关的配置
        DisplayImageOptions build = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_launcher_background)//下载期间显示的图片
                .showImageOnFail(R.drawable.ic_launcher_background)//设置图片为空和错误时显示的图片
                .showImageForEmptyUri(R.drawable.ic_launcher_background)//加载或解码错误时显示的图片
                .cacheInMemory(true)//设置下载图片是否下载到缓存中
                .cacheOnDisk(true)//设置图片是否缓存到SD卡
                .displayer(new RoundedBitmapDisplayer(20))//设置圆角
                .build();

        //使用imageloader加载图片
        ImageLoader instance = ImageLoader.getInstance();
        instance.displayImage(dataBean.getPic_url(),handView.image1,build);// ImageLoader对象利用图片的URL加载图片

        return convertView;
    }

    //控件缓存类
    class HandView {
        TextView text1,text2;
        ImageView image1;
    }


}
