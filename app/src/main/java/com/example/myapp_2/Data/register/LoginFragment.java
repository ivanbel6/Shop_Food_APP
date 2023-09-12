package com.example.myapp_2.Data.register;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapp_2.R;
import com.example.myapp_2.UI.view.fragments.FirstFragment;

public class LoginFragment extends Fragment {
    public static int profile_num;
    private EditText editTextEmail, editTextPassword;
    private Button buttonLogin;

    private UserDAO userDAO;

    public LoginFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userDAO = new UserDAO(getActivity());
        userDAO.open();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.login, container, false);
        Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.fragment_transition_animation);
        anim.setDuration(170);
        view.startAnimation(anim);
        getActivity().findViewById(R.id.bottomNavigationView).setVisibility(View.GONE);
        editTextEmail = view.findViewById(R.id.editTextEmail);
        editTextPassword = view.findViewById(R.id.editTextPassword);
        buttonLogin = view.findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                if (userDAO.login(email, password)) {

                    Toast.makeText(getActivity(), "Login successful", Toast.LENGTH_SHORT).show();

                    // set profile_num to the user’s id
                    profile_num = userDAO.getUserByEmail(email).getId();

                    // go to another fragment
                    SharedPreferences.Editor editor = getActivity().getSharedPreferences("MY_PREFS_NAME", getActivity().MODE_PRIVATE).edit();
                    editor.putInt("profile_num", profile_num);
                    editor.apply();

                    FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.nav_container, new FirstFragment());
                    transaction.addToBackStack(null);
                    transaction.commit();
                } else {
                    Toast.makeText(getActivity(), "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button buttonReturn = view.findViewById(R.id.buttonReturn);

        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_container, new RegistrationFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        userDAO.close();
    }
}

