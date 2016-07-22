//package com.edg.foodie.adapter;
//
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.edg.foodie.R;
//import com.edg.foodie.bean.Food;
//import com.edg.foodie.fragment.HomeFragment;
//import com.edg.foodie.utils.UIUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 作者：Aller  2016/7/21 08:50
// * <p/>
// * 邮箱：1105894953@qq.com
// * <p/>
// * 描述：
// */
//public class FoodListAdapter extends BaseAdapter {
//
//
//    List<Food> mFoodList;
//
//    @Override
//    public int getCount() {
//        return mFoodList.size();
//    }
//
//    @Override
//    public Food getItem(int position) {
//        return mFoodList.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View view;
//        ViewHolder holder;
//        if (convertView == null) {
//            view = View.inflate(UIUtils.getContext(), R.layout.list_food_item, null);
//            holder=new ViewHolder();
//            holder.iv_food = (ImageView) view.findViewById(R.id.iv_food);
//            holder.tv_name = (TextView) view.findViewById(R.id.tv_name);
//            holder.tv_count = (TextView) view.findViewById(R.id.tv_count);
//            view.setTag(holder);
//        }else{
//            view = convertView ;
//            holder = (ViewHolder)view.getTag();
//        }
//        Food foodData = mFoodList.get(position);
//        holder.tv_name.setText(foodData.getName());// 设置美食的名字
//        holder.tv_count.setText(foodData.getCount());
//        String iconUrl = foodData.getImg();
////        holder.iv_food.set
//        return view;
//
//    }
//
//    static class ViewHolder{
//        ImageView iv_food;
//        TextView tv_name,tv_count;
//    }
//
//}
