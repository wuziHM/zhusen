package allenhu.pig.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import allenhu.pig.R;
import allenhu.pig.adapter.MyPagerAdapter;
import allenhu.pig.base.BaseActivity;
import allenhu.pig.base.BaseFragment;
import allenhu.pig.bean.Pig;
import allenhu.pig.bean.SubjectForPig;
import allenhu.pig.fragment.NowFragment;
import allenhu.pig.fragment.TotalFragment;
import allenhu.pig.util.LogUtil;

/**
 * 卖猪的详情页
 */
public class SellActivity extends BaseActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private LayoutInflater mInflater;
    private List<String> mTitles = new ArrayList<>();
    private NowFragment view1;
    private TotalFragment view2;
    private List<BaseFragment> mViewList = new ArrayList<>();
    private SubjectForPig pig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
        initView();
    }

    private void initView() {


        mViewPager = (ViewPager) findViewById(R.id.vp_view);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);

        mInflater = LayoutInflater.from(this);
        view1 = new NowFragment();
        view2 = new TotalFragment();

        /**
         * 使用观察者模式，把TotalFragment的对象进行注册，
         *
         * 当NowFragment中发生变化的时候通知TotalFragment进行数据更新
         */
        pig = new SubjectForPig();
        view2.registerSubject(pig);

        mViewList.add(view1);
        mViewList.add(view2);

        mTitles.add("当前");
        mTitles.add("总和");

        //设置tab模式，当前为系统默认模式
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitles.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitles.get(1)));

        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), mViewList, mTitles);
        mViewPager.setAdapter(myPagerAdapter);
        //将TabLayout和ViewPager关联起来
        mTabLayout.setupWithViewPager(mViewPager);
        //将TabLayout和ViewPager关联起来
        mTabLayout.setTabsFromPagerAdapter(myPagerAdapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        view1.setPrice();
    }

    public void setChanged(List<Pig> pigs) {
        pig.setPigList(pigs);
    }
}
