package com.vivekbalachandra.pigdice;

import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Vivek Balachandran on 11/7/2015.
 */
public class Result  extends DialogFragment implements View.OnClickListener{
        boolean win;
        Button okbutton;
        TextView text;
        public Result(boolean flag)
        {
            win=flag;
        }

    @Nullable
@Override
public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState){

        View view=inflater.inflate(R.layout.resultdialoug,null);
        text= (TextView) view.findViewById(R.id.result);
        okbutton=(Button)view.findViewById(R.id.button);
        if(win)text.setText("you Win");
        else text.setText("cpuWin");
        setCancelable(false);
        okbutton.setOnClickListener(this);
        return view;
         }

@Override
public void onClick(View v){
        dismiss();
        }

        }