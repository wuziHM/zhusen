package allenhu.pig.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.ls.LSException;

import java.util.ArrayList;
import java.util.List;

import allenhu.pig.R;
import allenhu.pig.base.BaseActivity;

public class HistoryDetailActivity extends BaseActivity{

    private List list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histroy_detail);

        initView();
    }

    private void initView() {
        list = new ArrayList();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ((TextView)findViewById(R.id.tv_title)).setText("详情");
    }


}
