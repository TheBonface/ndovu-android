package com.equitygroupfoundation.databundleusage;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.astuetz.PagerSlidingTabStrip;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sebichondo on 29/04/2016.
 */
public class DataUsageFragment extends Fragment implements
        OnChartValueSelectedListener {

    private DataTypesPagerAdapter pagerAdapter;

    @Bind(R.id.pager)
    public ViewPager viewPager;

    @Bind(R.id.empty_view_2)
    FrameLayout emptyView;

    @Bind(R.id.empty_view_3)
    FrameLayout emptyView3;

    @Bind(R.id.tabs)
    PagerSlidingTabStrip tabs;

    @Bind(R.id.chart1)
    public PieChart mChart;
    private Typeface tf;

    private enum DataType {MOBILE, WIFI}

    DataType dataType;
    String TAG = MainActivity.class.toString();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_usage, container, false);

        ButterKnife.bind(this, view);

        tf = Typeface.createFromAsset(getActivity().getAssets(), "fonts/helvetica_neue-light.otf");
        String[] pageTitles = new String[]{"Mobile", "WiFi"};

        pagerAdapter = new DataTypesPagerAdapter(getActivity().getSupportFragmentManager(), pageTitles);
        viewPager.setAdapter(pagerAdapter);
        tabs.setViewPager(viewPager);
        tabs.setTypeface(tf, Typeface.NORMAL);


        mChart.setUsePercentValues(true);
        mChart.setDescription("");
        mChart.setExtraOffsets(5, 10, 5, 5);


        mChart.setDragDecelerationFrictionCoef(0.95f);


        mChart.setCenterTextTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/helvetica_neue-light.otf"));

        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(Color.WHITE);

        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(110);
        mChart.setHoleRadius(82f);
        mChart.setTransparentCircleRadius(61f);

        mChart.setDrawCenterText(true);

        mChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        mChart.setRotationEnabled(true);
        mChart.setHighlightPerTapEnabled(true);

        // add a selection listener
        mChart.setOnChartValueSelectedListener(this);

        mChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);

        mChart.getLegend().setEnabled(false);

        setData();

        return view;
    }

    private void setData() {
        emptyView3.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);

        mChart.setCenterText(generateCenterSpannableText(String.format("Total Usage\n %s %s \nfor %d days",
                "KES", 0.00, 0)));

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        int count = 1;

        for (int i = 0; i < count; i++) {
            float amnt = 1000;
            yVals1.add(new Entry(Math.abs(amnt), i));
        }

        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < count; i++)
            xVals.add("");

        PieDataSet dataSet = new PieDataSet(yVals1, "Election Results");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        // add a lot of colors
        String[] colorlist = getResources().getStringArray(R.array.summary_chart);

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (String c : colorlist)
            colors.add(Color.parseColor(c));

        dataSet.setColors(colors);

        PieData data = new PieData(xVals, dataSet);
        data.setValueTextSize(11f);
        data.setDrawValues(false);
        data.setValueTextColor(Color.BLACK);
        data.setValueTypeface(tf);
        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        mChart.invalidate();
    }

    private SpannableString generateCenterSpannableText(String text) {
        SpannableString s = new SpannableString(text);
        s.setSpan(new RelativeSizeSpan(1.0f), 0, 13, 0);
        s.setSpan(new StyleSpan(Typeface.BOLD), 13, s.length() - 15, 0);
        s.setSpan(new ForegroundColorSpan(Color.BLACK), 13, s.length() - 15, 0);
        s.setSpan(new RelativeSizeSpan(1.5f), 13, s.length() - 15, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), s.length() - 13, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(Color.BLACK), s.length() - 13, s.length(), 0);
        return s;
    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    public class DataTypesPagerAdapter extends FragmentStatePagerAdapter {
        String[] pageTitles;

        public DataTypesPagerAdapter(FragmentManager fm, String[] pageTitles) {
            super(fm);
            this.pageTitles = pageTitles;
        }

        @Override
        public Fragment getItem(int position) {
            DataTypeList fragment = DataTypeList.newInstance(position);
            return fragment;
        }

        @Override
        public int getCount() {
            return pageTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return pageTitles[0];
                case 1:
                    return pageTitles[1];
            }

            return "";
        }
    }
}
