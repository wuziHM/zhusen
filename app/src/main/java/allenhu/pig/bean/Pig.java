package allenhu.pig.bean;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;

import allenhu.pig.bean.db.PigsRecord;

/**
 * Author：燕青 $ on 2016/3/22  10:03
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
@DatabaseTable(tableName = "tb_pig")
public class Pig implements Serializable {

    @DatabaseField(generatedId = true, columnName = "_id")
    private int id;
    /**
     * 0表示kg《1表示斤
     */
    @DatabaseField(columnName = "_weightUnit")
    private int weightUnit;

    @DatabaseField(columnName = "_count")
    private int count;

    @DatabaseField(columnName = "_price")
    private String price;

    @DatabaseField(columnName = "_weight")
    private float weight;

    @DatabaseField(columnName = "_money", dataType = DataType.FLOAT)
    private float money;

    @DatabaseField(columnName = "_sellDate")
    private String sellDate;

    @DatabaseField(canBeNull = true, foreign = true, columnName = "_record", foreignAutoRefresh = true)
    private PigsRecord record;

    public Pig() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PigsRecord getRecord() {
        return record;
    }

    public void setRecord(PigsRecord record) {
        this.record = record;
    }

    public int getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(int weightUnit) {
        this.weightUnit = weightUnit;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getSellDate() {
        return sellDate;
    }

    public void setSellDate(String sellDate) {
        this.sellDate = sellDate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        String unit = weightUnit == 0 ? "公斤" : "斤";
        String s = "重量单位:" + unit + "   重量:" + weight + "    价格:" + price + "   价值:" + money;
        return s;
    }
}
