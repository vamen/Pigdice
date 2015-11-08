package com.vivekbalachandra.pigdice;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;


public class Mainplay extends android.support.v4.app.Fragment {
    static int count = 0;
    static int score;
    RelativeLayout relativeLayout;
    int tern;

    @Override
    public void onResume() {
        super.onResume();
        Log.i("onresume","called");
        ternscore=0;
        userscore=0;
        cpuscore=0;
        yourscoretext.setText(0 + " ");
        cpuscoretext.setText(0 + " ");
        Terntext.setText(0 + "");
    }

    View root;
    Button Roll;
    Button Hold;
    ImageView Dice;
    TextView status;
    int userscore = 0;
    int cpuscore = 0;
    int id[] = {
            R.drawable.dice1,
            R.drawable.dice2,
            R.drawable.dice3,
            R.drawable.dice4,
            R.drawable.dice5,
            R.drawable.dice6
    };

    Handler handler = new Handler();
    int ternscore;
    TextView yourscoretext;
    TextView cpuscoretext;
    TextView Terntext;
    boolean rolling;
    boolean flag = false;
    Runnable timeRunnable=new Runnable() {
        @Override
        public void run() {
            setImage();
        }
    };
    Runnable mRunnable=new Runnable() {
        @Override
        public void run() {
            Log.d("cpurolldone", "error  check");
            handler.removeCallbacks(timeRunnable);
            cpuscore = cpuscore + ternscore;
            cpuscoretext.setText(cpuscore + ".");
            staysleep();
            FragmentManager fragmentManager=getFragmentManager();
            Result r=new Result(cpuscore<userscore);
            r.show(fragmentManager, "result");
            Hold.setVisibility(View.VISIBLE);
            Roll.setVisibility(View.VISIBLE);
            Hold.setEnabled(true);
            Roll.setEnabled(true);
            userscore=0;
            cpuscore=0;
            flag=false;
            yourscoretext.setText(0 + " ");
            cpuscoretext.setText(0 + " ");
            Terntext.setText(0 + "");
            ternscore = 0;
            tern = 0;
           handler.removeCallbacks(mRunnable);

        }
    };

    private void staysleep() {
        TextView substatus= (TextView) root.findViewById(R.id.substatus);
        for(int i=0;i<5000;i++)
        {
            ;
        }
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            setImage();
            return;

        }
    };

    public Mainplay() {
        // Required empty public constructor
    }

    void setImage() {
        Log.d("rolldone", "error  check in set image " + count);
        if(count==0)Roll.setEnabled(false);
        count++;
        int i = new Random().nextInt(6);
        Dice.refreshDrawableState();
        Dice.setImageResource(id[i]);

        if (count < 6) {
            handler.removeCallbacks(runnable);
            handler.postDelayed(runnable, 500L);

        } else {

            Log.i("PASS","pass1");
            if (i == 0) {
                handler.removeCallbacks(runnable);
                Log.i("PASS","pass2");
                Snackbar.make(relativeLayout, "Turn Change", Snackbar.LENGTH_LONG).show();
                Log.i("PASS", "pass3");
                if (!flag) {
                    status.setText("CPU-Tern");
                    flag = true;
                    ternscore = 0;
                    flag=true;
                    rolling=false;
                    Roll.setEnabled(true);
                    tern=0;
                    Log.i("PASS", "pass4");
                    count=0;
                    setupcputer();
                    Thread thread=new Thread(new Runnable() {
                        @Override
                        public void run() {
                            startcpu();
                        }
                    });
                    thread.run();
                    Log.i("PASS", "pass5");
                    return;

                } else {
                    handler.removeCallbacks(mRunnable);
                    handler.post(mRunnable);
                    Roll.setEnabled(true);
                    Hold.setEnabled(true);
                    rolling=false;
                    status.setText("Your Tern :");
                    ternscore = 0;
                    flag = false;
                    count=0;
                    return;
                }

            } else {
                score = i % 6;
                ternscore += score + 1;
                Terntext.setText(ternscore + " ");
                count = 0;
                rolling = false;
                //Roll.setEnabled(true);
                if (!flag) {
                    Roll.setEnabled(true);
                    flag=false;
                }
                Toast.makeText(getActivity(), "score is " + score + 1, Toast.LENGTH_SHORT).show();
                Log.d("rolldone", "error  check in run");
                return;
            }


        }
        Log.d("rolldone", "error  check in set image " + count);
        return;
    }

    private void setupcputer() {
        Hold.setVisibility(View.GONE);
        Roll.setVisibility(View.GONE);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Infl ate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_mainplay, container, false);
        relativeLayout = (RelativeLayout) root.findViewById(R.id.rellayout);
        Roll = (Button) root.findViewById(R.id.Roll);
        Hold = (Button) root.findViewById(R.id.Hold);
        Dice = (ImageView) root.findViewById(R.id.dice);
        status = (TextView) root.findViewById(R.id.status);
        yourscoretext = (TextView) root.findViewById(R.id.uscore);
        cpuscoretext = (TextView) root.findViewById(R.id.cscore);
        Terntext = (TextView) root.findViewById(R.id.turnscore);
        yourscoretext.setText(0 + " ");
        cpuscoretext.setText(0 + " ");
        Terntext.setText(0 + "");
        return root;
    }

    void startcpu() {
        tern = 1;
        long val = 5000L;
        handler.removeCallbacks(runnable);
        Log.i("PASS", "pass6");
        while (tern < 6) {
            if (rolling == false && flag == true) {
                handler.postDelayed(timeRunnable, val * tern);
                Log.d("cpuroll", "noerror " + tern);
                tern++;
            }
        }
        Log.i("PASS", "pass7");
        handler.postDelayed(mRunnable,(tern+2)*val);

        //handler.removeCallbacks(runnable);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        status.setText("your tern :");
        Dice.setImageResource(R.drawable.dice);
        Roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!rolling) {
                    rolling = true;
                    handler.removeCallbacks(runnable);
                    handler.postDelayed(runnable, 1000L);

                }

            }

        });
        Hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userscore += ternscore;
                flag = true;
                ternscore = 0;
                yourscoretext.setText(userscore + " ");
                status.setText("CPU TERN :");
                Snackbar.make(relativeLayout, "Turn Change", Snackbar.LENGTH_LONG).show();
                setupcputer();
                status.setText("Your Tern");
                startcpu();


            }
        });

    }

}
