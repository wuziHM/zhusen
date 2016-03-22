package allenhu.pig.bean;

/**
 * Author：燕青 $ on 2016/3/21  13:57
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class Market {

    private volatile static Market singleton;

    private float price;
    //    private int count;
//    private float money;
//    private float weight;
    private WeightUnit weightUnit;

    private Market() {
    }

    public static Market getInstance() {
        if (singleton == null) {
            synchronized (Market.class) {
                if (singleton == null) {
                    singleton = new Market();
                }
            }
        }
        return singleton;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

//    public int getCount() {
//        return count;
//    }
//
//    public void setCount(int count) {
//        this.count = count;
//    }
//
//    public float getMoney() {
//        return money;
//    }
//
//    public void setMoney(float money) {
//        this.money = money;
//    }
//
//    public float getWeight() {
//        return weight;
//    }
//
//    public void setWeight(float weight) {
//        this.weight = weight;
//    }

    public WeightUnit getWeightUnit() {
        return weightUnit;
    }

    public void setWeightUnit(WeightUnit weightUnit) {
        this.weightUnit = weightUnit;
    }
}
