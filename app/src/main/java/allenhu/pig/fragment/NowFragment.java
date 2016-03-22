package allenhu.pig.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import allenhu.pig.R;
import allenhu.pig.activity.ModifActivity;
import allenhu.pig.activity.SellActivity;
import allenhu.pig.base.BaseFragment;
import allenhu.pig.bean.Market;
import allenhu.pig.bean.Pig;
import allenhu.pig.bean.WeightUnit;
import allenhu.pig.util.DecimalUtil;

/**
 * Author：燕青 $ on 2016/3/18  17:47
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class NowFragment extends BaseFragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private View rootView;
    private TextView tvCount, tvMoney, tvPrice;
    private EditText edtWeight;
    private Button btnNext;
    private Spinner spinner;
    private ImageView ivMod;
    private int count = 1;
    private List<Pig> pigs = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.content_sell1, null, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        tvCount = (TextView) rootView.findViewById(R.id.tv_count);
//        SpannableStringBuilder builder = setColor();
//        tvCount.setText(builder);
        setColor();

        tvMoney = (TextView) rootView.findViewById(R.id.tv_money);

        tvPrice = (TextView) rootView.findViewById(R.id.tv_price);
        tvPrice.setText(DecimalUtil.formatFloat(Market.getInstance().getPrice()) + " 元/斤");

        spinner = (Spinner) rootView.findViewById(R.id.spin_one);
        spinner.setOnItemSelectedListener(this);

        ivMod = (ImageView) rootView.findViewById(R.id.iv_modif);
        ivMod.setOnClickListener(this);

        btnNext = (Button) rootView.findViewById(R.id.btn_next);
        btnNext.setOnClickListener(this);

        edtWeight = (EditText) rootView.findViewById(R.id.edt_weight);
        edtWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() <= 0) {
                    tvMoney.setText("");
                    return;
                }
                Float weight = Float.parseFloat(s.toString());
                switch (spinner.getSelectedItemPosition()) {
                    case 0:
                        tvMoney.setText(DecimalUtil.formatFloat(weight * Market.getInstance().getPrice() * 2));
                        Market.getInstance().setWeightUnit(WeightUnit.KG);
                        break;

                    case 1:
                        tvMoney.setText(DecimalUtil.formatFloat(weight * Market.getInstance().getPrice()));
                        Market.getInstance().setWeightUnit(WeightUnit.JIN);
                        break;
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * 为TextView设置局部的颜色
     *
     * @return
     */
    @NonNull
    private void setColor() {
        SpannableStringBuilder builder = new SpannableStringBuilder("第 " + count + " 头");

        //ForegroundColorSpan 为文字前景色，BackgroundColorSpan为文字背景色
        ForegroundColorSpan gray = new ForegroundColorSpan(Color.GRAY);
        ForegroundColorSpan orange = new ForegroundColorSpan(getResources().getColor(R.color.orangered));


        builder.setSpan(gray, 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(orange, 2, builder.length() - 2, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        builder.setSpan(gray, builder.length() - 2, builder.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvCount.setText(builder);
    }


    /**
     * Spinner 的回调
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        float weight, money;

        if (TextUtils.isEmpty(edtWeight.getText().toString())) {
            return;
        }

        switch (position) {
            case 0:
                weight = Float.parseFloat(edtWeight.getText().toString());
                tvMoney.setText(DecimalUtil.formatFloat(weight * Market.getInstance().getPrice() * 2));

                break;

            case 1:
                weight = Float.valueOf(edtWeight.getText().toString());
//                money = weight * Market.getInstance().getPrice() * 1.00f;
                tvMoney.setText(DecimalUtil.formatFloat(weight * Market.getInstance().getPrice()));
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_modif:
                goActivity(new Intent(activity, ModifActivity.class), 1);
                break;

            case R.id.btn_next:
                Pig pig = new Pig();
                pig.setMoney(Float.parseFloat(tvMoney.getText().toString()));
                pig.setWeight(Float.parseFloat(edtWeight.getText().toString()));
                pig.setWeightUnit(spinner.getSelectedItemPosition());
                pig.setSellDate(new Date());
                pigs.add(pig);
                initData();
                ((SellActivity)getActivity()).setChanged(pigs);
                break;
        }
    }

    /**
     * 初始化数据
     */
    private void initData() {
        count++;
        setColor();
        edtWeight.setText("");
        tvMoney.setText("");
    }


    public void setPrice() {
        tvPrice.setText(Market.getInstance().getPrice() + "");
        if (edtWeight.getText().length() > 0) {
            tvMoney.setText(DecimalUtil.formatFloat(Float.parseFloat(edtWeight.getText().toString()) * Market.getInstance().getPrice()));
        }
    }
}
