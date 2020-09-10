package com.innova.leaderboard.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.innova.leaderboard.R;
import com.innova.leaderboard.models.HourLeader;

import java.util.ArrayList;
import java.util.List;

public class HourLeadersAdapter extends RecyclerView.Adapter<HourLeadersAdapter.ViewHolder> {
    private final String TAG = this.getClass().getSimpleName();
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private List<HourLeader> mHourLeaders;

    public HourLeadersAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);

        mHourLeaders = new ArrayList<>();
    }

    public void setData(List<HourLeader> hourLeaders) {
        this.mHourLeaders = hourLeaders;
        notifyDataSetChanged();
        Log.d(TAG, "SIZE " + mHourLeaders.size());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.item_leaders, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HourLeadersAdapter.ViewHolder holder, int position) {
        HourLeader leader = mHourLeaders.get(position);
        holder.mName.setText(leader.getName());
        holder.mDescription.setText(leader.getDescription());

        Glide
                .with(mContext)
                .load(leader.getBadgeUrl())
                .placeholder(R.drawable.top_learner)
                .into(holder.mBadge);
    }

    @Override
    public int getItemCount() {
        return mHourLeaders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mBadge;
        private final TextView mName;
        private final TextView mDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mBadge = itemView.findViewById(R.id.badge);
            mName = itemView.findViewById(R.id.name);
            mDescription = itemView.findViewById(R.id.description);
        }
    }
}
