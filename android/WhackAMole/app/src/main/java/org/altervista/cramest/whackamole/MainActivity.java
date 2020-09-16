package org.altervista.cramest.whackamole;

import android.annotation.TargetApi;
import android.app.usage.UsageEvents;
import android.content.Intent;
import android.media.Image;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.EventLog;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.EventListenerProxy;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static MainActivity instance;
    public ImageButton[] bottoniTalpa = new ImageButton[9];
    public List<Talpa> talpe = new ArrayList<Talpa>();;
    int punti = 0;
    TextView testoPunti;
    public boolean eAttivo = false;
    private threadTalpa tp;
    private TimeThread tt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //imposto la variabile statica dell'istance come questa
        instance = this;
        //mi salvo i bottoni talpa
        setBottoniTalpa();
        //e creo il nuovo thread con array di talpe
        tp = new threadTalpa(listTalpaToArray());
        //prendo i dati passati dalla vecchia activity
        ContoAllaRovescia conto = new ContoAllaRovescia(5.5f,(FrameLayout)findViewById(R.id.FrameContoRovescia));
        conto.start();
    }

    void Reset(){
        Talpa[] arrayTalpe = new Talpa[talpe.size()];
        talpe.toArray(arrayTalpe);
        tp = new threadTalpa(arrayTalpe);
    }

    void Start(){
        Intent intent = getIntent();
        int diff = intent.getIntExtra("diff", 1);
        System.out.println("Difficolta caricata: "+diff);
        eAttivo = true;
        tt = new TimeThread(diff*10,tp);
        tp.start();
        tt.start();
    }

    Talpa[] listTalpaToArray(){
        Talpa[] arrayTalpe = new Talpa[talpe.size()];
        return talpe.toArray(arrayTalpe);
    }

    void setBottoniTalpa(){
        talpe.add(new Talpa((ImageButton) findViewById(R.id.imageButton), 0));
        talpe.add(new Talpa((ImageButton) findViewById(R.id.imageButton2), 0));
        talpe.add(new Talpa((ImageButton) findViewById(R.id.imageButton3), 0));
        talpe.add(new Talpa((ImageButton) findViewById(R.id.imageButton4), 0));
        talpe.add(new Talpa((ImageButton) findViewById(R.id.imageButton5), 0));
        talpe.add(new Talpa((ImageButton) findViewById(R.id.imageButton6), 0));
        talpe.add(new Talpa((ImageButton) findViewById(R.id.imageButton7), 0));
        talpe.add(new Talpa((ImageButton) findViewById(R.id.imageButton8), 0));
        talpe.add(new Talpa((ImageButton) findViewById(R.id.imageButton9), 0));
        testoPunti = (TextView)findViewById(R.id.textPoints);
        setImageButtonListener();
    }

    void setImageButtonListener(){
        for(int i=0;i<talpe.size();i++){
            final Talpa talpa = talpe.get(i);
            final ImageButton bottone = talpa.getButton();
            bottone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SonoStatoPremuto(talpa);
                }
            });
            talpa.onStateChangedListener = new OnStateChangedListener() {
                @Override
                public void OnStateChangedOccurred(OnStateChanged event) {
                    cambiaImmagine(talpa,talpa.isAlive());
                }
            };
        }
    }

    public void cambiaImmagine(final Talpa t,final boolean conTalpa){
        runOnUiThread(new Runnable() {
            public void run() {
                if (conTalpa) {
                    t.bottone.setImageDrawable(MainActivity.instance.getResources().getDrawable(R.drawable.molehole));
                } else {
                    t.bottone.setImageDrawable(MainActivity.instance.getResources().getDrawable(R.drawable.emptyhole));
                }
            }
        });
    }

    public void SonoStatoPremuto(Talpa talpa){
        if(threadTalpa.instance.eVivo()) {
            if (talpa.isAlive()) {
                talpa.Muori();
                punti++;
            } else {
                punti--;
            }
            testoPunti.setText(punti + "");
        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(findViewById(R.id.textTimeLeft).getContext(), MainMenu.class);
        tt.blocca();
        startActivity(i);
    }

    public class TimeThread extends Thread{

        private float time;
        private threadTalpa thread;
        private TextView tw;
        private FrameLayout frame;

        public TimeThread(float time,threadTalpa thread){
            this.time = time;
            this.thread = thread;
        }

        public void run(){
            tw = (TextView) findViewById(R.id.textTimeLeft);
            int freq = 100; //ogni quanti millesimi deve andare il thread
            long lastFrameTime = System.currentTimeMillis();
            long nextCheck = System.currentTimeMillis() + freq;
            float deltaTime = freq; //boh tanto dopo si setta da solo
            int gameTime = 0;
            while(time > 0){
                if(System.currentTimeMillis() >= nextCheck) {
                    deltaTime = (int) (System.currentTimeMillis() - lastFrameTime);
                    System.out.println("deltaTime: " + deltaTime);
                    time -= deltaTime / 1000;
                    cambiaTesto();
                    lastFrameTime = System.currentTimeMillis();
                    nextCheck = System.currentTimeMillis() + freq;
                }
            }
            thread.blocca();
            frame = (FrameLayout)findViewById(R.id.FrameContoRovescia);
            mostraFrame();
        }

        private void mostraFrame() {
            runOnUiThread(new Runnable() {
                public void run() {
                    frame.setVisibility(View.VISIBLE);
                    TextView testo = (TextView) frame.findViewById(R.id.textContoRovescia);
                    testo.setText("FINITO!");
                }
            });
        }

        public void blocca(){
            time = 0;
        }

        private void cambiaTesto(){
            runOnUiThread(new Runnable() {
                public void run() {
                    tw.setText(Math.round(time) + "");
                }
            });
        }
    }

    public class ContoAllaRovescia extends Thread{

        private float time;
        private TextView testo;
        private FrameLayout frame;
        public ContoAllaRovescia(float time,FrameLayout frame){
            this.time = time;
            this.frame = frame;
            this.testo = (TextView)frame.findViewById(R.id.textContoRovescia);
        }

        public void run(){
            int freq = 100; //ogni quanti millesimi deve andare il thread
            long lastFrameTime = System.currentTimeMillis();
            long nextCheck = System.currentTimeMillis() + freq;
            float deltaTime = freq; //boh tanto dopo si setta da solo
            int gameTime = 0;
            while(time > 0) {
                if (System.currentTimeMillis() >= nextCheck) {
                    deltaTime = (int) (System.currentTimeMillis() - lastFrameTime);
                    System.out.println("deltaTime: " + deltaTime);
                    time -= deltaTime / 1000;
                    cambiaTesto();
                    lastFrameTime = System.currentTimeMillis();
                    nextCheck = System.currentTimeMillis() + freq;
                }
            }
            eliminaFrame();
            Start();
        }

        private void eliminaFrame(){
            runOnUiThread(new Runnable() {
                public void run() {
                    frame.setVisibility(View.GONE);
                }
            });
        }

        private void cambiaTesto(){
            runOnUiThread(new Runnable() {
                public void run() {
                    int tempo = Math.round(time);
                    if(tempo != 0) {
                        testo.setText(tempo + "");
                    }else{
                        testo.setText("VIA!");
                    }
                }
            });
        }
    }

}