package com.equitygroupfoundation.databundleusage;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sebichondo on 29/04/2016.
 */
public class DataBundleHistoryAdapter extends RecyclerView.Adapter<DataBundleHistoryAdapter.ViewHolder> {
    List<DataBundles> dataBundles;
    private Context mContext;

    public DataBundleHistoryAdapter(List<DataBundles> dataBundles, Context mContext) {
        this.dataBundles = dataBundles;
        this.mContext = mContext;
    }

    @Override
    public DataBundleHistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_bundle_item, null);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.application_image)
        ImageView applicationIcon;
        @Bind(R.id.app_description)
        TextView appDescription;
        @Bind(R.id.app_usage)
        TextView appUsage;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    @Override
    public void onBindViewHolder(DataBundleHistoryAdapter.ViewHolder holder, int position) {
        DataBundles dataBundle = dataBundles.get(position);
        String activiationDate = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault()).format(dataBundle.getActivationDate());
        String expiryDate = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault()).format(dataBundle.getExpirationDate());

        if(dataBundle.isStatus())
            holder.applicationIcon.setBackgroundColor(mContext.getResources().getColor(R.color.green));
        else
            holder.applicationIcon.setBackgroundColor(mContext.getResources().getColor(R.color.red));

        holder.appDescription.setText("Data bundle period : " + activiationDate + " - " + expiryDate);
        holder.appUsage.setText(dataBundle.getProductType());
    }

    @Override
    public int getItemCount() {
        return (null != dataBundles ? dataBundles.size() : 0);
    }
}
