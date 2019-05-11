package com.example.song_xinbai.baidumap;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Application;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import java.util.List;

/**
 * @author Administrator
 * @des ${TODO}
 * @vresion $Rev$
 * @updateauthor $Author$
 * @updatedes ${TODO}
 */
public class showmap extends Activity {
    private MapView mMapView = null;
    public static int userID=-1;
    private double lon,lat;
    private BaiduMap mBaiduMap;
    //定位服务的客户端。宿主程序在客户端声明此类，并调用，目前只支持在主线程中启动
    public LocationClient mylocationClient = new LocationClient(this);
    //声明LocationClient类实例并配置定位参数
    public LocationClientOption mylocationOption = new LocationClientOption();
    public MyLocationListener myLocationListener = new MyLocationListener();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        setContentView(R.layout.activity_main);
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        initLocationOption();
        Button send_text=(Button)findViewById(R.id.send_text);
        send_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(lon);
                System.out.println(lat);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }
    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //Receive Location
//            StringBuffer sb = new StringBuffer(256);
//            sb.append("time : ");
//            sb.append(location.getTime());
//            sb.append("\nerror code : ");
//            sb.append(location.getLocType());
//            sb.append("\nlatitude : ");
//            sb.append(location.getLatitude());
//            sb.append("\nlontitude : ");
//            sb.append(location.getLongitude());
//            sb.append("\nradius : ");
//            sb.append(location.getRadius());

//            if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
//                sb.append("\nspeed : ");
//                sb.append(location.getSpeed());// 单位：公里每小时
//                sb.append("\nsatellite : ");
//                sb.append(location.getSatelliteNumber());
//                sb.append("\nheight : ");
//                sb.append(location.getAltitude());// 单位：米
//                sb.append("\ndirection : ");
//                sb.append(location.getDirection());// 单位度
//                sb.append("\naddr : ");
//                sb.append(location.getAddrStr());
//                sb.append("\ndescribe : ");
//                sb.append("gps定位成功");
//
//            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
//                sb.append("\naddr : ");
//                sb.append(location.getAddrStr());
//                //运营商信息
//                sb.append("\noperationers : ");
//                sb.append(location.getOperators());
//                sb.append("\ndescribe : ");
//                sb.append("网络定位成功");
//            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
//                sb.append("\ndescribe : ");
//                sb.append("离线定位成功，离线定位结果也是有效的");
//            } else if (location.getLocType() == BDLocation.TypeServerError) {
//                sb.append("\ndescribe : ");
//                sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
//            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
//                sb.append("\ndescribe : ");
//                sb.append("网络不同导致定位失败，请检查网络是否通畅");
//            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
//                sb.append("\ndescribe : ");
//                sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
//            }
//            sb.append("\nlocationdescribe : ");
//            sb.append(location.getLocationDescribe());// 位置语义化信息
//            List<Poi> list = location.getPoiList();// POI数据
//            if (list != null) {
//                sb.append("\npoilist size = : ");
//                sb.append(list.size());
//                for (Poi p : list) {
//                    sb.append("\npoi= : ");
//                    sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
//                }
//            }
            if (location.getLocType() == BDLocation.TypeGpsLocation)
            {
                lat = location.getLatitude();
                lon = location.getLongitude();
                System.out.println("GPS定位");
                System.out.println(location.getLocType());
            }
            else if (location.getLocType() == BDLocation.TypeNetWorkLocation)
            {
                lat = location.getLatitude();
                lon = location.getLongitude();
                System.out.println("网络定位");
                System.out.println(location.getLocType());
            } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                System.out.println("离线定位成功，离线定位结果也是有效的");
            } else if (location.getLocType() == BDLocation.TypeServerError) {
                System.out.println("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
            } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                System.out.println("网络不同导致定位失败，请检查网络是否通畅");
            } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                System.out.println("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
            }
//            else
//                System.out.println(location.getLocTypeDescription());

            //这个判断是为了防止每次定位都重新设置中心点和marker
//            if(isFirstLocation){
//                isFirstLocation = false;
//                setMarker();
//                setUserMapCenter();
//            }
//            Log.v("pcw","lat : " + lat+" lon : " + lon);
//            Log.i("BaiduLocationApiDem", sb.toString());
        }
    }
    private void initLocationOption() {
        mylocationClient.registerLocationListener(myLocationListener);
//        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
//        mylocationOption.setLocationMode(LocationMode.Hight_Accuracy);
        //可选，默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09ll;
//        mylocationOption.setCoorType("gcj02");
//        //可选，默认0，即仅定位一次，设置发起连续定位请求的间隔需要大于等于1000ms才是有效的
        mylocationOption.setScanSpan(1000);
        //可选，设置是否需要地址信息，默认不需要
        mylocationOption.setIsNeedAddress(false);
        //可选，设置是否需要地址描述
        mylocationOption.setIsNeedLocationDescribe(false);
        //可选，设置是否需要设备方向结果
        mylocationOption.setNeedDeviceDirect(false);
        //可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        mylocationOption.setLocationNotify(true);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        mylocationOption.setIgnoreKillProcess(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        mylocationOption.setIsNeedLocationDescribe(false);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        mylocationOption.setIsNeedLocationPoiList(false);
        //可选，默认false，设置是否收集CRASH信息，默认收集
        mylocationOption.SetIgnoreCacheException(false);
        //可选，默认false，设置是否开启Gps定位
        mylocationOption.setOpenGps(true);
        //可选，默认false，设置定位时是否需要海拔信息，默认不需要，除基础定位版本都可用
        mylocationOption.setIsNeedAltitude(false);
        //设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者，该模式下开发者无需再关心定位间隔是多少，定位SDK本身发现位置变化就会及时回调给开发者
        mylocationOption.setOpenAutoNotifyMode();
        //设置打开自动回调位置模式，该开关打开后，期间只要定位SDK检测到位置变化就会主动回调给开发者
        mylocationOption.setOpenAutoNotifyMode(3000,1, LocationClientOption.LOC_SENSITIVITY_HIGHT);
        //需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        mylocationClient.setLocOption(mylocationOption);
        //开始定位
        mylocationClient.start();
    }
}
