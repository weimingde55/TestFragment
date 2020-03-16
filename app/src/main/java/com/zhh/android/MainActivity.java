package com.zhh.android;

import android.os.Bundle;

import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends FragmentActivity {
    //  单选框布局
    private RadioGroup radioGroup;
    //  单选框1
    private RadioButton rbProject;
    //  单选框2
    private RadioButton rbData;
    //  单选框3
    private RadioButton rbMine;
    //  fragment标记
    public static final String fragment1Tag = "fragment1";
    public static final String fragment2Tag = "fragment2";
    public static final String fragment3Tag = "fragment3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initOnclick();
    }

    public void initView() {
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        rbProject = (RadioButton) findViewById(R.id.rbProject);
        rbData = (RadioButton) findViewById(R.id.rbData);
        rbMine = (RadioButton) findViewById(R.id.rbMine);
        firstShow();
    }


    public void initOnclick() {
//      单选框的点击事件，判断哪一个选中
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//      用对应的标记在内存中找对应的Fragment对象
                Fragment blankFragment1 = fragmentManager.findFragmentByTag(fragment1Tag);
                Fragment blankFragment2 = fragmentManager.findFragmentByTag(fragment2Tag);
                Fragment blankFragment3 = fragmentManager.findFragmentByTag(fragment3Tag);


                if (blankFragment1 != null) {
                    fragmentTransaction.hide(blankFragment1);
                }
                if (blankFragment2 != null) {
                    fragmentTransaction.hide(blankFragment2);
                }
                if (blankFragment3 != null) {
                    fragmentTransaction.hide(blankFragment3);
                }

                switch (checkedId) {
                    case R.id.rbProject:
                        Log.e("111", "button1");
                        if (blankFragment1 == null) {
                            blankFragment1 = new HomeFragment();
                            fragmentTransaction.add(R.id.activity_main_framlayout, blankFragment1, fragment1Tag);

                        } else {
                            fragmentTransaction.show(blankFragment1);

                        }
                        break;
                    case R.id.rbData:
                        if (blankFragment2 == null) {
                            blankFragment2 = new DataFragment();
                            fragmentTransaction.add(R.id.activity_main_framlayout, blankFragment2, fragment2Tag);

                        } else {
                            fragmentTransaction.show(blankFragment2);

                        }
                        break;
                    case R.id.rbMine:
                        if (blankFragment3 == null) {
                            blankFragment3 = new AppMineFragment();
                            fragmentTransaction.add(R.id.activity_main_framlayout, blankFragment3, fragment3Tag);
                        } else {
                            fragmentTransaction.show(blankFragment3);
                        }
                        break;
                }
                fragmentTransaction.commit();
            }
        });

    }

    /**
     * 初始化时显示的fragment
     */
    private void firstShow() {
        //实例化fragment对象
        HomeFragment blankFragment1 = new HomeFragment();
//              获取fragment管理器,并显示fragment,传入一个记号
        getSupportFragmentManager().beginTransaction().add(R.id.activity_main_framlayout, blankFragment1, fragment1Tag).commit();
    }

}
