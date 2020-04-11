package com.log.koronatakip;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class ViewPagerAdapterBottom extends PagerAdapter {

    private LayoutInflater layoutInflater;
    private Context context;

    private int total;
    private int active;
    private int recovery;
    private int death;
    private int last24;
    private ArrayList<Integer> list_int = new ArrayList<>();
    private ArrayList<String> list_string = new ArrayList<>();



    public ViewPagerAdapterBottom(Context context, int total, int active, int recovery, int death, int last24) {
        this.context = context;
        this.total = total;
        this.active = active;
        this.recovery = recovery;
        this.death = death;
        this.last24 = last24;

        list_int.add(total);
        list_int.add(active);
        list_int.add(death);
        list_int.add(recovery);
        list_int.add(last24);

        list_string.add("Toplam Vaka Sayısı");
        list_string.add("Aktif Vaka Sayısı");
        list_string.add("Ölüm Sayısı");
        list_string.add("İyileşen Hasta Sayısı");
        list_string.add("Yeni Vakalar");
    }


    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view == (ConstraintLayout)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.view_pager_2_container, container, false);

        TextView textView1 = view.findViewById(R.id.container_text_main);
        TextView textView2 = view.findViewById(R.id.container_text_secondary);

        textView1.setText(list_int.get(position).toString());
        textView2.setText(list_string.get(position));

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
