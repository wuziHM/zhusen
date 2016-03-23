package allenhu.pig.util;

import java.util.List;

/**
 * Author：燕青 $ on 2016/3/23  17:21
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class AppUtil {

    /**
     * 判断是否为空，
     *
     * @param object
     * @return 如果不为空返回true,否则返回false
     */
    public static boolean isAvailable(Object object) {
        return object != null;
    }

    /**
     * 判断list是否为空且长度大于0，
     *
     * @param list
     * @return 如果不为空，且长度大于0返回true，否则返回false
     */
    public static boolean isAvailable(List list) {
        if (list != null) {
            if (list.size() > 0) {
                return true;
            }
        }
        return false;
    }


}
