package zyh.com.frag;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import zyh.com.adapter.MyAdapterList;
import zyh.com.bean.JsonBean;
import zyh.com.day01.R;
import zyh.com.util.NetUtil;

public class Fragment_01 extends Fragment {

    String jsonUril = "http://api.expoon.com/AppNews/getNewsList/type/1/p/1";
    private ArrayList<JsonBean.DataBean> list = new ArrayList<JsonBean.DataBean>();
    private ListView listView;
    private MyAdapterList myAdapterList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag01_item, container, false);
        //获取控件
        listView = (ListView) view.findViewById(R.id.listView_list);
        /**
         * 这行代码要在主线程调用;,execute里面的参数是接口地址；让async请求网络,并更新UI;
         * 1 不用开子线程,
         * 2 不用写handler;
         * 因为asynctask底层原理就是用的Thread+handler帮我们把事情做了;
         *
         */
        //创建
        new MyAsycTast().execute(jsonUril);

        //适配器创建
        myAdapterList = new MyAdapterList(getActivity(), list);
        listView.setAdapter(myAdapterList);

        return view;
    }

    /**
     * 第一泛型是execute方法传入参数的类型
     * 第三个泛型是:子线程处理完后返回的数据类型;
     */
    //创建
    public class MyAsycTast extends AsyncTask<String, Void, String> {

        //子线程,可以请求网络
        @Override
        protected String doInBackground(String... strings) {
            //调用工具类的方法
            String netjson = NetUtil.getJson(strings[0]);

            return netjson;
        }

        //主线程,doInBackground方法的返回数据
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //创建gson
            Gson gson = new Gson();
            //进行解析
            JsonBean jsonBean = gson.fromJson(s, JsonBean.class);
            //获取要展示的信息
            List<JsonBean.DataBean> data = jsonBean.getData();
            //把一个集合的数据存到另一个集合里
            list.addAll(data);

            myAdapterList.notifyDataSetChanged();
        }
    }

}
