package com.mph.basedroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mph.basedroid.R;
import com.mph.library.base.SimpleRecAdapter;

/**
 * Created by：hcs on 2016/12/28 10:57
 * e_mail：aaron1539@163.com
 */
public class TestRecAdapter extends SimpleRecAdapter<TestRecAdapter.Item,TestRecAdapter.ViewHolder> {

    public TestRecAdapter(Context context) {
        super(context);
    }

    @Override
    public TestRecAdapter.ViewHolder newViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.adapter_test;
    }

    @Override
    public void onBindViewHolder(final TestRecAdapter.ViewHolder holder, final int position) {
        final Item item = data.get(position);
        holder.textView.setText(item.getText());

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getRecItemClick()!=null){
                    getRecItemClick().onItemClick(position,item,0,holder);
                }
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.textView);
        }
    }

    public static class Item{
        private String text;

        public Item(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
