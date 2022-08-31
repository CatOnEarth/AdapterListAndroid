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

/** Custom adapter */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    /** ArrayList of animals which display in recyclerView */
    private final ArrayList<Animal> localAnimals;
    /** Context */
    private final Context           mContext;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        /** TextView name animal in list */
        private final TextView textViewAnimalName;
        /** TextView age animal in list */
        private final TextView textViewAnimalAge;

        /** Constructor
         *
         * @param view View
         */
        public ViewHolder(View view) {
            super(view);
            textViewAnimalName = (TextView) view.findViewById(R.id.textViewAnimalNameRecyclerView);
            textViewAnimalAge = (TextView) view.findViewById(R.id.textViewAnimalAgeRecyclerView);
        }
    }

    /**Constructor custom adapter
     *
     * @param animals animals arraylist
     * @param context context
     */
    public CustomAdapter(ArrayList<Animal> animals, Context context) {
        localAnimals = animals;
        mContext = context;
    }

    /**Create new views (invoked by the layout manager)
     *
     * @param viewGroup The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);

        return new ViewHolder(view);
    }

    /**Replace the contents of a view (invoked by the layout manager)
     *
     * @param viewHolder The ViewHolder which should be updated to represent the contents of the item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.textViewAnimalName.setText(localAnimals.get(position).getName());
        viewHolder.textViewAnimalAge.setText(String.valueOf(localAnimals.get(position).getAge()));

        viewHolder.itemView.setOnClickListener(view -> StartDescriptionActivity(position));
    }

    /**Start description activity
     *
     * @param position position in recyclerView
     */
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

    /**Method to get number of item's to display in recyclerView
     *
     * @return num of item's to display
     */
    @Override
    public int getItemCount() {
        return localAnimals.size();
    }
}