package allenhu.pig.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Author：燕青 $ on 2016/3/23  14:54
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class DateUtil {

    /**
     * 把date格式化成 yy-MM-dd HH:mm
     *
     * @param date
     * @return
     */
    public static String getDate(Date date) {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        DateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm");
        return format.format(date);
    }

    /**
     * 通过时间类型的long值转为String类型的日期
     *
     * @param date
     * @return
     */
    public static String getDate(Long date) {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        Date d = new Date(date);
        return getDate(d);
    }

    /**
     * 获取long型的date
     * @return
     */
    public static long getDate() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime().getTime();
    }
}
