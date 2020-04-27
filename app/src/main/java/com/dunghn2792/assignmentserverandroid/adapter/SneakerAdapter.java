package com.dunghn2792.assignmentserverandroid.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.dunghn2792.assignmentserverandroid.activity.DetailActivity;
import com.dunghn2792.assignmentserverandroid.R;
import com.dunghn2792.assignmentserverandroid.model.Sneaker;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import static com.dunghn2792.assignmentserverandroid.api.Constants.ROOT_URL;

public class SneakerAdapter extends RecyclerView.Adapter<SneakerAdapter.RecyclerViewHolder> {
    Activity context;
    List<Sneaker> data = new ArrayList<>();

    public SneakerAdapter(Activity context, List<Sneaker> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public SneakerAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sneaker, parent, false);
        return new SneakerAdapter.RecyclerViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final SneakerAdapter.RecyclerViewHolder holder, final int position) {
        Sneaker sneaker = this.data.get(position);

        try {
            Picasso.get()
                    .load(ROOT_URL + sneaker.getImage())
                    .into(holder.image);
        } catch (Exception e) {

        }
//        NumberFormat formatter = new DecimalFormat("#,###");
//        int myNumber = sneaker.getPrice();
//        String formattedNumber = formatter.format(myNumber);

        holder.tvNameItem.setText(sneaker.getNameSneaker());
        holder.tvPriceItem.setText(sneaker.getPrice() + " VND");
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("id", data.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView tvNameItem, tvPriceItem;
        CardView item;

        public RecyclerViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            tvNameItem = itemView.findViewById(R.id.tvNameItem);
            tvPriceItem = itemView.findViewById(R.id.tvPriceItem);
            item = itemView.findViewById(R.id.item);
        }
    }
}

