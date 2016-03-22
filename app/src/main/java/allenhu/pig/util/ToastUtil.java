package allenhu.pig.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Author：燕青 $ on 2016/3/21  17:45
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class ToastUtil {

    /**
     * 土司打印消息
     *
     * @param context
     * @param msg
     */
    public static void toast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT);
    }
    /**
     * 土司打印消息,长时间的消息
     *
     * @param context
     * @param msg
     */
    public static void toastLong(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG);
    }

}
