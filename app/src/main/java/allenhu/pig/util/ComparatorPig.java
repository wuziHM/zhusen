package allenhu.pig.util;

import java.util.Comparator;

import allenhu.pig.bean.Pig;

/**
 * Author：燕青 $ on 2016/3/22  14:18
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class ComparatorPig implements Comparator {
    @Override
    public int compare(Object lhs, Object rhs) {
        Pig pigA = (Pig) lhs;
        Pig pigB = (Pig) rhs;
        String a = pigA.getCount() + "";
        String b = pigB.getCount() + "";
        return a.compareTo(b);
    }
}
