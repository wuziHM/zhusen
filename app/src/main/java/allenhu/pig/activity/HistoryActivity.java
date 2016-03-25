package allenhu.pig.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import allenhu.pig.R;
import allenhu.pig.adapter.DividerItemDecoration;
import allenhu.pig.adapter.RecordsAdapter;
import allenhu.pig.base.BaseActivity;
import allenhu.pig.bean.db.PigsRecord;
import allenhu.pig.db.PigsRecordDao;
import allenhu.pig.listener.OnItemClickListener;

public class HistoryActivity extends BaseActivity implements View.OnClickListener {

    private RecyclerView recycler;
    private ImageView ivBack;
    private List records;
    private RecordsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        initData();
        initView();
    }

    /**
     * 查询数据库，获取数据
     */
    private void initData() {
        PigsRecordDao dao = new PigsRecordDao(this);
        records = dao.getAllRecord();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((TextView)findViewById(R.id.tv_title)).setText("卖猪记录");

        recycler = (RecyclerView) findViewById(R.id.recycle);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        adapter = new RecordsAdapter(records, this);
        recycler.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                PigsRecord r = (PigsRecord) records.get(position - 1);
                Intent intent = new Intent(HistoryActivity.this, HistoryDetailActivity.class);
                intent.putExtra("data", r.getId());
                goActivity(HistoryActivity.this, intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.period, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_three:

                break;

            case R.id.action_six:

                break;

            case R.id.action_all:

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }


}
