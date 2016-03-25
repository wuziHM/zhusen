package allenhu.pig;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import allenhu.pig.bean.Pig;
import allenhu.pig.bean.db.PigsRecord;
import allenhu.pig.db.PigDao;
import allenhu.pig.db.PigsRecordDao;
import allenhu.pig.util.AppUtil;
import allenhu.pig.util.ArithUtil;
import allenhu.pig.util.DateUtil;
import allenhu.pig.util.LogUtil;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void testDb() {
        PigDao dao = new PigDao(getContext());
        List<Pig> pigs = dao.getAllPig();
        if (AppUtil.isAvailable(pigs)) {
            for (Pig p : pigs) {
                LogUtil.e(p.toString());
            }
        }
    }

    public void testFloat() {
        Double[] floats = {4326.75, 3749.89};
        float sum1 = 0;
        for (int i = 0; i < floats.length; i++) {
            sum1 = (float) ArithUtil.add(sum1, floats[i]);
        }
        LogUtil.e("sum1:" + sum1);

        float sum2 = (float) ArithUtil.add(4326.75d, 3749.89d);
        LogUtil.e("sum2:" + sum2);

    }

    public void testGetTime() {
        Calendar now;
        SimpleDateFormat fmt;

        now = Calendar.getInstance();
        fmt = new SimpleDateFormat("yy-MM-dd HH:mm");


//        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
//        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        Calendar calendar = Calendar.getInstance();
        LogUtil.e("hour  " + calendar.get(Calendar.HOUR));
        LogUtil.e("hour_of_day  " + calendar.get(Calendar.HOUR_OF_DAY));
//        long l = System.currentTimeMillis();
        LogUtil.e(DateUtil.getDate(calendar.getTime()));

        try {
            Date d = fmt.parse("16-03-23 23:28");
            calendar.setTime(d);
            LogUtil.e("hour  " + calendar.get(Calendar.HOUR));
            LogUtil.e("hour_of_day  " + calendar.get(Calendar.HOUR_OF_DAY));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void testDate() {

        long l = DateUtil.getDate();
        LogUtil.i("" + l);

        Date date = new Date(1458872704016l);
        LogUtil.e(DateUtil.getDate(date));
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        LogUtil.i("" + c.get(Calendar.HOUR_OF_DAY));


        LogUtil.e("");
    }

    public void testDate1() {

        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
        Calendar c = Calendar.getInstance();
        c.set(2016, 3, 1, 0, 0);
        long l1 = c.getTime().getTime();

        Calendar c1 = Calendar.getInstance();
        c1.set(2015, 9, 1, 0, 0);
        long l2 = c1.getTime().getTime();

        LogUtil.e("l1:" + l1 + "    l2:" + l2 + "    l1-l2:" + (l1 - l2));
    }

    public void testRecord() {
        PigsRecordDao dao = new PigsRecordDao(getContext());
        List<PigsRecord> list = dao.getAllRecord();
        if (AppUtil.isAvailable(list)) {
            for (PigsRecord r : list) {
                LogUtil.e(r.toString());
            }
        } else {
            LogUtil.e("查询的是空的，没找到数据");
        }
    }
}