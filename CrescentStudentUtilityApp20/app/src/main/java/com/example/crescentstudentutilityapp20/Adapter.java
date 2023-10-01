package com.example.crescentstudentutilityapp20;
import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/*https://guides.codepath.com/android/using-the-recyclerview*/
public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    LayoutInflater inflater;

    private ArrayList<String> weekdays;

    public Adapter(ArrayList<String> a){
        this.weekdays = a;
    }

    public Adapter(Context context){
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public int getItemCount() { return weekdays.size(); }

    @Override
    public Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Adapter.MyViewHolder holder, int position) {
        // Get the data model based on position
        String day = weekdays.get(position);

        // Set item views based on your views and data model
        TextView tv1 = holder.name;
        tv1.setText(day);


    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name;

        private Pair<String, String> currentPair;

        public MyViewHolder(final View itemView) {
            super(itemView);

            name = ((TextView) itemView.findViewById(R.id.weekDay));

        }

    }

}