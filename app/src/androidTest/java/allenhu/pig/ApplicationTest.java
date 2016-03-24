package allenhu.pig;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.util.List;

import allenhu.pig.bean.Pig;
import allenhu.pig.db.PigDao;
import allenhu.pig.util.AppUtil;
import allenhu.pig.util.ArithUtil;
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
}