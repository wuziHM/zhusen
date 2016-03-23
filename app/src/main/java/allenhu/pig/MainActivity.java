package allenhu.pig;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import allenhu.pig.activity.SellActivity;
import allenhu.pig.base.BaseActivity;
import allenhu.pig.bean.Market;
import allenhu.pig.bean.db.PigsRecord;
import allenhu.pig.db.PigsRecordDao;
import allenhu.pig.util.Constant;
import allenhu.pig.util.DateUtile;
import allenhu.pig.util.DecimalUtil;
import allenhu.pig.util.KeyBorderUtil;
import allenhu.pig.util.ToastUtil;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private EditText edtPrice, edtCount;
    private Button btnSell;
    private View content;
    private Market market;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("猪森");
        setSupportActionBar(toolbar);
        initView();

    }

    private void initView() {

        market = Market.getInstance();

        content = findViewById(R.id.view_content);
        content.setOnClickListener(this);

        edtPrice = (EditText) findViewById(R.id.edt_price);
        edtCount = (EditText) findViewById(R.id.edt_count);
        edtPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                /**
                 * 价格不能为空，不能以.开头，不能是0
                 */
                if (DecimalUtil.isRightFloat(s.toString())) {
                    btnSell.setBackgroundResource(R.color.orangered);
                } else {
                    btnSell.setBackgroundColor(getResources().getColor(R.color.gray));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        btnSell = (Button) findViewById(R.id.btn_sell);
        btnSell.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sell:
                if (DecimalUtil.isRightFloat(edtPrice.getText().toString())) {
                    PigsRecord record = addRecord();
                    Intent intent = new Intent(MainActivity.this, SellActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("data", record);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    market.setPrice(DecimalUtil.formatFloat(Float.parseFloat(edtPrice.getText().toString())));
                    finish();
                } else
                    Toast.makeText(MainActivity.this, "请设置好单价", Toast.LENGTH_SHORT).show();
                break;

            case R.id.view_content:
                KeyBorderUtil.hideKeyBorder(content, MainActivity.this);
                break;
        }
    }

    /**
     * 创建一条添加的记录
     */
    private PigsRecord addRecord() {
        PigsRecordDao dao = new PigsRecordDao(MainActivity.this);
        PigsRecord record = new PigsRecord();
        record.setCount(0);
        record.setDate(DateUtile.getDate(new Date()));
        record.setIncome(0);
        record.setPirce(0);
        record.setUser(Constant.USER);
        record.setWeight(0);
        dao.addRecord(record);
        return record;
    }

}
