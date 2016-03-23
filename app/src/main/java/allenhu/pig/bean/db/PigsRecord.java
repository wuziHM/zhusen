package allenhu.pig.bean.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Author：燕青 $ on 2016/3/23  9:49
 * E-mail：359222347@qq.com
 * <p/>
 * 每次卖猪的记录，记录买的头数，总重量，总价格，所属账户
 */

@DatabaseTable(tableName = "tb_pigs_record")
public class PigsRecord {

    @DatabaseField(generatedId = true, canBeNull = false, columnName = "_id")
    private int id;

    @DatabaseField(columnName = "_count", canBeNull = false)
    private int count;

    @DatabaseField(columnName = "_weight", canBeNull = false)
    private float weight;

    @DatabaseField(columnName = "_price", canBeNull = false)
    private float pirce;

    @DatabaseField(columnName = "_income", canBeNull = false)
    private float income;

    @DatabaseField(canBeNull = true, foreign = true, columnName = "user_id", foreignAutoRefresh = true)
    private User user;

    @DatabaseField(columnName = "_date", canBeNull = false)
    private String date;

    public PigsRecord() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getPirce() {
        return pirce;
    }

    public void setPirce(float pirce) {
        this.pirce = pirce;
    }

    public float getIncome() {
        return income;
    }

    public void setIncome(float income) {
        this.income = income;
    }

    private User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
