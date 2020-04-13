package com.log.koronatakip;

import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class graphs extends Fragment {


    public static SearchView searchView;


    public static ListView listView;
    ArrayAdapter<String> searchList_adapter;


    ArrayList<String> spinnerItems = new ArrayList<>();
    ArrayList<String> Slug = new ArrayList<>();


    ArrayList<Integer> ConfirmedArray = new ArrayList<>();
    ArrayList<Integer> ActiveArray = new ArrayList<>();
    ArrayList<Integer> RecoveredArray = new ArrayList<>();
    ArrayList<Integer> DeathsArray = new ArrayList<>();

    int Last24;
    int confirmed;
    int active;
    int recovered;
    int deahts;

    ArrayList<String> Date = new ArrayList<>();
    ArrayList<String> Date_Parameter = new ArrayList<>();


    final String baseUrl = "https://api.covid19api.com/";



    ViewPager viewPager2;
    ViewPagerAdapterBottom viewPagerAdapterBottom;


    public static ViewPager viewPager_graph;
    ViewPagerAdapter_Graph viewPagerAdapter_graph;

    TextView textView_country;


    Context context;
    Activity activity_graph;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);



        if (context instanceof Activity){
            this.context = context;
            activity_graph = (Activity) context;
        }

    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.graphs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // init elements

        textView_country = view.findViewById(R.id.text_country);

        listView = view.findViewById(R.id.list_item);

        searchView = view.findViewById(R.id.search_bar);

        viewPager2 = view.findViewById(R.id.viewPager2);

        viewPager_graph = view.findViewById(R.id.viewPager);





        //      Getting Retrofit

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Countries>> call = jsonPlaceHolderApi.getCountries();

        call.enqueue(new Callback<List<Countries>>() {
            @Override
            public void onResponse(Call<List<Countries>> call, retrofit2.Response<List<Countries>> response) {

                if (response.isSuccessful()){

                    List<Countries> countries = response.body();

                    for (Countries country : countries){
                        spinnerItems.add(country.getCountry());
                        Slug.add(country.getSlug());

                    }

                    firstStage_into();
                } else {
                    Toast.makeText(activity_graph, response.code(), Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(Call<List<Countries>> call, Throwable t) {
                Toast.makeText(activity_graph, t.getMessage(), Toast.LENGTH_SHORT).show();

            }

        });
    }


    private void firstStage_into() {

        Sort(spinnerItems);

        searchList_adapter = new ArrayAdapter<String>(
                activity_graph,
                android.R.layout.simple_list_item_1,
                spinnerItems
        );

        listView.setAdapter(searchList_adapter);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);


        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager_graph.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
            }
        });



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList_adapter.getFilter().filter(newText);
                return false;
            }
        });




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                for (int a = 0; a < spinnerItems.size() ; a++) {
                    if (listView.getItemAtPosition(position).equals(spinnerItems.get(a))){

                        textView_country.setText(spinnerItems.get(a));

                        Call<List<SpesificData>> call = jsonPlaceHolderApi.getSpesificData(Slug.get(a));
                        call.enqueue(new Callback<List<SpesificData>>() {
                            @Override
                            public void onResponse(Call<List<SpesificData>> call, Response<List<SpesificData>> response) {
                                if (response.isSuccessful()){
                                    clear_Arrays();

                                    List<SpesificData> data = response.body();

                                    for (SpesificData spesificData : data){

                                        Date.add(spesificData.getDate());


                                        if (Date.size() > 1){
                                            String s1 = Date.get(Date.size()-2);
                                            String s2 = Date.get(Date.size()-1);
                                            if (s1.equals(s2)){
                                                ConfirmedArray.set(ConfirmedArray.size()-1, ConfirmedArray.get(ConfirmedArray.size()-1)+spesificData.getConfirmed());
                                                RecoveredArray.set(RecoveredArray.size()-1, RecoveredArray.get(RecoveredArray.size()-1)+spesificData.getRecovered());
                                                DeathsArray.set(DeathsArray.size()-1, DeathsArray.get(DeathsArray.size()-1)+spesificData.getDeaths());

                                                //ActiveArray.set(ActiveArray.size()-1, ActiveArray.get(ActiveArray.size()-1)+spesificData.getActive());
                                                ActiveArray.set(ActiveArray.size()-1, ActiveArray.get(ActiveArray.size()-1)
                                                        +spesificData.getConfirmed()
                                                        -spesificData.getRecovered()
                                                        -spesificData.getDeaths());

                                            } else {
                                                ConfirmedArray.add(spesificData.getConfirmed());
                                                RecoveredArray.add(spesificData.getRecovered());
                                                DeathsArray.add(spesificData.getDeaths());

                                                //ActiveArray.add(spesificData.getActive());
                                                ActiveArray.add(
                                                        +spesificData.getConfirmed()
                                                        -spesificData.getRecovered()
                                                        -spesificData.getDeaths());
                                            }
                                        } else {
                                            ConfirmedArray.add(spesificData.getConfirmed());
                                            RecoveredArray.add(spesificData.getRecovered());
                                            DeathsArray.add(spesificData.getDeaths());

                                            //ActiveArray.add(spesificData.getActive());
                                            ActiveArray.add(
                                                    +spesificData.getConfirmed()
                                                    -spesificData.getRecovered()
                                                    -spesificData.getDeaths());

                                        }

                                    }



                                    if (ConfirmedArray.size() > 2){
                                        Last24 = (ConfirmedArray.get(ConfirmedArray.size()-1))-(ConfirmedArray.get(ConfirmedArray.size()-2));
                                    } else {    Last24 = 0;}

                                    if (ConfirmedArray.size() > 0){
                                        confirmed = ConfirmedArray.get(ConfirmedArray.size()-1);
                                    } else {    confirmed = 0;}

                                    if (ActiveArray.size() > 0){
                                        active = ActiveArray.get(ActiveArray.size()-1);
                                    } else {    active = 0;}

                                    if (RecoveredArray.size() > 0){
                                        recovered = RecoveredArray.get(RecoveredArray.size()-1);
                                    } else {    recovered = 0;}

                                    if (DeathsArray.size() > 0){
                                        deahts = DeathsArray.get(DeathsArray.size()-1);
                                    } else {    deahts = 0;}


                                    // calling bottom viewpager
                                    viewPagerAdapterBottom = new ViewPagerAdapterBottom(activity_graph,
                                            confirmed, active, recovered, deahts, Last24);
                                    viewPager2.setAdapter(viewPagerAdapterBottom);
                                    viewPager2.setClipToPadding(false);
                                    viewPager2.setPageMargin(200);

                                    // calling top viewpager
                                    viewPagerAdapter_graph = new ViewPagerAdapter_Graph(activity_graph,
                                            ConfirmedArray,ActiveArray, RecoveredArray,DeathsArray, Date_Parameter);
                                    viewPager_graph.setAdapter(viewPagerAdapter_graph);
                                    viewPager_graph.setClipToPadding(false);
                                    viewPager_graph.setPageMargin(200);


                                }
                                else{
                                    Toast.makeText(activity_graph, response.code(), Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onFailure(Call<List<SpesificData>> call, Throwable t) {

                            }
                        });

                    }
                }
                close_keyboard();
                listView.setVisibility(View.GONE);
                searchView.setIconified(true);
                searchView.onActionViewCollapsed();
                viewPager_graph.setVisibility(View.VISIBLE);

            }

        });



        listView.performItemClick(listView, 227, listView.getItemIdAtPosition(2));


        /*MotionEvent event = MotionEvent.obtain(10, 30, MotionEvent.ACTION_DOWN,
                viewPager2.getX(), viewPager2.getY(), 10, 1,
                2, viewPager2.getX(), viewPager2.getY(),
                viewPager2.getId(), 3);*/

        //onTouchEvent(event);

        viewPager2.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if (position == 4){

                } else {
                    viewPager_graph.setCurrentItem(position, true);
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPager_graph.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewPager2.setCurrentItem(position, true);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    private void close_keyboard(){
        View view = activity_graph.getCurrentFocus();
        if (view != null){
            InputMethodManager imm = (InputMethodManager) activity_graph.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }

    private void clear_Arrays() {
        ConfirmedArray.clear();
        ActiveArray.clear();
        RecoveredArray.clear();
        DeathsArray.clear();
        Date.clear();
        Date_Parameter.clear();
    }

    private void Sort(ArrayList<String> spinnerItems) {

        for (int a = 0 ; a+1 < spinnerItems.size() ; a++){


            if (spinnerItems.get(a).compareTo(spinnerItems.get(a+1)) > 0){

                change(spinnerItems, a, a+1);
                change(Slug, a, a+1);

                a = 0;
            }

        }

        for (int a = 0 ; a+1 < spinnerItems.size() ; a++){


            if (spinnerItems.get(a).compareTo(spinnerItems.get(a+1)) > 0){

                change(spinnerItems, a, a+1);
                change(Slug, a, a+1);

                a = 0;
            }

        }


    }

    private void change(ArrayList<String> arrayList, int index1, int index2){
        String s1 = arrayList.get(index1);
        String s2 = arrayList.get(index2);

        arrayList.set(index1, s2);
        arrayList.set(index2, s1);
    }



}
