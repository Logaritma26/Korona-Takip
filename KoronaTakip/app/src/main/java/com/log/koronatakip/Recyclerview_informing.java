package com.log.koronatakip;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Recyclerview_informing extends RecyclerView.Adapter <Recyclerview_informing.ViewHolder>{

    private ArrayList<String> text_title = new ArrayList<>();
    private ArrayList<String> text_content = new ArrayList<>();

    public Recyclerview_informing(ArrayList<String> text_title, ArrayList<String> text_content) {
        this.text_title = text_title;
        this.text_content = text_content;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.informing_recyclerview, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.text_title.setText(text_title.get(position));
        holder.text_content.setText(text_content.get(position));

        if (text_title.get(position).equals("")){
            holder.text_title.setVisibility(View.GONE);
        }


    }


    @Override
    public int getItemCount() {
        return text_content.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView text_title;
        TextView text_content;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            text_title = itemView.findViewById(R.id.recycler_title);
            text_content = itemView.findViewById(R.id.recycler_content);
        }
    }
}
