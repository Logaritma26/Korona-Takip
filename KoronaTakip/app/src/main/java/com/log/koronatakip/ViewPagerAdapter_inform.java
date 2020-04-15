package com.log.koronatakip;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class ViewPagerAdapter_inform extends PagerAdapter {


    private Context context;
    private LayoutInflater layoutInflater;

    private ArrayList<String> maint_text_title = new ArrayList<>();

    private ArrayList<ArrayList<String>> recyclerview_title = new ArrayList<>();
    private ArrayList<ArrayList<String>> recyclerview_content = new ArrayList<>();

    private ArrayList<String> page1_title = new ArrayList<>();
    private ArrayList<String> page1_content = new ArrayList<>();

    private ArrayList<String> page2_title = new ArrayList<>();
    private ArrayList<String> page2_content = new ArrayList<>();

    private ArrayList<String> page3_title = new ArrayList<>();
    private ArrayList<String> page3_content = new ArrayList<>();

    private ArrayList<String> page4_title = new ArrayList<>();
    private ArrayList<String> page4_content = new ArrayList<>();

    private ArrayList<String> page5_title = new ArrayList<>();
    private ArrayList<String> page5_content = new ArrayList<>();



    public ViewPagerAdapter_inform(Context context,
                                   ArrayList<String> main_text_title,
                                   ArrayList<String> page1_title,
                                   ArrayList<String> page1_content,
                                   ArrayList<String> page2_title,
                                   ArrayList<String> page2_content,
                                   ArrayList<String> page3_title,
                                   ArrayList<String> page3_content,
                                   ArrayList<String> page4_title,
                                   ArrayList<String> page4_content,
                                   ArrayList<String> page5_title,
                                   ArrayList<String> page5_content) {

        this.context = context;

        this.maint_text_title = main_text_title;

        this.page1_title = page1_title;
        this.page1_content = page1_content;

        this.page2_title = page2_title;
        this.page2_content = page2_content;

        this.page3_title = page3_title;
        this.page3_content = page3_content;

        this.page4_title = page4_title;
        this.page4_content = page4_content;

        this.page5_title = page5_title;
        this.page5_content = page5_content;


        recyclerview_title.add(page1_title);
        recyclerview_title.add(page2_title);
        recyclerview_title.add(page3_title);
        recyclerview_title.add(page4_title);
        recyclerview_title.add(page5_title);

        recyclerview_content.add(page1_content);
        recyclerview_content.add(page2_content);
        recyclerview_content.add(page3_content);
        recyclerview_content.add(page4_content);
        recyclerview_content.add(page5_content);


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
        View view = layoutInflater.inflate(R.layout.informing_viewpager, container, false);

        final ImageView left = view.findViewById(R.id.left_arrow_i);
        final ImageView right = view.findViewById(R.id.right_arrow_i);


        if (position == 0){
            left.setVisibility(View.GONE);
            right.setVisibility(View.VISIBLE);
        }   else if (position == getCount()-1){
            left.setVisibility(View.VISIBLE);
            right.setVisibility(View.GONE);
        }   else {
            left.setVisibility(View.VISIBLE);
            right.setVisibility(View.VISIBLE);
        }


        TextView main_title = view.findViewById(R.id.informing_title);

        main_title.setText(maint_text_title.get(position));


        RecyclerView recyclerView = view.findViewById(R.id.informing_recycler);
        Recyclerview_informing adapter = new Recyclerview_informing(recyclerview_title.get(position), recyclerview_content.get(position));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));



        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }

}
