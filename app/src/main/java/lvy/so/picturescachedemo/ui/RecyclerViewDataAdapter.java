package lvy.so.picturescachedemo.ui;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import lvy.so.picturescachedemo.R;
import lvy.so.picturescachedemo.imagecacheutils.LoadImage;

/**
 * @author gping  email: gping.vip@gmail.com
 * @date Created by 2016/8/24.19:19
 * @filename RecyclerViewDataAdapter.class
 * @description
 * @TODO
 */
public class RecyclerViewDataAdapter extends RecyclerView.Adapter<RecyclerViewDataAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<PictureBean.ResultsEntity> mList;
    private LayoutInflater inflater;

    public RecyclerViewDataAdapter(Context mContext, ArrayList<PictureBean.ResultsEntity> list) {
        super();
        this.mContext = mContext;
        this.mList = list;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LoadImage.getLoadImageInstance(mContext).showImg(holder.itemImg, mList.get(position).getUrl());
        holder.tvName.setText(mList.get(position).getWho());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private ImageView itemImg;
        private TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            itemImg = (ImageView) itemView.findViewById(R.id.img_item);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }
}
