package cn.doubleq666.bingo;

import static java.lang.Thread.sleep;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment3 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View mView;

    private int flag = 0;
    private int score = 0;
    private int[] cnt = {0, 0, 0, 0, 0, 0, 0};

    public BlankFragment3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment3.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment3 newInstance(String param1, String param2) {
        BlankFragment3 fragment = new BlankFragment3();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_blank3, container, false);
        mView.findViewById(R.id.textView3).setVisibility(View.INVISIBLE);
        ((TextView) mView.findViewById(R.id.textView4)).setText("总分：" + score);
        mView.findViewById(R.id.textView2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_blankFragment3_to_blankFragment2);
            }
        });
        
        mView.findViewById(R.id.imageView2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("miaowu", String.valueOf(flag));
                if (flag == 0) {
                    flag = 1;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (flag == 1) {
                                try {

                                    int[] getDice = {0, R.drawable.dice1, R.drawable.dice2, R.drawable.dice3, R.drawable.dice4, R.drawable.dice5, R.drawable.dice6};
                                    ImageView[] getImage = {mView.findViewById(R.id.imageView3), mView.findViewById(R.id.imageView15), mView.findViewById(R.id.imageView14), mView.findViewById(R.id.imageView16), mView.findViewById(R.id.imageView17), mView.findViewById(R.id.imageView18)};


                                    int a = Math.abs(SecureRandom.getInstance("SHA1PRNG").nextInt()) % 6 + 1;
                                    int b = Math.abs(SecureRandom.getInstance("SHA1PRNG").nextInt()) % 6 + 1;
                                    int c = Math.abs(SecureRandom.getInstance("SHA1PRNG").nextInt()) % 6 + 1;
                                    int d = Math.abs(SecureRandom.getInstance("SHA1PRNG").nextInt()) % 6 + 1;
                                    int e = Math.abs(SecureRandom.getInstance("SHA1PRNG").nextInt()) % 6 + 1;
                                    int f = Math.abs(SecureRandom.getInstance("SHA1PRNG").nextInt()) % 6 + 1;

                                    for (int i = 1; i <= 6; i++) {
                                        cnt[i] = 0;
                                    }
                                    cnt[a]++;
                                    cnt[b]++;
                                    cnt[c]++;
                                    cnt[d]++;
                                    cnt[e]++;
                                    cnt[f]++;

                                    mView.findViewById(R.id.imageView3).post(new Runnable() {
                                        @Override
                                        public void run() {
                                            ((ImageView) mView.findViewById(R.id.imageView3)).setImageResource(getDice[a]);
                                        }
                                    });

                                    mView.findViewById(R.id.imageView15).post(new Runnable() {
                                        @Override
                                        public void run() {
                                            ((ImageView) mView.findViewById(R.id.imageView15)).setImageResource(getDice[b]);
                                        }
                                    });

                                    mView.findViewById(R.id.imageView14).post(new Runnable() {
                                        @Override
                                        public void run() {
                                            ((ImageView) mView.findViewById(R.id.imageView14)).setImageResource(getDice[c]);
                                        }
                                    });

                                    mView.findViewById(R.id.imageView16).post(new Runnable() {
                                        @Override
                                        public void run() {
                                            ((ImageView) mView.findViewById(R.id.imageView16)).setImageResource(getDice[d]);
                                        }
                                    });

                                    mView.findViewById(R.id.imageView17).post(new Runnable() {
                                        @Override
                                        public void run() {
                                            ((ImageView) mView.findViewById(R.id.imageView17)).setImageResource(getDice[e]);
                                        }
                                    });

                                    mView.findViewById(R.id.imageView18).post(new Runnable() {
                                        @Override
                                        public void run() {
                                            ((ImageView) mView.findViewById(R.id.imageView18)).setImageResource(getDice[f]);
                                        }
                                    });

                                    sleep(50);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                } catch (NoSuchAlgorithmException e) {
                                    e.printStackTrace();
                                }
                            }

                        }
                    }).start();
                } else {
                    flag = 0;

                    if (cnt[4] == 4 && cnt[1] == 2) {
                        try {
                            setLine("金花");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        score += 300;
                    }//四4两2金花
                    else if (cnt[4] == 6) {
                        try {
                            setLine("六杯红");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        score += 260;
                    }//六4六杯红
                    else if (cnt[1] == 6) {
                        try {
                            setLine("遍地锦");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        score += 240;
                    }//六1遍地锦
                    else if (cnt[2] == 6 || cnt[3] == 6 || cnt[5] == 6 || cnt[6] == 6) {
                        try {
                            setLine("黑六勃");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        score += 230;
                    }//六任意黑六勃
                    else if (cnt[4] == 5) {
                        try {
                            setLine("五红");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        score += 220;
                    }//五4五红

                    else if (cnt[1] == 5 || cnt[2] == 5 || cnt[3] == 5 || cnt[5] == 5 || cnt[6] == 5) {
                        try {
                            setLine("五子登科");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        score += 210;
                        score += (cnt[1] * 1 + cnt[2] * 2 + cnt[3] * 3 + cnt[5] * 5 + cnt[6] * 6);
                    }//五任意五子登科

                    else if (cnt[4] == 4) {
                        try {
                            setLine("四点红");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        score += 200;
                        score += (cnt[1] * 1 + cnt[2] * 2 + cnt[3] * 3 + cnt[5] * 5 + cnt[6] * 6);
                    }//四4四点红


                    else if (cnt[1] == 1 && cnt[2] == 1 && cnt[3] == 1 && cnt[4] == 1 && cnt[5] == 1 && cnt[6] == 1) {
                        try {
                            setLine("对堂");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        score += 150;
                    }//六子对堂
                    else if ((cnt[1] == 3 && cnt[2] == 3) || (cnt[1] == 3 && cnt[3] == 3) || (cnt[1] == 3 && cnt[5] == 3) || (cnt[1] == 3 && cnt[6] == 3) || (cnt[2] == 3 && cnt[3] == 3) || (cnt[2] == 3 && cnt[5] == 3) || (cnt[2] == 3 && cnt[6] == 3) || (cnt[3] == 3 && cnt[5] == 3) || (cnt[3] == 3 && cnt[6] == 3) || (cnt[5] == 3 && cnt[6] == 3)) {
                        try {
                            setLine("对堂");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        score += 150;
                    }//对三对堂


                    else if (cnt[4] == 3) {
                        try {
                            setLine("三红");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        score += 90;
                    }//三4三红
                    else if (cnt[1] == 4 || cnt[2] == 4 || cnt[3] == 4 || cnt[5] == 4 || cnt[6] == 4) {
                        try {
                            setLine("四进");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        score += 80;
                    }//四任意四进
                    else if (cnt[4] == 2) {
                        try {
                            setLine("二举");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        score += 70;
                    }//二4二举
                    else if (cnt[4] == 1) {
                        try {
                            setLine("一秀");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        score += 60;
                    }//一4一秀

                    else {
                        try {
                            setLine("寄！");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        score += 1;
                    }


                    mView.findViewById(R.id.textView4).post(new Runnable() {
                        @Override
                        public void run() {
                            ((TextView) mView.findViewById(R.id.textView4)).setText("总分：" + score);
                        }
                    });
                }

            }
        });
        return mView;
    }

    public void setLine(String x) throws InterruptedException {
        mView.findViewById(R.id.textView3).setVisibility(View.VISIBLE);
        ((TextView) mView.findViewById(R.id.textView3)).setText(x);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    mView.findViewById(R.id.textView3).post(new Runnable() {
                        @Override
                        public void run() {
                            mView.findViewById(R.id.textView3).setVisibility(View.INVISIBLE);
                        }
                    });

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}