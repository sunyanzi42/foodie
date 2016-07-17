package com.edg.foodie.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edg.foodie.R;


public class HomeFragment extends Fragment {

//    private String content;
//    public HomeFragment(String content) {
//        this.content = content;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
//        TextView txt_content = (TextView) view.findViewById(R.id.txt_content);
//        txt_content.setText(content);
        return view;
    }
}
