package com.example.administrator.myapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.AddressFragment;
import com.example.administrator.myapplication.FrdFragment;
import com.example.administrator.myapplication.LebenFragment;
import com.example.administrator.myapplication.PopWindow;

import com.example.administrator.myapplication.SettingFragment;


//我们使用的是android v4包下的fragment,这里必须要继承自FragmentActivity，而不是Activity
public class MainActivity extends FragmentActivity implements OnClickListener{
    //底部的4个导航控件
    private LinearLayout mTabLeben;
    private LinearLayout mTabFrd;
    private LinearLayout mTabAddress;
    private LinearLayout mTabSetting;
    //底部4个导航控件中的图片按钮
    private ImageButton mImgLeben;
    private ImageButton mImgFrd;
    private ImageButton mImgAddress;
    private ImageButton mImgSetting;
    private ImageButton mImgmenu;
    //初始化4个Fragment
    private Fragment tab01;
    private Fragment tab02;
    private Fragment tab03;
    private Fragment tab04;
    //再按一次退出
    private long firstTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();//初始化所有的view
        initEvents();
        setSelect(0);//默认显示Leben主界面
    }

    //添加按钮监听事件
    private void initEvents() {
        mTabLeben.setOnClickListener(this);
        mTabFrd.setOnClickListener(this);
        mTabAddress.setOnClickListener(this);
        mTabSetting.setOnClickListener(this);

    }

    private void initView() {
        mTabLeben = (LinearLayout)findViewById(R.id.id_tab_leben);
        mTabFrd = (LinearLayout)findViewById(R.id.id_tab_frd);
        mTabAddress = (LinearLayout)findViewById(R.id.id_tab_address);
        mTabSetting = (LinearLayout)findViewById(R.id.id_tab_setting);

        mImgLeben = (ImageButton)findViewById(R.id.id_tab_leben_img);
        mImgFrd = (ImageButton)findViewById(R.id.id_tab_frd_img);
        mImgAddress = (ImageButton)findViewById(R.id.id_tab_address_img);
        mImgSetting = (ImageButton)findViewById(R.id.id_tab_setting_img);

    }


    @Override
    public void onClick(View v) {
        resetImg();
        switch (v.getId()) {
            case R.id.id_tab_leben://当点击首页按钮时，切换图片为亮色，切换fragment为软件的首页
                setSelect(0);
                break;
            case R.id.id_tab_frd:
                setSelect(1);
                break;
            case R.id.id_tab_address:
                setSelect(2);
                break;
            case R.id.id_tab_setting:
                setSelect(3);
                break;
            case R.id.top_add:
                if(v.getId() == R.id.top_add){
                    PopWindow popWindow = new PopWindow(this);
                    popWindow.showPopupWindow(findViewById(R.id.top_add));
                }

            default:
                break;
        }

    }

    /*
     * 将图片设置为亮色的；切换显示内容的fragment
     * */
    private void setSelect(int i) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();//创建一个事务
        hideFragment(transaction);//我们先把所有的Fragment隐藏了，然后下面再开始处理具体要显示的Fragment
        switch (i) {
            case 0:
                if (tab01 == null) {
                    tab01 = new LebenFragment();
                    /*
                     * 将Fragment添加到活动中，public abstract FragmentTransaction add (int containerViewId, Fragment fragment)
                     *containerViewId即为Optional identifier of the container this fragment is to be placed in. If 0, it will not be placed in a container.
                     * */
                    transaction.add(R.id.id_content, tab01);//将首页的Fragment添加到Activity中
                }else {
                    transaction.show(tab01);
                }
                mImgLeben.setImageResource(R.drawable.leben_pressed);
                break;
            case 1:
                if (tab02 == null) {
                    tab02 = new FrdFragment();
                    transaction.add(R.id.id_content, tab02);
                }else {
                    transaction.show(tab02);
                }
                mImgFrd.setImageResource(R.drawable.find_frd_pressed);
                break;
            case 2:
                if (tab03 == null) {
                    tab03 = new AddressFragment();
                    transaction.add(R.id.id_content, tab03);
                }else {
                    transaction.show(tab03);
                }
                mImgAddress.setImageResource(R.drawable.address_pressed);
                break;
            case 3:
                if (tab04 == null) {
                    tab04 = new SettingFragment();
                    transaction.add(R.id.id_content, tab04);
                }else {
                    transaction.show(tab04);
                }
                mImgSetting.setImageResource(R.drawable.settings_pressed);
                break;

            default:
                break;
        }
        transaction.commit();//提交事务
    }

    /*
     * 隐藏所有的Fragment
     * */
    private void hideFragment(FragmentTransaction transaction) {
        if (tab01 != null) {
            transaction.hide(tab01);
        }
        if (tab02 != null) {
            transaction.hide(tab02);
        }
        if (tab03 != null) {
            transaction.hide(tab03);
        }
        if (tab04 != null) {
            transaction.hide(tab04);
        }

    }

    private void resetImg() {
        mImgLeben.setImageResource(R.drawable.leben_normal);
        mImgFrd.setImageResource(R.drawable.find_frd_normal);
        mImgAddress.setImageResource(R.drawable.address_normal);
        mImgSetting.setImageResource(R.drawable.settings_normal);
    }




    /**
     * 回退按钮两次退出
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        long secondTime = System.currentTimeMillis();

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (secondTime - firstTime < 2000) {
                System.exit(0);
            } else {
                Toast.makeText(getApplicationContext(), "再按一次退出Leben", Toast.LENGTH_SHORT).show();
                firstTime = System.currentTimeMillis();
            }

            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

}




