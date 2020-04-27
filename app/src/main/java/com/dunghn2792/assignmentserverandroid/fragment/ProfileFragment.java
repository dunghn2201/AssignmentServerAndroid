package com.dunghn2792.assignmentserverandroid.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dunghn2792.assignmentserverandroid.R;
import com.dunghn2792.assignmentserverandroid.activity.LoginActivity;
import com.dunghn2792.assignmentserverandroid.activity.MainActivity;
import com.dunghn2792.assignmentserverandroid.model.Sneaker;
import com.dunghn2792.assignmentserverandroid.model.User;
import com.squareup.picasso.Picasso;

import static com.dunghn2792.assignmentserverandroid.api.Constants.ROOT_URL;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ImageView imgAvatar;
    private TextView fullName, Email, Phone;
    private Button btnLogout;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        MainActivity activity = (MainActivity) getActivity();
        String idProfile = activity.getMyData();

        imgAvatar = rootView.findViewById(R.id.imgProfile);
        fullName = rootView.findViewById(R.id.fullName);
        Email = rootView.findViewById(R.id.email);
        Phone = rootView.findViewById(R.id.phone);
        btnLogout = rootView.findViewById(R.id.btnLogOut);

        for (User item : LoginActivity.userList) {
            if (item.getId().equals(idProfile)) {
                Picasso.get().load(ROOT_URL + item.getAvatar()).into(imgAvatar);
                fullName.setText(item.getFullName());
                Email.setText(item.getEmail());
                Phone.setText(item.getPhone());
            }
        }

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
        return rootView;
    }
}
