package allenhu.pig.bean.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Author：燕青 $ on 2016/3/23  10:40
 * E-mail：359222347@qq.com
 * <p/>
 * 账户
 */
@DatabaseTable(tableName = "tb_user")
public class User implements Serializable{

    @DatabaseField(generatedId = true, canBeNull = false, columnName = "_id")
    private int id;

    @DatabaseField(columnName = "_name")
    private String name;

    @DatabaseField(columnName = "_phone")
    private String phone;

    public User() {
    }

    public User(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
