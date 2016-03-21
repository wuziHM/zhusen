package allenhu.pig.base;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Author：燕青 $ on 2016/3/18  17:47
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class BaseFragment extends Fragment {

    protected Activity activity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    protected void goActivity(Intent intent) {
        BaseActivity.goActivity(activity, intent);
    }

    /**
     * Activity跳转
     *
     * @param intent
     * @param resultCode
     */
    protected void goActivity(Intent intent, int resultCode) {
        BaseActivity.goActivity(activity, intent, resultCode);
    }

}
