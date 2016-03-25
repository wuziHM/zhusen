package allenhu.pig.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.ls.LSException;

import java.util.ArrayList;
import java.util.List;

import allenhu.pig.R;
import allenhu.pig.adapter.DividerItemDecoration;
import allenhu.pig.adapter.HistoryDetailAdapter;
import allenhu.pig.base.BaseActivity;
import allenhu.pig.bean.Pig;
import allenhu.pig.db.PigDao;

public class HistoryDetailActivity extends BaseActivity {

    private List list;
    private HistoryDetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histroy_detail);

        initView();
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {

        int i = getIntent().getIntExtra("data", -1);
        if (i != -1) {
            PigDao dao = new PigDao(this);
            List<Pig> list1 = dao.getPigByRecordId(i);
            list.clear();
            list.addAll(list1);
            adapter.notifyDataSetChanged();
        }
    }

    private void initView() {
        list = new ArrayList();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ((TextView) findViewById(R.id.tv_title)).setText("详情");

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        adapter = new HistoryDetailAdapter(list, this);
        recyclerView.setAdapter(adapter);
    }


}
