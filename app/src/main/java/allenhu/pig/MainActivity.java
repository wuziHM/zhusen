package allenhu.pig;

import android.content.Intent;
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

import allenhu.pig.activity.SellActivity;
import allenhu.pig.base.BaseActivity;
import allenhu.pig.bean.Pig;
import allenhu.pig.util.KeyBorderUtil;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private EditText edtPrice, edtCount;
    private Button btnSell;
    private View content;
    private Pig pig;

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

        pig = Pig.getPig();

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
                if (s.length() > 0) {
                    btnSell.setBackgroundResource(R.color.orangered);
                    btnSell.setTag(2);
                    Float price = Float.parseFloat(s.toString());
                    pig.setPrice(price);
                } else {
                    btnSell.setBackgroundColor(getResources().getColor(R.color.gray));
                    btnSell.setTag(1);
                    pig.setPrice(-1.0f);
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
                if (!"1".equals(btnSell.getTag()))
                    startActivity(new Intent(MainActivity.this, SellActivity.class));
                else
                    Toast.makeText(MainActivity.this, "请设置好单价", Toast.LENGTH_SHORT).show();
                break;

            case R.id.view_content:
                KeyBorderUtil.hideKeyBorder(content, MainActivity.this);
                break;
        }
    }
}
