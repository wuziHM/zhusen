package allenhu.pig.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import allenhu.pig.bean.Pig;
import allenhu.pig.bean.db.PigsRecord;
import allenhu.pig.bean.db.User;

/**
 * Author：燕青 $ on 2016/3/23  11:12
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class DataBaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String TABLE_NAME = "zhusen.allenhu.pig.db";

    private Map<String, Dao> daos = new HashMap<String, Dao>();

    private DataBaseHelper(Context context) {
        super(context, TABLE_NAME, null, 4);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, User.class);
            TableUtils.createTable(connectionSource, Pig.class);
            TableUtils.createTable(connectionSource, PigsRecord.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource, User.class, true);
            TableUtils.dropTable(connectionSource, Pig.class, true);
            TableUtils.dropTable(connectionSource, PigsRecord.class, true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static DataBaseHelper instance;

    public static synchronized DataBaseHelper getHelper(Context context) {
        context = context.getApplicationContext();
        if (instance == null) {
            synchronized (DataBaseHelper.class) {
                if (instance == null) {
                    instance = new DataBaseHelper(context);
                }
            }
        }
        return instance;
    }

    public synchronized Dao getDao(Class clazz) throws SQLException {
        Dao dao = null;
        String className = clazz.getSimpleName();
        if (daos.containsKey(className)) {
            dao = daos.get(className);
        }
        if (dao == null) {
            dao = super.getDao(clazz);
            daos.put(className, dao);
        }
        return dao;
    }

    @Override
    public void close() {
        super.close();
        for(String key:daos.keySet()){
            Dao dao = daos.get(key);
            dao = null;
        }
    }
}
