package allenhu.pig.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import allenhu.pig.R;
import allenhu.pig.base.BaseActivity;
import allenhu.pig.bean.Market;
import allenhu.pig.util.DecimalUtil;
import allenhu.pig.util.ToastUtil;

public class ModifActivity extends BaseActivity {

    private EditText edtPrice;
    private Button btnSure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modif);
        initView();
    }

    private void initView() {
        edtPrice = (EditText) findViewById(R.id.edt_modif_price);
        btnSure = (Button) findViewById(R.id.btn_modif_sure);
//        btnSure.setBackgroundColor(getResources().getColor(R.color.gray));
        btnSure.setBackgroundResource(R.color.gray);

        edtPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //如果文本框里的为空的话  确定的按钮就是灰色
                if (DecimalUtil.isRightFloat(s.toString())) {
                    btnSure.setBackgroundResource(R.color.orangered);

                    if (s.toString().contains(".")) {
                        if (s.length() - 1 - s.toString().indexOf(".") > 2) {
                            s = s.toString().subSequence(0,
                                    s.toString().indexOf(".") + 3);
                            edtPrice.setText(s);
                            edtPrice.setSelection(s.length());
                        }
                    }
                } else {
                    btnSure.setBackgroundResource(R.color.gray);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //按钮设置监听事件
        btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!DecimalUtil.isRightFloat(edtPrice.getText().toString())) {
                    ToastUtil.toast(ModifActivity.this, "请填写价格");
                    return;
                } else {
                    Market.getInstance().setPrice(DecimalUtil.formatFloat(Float.parseFloat(edtPrice.getText().toString())));
                    setResult(RESULT_OK);
                    finish();
                }
            }
        });
    }
}
