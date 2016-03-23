package allenhu.pig;

import android.app.Application;
import android.test.ApplicationTestCase;

import java.util.List;

import allenhu.pig.bean.Pig;
import allenhu.pig.db.PigDao;
import allenhu.pig.util.AppUtil;
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
}