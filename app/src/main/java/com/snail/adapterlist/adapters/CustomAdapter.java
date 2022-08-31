package com.snail.adapterlist.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.snail.adapterlist.R;
import com.snail.adapterlist.activities.DescriptionObjectActivity;
import com.snail.adapterlist.objects.Animal;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private final ArrayList<Animal> localAnimals;
    private final Context           mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewAnimalName;
        private final TextView textViewAnimalAge;

        public ViewHolder(View view) {
            super(view);
            textViewAnimalName = (TextView) view.findViewById(R.id.textViewAnimalNameRecyclerView);
            textViewAnimalAge = (TextView) view.findViewById(R.id.textViewAnimalAgeRecyclerView);
        }
    }

    public CustomAdapter(ArrayList<Animal> animals, Context context) {
        localAnimals = animals;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.textViewAnimalName.setText(localAnimals.get(position).getName());
        viewHolder.textViewAnimalAge.setText(String.valueOf(localAnimals.get(position).getAge()));

        viewHolder.itemView.setOnClickListener(view -> StartDescriptionActivity(position));
    }

    public void StartDescriptionActivity(int position) {
        Intent intent = new Intent(mContext, DescriptionObjectActivity.class);
        intent.putExtra(DescriptionObjectActivity.KEY_ID,     localAnimals.get(position).get_id());
        intent.putExtra(DescriptionObjectActivity.KEY_NAME,   localAnimals.get(position).getName());
        intent.putExtra(DescriptionObjectActivity.KEY_AGE,    localAnimals.get(position).getAge());
        intent.putExtra(DescriptionObjectActivity.KEY_LENGTH, localAnimals.get(position).getLength());
        intent.putExtra(DescriptionObjectActivity.KEY_WEIGHT, localAnimals.get(position).getWeight());
        intent.putExtra(DescriptionObjectActivity.KEY_COLOR,  localAnimals.get(position).getColor());

        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return localAnimals.size();
    }
}