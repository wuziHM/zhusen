package allenhu.pig.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

import allenhu.pig.bean.db.User;

/**
 * Author：燕青 $ on 2016/3/23  11:50
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class UserDao {
    private Dao<User, Integer> userDao;
    private DataBaseHelper helper;

    public UserDao(Context context) {
        try {
            helper = DataBaseHelper.getHelper(context);
            userDao = helper.getDao(User.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加一个用户
     *
     * @param user
     */
    public void addUser(User user) {
        try {
            userDao.create(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除用户
     *
     * @param user
     */
    public void deleteUser(User user) {
        try {
            userDao.delete(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
