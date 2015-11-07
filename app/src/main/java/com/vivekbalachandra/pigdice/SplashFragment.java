package com.vivekbalachandra.pigdice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.zip.Inflater;

/**
 * Created by Vivek Balachandran on 10/31/2015.
 */

public class SplashFragment extends Fragment {
    View root;
    MyFraginterface minterface;
    Button PlayWithfriends;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root=inflater.inflate(R.layout.splashfragment,container,false);
        PlayWithfriends= (Button) root.findViewById(R.id.pwf);
        PlayWithfriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"yet to impliment",Toast.LENGTH_LONG).show();
            }
        });
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        minterface=(MyFraginterface)getActivity();
        Button button=(Button)root.findViewById(R.id.PLAY);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                minterface.Replace();
            }
        });
    }
}
