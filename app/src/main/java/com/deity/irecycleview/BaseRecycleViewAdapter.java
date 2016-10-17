package com.deity.irecycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**感谢:https://www.easydone.cn/2015/10/26/
 * Created by Deity on 2016/10/17.
 */

public class BaseRecycleViewAdapter extends RecyclerView.Adapter<BaseRecycleViewAdapter.ItemViewHolder> {
    private List<String> mList;
    private Context context;

    public BaseRecycleViewAdapter(List<String> mList, Context context){
        this.mList = mList;
        this.context = context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new BaseRecycleViewAdapter.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        String data = mList.get(position);
        holder.mTvContent.setText(data);
    }

    @Override
    public int getItemCount() {
        if (null==mList) return 0;
        return mList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        public TextView mTvContent;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mTvContent = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }
}
