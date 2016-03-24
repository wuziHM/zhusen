package allenhu.pig.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import allenhu.pig.R;
import allenhu.pig.adapter.MyPagerAdapter;
import allenhu.pig.base.BaseActivity;
import allenhu.pig.base.BaseFragment;
import allenhu.pig.bean.Pig;
import allenhu.pig.bean.SubjectForPig;
import allenhu.pig.bean.db.PigsRecord;
import allenhu.pig.db.PigsRecordDao;
import allenhu.pig.fragment.NowFragment;
import allenhu.pig.fragment.TotalFragment;
import allenhu.pig.util.AppUtil;
import allenhu.pig.util.ArithUtil;
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

    // 定义一个变量，来标识是否退出
    private static boolean isExit = false;
    private PigsRecord record;
    private PigsRecordDao recordDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
        initView();
    }

    private void initView() {


        Intent intent = getIntent();
        // 获取该Intent所携带的数据
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                // 从bundle数据包中取出数据
                record = (PigsRecord) bundle.getSerializable("data");
            }
        }
//        record = (PigsRecord) getIntent().getExtras().getSerializable("data");

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

    /**
     * 在nowFragment页面中添加了猪头，然后就需要在TotalFragment中进行对应的更新，
     *
     * 同时也对数据库进行更新
     *
     * @param pigs
     */
    public void setChanged(List<Pig> pigs) {
        if (AppUtil.isAvailable(pigs)) {
            pig.setPigList(pigs);
            if (AppUtil.isAvailable(record)) {
                double weight = 0;
                double income = 0;
                for (Pig pig : pigs) {
                    weight = pig.getWeightUnit() == 0 ? ArithUtil.add(weight, pig.getWeight() * 2) : ArithUtil.add(weight, pig.getWeight());
                    income = ArithUtil.add(pig.getMoney(), income);
                }
                record.setIncome(income);
                record.setWeight(weight);
                if (!AppUtil.isAvailable(recordDao)) {
                    recordDao = new PigsRecordDao(SellActivity.this);
                }
                recordDao.updateRecord(record);
            }
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }


    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    public PigsRecord getRecord() {
        return record;
    }
}
