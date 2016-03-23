package allenhu.pig.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author：燕青 $ on 2016/3/23  14:54
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class DateUtile {

    public static String getDate(Date date){
        DateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm");
        return format.format(date);
    }

}
