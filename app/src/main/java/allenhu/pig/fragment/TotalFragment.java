package allenhu.pig.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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

/**
 * Author：燕青 $ on 2016/3/18  17:47
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class TotalFragment extends BaseFragment implements Observer {

    private View rootView;
    private List<Pig> list;
    private RecyclerView recyclerView;

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
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycle);
        SumMoneyAdapter adapter = new SumMoneyAdapter(list, activity);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.addItemDecoration(new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL_LIST));
//        recyclerView.setLayoutManager(new GridLayoutManager(context, 4));
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL));
//        recyclerView.addItemDecoration(new DividerGridItemDecoration(context));
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


    public void registerSubject(Observable observable)
    {
        observable.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object data) {
        if(observable instanceof SubjectForPig){
            SubjectForPig subjectForPig = (SubjectForPig) observable;
            list =  subjectForPig.getPigList();
        }
    }
}
