package allenhu.pig.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by AllenHu on 2016/2/15.
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏

    }




    /**
     * @param context
     * @param intent
     * @param requestCode >=0 使用startActivityForResult请求
     */
    public static void goActivity(Context context, Intent intent, int requestCode) {
        if (intent != null) {
            if (requestCode < 0) {
                context.startActivity(intent);
            } else {
                ((Activity) context).startActivityForResult(intent, requestCode);
            }
        }
    }


    /**
     * @param context
     * @param intent
     */
    public static void goActivity(Context context, Intent intent) {
        if (intent != null) {
            goActivity(context,intent,-1);
        }
    }
}
