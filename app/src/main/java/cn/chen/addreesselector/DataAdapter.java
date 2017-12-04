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
        if (convertView==null){
            convertView = View.inflate(parent.getContext(), R.layout.item_list, null);
        }else {

        }
        return null;
    }

    static class ViewHolder{
        TextView tv_name;
        ImageView iv_select;
    }
}
