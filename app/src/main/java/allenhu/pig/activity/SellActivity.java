package allenhu.pig.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;

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
    private SubjectForPig subjectPig;

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
        subjectPig = new SubjectForPig();
        view2.registerSubject(subjectPig);

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
     * <p/>
     * 同时也对数据库进行更新
     *
     * @param pigs
     */
    public void setChanged(List<Pig> pigs) {
        if (AppUtil.isAvailable(pigs)) {
            subjectPig.setPigList(pigs);
            if (AppUtil.isAvailable(record)) {
                double weight = 0;
                double income = 0;
                for (Pig pig : pigs) {
                    weight = pig.getWeightUnit() == 0 ? ArithUtil.add(weight, pig.getWeight() * 2) : ArithUtil.add(weight, pig.getWeight());
                    income = ArithUtil.add(pig.getMoney(), income);
                }
                record.setIncome(income);
                record.setWeight(weight);
                record.setCount(pigs.size());
                if (!AppUtil.isAvailable(recordDao)) {
                    recordDao = new PigsRecordDao(SellActivity.this);
                }
                recordDao.updateRecord(record);
            }
        }
    }


    public PigsRecord getRecord() {
        return record;
    }
}
