package com.log.koronatakip;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import java.security.cert.TrustAnchor;
import java.util.ArrayList;

import static com.log.koronatakip.graphs.viewPager_graph;

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
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {




        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.view_pager_2_container, container, false);

        //isTouch = false;

        final ImageView imageLeft = view.findViewById(R.id.left_arrow);
        final ImageView imageRight = view.findViewById(R.id.right_arrow);

        if (position == 0){
            imageLeft.setVisibility(View.GONE);
            imageRight.setVisibility(View.VISIBLE);
        }   else if (position == getCount()-1){
            imageLeft.setVisibility(View.VISIBLE);
            imageRight.setVisibility(View.GONE);
        }   else {
            imageLeft.setVisibility(View.VISIBLE);
            imageRight.setVisibility(View.VISIBLE);
        }


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
