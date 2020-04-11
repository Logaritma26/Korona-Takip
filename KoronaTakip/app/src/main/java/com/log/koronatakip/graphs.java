package com.log.koronatakip;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
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

public class graphs extends Fragment  {


    public static SearchView searchView;


    public static ListView listView;
    ArrayAdapter<String> searchList_adapter;


    //Spinner spinner;
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
                                                ActiveArray.set(ActiveArray.size()-1, ActiveArray.get(ActiveArray.size()-1)+spesificData.getActive());
                                                RecoveredArray.set(RecoveredArray.size()-1, RecoveredArray.get(RecoveredArray.size()-1)+spesificData.getRecovered());
                                                DeathsArray.set(DeathsArray.size()-1, DeathsArray.get(DeathsArray.size()-1)+spesificData.getDeaths());

                                            } else {
                                                ConfirmedArray.add(spesificData.getConfirmed());
                                                ActiveArray.add(spesificData.getActive());
                                                RecoveredArray.add(spesificData.getRecovered());
                                                DeathsArray.add(spesificData.getDeaths());

                                            }
                                        } else {
                                            ConfirmedArray.add(spesificData.getConfirmed());
                                            ActiveArray.add(spesificData.getActive());
                                            RecoveredArray.add(spesificData.getRecovered());
                                            DeathsArray.add(spesificData.getDeaths());
                                        }

                                    }


                            /*if (check){
                                Date_Parameter.add(0,Date.get(0));
                                for (int a = 0; a < Date.size() ; a ++){
                                    isThere_matchup = false;
                                    for (int q = 0 ; q < Date_Parameter.size(); q++){
                                        if (Date.get(a).equals(Date_Parameter.get(q))){
                                            isThere_matchup = true;
                                        }
                                        if (!isThere_matchup){
                                            Date_Parameter.add(Date.get(a));
                                        }
                                    }
                                }
                            }*/


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


                                    viewPagerAdapterBottom = new ViewPagerAdapterBottom(activity_graph,
                                            confirmed, active, recovered, deahts, Last24);
                                    viewPager2.setAdapter(viewPagerAdapterBottom);
                                    viewPager2.setClipToPadding(false);
                                    viewPager2.setPageMargin(200);


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



        listView.performItemClick(listView, 2, listView.getItemIdAtPosition(2));


        //listView.setSelection(236);

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
