package allenhu.pig.adapter;

import android.content.Context;
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
import allenhu.pig.listener.OnItemClickListener;
import allenhu.pig.util.ComparatorPig;


/**
 * 每头猪记录的条目的适配器
 *
 * Created by AllenHu on 2016/2/14.
 */
public class SumMoneyAdapter extends RecyclerView.Adapter<SumMoneyAdapter.MyViewHolder> {

    private List mData;
    private Context context;
    private OnItemClickListener onItemClickListener;


    public SumMoneyAdapter(List mData, Context context) {
        Collections.sort(mData, new ComparatorPig());
        this.mData = mData;
        this.context = context;

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_sum, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Pig pig = (Pig) mData.get(position);
        holder.tvCount.setText(pig.getCount() + "");
        holder.tvPrice.setText(pig.getPrice());
        if (pig.getWeightUnit() == 0) {
            holder.tvWeight.setText(pig.getWeight() + " 公斤");
        } else if (pig.getWeightUnit() == 1) {
            holder.tvWeight.setText(pig.getWeight() + " 斤");
        }
        holder.tvMoney.setText(pig.getMoney() + "");
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

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends ViewHolder {

        TextView tvCount, tvPrice, tvWeight, tvMoney;

        public MyViewHolder(View view) {
            super(view);
            tvCount = (TextView) view.findViewById(R.id.tv_item_count);
            tvPrice = (TextView) view.findViewById(R.id.tv_item_price);
            tvWeight = (TextView) view.findViewById(R.id.tv_item_weight);
            tvMoney = (TextView) view.findViewById(R.id.tv_item_money);
        }
    }

}
