package allenhu.pig.bean;

import java.util.Date;

/**
 * Author：燕青 $ on 2016/3/22  10:03
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class Pig {
    /**
     * 0表示kg《1表示斤
     */
    private int weightUnit;


    private float weight;
    private float money;
    private Date sellDate;

    public Pig() {
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

    public Date getSellDate() {
        return sellDate;
    }

    public void setSellDate(Date sellDate) {
        this.sellDate = sellDate;
    }
}
