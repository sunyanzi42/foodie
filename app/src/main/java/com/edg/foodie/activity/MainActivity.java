package com.edg.foodie.activity;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.edg.foodie.R;
import com.edg.foodie.fragment.FindFragment;
import com.edg.foodie.fragment.HomeFragment;
import com.edg.foodie.fragment.OrderFragment;

public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    private TextView txt_waimai;
    private TextView txt_find;
    private TextView txt_order;
    private FrameLayout ly_content;

    private HomeFragment homeFragment;
    private FindFragment findFragment;
    private OrderFragment orderFragment;
    private android.app.FragmentManager fManager ;

    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView lvLeftMenu;
    private String[] lvs = {"账户信息", "我的余额", "我的收藏", "服务中心", "系统设置"};//可以放入String中，在拿取出来
    private ArrayAdapter arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fManager = getFragmentManager();
        bindViews();
        txt_waimai.performClick();   //模拟一次点击，既进去后选择第一项


        findViews(); //获取控件
        toolbar.setTitle("Foodie");//设置Toolbar标题
        toolbar.setTitleTextColor(Color.parseColor("#000000")); //设置标题颜色
        //创建返回键，并实现打开关/闭监听
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
                R.string.open_drawer, R.string.close_drawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        //设置菜单列表
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lvs);
        lvLeftMenu.setAdapter(arrayAdapter);

        lvLeftMenu.setOnItemClickListener(new DrawerItemClickListener());

//        setStatusBar();//设置沉浸式状态栏，5.0.1系统有点Bug


    }


    //UI组件初始化与事件绑定
    private void bindViews() {
        txt_waimai = (TextView) findViewById(R.id.txt_waimai);
        txt_find = (TextView) findViewById(R.id.txt_find);
        txt_order = (TextView) findViewById(R.id.txt_order);

        ly_content = (FrameLayout) findViewById(R.id.ly_content);

        txt_waimai.setOnClickListener(this);
        txt_find.setOnClickListener(this);
        txt_order.setOnClickListener(this);
    }


    //重置所有文本的选中状态
    private void setSelected(){
        txt_waimai.setSelected(false);
        txt_find.setSelected(false);
        txt_order.setSelected(false);
    }

    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(homeFragment != null)fragmentTransaction.hide(homeFragment);
        if(findFragment != null)fragmentTransaction.hide(findFragment);
        if(orderFragment != null)fragmentTransaction.hide(orderFragment);
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fTransaction = fManager.beginTransaction();
        hideAllFragment(fTransaction);
        switch (v.getId()){
            case R.id.txt_waimai:
                setSelected();
                txt_waimai.setSelected(true);
                if(homeFragment == null){
                    homeFragment = new HomeFragment();
                    fTransaction.add(R.id.ly_content,homeFragment);
                }else{
                    fTransaction.show(homeFragment);
                }
                break;
            case R.id.txt_find:
                setSelected();
                txt_find.setSelected(true);
                if(findFragment == null){
                    findFragment = new FindFragment();
                    fTransaction.add(R.id.ly_content,findFragment);
                }else{
                    fTransaction.show(findFragment);
                }
                break;
            case R.id.txt_order:
                setSelected();
                txt_order.setSelected(true);
                if(orderFragment == null){
                    orderFragment = new OrderFragment();
                    fTransaction.add(R.id.ly_content,orderFragment);
                }else{
                    fTransaction.show(orderFragment);
                }
                break;
        }
        fTransaction.commit();
    }

    /**
     * ListView上的Item点击事件
     *
     */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    /**
     * 切换Item点击显示的页面
     *
     * @param position
     */
    private void selectItem(int position) {
        switch (position){
            case 0:
                startActivity(new Intent(this,AccountActivity.class));
                break;
            case 1:
                startActivity(new Intent(this,WalletActivity.class));
                break;
            case 2:
                startActivity(new Intent(this,CollectActivity.class));
                break;
            case 3:
                startActivity(new Intent(this,ServiceCenterActivity.class));
                break;
            case 4:
                startActivity(new Intent(this,SettingActivity.class));
                break;
            default:
                break;
        }
    }

    /**
     * 获取控件
     */
    private void findViews() {
//        ivRunningMan = (ImageView) findViewById(R.id.iv_main);
        toolbar = (Toolbar) findViewById(R.id.tl_custom);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_left);
        lvLeftMenu = (ListView) findViewById(R.id.lv_left_menu);
    }


    /**
     * 设置沉浸式状态栏，需4.4版本以上
     */
//    protected void setStatusBar() {
//        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary));
//        StatusBarUtil.setColorForDrawerLayout(this, (DrawerLayout) findViewById(R.id.dl_left), getResources()
//                .getColor(R.color.colorPrimary));
//    }

    /**
     * 双击退出
     */
    long waitTime = 2000;
    long touchTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN && KeyEvent.KEYCODE_BACK == keyCode) {
            long currentTime = System.currentTimeMillis();
            if((currentTime-touchTime)>=waitTime) {
                //让Toast的显示时间和等待时间相同
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                touchTime = currentTime;
            }else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
