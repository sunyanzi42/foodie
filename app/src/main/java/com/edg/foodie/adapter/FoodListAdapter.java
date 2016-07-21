package com.edg.foodie.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.edg.foodie.R;
import com.edg.foodie.bean.Food;
import com.edg.foodie.utils.UIUtils;

import java.util.List;

/**
 * 作者：Aller  2016/7/21 08:50
 * <p/>
 * 邮箱：1105894953@qq.com
 * <p/>
 * 描述：
 */
public class FoodListAdapter extends BaseAdapter {

    private List<Food> datas;

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;

    }

}
