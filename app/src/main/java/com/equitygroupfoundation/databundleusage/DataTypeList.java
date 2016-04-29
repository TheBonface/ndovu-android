package com.equitygroupfoundation.databundleusage;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sebichondo on 28/04/2016.
 */
public class DataTypeList extends Fragment {
    private static final String ARG_POSITION = "param1";
    private int position;
    private DataSummaryAdapter mAdapter;


    @Bind(R.id.application_list)
    RecyclerView rv;

    public DataTypeList() {
        // Required empty public constructor
    }

    public static DataTypeList newInstance(int position) {
        DataTypeList fragment = new DataTypeList();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_types_list, container, false);
        ButterKnife.bind(this, view);

        position = getArguments().getInt(ARG_POSITION, 0);

        switch (position) {
            case 0:
                setupDataBundles();
                break;
            case 1:
                getApplicationDataUsage();
                break;
        }

        return view;
    }

    private void setupDataBundles() {
        List<DataBundles> dataBundles=new ArrayList<DataBundles>();
        Date date= new Date();
        dataBundles.add(new DataBundles("100 MB",110, date,date,true));
        dataBundles.add(new DataBundles("1 GB",1000, date,date,false));
        dataBundles.add(new DataBundles("20 GB",7999, date,date,false));
        dataBundles.add(new DataBundles("3 GB",1999, date,date,false));
        dataBundles.add(new DataBundles("8 GB",3999, date,date,false));

        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(new DataBundleHistoryAdapter(dataBundles,getActivity()));
    }

    private void getApplicationDataUsage() {
        final PackageManager pm = getActivity().getPackageManager();
        // get a list of installed apps.
        List<ApplicationInfo> packages = pm.getInstalledApplications(0);

        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setAdapter(new DataSummaryAdapter(packages,getActivity()));
    }
}
