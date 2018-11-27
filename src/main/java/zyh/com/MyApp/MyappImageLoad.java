package zyh.com.MyApp;

import android.app.Application;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;

public class MyappImageLoad extends Application {

    File file = new File(Environment.getExternalStorageDirectory()+"/"+"img");

    //（当应用启动的时候，会在任何activity、Service或者接收器被创建之前调用，所以在这里进行ImageLoader 的配置）
    // 当前类需要在清单配置文件里面的application下进行name属性的设置。
    @Override

    public void onCreate() {
        super.onCreate();

        //创建DisplayImageOptions
        DisplayImageOptions displaybuild = new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .build();

        //创建ImageLoaderConfiguration
        ImageLoaderConfiguration build = new ImageLoaderConfiguration.Builder(this)
                .threadPoolSize(3)//配置线程池的数量
                .diskCacheSize(50 * 1024 * 1024)//sd卡(本地)缓存的最大值
                .diskCache(new UnlimitedDiskCache(file))//设置缓存目录
                .writeDebugLogs()
                .build();

        //初始化
        ImageLoader.getInstance().init(build);

    }
}
