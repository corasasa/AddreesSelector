package cn.chen.addreesselector;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by chenjinglan on 2017/12/4.
 * email:13925024285@163.com
 */

public class DataAdapter extends BaseAdapter {
    private List<String> mData;
    private int mSelectPosition = -1;

    @Override
    public int getCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public String getItem(int position) {
        return mData == null ? "" : mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(parent.getContext(), R.layout.item_list, null);
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.iv_select = (ImageView) convertView.findViewById(R.id.iv_select);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        String name = mData.get(position);
        holder.tv_name.setText(name);
        holder.iv_select.setVisibility(position == mSelectPosition ? View.VISIBLE : View.GONE);
        return convertView;
    }

    public void setData(List<String> data) {
        this.mData = data;
        this.notifyDataSetChanged();
    }

    public String getSelectName() {
        if (mSelectPosition == -1) {
            return "";
        }
        return mData == null ? "" : mData.get(mSelectPosition);
    }

    public void setSelectPosition(int selectPosition) {
        this.mSelectPosition = selectPosition;
        this.notifyDataSetChanged();
    }

    static class ViewHolder{
        TextView tv_name;
        ImageView iv_select;
    }
}
