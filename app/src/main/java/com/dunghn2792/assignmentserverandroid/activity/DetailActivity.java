package com.dunghn2792.assignmentserverandroid.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dunghn2792.assignmentserverandroid.R;
import com.dunghn2792.assignmentserverandroid.api.API;
import com.dunghn2792.assignmentserverandroid.api.RetrofitClient;
import com.dunghn2792.assignmentserverandroid.fragment.HomeFragment;
import com.dunghn2792.assignmentserverandroid.model.Sneaker;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.dunghn2792.assignmentserverandroid.api.Constants.ROOT_URL;

public class DetailActivity extends AppCompatActivity {
    ImageView image, btnBack;
    TextView tvNameSneaker, tvSize, tvPrice, tvColor;
    Sneaker sneaker = new Sneaker();
    RetrofitClient retrofit = new RetrofitClient();
    API api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        final String idSneaker = intent.getStringExtra("id");
//        Toast.makeText(this, "idSneaker: " + idSneaker, Toast.LENGTH_SHORT).show();

        //anh xa
        image = findViewById(R.id.image);
        btnBack = findViewById(R.id.btnBack);
        tvNameSneaker = findViewById(R.id.tvNameSneaker);
        tvSize = findViewById(R.id.tvSize);
        tvPrice = findViewById(R.id.tvPrice);
        tvColor = findViewById(R.id.tvColor);

        api = retrofit.getClient().create(API.class);

        for (Sneaker item : HomeFragment.sneakerList) {
            if (item.getId().equals(idSneaker)) {
                Picasso.get().load(ROOT_URL + item.getImage()).into(image);
                tvNameSneaker.setText(item.getNameSneaker());
                tvColor.setText(item.getColor());
                tvSize.setText(String.valueOf(item.getSize()));
                tvPrice.setText(String.valueOf(item.getPrice()));

                sneaker.setPrice(item.getPrice());
                sneaker.setImage(item.getImage());
            }
        }
        //event
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
