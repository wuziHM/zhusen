package allenhu.pig.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import allenhu.pig.R;
import allenhu.pig.bean.Pig;
import allenhu.pig.bean.db.PigsRecord;
import allenhu.pig.listener.OnItemClickListener;
import allenhu.pig.util.ComparatorPig;
import allenhu.pig.util.DateUtil;


/**
 * 每头猪记录的条目的适配器
 * <p/>
 * Created by AllenHu on 2016/2/14.
 */
public class RecordsAdapter extends RecyclerView.Adapter<RecordsAdapter.MyViewHolder> {

    private List mData;
    private Context context;
    private OnItemClickListener onItemClickListener;


    public RecordsAdapter(List mData, Context context) {
//        Collections.sort(mData, new ComparatorPig());
        this.mData = mData;
        this.context = context;

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_record, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        if (position == 0) {
            holder.tvCount.setText("头数");
            holder.tvCount.setTextColor(Color.BLACK);
            holder.tvDate.setText("日期");
            holder.tvDate.setTextColor(Color.BLACK);
            holder.tvWeight.setText("重量(斤)");
            holder.tvWeight.setTextColor(Color.BLACK);
            holder.tvMoney.setText("收入(元)");
            holder.tvMoney.setTextColor(Color.BLACK);
        } else {
            PigsRecord record = (PigsRecord) mData.get(position - 1);
            holder.tvCount.setText(record.getCount() + "");
            holder.tvDate.setText(DateUtil.getDate(record.getDate()));
            holder.tvWeight.setText(record.getWeight() + "");
            holder.tvMoney.setText(record.getIncome() + "");

            if (onItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getLayoutPosition();
                        onItemClickListener.onItemClick(holder.itemView, pos);
                    }
                });

                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int pos = holder.getLayoutPosition();
                        onItemClickListener.onItemLongClick(holder.itemView, pos);
                        return false;
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return mData.size() + 1;
    }

    class MyViewHolder extends ViewHolder {

        TextView tvCount, tvDate, tvWeight, tvMoney;

        public MyViewHolder(View view) {
            super(view);
            tvCount = (TextView) view.findViewById(R.id.tv_item_count);
            tvDate = (TextView) view.findViewById(R.id.tv_item_date);
            tvWeight = (TextView) view.findViewById(R.id.tv_item_weight);
            tvMoney = (TextView) view.findViewById(R.id.tv_item_money);
        }
    }

}
