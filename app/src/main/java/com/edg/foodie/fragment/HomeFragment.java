package com.edg.foodie.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.edg.foodie.R;
//import com.edg.foodie.adapter.FoodListAdapter;
import com.edg.foodie.adapter.HeaderAdapter;
import com.edg.foodie.bean.Food;
import com.edg.foodie.controller.BoutiqueActivity;
import com.edg.foodie.controller.ConvenienceStoreActivity;
import com.edg.foodie.controller.NewStoreActivity;
import com.edg.foodie.utils.UIUtils;
import com.edg.foodie.view.ListViewForScrollView;
import com.google.gson.Gson;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment{

    private Food food;

    private RollPagerView mRollViewPager;
    private Button btn_boutique;
    private Button btn_convenience;
    private Button btn_new_store;

    private ListView lv_food_list;

//    List<Food> mFoodList;
     List<Food> foodInfos = new ArrayList<Food>();//声明全局的才有效果
    private RelativeLayout rl_top;
    private LinearLayout ll_centerButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mRollViewPager = (RollPagerView)view.findViewById(R.id.roll_view_pager);
        btn_boutique = (Button)view.findViewById(R.id.btn_boutique);
        btn_convenience = (Button)view.findViewById(R.id.btn_convenience);
        btn_new_store = (Button)view.findViewById(R.id.btn_new_store);

        rl_top = (RelativeLayout) view.findViewById(R.id.rl_top);
        ll_centerButton = (LinearLayout)view.findViewById(R.id.ll_centerButton);


        //ListView的布局设置
        lv_food_list = (ListView)view.findViewById(R.id.lv_food_list);
        lv_food_list.setAdapter(new FoodAdapter());

        //ListView滑动的监听
        lv_food_list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {

                    case SCROLL_STATE_FLING:
                        //滑动中隐藏view
//                        Toast.makeText(UIUtils.getContext(),"滑动了吗？？？？？？？？？？？？？",Toast.LENGTH_SHORT).show();
//                        rl_top.setVisibility(View.GONE);
//                        ll_centerButton.setVisibility(View.GONE);
                        break;

                    case SCROLL_STATE_IDLE:
                        //空闲的时候显示
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });


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
                startActivity(new Intent(getActivity(), NewStoreActivity.class));
            }
        });


        getDataFromServer() ;


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
    private void getDataFromServer() {

        RequestParams params = new RequestParams("http://www.tngou.net/api/cook/list");
        //params.setSslSocketFactory(...); // 设置ssl
        params.addQueryStringParameter("wd", "xUtils");
        x.http().get(params,new Callback.CommonCallback<String>(){


            @Override
            public void onSuccess(String result) {

                Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();//拿到数据

                parseData(result);//解析数据


            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }

    /**
     * 解析网络数据
     * @param result
     */
    public List<Food> parseData(String result) {

//        foodInfos = new ArrayList<Food>();
        try {
            JSONObject jsonObject=new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("tngou");
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObj = jsonArray.getJSONObject(i);
                int id=jsonObj.getInt("id");
                String name = jsonObj.getString("name");
                String img=jsonObj.getString("img");
                String count = jsonObj.getString("count");

                Food info=new Food(id, name,img,count);
                foodInfos.add(info);
            }

//            for (Food food: foodInfos){
//                Log.d("解析成功",food.toString());
//            }
            return foodInfos;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private class FoodAdapter extends BaseAdapter{

    @Override
    public int getCount() {
        return foodInfos.size();
    }

    @Override
    public Food getItem(int position) {
        return foodInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder holder;
        if (convertView == null) {
            view = View.inflate(UIUtils.getContext(), R.layout.list_food_item, null);
            holder=new ViewHolder();
            holder.iv_food = (ImageView) view.findViewById(R.id.iv_food);
            holder.tv_name = (TextView) view.findViewById(R.id.tv_name);
            holder.tv_count = (TextView) view.findViewById(R.id.tv_count);
            view.setTag(holder);
        }else{
            view = convertView ;
            holder = (ViewHolder)view.getTag();
        }
        Food foodData = foodInfos.get(position);
        holder.tv_name.setText(foodData.getName());// 设置美食的名字
        holder.tv_count.setText("收藏数：" + foodData.getCount()+" 次");
//        String iconUrl = foodData.getImg();
//        holder.iv_food.set
        return view;

    }
}
    static class ViewHolder{
        ImageView iv_food;
        TextView tv_name,tv_count;
    }


}

