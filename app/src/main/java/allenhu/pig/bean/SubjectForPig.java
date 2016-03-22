package allenhu.pig.bean;

import java.util.List;
import java.util.Observable;

/**
 * Author：燕青 $ on 2016/3/22  11:38
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class SubjectForPig extends Observable {

    private List<Pig> pigList;

    public List<Pig> getPigList() {
        return pigList;
    }

    /**
     * 主题更新消息
     *
     * @param pigList
     */
    public void setPigList(List<Pig> pigList) {
        this.pigList = pigList;
        setChanged();
        notifyObservers();
    }


//    /**
//     * 主题更新消息
//     *
//     * @param msg
//     */
//    public void setMsg(String msg)
//    {
//        this.msg = msg  ;
//
//    }
}
