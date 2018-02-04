package com.example.amin.myhttprest;

import android.view.LayoutInflater;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.amin.myhttprest.model.Recipes;

import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.NumberViewHolder> {

    private Context context;
    private List<Recipes> recipesList;

    public RecyclerAdapter(Context context, List<Recipes> recipesList) {
        this.context = context;
        this.recipesList = recipesList;
    }

    @Override
    public RecyclerAdapter.NumberViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutForListItems = R.layout.recipe_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmeddiatly = false;

        View view = inflater.inflate(layoutForListItems,viewGroup,shouldAttachToParentImmeddiatly);
        NumberViewHolder viewHolder = new NumberViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.NumberViewHolder  holder, int position) {
        holder.bind(position);

    }

    @Override
    public int getItemCount() {
        return recipesList.size();
    }

     class NumberViewHolder extends RecyclerView.ViewHolder {

       public TextView name , timestamp , description , price , chef;
        public ImageView thmubnail;

        // @param itemView
        public NumberViewHolder(View itemView){
            super(itemView);
            name= (TextView)itemView.findViewById(R.id.name);
            description = (TextView)itemView.findViewById(R.id.description);
            chef = (TextView)itemView.findViewById(R.id.chef);
            price = (TextView)itemView.findViewById(R.id.price);
            timestamp = (TextView)itemView.findViewById(R.id.timestamp);
            thmubnail = (ImageView)itemView.findViewById(R.id.thumbnail);

        }

        void bind (int listIndex){

            final Recipes recipe = recipesList.get(listIndex);
            name.setText(recipe.getName());
            chef.setText("By " + recipe.getChef());
            description.setText(recipe.getDescription());
            price.setText("Price: $ " + recipe.getPrice());
            timestamp.setText(recipe.getTimestamp());

            Glide.with(context)
                    .load(recipe.getThumbnail())
                    .into(thmubnail);

        }
    }


}
