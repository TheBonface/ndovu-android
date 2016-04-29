package com.equitygroupfoundation.databundleusage;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.TrafficStats;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sebichondo on 28/04/2016.
 */
public class DataSummaryAdapter extends RecyclerView.Adapter<DataSummaryAdapter.ViewHolder> {
    List<ApplicationInfo> applicationItems;
    private Context mContext;

    public DataSummaryAdapter(List<ApplicationInfo> applicationItems, Context mContext) {
        this.applicationItems = applicationItems;
        this.mContext = mContext;
    }

    @Override
    public DataSummaryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.application_item, null);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DataSummaryAdapter.ViewHolder holder, int position) {
        ApplicationInfo applicationItem = applicationItems.get(position);
        final PackageManager pm = mContext.getPackageManager();

        ApplicationInfo app = null;
        try {
            app = pm.getApplicationInfo(applicationItem.packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // get the UID for the selected app
        int UID = applicationItem.uid;

        // internet usage for particular app(sent and received)
        double received = (double) TrafficStats.getUidRxBytes(UID)
                / (1024 * 1024);
        double send = (double) TrafficStats.getUidTxBytes(UID)
                / (1024 * 1024);
        double total = received + send;

        holder.applicationIcon.setImageDrawable(pm.getApplicationIcon(app));
        holder.appDescription.setText(pm.getApplicationLabel(app));
        holder.appUsage.setText(String.format( "%.2f", total )+" MB");
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
    public int getItemCount() {
        return (null != applicationItems ? applicationItems.size() : 0);
    }
}
