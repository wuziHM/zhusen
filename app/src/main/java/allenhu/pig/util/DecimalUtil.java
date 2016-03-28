package allenhu.pig.util;

import android.text.TextUtils;

import java.text.DecimalFormat;

/**
 * Author：燕青 $ on 2016/3/22  11:06
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class DecimalUtil {
    public static String formatFloat(float f) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        return  decimalFormat.format(f);//format 返回的是字符串
    }

    public static String formatFloat(double d) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        return  decimalFormat.format(d);//format 返回的是字符串
    }

    public static boolean isRightFloat(String s){
        if(TextUtils.isEmpty(s)||s.startsWith(".")){
            return false;
        }else if(Float.parseFloat(s)<=0){
            return false;
        }
        return true;
    }
}
