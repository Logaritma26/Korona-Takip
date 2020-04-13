package com.log.koronatakip;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ViewPagerAdapter_Graph extends PagerAdapter {


    private Context context;
    private LayoutInflater layoutInflater;

    ArrayList<Integer> ConfirmedArray = new ArrayList<>();
    ArrayList<Integer> ActiveArray = new ArrayList<>();
    ArrayList<Integer> RecoveredArray = new ArrayList<>();
    ArrayList<Integer> DeathsArray = new ArrayList<>();
    ArrayList<String> Date = new ArrayList<>();


    BarChart chart;
    BarDataSet barDataSet;
    BarData barData;
    //ArrayList<IBarDataSet> dataSets = new ArrayList<>();

    ArrayList<BarEntry> Confirmed = new ArrayList<>();
    ArrayList<BarEntry> Active = new ArrayList<>();
    ArrayList<BarEntry> Recovered = new ArrayList<>();
    ArrayList<BarEntry> Deaths = new ArrayList<>();

    ArrayList<ArrayList<BarEntry>> Holder = new ArrayList<>();

    Float aFloat = 0f;


    ArrayList<Integer> colors = new ArrayList<>();
    ArrayList<String> explanation = new ArrayList<>();


    public ViewPagerAdapter_Graph(Context context,
                                  ArrayList<Integer> confirmedArray,
                                  ArrayList<Integer> activeArray,
                                  ArrayList<Integer> recoveredArray,
                                  ArrayList<Integer> deathsArray,
                                  ArrayList<String> date) {
        this.context = context;
        this.ConfirmedArray = confirmedArray;
        this.ActiveArray = activeArray;
        this.RecoveredArray = recoveredArray;
        this.DeathsArray = deathsArray;
        this.Date = date;

        ConfirmedArray = decrease(ConfirmedArray);
        ActiveArray = decrease(ActiveArray);
        RecoveredArray = decrease(RecoveredArray);
        DeathsArray = decrease(DeathsArray);


        for (int a = 0 ; a < ConfirmedArray.size() ; a++){
            aFloat += 2f;
            Confirmed.add(new BarEntry(1f+aFloat, ConfirmedArray.get(a).floatValue()));
            Active.add(new BarEntry(1f+aFloat, ActiveArray.get(a).floatValue()));
            Deaths.add(new BarEntry(1f+aFloat, DeathsArray.get(a).floatValue()));
            Recovered.add(new BarEntry(1f+aFloat, RecoveredArray.get(a).floatValue()));


        }

        aFloat = 0f;

        Holder.add(0, Confirmed);
        Holder.add(1, Active);
        Holder.add(2, Deaths);
        Holder.add(3, Recovered);

        //Color.argb(1,116,83,214);
        //Color.argb(1,64,114,238);
        //Color.argb(1,219,93,113);
        //Color.argb(1, 144,200,142);

        colors.add(0, ContextCompat.getColor(context,R.color.total));
        colors.add(1, ContextCompat.getColor(context,R.color.active));
        colors.add(2, ContextCompat.getColor(context,R.color.deaths));
        colors.add(3, ContextCompat.getColor(context,R.color.recovery));


        explanation.add("Toplam Vaka Sayısı");
        explanation.add("Aktif Vaka Sayısı");
        explanation.add("Ölüm Sayısı");
        explanation.add("İyileşen Hasta Sayısı");


    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (ConstraintLayout)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.view_pager_graph, container, false);

        chart = view.findViewById(R.id.chart_view);

        barDataSet = new BarDataSet(Holder.get(position),explanation.get(position));
        //barDataSet.setColors(Color.RED);
        barDataSet.setColors(colors.get(position));
        barDataSet.setDrawIcons(false);
        barDataSet.setDrawValues(true);
        barDataSet.setValueTextSize(13f);

        barData = new BarData(barDataSet);  // dataSets

        barData.setBarWidth(1f); // set custom bar width


        chart.getDescription().setText(""); // set description label to space
        chart.setFitBars(true); // make the x-axis fit exactly all bars
        chart.animateY(1000); // animate charts
        chart.setPinchZoom(false);

        chart.setData(barData);
        chart.invalidate();

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }




    //  decrease array size to intended level
    public ArrayList<Integer> decrease(ArrayList<Integer> list){
        ArrayList<Integer> holder = new ArrayList<>();
        if (list.size() > 7){
            for (int a = 0 ; a < 7 ; a++){
                holder.add( list.get(list.size() -7 +a) );
            }
            return holder;
        }
        return list;
    }
}
