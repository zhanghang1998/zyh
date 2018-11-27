package zyh.com.day01;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.SupportActionModeWrapper;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

import zyh.com.adapter.MyAdapterfrag;
import zyh.com.frag.Fragment_01;
import zyh.com.frag.Fragment_02;
import zyh.com.frag.Fragment_03;

public class MainActivity extends FragmentActivity {

    private ViewPager viewPager;
    private ArrayList<Fragment> list;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取控件
        viewPager = findViewById(R.id.viewPager_frag);
        radioGroup = findViewById(R.id.radioGroup_frag);
        //获取fragment
        Fragment_01 fragment_01 = new Fragment_01();
        Fragment_02 fragment_02 = new Fragment_02();
        Fragment_03 fragment_03 = new Fragment_03();
        //创建集合存放fragment
        list = new ArrayList<>();
        list.add(fragment_01);
        list.add(fragment_02);
        list.add(fragment_03);
        //获取管理器
        FragmentManager manager = getSupportFragmentManager();
        //适配器
        MyAdapterfrag myAdapterfrag = new MyAdapterfrag(manager, list);
        viewPager.setAdapter(myAdapterfrag);

        //按钮监听
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton_shou:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.radioButton_vedio:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.radioButton_my:
                        viewPager.setCurrentItem(2);
                        break;

                }


            }
        });//按钮end

    }
}
