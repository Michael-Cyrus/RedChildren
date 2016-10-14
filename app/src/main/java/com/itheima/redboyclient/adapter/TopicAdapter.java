package com.itheima.redboyclient.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.itheima.redboyclient.App;
import com.itheima.redboyclient.R;
import com.itheima.redboyclient.net.resp.TopicResponse;
import com.itheima.redboyclient.utils.ConstantsRedBaby;

import org.senydevpkg.base.AbsBaseAdapter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class TopicAdapter extends AbsBaseAdapter<TopicResponse.TopicEntity> {


    /**
     * 接收AbsListView要显示的数据
     *
     * @param context
     * @param data    要显示的数据
     */
    public TopicAdapter(Context context, List<TopicResponse.TopicEntity> data) {
        super(context, data);
    }

    @Override
    protected ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TopicViewHolder(View.inflate(getContext(), R.layout.topic_item, null));
    }

    @Override
    protected void onBindViewHolder(ViewHolder holder, int position) {
        final TopicResponse.TopicEntity limitbuy = getItem(position);
        //使用Volley的ImageLoader加载图片
        TopicViewHolder tvHolder = (TopicViewHolder) holder;
        App.HL.display(tvHolder.mTopicIV, ConstantsRedBaby.URL_SERVER + limitbuy.pic);
        tvHolder.mTopicTV.setText(limitbuy.name);

    }


    static class TopicViewHolder extends ViewHolder {
        @InjectView(R.id.topic_TV)
        TextView mTopicTV;
        @InjectView(R.id.topic_IV)
        ImageView mTopicIV;

        TopicViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }
}



