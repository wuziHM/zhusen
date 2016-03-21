package allenhu.pig.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;

import java.util.List;

import allenhu.pig.base.BaseFragment;

/**
 * Author：燕青 $ on 2016/3/18  15:47
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class MyPagerAdapter extends FragmentStatePagerAdapter {
    private List<BaseFragment> mViewList;
    private List<String> mTitle;

    public MyPagerAdapter(FragmentManager fm, List<BaseFragment> mViewList, List<String> mTitles) {
        super(fm);
        this.mViewList = mViewList;
        this.mTitle = mTitles;
    }

    @Override
    public Fragment getItem(int position) {
        return mViewList.get(position);
    }

    @Override
    public int getCount() {
        return mViewList.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle.get(position);
    }
}
