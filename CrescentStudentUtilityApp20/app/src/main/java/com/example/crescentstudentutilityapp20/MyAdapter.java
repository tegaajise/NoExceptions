package com.example.crescentstudentutilityapp20;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/*Credit to - https://guides.codepath.com/android/using-the-recyclerview*/
/*An adapter is what aids the program to read data and put it in the form of a RecyclerView
essentially self-explanatory
- visual explanation found in the link above
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private LayoutInflater inflater;

    private ArrayList<Schedule> items;

    public MyAdapter(ArrayList<Schedule> a){
        this.items = a;
    }

    public MyAdapter(Context context){
        this.inflater = LayoutInflater.from(context);
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Reads and inflates what one item of a list is meant to look like
        //Basically trying to understand the "template"
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.sample_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyAdapter.MyViewHolder holder, int position) {
        // Get the data model based on position
        Schedule schedule = items.get(position);

        // Set item views based on your views and data model
        TextView tv1 = holder.name;
        tv1.setText(schedule.getTime());

        TextView tv2 = holder.description;
        tv2.setText(schedule.getEvent());

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView description;

        public MyViewHolder(final View itemView) {
            super(itemView);
            //What the items from the UI get initialized to from the code
            name = ((TextView) itemView.findViewById(R.id.item_time));
            description = ((TextView) itemView.findViewById(R.id.item_event));
        }

    }

}