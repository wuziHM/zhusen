package allenhu.pig.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

import allenhu.pig.bean.Pig;

/**
 * Author：燕青 $ on 2016/3/23  11:56
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class PigDao {
    private Dao<Pig, Integer> pigDaoOpe;
    private DataBaseHelper helper;

    public PigDao(Context context) {
        try {
            helper = DataBaseHelper.getHelper(context);
            pigDaoOpe = helper.getDao(Pig.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 在猪的表里插入一条数据
     *
     * @param pig
     */
    public void addPig(Pig pig) {
        try {
            pigDaoOpe.create(pig);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 在猪的表里删除数据
     *
     * @param pig
     */
    public void deletePig(Pig pig) {
        try {
            pigDaoOpe.delete(pig);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
