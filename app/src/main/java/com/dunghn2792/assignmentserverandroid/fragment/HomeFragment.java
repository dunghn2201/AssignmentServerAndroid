package com.dunghn2792.assignmentserverandroid.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.dunghn2792.assignmentserverandroid.R;
import com.dunghn2792.assignmentserverandroid.activity.MainActivity;
import com.dunghn2792.assignmentserverandroid.adapter.SneakerAdapter;
import com.dunghn2792.assignmentserverandroid.api.API;
import com.dunghn2792.assignmentserverandroid.api.RetrofitClient;
import com.dunghn2792.assignmentserverandroid.model.Sneaker;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public static List<Sneaker> sneakerList = new ArrayList<>();
    List<Sneaker> searchList;
    ViewFlipper viewFlipper;
    RecyclerView rvSneaker;
    SneakerAdapter sneakerAdapter;
    EditText edtSearch;

    RetrofitClient retrofit;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_home, container, false);
        retrofit = new RetrofitClient();
        edtSearch = rootview.findViewById(R.id.edtSearch);
        rvSneaker = rootview.findViewById(R.id.rvSneaker);
        RecyclerView.LayoutManager manager = new GridLayoutManager(getActivity(), 2);
        rvSneaker.setHasFixedSize(true);
        rvSneaker.setLayoutManager(manager);
        viewFlipper = rootview.findViewById(R.id.viewFlipper);
        int images[] = {R.drawable.slide1, R.drawable.slide2, R.drawable.slide3, R.drawable.slide4, R.drawable.slide5, R.drawable.slide6};
//        for(int i=0;i<images.length;i++){
//            flipperImages(images[i]);
//        }
        //but i prefer foreach
        for (int image : images) {
            flipperImages(image);
        }
        event();
        getData();
        return rootview;
    }


    private void flipperImages(int image) {
        ImageView imageView = new ImageView(getActivity());
        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);

        //animation
        viewFlipper.setInAnimation(getActivity(), android.R.anim.slide_in_left);
        viewFlipper.setInAnimation(getActivity(), android.R.anim.slide_out_right);
    }

    private void event() {
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchList = new ArrayList<>();
                if (s.length() == 0) {
                    searchList = sneakerList;
                } else {
                    for (Sneaker item : sneakerList) {
                        if (item.getNameSneaker().toLowerCase().contains(s.toString().toLowerCase())
                                || item.getColor().toLowerCase().contains(s.toString().toLowerCase())) {
                            searchList.add(item);
                        }
                    }
                }
                sneakerAdapter = new SneakerAdapter(getActivity(), searchList);
                rvSneaker.setAdapter(sneakerAdapter);
                Log.d("Testdata", "onTextChanged: " + searchList);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    private void getData() {
        //Creating an object of our api interface
        API api = retrofit.getClient().create(API.class);
        //Defining the method
        api.getSneakers().enqueue(new Callback<List<Sneaker>>() {
            @Override
            public void onResponse(Call<List<Sneaker>> call, Response<List<Sneaker>> response) {
                if (response.isSuccessful()) {
                    sneakerList = response.body();
                    Log.d("ERRRR", String.valueOf(sneakerList.size()));
                    if (sneakerList != null) {
                        sneakerAdapter = new SneakerAdapter(getActivity(), sneakerList);
                        rvSneaker.setAdapter(sneakerAdapter);
                        Log.d("ERRRR", String.valueOf(sneakerList.size()));
                    } else {
                        Log.d("ERRRR", "error list");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Sneaker>> call, Throwable t) {
                Toast.makeText(getActivity(), "Failed get data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
