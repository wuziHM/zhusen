package allenhu.pig.db;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import allenhu.pig.bean.db.PigsRecord;

/**
 * Author：燕青 $ on 2016/3/23  14:03
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class PigsRecordDao {
    private Dao<PigsRecord, Integer> dao;
    private DataBaseHelper helper;

    public PigsRecordDao(Context context) {
        try {
            helper = DataBaseHelper.getHelper(context);
            dao = helper.getDao(PigsRecord.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加一次卖猪的记录
     *
     * @param pigsRecord
     */
    public void addRecord(PigsRecord pigsRecord) {
        try {
            dao.create(pigsRecord);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除卖猪的记录
     *
     * @param record
     */
    public void deleteRecord(PigsRecord record) {
        try {
            dao.delete(record);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新记录
     *
     * @param record
     */
    public void updateRecord(PigsRecord record) {
        try {
            dao.update(record);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取所有的记录
     */
    public List<PigsRecord> getAllRecord() {
        List<PigsRecord> pigsRecords = null;
        try {
//            pigsRecords = dao.queryForAll();
            pigsRecords = dao.queryBuilder().where().ne("_weight", 0).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pigsRecords;
    }

    /**
     * 根据id进行查询
     *
     * @param id
     * @return
     */
    public PigsRecord getRecordById(int id) {
        try {
            return dao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
