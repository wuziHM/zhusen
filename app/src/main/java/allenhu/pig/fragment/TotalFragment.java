package allenhu.pig.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import allenhu.pig.R;
import allenhu.pig.adapter.DividerItemDecoration;
import allenhu.pig.adapter.SumMoneyAdapter;
import allenhu.pig.base.BaseFragment;
import allenhu.pig.bean.Pig;
import allenhu.pig.bean.SubjectForPig;
import allenhu.pig.listener.OnItemClickListener;
import allenhu.pig.util.LogUtil;

/**
 * Author：燕青 $ on 2016/3/18  17:47
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class TotalFragment extends BaseFragment implements Observer {

    private View rootView;
    private TextView tvCount, tvSumMoney;
    private List<Pig> list;
    private RecyclerView recyclerView;
    private SumMoneyAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.content_sell2, null, false);
        list = new ArrayList<>();
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        tvCount = (TextView) rootView.findViewById(R.id.tv_count);
        tvSumMoney = (TextView) rootView.findViewById(R.id.tv_sum_money);

        tvCount.setText(list.size() + "");
        tvSumMoney.setText(getSumMoney(list));

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycle);
        adapter = new SumMoneyAdapter(list, activity);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.addItemDecoration(new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(adapter);
        //设置监听，监听是自己写的
        adapter.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }


    public void registerSubject(Observable observable) {
        observable.addObserver(this);
    }

    /**
     * 接受到通知的回调
     *
     * @param observable
     * @param data
     */
    @Override
    public void update(Observable observable, Object data) {
        if (observable instanceof SubjectForPig) {
            SubjectForPig subjectForPig = (SubjectForPig) observable;
            if (subjectForPig.getPigList() != null) {
                list.clear();
                list.addAll(subjectForPig.getPigList());
                adapter.notifyDataSetChanged();

                tvCount.setText(list.size() + " 头");
                tvSumMoney.setText(getSumMoney(list));
            }
        }
    }

    /**
     * 计算总共的价钱
     *
     * @param list
     * @return
     */
    public String getSumMoney(List<Pig> list) {
        if (list.size() == 0) {
            return "0";
        }
        float sum = 0.0f;
        for (Pig pig : list) {
            sum += pig.getMoney();
        }

        return "共计 " + sum + " 元";
    }
}
