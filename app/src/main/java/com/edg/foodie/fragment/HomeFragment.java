package com.edg.foodie.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.edg.foodie.R;
import com.edg.foodie.adapter.FoodListAdapter;
import com.edg.foodie.adapter.HeaderAdapter;
import com.edg.foodie.controller.BoutiqueActivity;
import com.edg.foodie.controller.ConvenienceStoreActivity;
import com.edg.foodie.controller.NewStoreActivity;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;


public class HomeFragment extends Fragment{

    private RollPagerView mRollViewPager;
    private Button btn_boutique;
    private Button btn_convenience;
    private Button btn_new_store;
    private Button btn_HttpTest;

    private ListView lv_food_list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mRollViewPager = (RollPagerView)view.findViewById(R.id.roll_view_pager);
        btn_boutique = (Button)view.findViewById(R.id.btn_boutique);
        btn_convenience = (Button)view.findViewById(R.id.btn_convenience);
        btn_new_store = (Button)view.findViewById(R.id.btn_new_store);

        btn_HttpTest = (Button)view.findViewById(R.id.btn_HttpTest) ;

//        //ListView的布局设置
//        lv_food_list = (ListView)view.findViewById(R.id.lv_food_list);
//        lv_food_list.setAdapter(new FoodListAdapter());


        btn_boutique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), BoutiqueActivity.class));
            }
        });

        btn_convenience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ConvenienceStoreActivity.class));
            }
        });

        btn_new_store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),NewStoreActivity.class));
            }
        });


        btn_HttpTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestParams params = new RequestParams("http://www.tngou.net/api/cook/list");
                //params.setSslSocketFactory(...); // 设置ssl
                params.addQueryStringParameter("wd", "xUtils");
                x.http().get(params, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();//已经拿到数据

                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        //Toast.makeText(x.app(), ex.getMessage(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {
                        //Toast.makeText(x.app(), "cancelled", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFinished() {

                    }
                });
            }
        });



        //getDataFromServer() ;



        //设置播放时间间隔
        mRollViewPager.setPlayDelay(3000);
        //设置透明度
        mRollViewPager.setAnimationDurtion(500);
        //设置适配器
        mRollViewPager.setAdapter(new HeaderAdapter());
        mRollViewPager.setHintView(new ColorPointHintView(getActivity(), Color.YELLOW, Color.WHITE));
        return view;
    }

    /**
     * 从服务器获取数据
     */
//    private void getDataFromServer() {
//        //Toast.makeText(getActivity(),"走到这了吗。。。。。。。。。。。",Toast.LENGTH_LONG).show();
//
//        RequestParams params = new RequestParams("http://www.tngou.net/api/cook/list");
//        //params.setSslSocketFactory(...); // 设置ssl
//        params.addQueryStringParameter("wd", "xUtils");
//        x.http().get(params,new Callback.CommonCallback<String>(){
//
//
//            @Override
//            public void onSuccess(String result) {
//
//                Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//                //Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//                //Toast.makeText(getActivity(),"走到这了吗。。。。。cancall。。。。。。",Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onFinished() {
//                Toast.makeText(getActivity(),"走到这了吗。。finish。。。。。。。。。",Toast.LENGTH_LONG).show();
//            }
//        });
//    }



}

