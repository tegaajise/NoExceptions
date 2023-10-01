package com.example.crescentstudentutilityapp20.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.crescentstudentutilityapp20.LoginActivity;
import com.example.crescentstudentutilityapp20.R;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Calendar;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private TextView date;
    private TextView studentName;

    private Button exit;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
                /*VARIABLE INITIALIZATIONS WONT WORK BECAUSE OF FRAGMENT*/
        date = (TextView)root.findViewById(R.id.tvDatetxt);
        //https://www.youtube.com/watch?v=Le47R9H3qow - Variable that tracks live date
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        date.setText(currentDate);

        studentName = (TextView)root.findViewById(R.id.tvStudentName);
        studentName.setText(LoginActivity.name);

        exit = (Button)root.findViewById(R.id.bExit);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
            }
        });
        return root;


    }


}
