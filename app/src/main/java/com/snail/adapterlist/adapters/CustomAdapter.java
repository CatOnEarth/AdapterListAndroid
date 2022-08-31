package com.snail.adapterlist.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.snail.adapterlist.R;
import com.snail.adapterlist.objects.Animal;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private ArrayList<Animal> localAnimals;
    private Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final TextView textView2;

        public ViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.textView);
            textView2 = (TextView) view.findViewById(R.id.textView2);
        }

        public TextView getTextView() {
            return textView;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public CustomAdapter(ArrayList<Animal> animals, Context context) {
        localAnimals = animals;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.getTextView().setText(localAnimals.get(position).getName());
        viewHolder.textView2.setText("SDAD");

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Click item" + String.valueOf(viewHolder.getAdapterPosition()), Toast.LENGTH_LONG).show();
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localAnimals.size();
    }
}