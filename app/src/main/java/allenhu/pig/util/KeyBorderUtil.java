package allenhu.pig.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Author：燕青 $ on 2016/3/21  11:52
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class KeyBorderUtil {

    /**
     * 显示软键盘
     *
     * @param view
     * @param activity
     */
    public static void showKeyBorder(View view, Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (!imm.isActive())
            imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    /**
     * 隐藏软键盘
     *
     * @param view
     * @param activity
     */
    public static void hideKeyBorder(View view, Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive())
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 返回true则表示输入法打开，否则是输入法关闭
     *
     * @param activity
     * @return
     */
    /*public static boolean isShowing(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        return imm.isActive();//isOpen若返回true，则表示输入法打开
    }*/

}
