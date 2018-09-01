package com.example.money.bee_game;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends Activity {

    ImageView bee_imgleft,ball_img;
    bluetooth_40 Bl_0;
    RelativeLayout bu_xml,top_xml,righ_xml,lef_xml,main_xml,relayout2,relayout1;
    float setfirst_xR,setfirst_xL,setfirst_y,setfirst_zH,setfirst_zL;
    DisplayMetrics v;
    ImageView []beeimg =new ImageView[5];
    ImageView catch_usenet;
    int randomshow =0;
    Thread thread1,thread2;
    RelativeLayout.LayoutParams layoutParams,bee1Params,bee2Params,bee3Params;
    private MediaPlayer mediaPlayer;
    int pc_height,pc_width;
    DisplayMetrics displaymetrics;
    ListView listView_use;
    //ArrayAdapter<String> btArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        Find_alluse();
         v =new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(v);

        //righ_xml.setVisibility(View.VISIBLE);
       Bl_0 =new bluetooth_40(this);
        thread1 =new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                        while (true){
                            Message s1 =new Message();
                            s1.what =1;
                            mHandler.sendMessage(s1);
                            Thread.sleep(100);

                        }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.start();
        listView_use.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("'jim"," onItemClick :"+Bl_0.mBleName.get(position));
                Bl_0.DEVICE_adress =Bl_0.mBleName.get(position);
                Bl_0.Connectoutside();
                changepage();
            }
        });
        thread2 =new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true){
                        Message s1 =new Message();
                        s1.what =2;
                        mHandler.sendMessage(s1);
                        Thread.sleep(400);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread2.start();

    }
    private  void changepage(){
        if(Bl_0.BLe_stus==true){

            relayout2.setVisibility(View.GONE);
            relayout1.setVisibility(View.VISIBLE);

        }
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.button:
               // bee_imgleft.layout(bee_imgleft.getLeft(),bee_imgleft.getTop(),bee_imgleft.getRight()+50,bee_imgleft.getBottom());

                randomshow =(int)(Math.random()*3);
               // Log.i("jim","random"+randomshow);

                break;
            case R.id.right_btn:
                setfirst_xR =Bl_0.mpu_x;
                Log.i("jim","setright:   "+setfirst_xR);
                Log.i("jim","densityDpi:   "+v.widthPixels);
                Log.i("jim","densityDpi:   "+v.heightPixels);  //平板1920*1200
                righ_xml.setVisibility(View.GONE);
                lef_xml.setVisibility(View.VISIBLE);
                break;
            case R.id.left_btn:
                setfirst_xL=Bl_0.mpu_x;
                Log.i("jim","setleft:   "+setfirst_xL);
                lef_xml.setVisibility(View.GONE);
                top_xml.setVisibility(View.VISIBLE);
                break;
            case R.id.top_btn:
                setfirst_zH=Bl_0.mpu_z;
                Log.i("jim","settop:   "+setfirst_zH);
                top_xml.setVisibility(View.GONE);
                bu_xml.setVisibility(View.VISIBLE);
                break;
            case R.id.buttom_btn_btn:
                setfirst_zL=Bl_0.mpu_z;
                Log.i("jim","setdown:   "+setfirst_zL);
                bu_xml.setVisibility(View.GONE);
                main_xml.setVisibility(View.VISIBLE);
                break;
        }
    }
    private  void Find_alluse(){
        bu_xml    =(RelativeLayout)findViewById(R.id.buttom_xml);
        top_xml   =(RelativeLayout)findViewById(R.id.top_xml);
        righ_xml  =(RelativeLayout)findViewById(R.id.right_xml);
        lef_xml   =(RelativeLayout)findViewById(R.id.left_xml);
        main_xml =(RelativeLayout)findViewById(R.id.main_xml);
        catch_usenet=(ImageView)findViewById(R.id.catch_net);
        ball_img =(ImageView)findViewById(R.id.ball_img);
        listView_use=(ListView)findViewById(R.id.listView);
        //bee_imgleft=(ImageView)findViewById(R.id.bee_lftig);
        beeimg[0]=(ImageView)findViewById(R.id.bee_lftig1);
        beeimg[1]=(ImageView)findViewById(R.id.bee_lftig2);
        beeimg[2]=(ImageView)findViewById(R.id.bee_lftig3);
       // beeimg[0].setVisibility(View.VISIBLE);
        relayout2=(RelativeLayout)findViewById(R.id.bluelist) ;
        relayout1=(RelativeLayout)findViewById(R.id.main_xml) ;
        //-------------------------------------------------------------
        layoutParams =(RelativeLayout.LayoutParams) catch_usenet.getLayoutParams();
        bee1Params =(RelativeLayout.LayoutParams) beeimg[0].getLayoutParams();
        bee2Params =(RelativeLayout.LayoutParams) beeimg[1].getLayoutParams();
        bee3Params =(RelativeLayout.LayoutParams) beeimg[2].getLayoutParams();
        displaymetrics =new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        pc_height =displaymetrics.heightPixels;
        pc_width =displaymetrics.widthPixels;
        //Log.i("'jim"," pc_height :"+pc_height);
       // Log.i("'jim"," pc_width :"+pc_width);
        mediaPlayer =new MediaPlayer();
        mediaPlayer =MediaPlayer.create(this, R.raw.music1);
        mediaPlayer.start();
        //---------------------------------------------------------------
        Bl_0.mBleName =new  ArrayList<>();
        Bl_0.btArrayAdapter= new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,Bl_0.mBleName );
        listView_use.setAdapter(Bl_0.btArrayAdapter);
    }
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                   movehand();
                    break;
                case  2:
                    move_useobj();
                    break;

            }
        }
    };
    private  void movehand(){
       if(Bl_0.mpu_movestus==true){
           // catch_usenet.layout( catch_usenet.getLeft(), catch_usenet.getTop(), catch_usenet.getRight()+50,catch_usenet.getBottom());

          if(layoutParams.leftMargin + 50<pc_width - layoutParams.width){
              layoutParams.leftMargin+=50;
              catch_usenet.setLayoutParams(layoutParams);
           }
        }else{
            //catch_usenet.layout( catch_usenet.getLeft()-50, catch_usenet.getTop(), catch_usenet.getRight(),catch_usenet.getBottom());
           if(layoutParams.leftMargin - 50>0){
               layoutParams.leftMargin-=50;
               catch_usenet.setLayoutParams(layoutParams);
           }

        }
        if(Bl_0.mpu_moveup==true){
            //catch_usenet.layout( catch_usenet.getLeft(), catch_usenet.getTop()-10, catch_usenet.getRight(),catch_usenet.getBottom());
            if(layoutParams.topMargin - 50>0){
                layoutParams.topMargin-=50;
                catch_usenet.setLayoutParams(layoutParams);
            }

        }
        if(Bl_0.mpu_moveup==false){
           // catch_usenet.layout( catch_usenet.getLeft(), catch_usenet.getTop(), catch_usenet.getRight(),catch_usenet.getBottom()+10);
            if(layoutParams.topMargin +50<1200-layoutParams.height){
                layoutParams.topMargin+=50;
                catch_usenet.setLayoutParams(layoutParams);
            }
        }
        if(Bl_0.swing_stus==true){
           // ball_img.setVisibility(View.GONE);
          /*  Intent intent = new Intent(Intent.ACTION_DIAL);
            Uri data = Uri.parse("tel:" +"0912496861");
            intent.setData(data);
            startActivity(intent);*/
        }else{
           // ball_img.setVisibility(View.VISIBLE);
        }

    }
    private  void move_useobj(){
        //移動物件，6個物件
        //

        if(bee1Params.leftMargin + 50<pc_width - bee1Params.width){
            bee1Params.leftMargin+=50;
            beeimg[0].setLayoutParams(bee1Params);
        }else{
            bee1Params.leftMargin=0;
            beeimg[0].setLayoutParams(bee1Params);
        }
        //左2
        if(bee2Params.leftMargin + 50<pc_width - bee2Params.width){
            bee2Params.leftMargin+=50;
            beeimg[1].setLayoutParams(bee2Params);
        }else{
            bee2Params.leftMargin=0;
            beeimg[1].setLayoutParams(bee2Params);
        }
        //左3
        if(bee3Params.leftMargin + 50<pc_width - bee3Params.width){
            bee3Params.leftMargin+=50;
            beeimg[2].setLayoutParams(bee3Params);
        }else{
            bee3Params.leftMargin=0;
            beeimg[2].setLayoutParams(bee3Params);
        }



    }
}
