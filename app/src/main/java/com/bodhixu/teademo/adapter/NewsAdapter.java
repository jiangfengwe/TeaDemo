package com.bodhixu.teademo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bodhixu.teademo.R;
import com.bodhixu.teademo.info.NewsInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by bodhixu on 2016/10/11.
 * 主画面列表适配器
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {

    public interface IOnItemClickListener{
        void onclick(int position);
    }

    private Context context;
    private List<NewsInfo.DataBean> datas;
    private IOnItemClickListener itemClickListener;

    public NewsAdapter(Context context, List<NewsInfo.DataBean> datas, IOnItemClickListener itemClickListener) {
        this.context = context;
        this.datas = datas;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);

        return new MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        NewsInfo.DataBean data = datas.get(position);
        holder.titleTv.setText(data.getTitle());
        holder.timeTv.setText(data.getCreate_time());

        String url = data.getWap_thumb();
        if (!TextUtils.isEmpty(url)) {
            Picasso.with(context).load(url)
                    .placeholder(R.mipmap.ic_logo)
                    .error(R.mipmap.ic_logo)
                    .fit().into(holder.iv);
        } else {
            Picasso.with(context).load(R.mipmap.ic_logo).fit().into(holder.iv);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickListener.onclick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titleTv, timeTv;
        ImageView iv;

        public MyViewHolder(View itemView) {
            super(itemView);

            titleTv = (TextView) itemView.findViewById(R.id.news_title_tv);
            timeTv = (TextView) itemView.findViewById(R.id.news_time_tv);
            iv = (ImageView) itemView.findViewById(R.id.news_image_iv);

        }
    }


}
