package allenhu.pig.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import allenhu.pig.R;
import allenhu.pig.activity.SellActivity;
import allenhu.pig.adapter.DividerItemDecoration;
import allenhu.pig.adapter.SumMoneyAdapter;
import allenhu.pig.base.BaseFragment;
import allenhu.pig.bean.Pig;
import allenhu.pig.bean.SubjectForPig;
import allenhu.pig.bean.db.PigsRecord;
import allenhu.pig.db.PigDao;
import allenhu.pig.listener.OnItemClickListener;
import allenhu.pig.util.AppUtil;
import allenhu.pig.util.ArithUtil;
import allenhu.pig.util.DecimalUtil;

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

        tvCount.setText(list.size() + " 头 ");
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
//                CustomDialog.Builder builder = new CustomDialog.Builder(activity);
//                builder.setMessage("这个就是自定义的提示框");
//                builder.setTitle("提示");
//                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                        //设置你的操作事项
//                    }
//                });
//
//                builder.setNegativeButton("编辑",
//                        new android.content.DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        });
//
//                builder.create().show()
                showDialog(position);
            }
        });
    }

    private void showDialog(final int position) {
        Dialog dialog = new AlertDialog.Builder(activity).setTitle("删除数据").setMessage(
                "您要删除这条数据吗").setPositiveButton("取消",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.dismiss();
                    }
                }).setNegativeButton("删除", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                doDelete(position);
            }
        }).create();
        dialog.show();
    }

    private void showModifDialog(int position) {

        View view = activity.getLayoutInflater().inflate(R.layout.layout_modif_pig, null);
        EditText edtPrice = (EditText) view.findViewById(R.id.edt_price);
        EditText edtWeight = (EditText) view.findViewById(R.id.edt_weight);
        edtPrice.setHint(list.get(position - 1).getPrice());
        edtWeight.setHint(DecimalUtil.formatFloat(list.get(position - 1).getWeight()) + "");
        Dialog dialog = new AlertDialog.Builder(activity).setView(view).create();
        dialog.show();

    }

    private void doDelete(int position) {
        list.remove(position - 1);
        adapter.notifyDataSetChanged();
        new PigDao(activity).deletePig(list.get(position - 1));
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
            PigsRecord record = ((SellActivity) getActivity()).getRecord();
            PigDao dao = new PigDao(activity);
            List<Pig> list1 = dao.getPigByRecordId(record.getId());
            if (AppUtil.isAvailable(list1)) {
                list.clear();
                list.addAll(list1);
                adapter.notifyDataSetChanged();

                tvCount.setText(list.size() + " 头 ");
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
            return "共计 " + 0 + " 元";
        }
        double sum = 0;
        for (Pig pig : list) {
            sum = ArithUtil.add(sum, pig.getMoney());
        }
        return "共计 " + sum + " 元";
    }
}
