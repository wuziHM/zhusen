package allenhu.pig.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import allenhu.pig.listener.OnItemClickListener;

/**
 * Author：燕青 $ on 2016/3/25  14:37
 * E-mail：359222347@qq.com
 * <p/>
 * use to...
 */
public abstract class MyAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {

    /**
     * 数据源
     */
    protected List mData;
    protected Context context;

    /**
     * 监听事件
     */
    protected OnItemClickListener onItemClickListener;


    public MyAdapter(List mData, Context context) {
        this.mData = mData;
        this.context = context;

    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public T onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context).inflate(getItemViewLayoutId(), parent, false);
        return getViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final T holder, int position) {

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

        onBindViewHolderData(holder, position);
    }


    @Override
    public int getItemCount() {
        return 0;
    }

    protected abstract void onBindViewHolderData(T holder, int position);

    protected abstract T getViewHolder(View view);


    protected abstract int getItemViewLayoutId();
}
