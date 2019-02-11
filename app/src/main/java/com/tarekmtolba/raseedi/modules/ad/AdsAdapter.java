package com.tarekmtolba.raseedi.modules.ad;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.tarekmtolba.raseedi.R;
import com.tarekmtolba.raseedi.data.model.Ad;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class AdsAdapter extends RecyclerView.Adapter<AdsAdapter.RecyclerViewHolder> {

    private List<Ad> ads;
    private ListItemClickListener mOnClickListener;

    public AdsAdapter(List<Ad> ads, ListItemClickListener mOnClickListener) {
        this.ads = ads;
        this.mOnClickListener = mOnClickListener;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ad_list_item,parent,false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        Ad ad = ads.get(position);
        holder.mAdTitleTextView.setText(ad.getTitle());
        Picasso.get().load(ad.getPicture()).into(holder.mAdImageView, new Callback() {
            @Override
            public void onSuccess() {
                holder.mProgressBar.setVisibility(View.GONE);
            }
            @Override
            public void onError(Exception e) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return ads.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mAdImageView;
        private TextView mAdTitleTextView;
        private CardView mAdCardView;
        private ProgressBar mProgressBar;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            mAdImageView = itemView.findViewById(R.id.ad_imageview);
            mAdTitleTextView = itemView.findViewById(R.id.ad_title_textview);
            mProgressBar = itemView.findViewById(R.id.progressBar);
            mAdCardView = itemView.findViewById(R.id.ad_cardview);
            mAdCardView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnClickListener.onListItemClick(ads.get(getAdapterPosition()));
        }
    }

    public interface ListItemClickListener {
        void onListItemClick(Ad clickedAd);
    }
}
