package com.example.eathaca.ui.community;



import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.eathaca.R;

import java.util.List;


public class CommunityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    private List<CommunityItem> mList;
    private OnItemClickListener mItemClickListener;
    private Context context;
    public CommunityAdapter(Context context, List<CommunityItem> list) {
        this.mList = list;
        this.context=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_community_content, parent, false);
        view.setOnClickListener(this);
        return new EventViewHolder(view);
    }





    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CommunityItem object = mList.get(position);
        if (object != null) {
            ((EventViewHolder) holder).mTitle.setText(object.getAdvertiseTitle());
            ((EventViewHolder) holder).mFarmName.setText(object.getFarmName());
            ((EventViewHolder) holder).mImage.setImageResource(object.getImageUrl());


//            Glide.with(context)
//                    .load(object.getImageUrl())
//                    .into(((EventViewHolder) holder).mImage);



            holder.itemView.setTag(position);
        }
    }

    @Override
    public int getItemCount() {
        if (mList == null)
            return 0;
        return mList.size();
    }


    public List<CommunityItem> getmList() {
        return mList;
    }
    public void setItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
        if (mItemClickListener != null) {
            mItemClickListener.onItemClick((Integer) v.getTag());
        }
    }


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    static class EventViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitle;
        private TextView mFarmName;
        private ImageView mImage;
        private ImageButton imageButton;
        public EventViewHolder(View itemView) {
            super(itemView);
            mFarmName= itemView.findViewById(R.id.name_localFarm);
            mTitle = itemView.findViewById(R.id.descriptionTextView);
            mImage=  itemView.findViewById(R.id.farm_image);

        }


    }

    public void addData(CommunityItem communityItem) {
        mList.add(0, communityItem);
        notifyItemInserted(mList.size());
    }

    public void removeData(int pos) {
        mList.remove(pos);
        notifyItemRemoved(pos);
        notifyDataSetChanged();
        notifyItemRangeChanged(pos, mList.size());

    }


}