package allenhu.pig.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import allenhu.pig.R;
import allenhu.pig.bean.Pig;

/**
 * Author：燕青 $ on 2016/3/25  15:17
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public class HistoryDetailAdapter extends MyAdapter<HistoryDetailAdapter.ViewHolder> {

    public HistoryDetailAdapter(List mData, Context context) {
        super(mData, context);
    }

    /**
     * 绑定数据
     *
     * @param holder
     * @param position
     */
    @Override
    protected void onBindViewHolderData(ViewHolder holder, int position) {
        if (position == 0) {
            holder.tvCount.setText("");
            holder.tvPrice.setText("价格");
            holder.tvPrice.setTextColor(Color.BLACK);
            holder.tvWeight.setText("重量");
            holder.tvWeight.setTextColor(Color.BLACK);
            holder.tvMoney.setText("收入");
            holder.tvMoney.setTextColor(Color.BLACK);
        } else {
            Pig pig = (Pig) mData.get(position - 1);
            holder.tvCount.setText(pig.getCount() + "");
            holder.tvPrice.setText(pig.getPrice());
            if (pig.getWeightUnit() == 0) {
                holder.tvWeight.setText(pig.getWeight() + " 公斤");
            } else if (pig.getWeightUnit() == 1) {
                holder.tvWeight.setText(pig.getWeight() + " 斤");
            }
            holder.tvMoney.setText(pig.getMoney() + "");

        }
    }

    /**
     * 数据条数
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return mData.size() + 1;
    }

    @Override
    protected ViewHolder getViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    protected int getItemViewLayoutId() {
        return R.layout.item_sum;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCount, tvPrice, tvWeight, tvMoney;

        public ViewHolder(View view) {
            super(view);
            tvCount = (TextView) view.findViewById(R.id.tv_item_count);
            tvPrice = (TextView) view.findViewById(R.id.tv_item_price);
            tvWeight = (TextView) view.findViewById(R.id.tv_item_weight);
            tvMoney = (TextView) view.findViewById(R.id.tv_item_money);
        }
    }
}
