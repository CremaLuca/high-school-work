package org.altervista.cramest.whackamole;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Start();
    }

    void Start(){
        btnListeners();
    }

    void btnListeners(){
        //10 Secondi
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creo un intento
                Intent i = new Intent(v.getContext(), MainActivity.class);
                // Inserisco i valori nell'intento
                i.putExtra("diff", 1);
                System.out.println("Difficolta messa a 1");
                // attivo la nuova Activity
                startActivity(i);
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creo un intento
                Intent i = new Intent(v.getContext(), MainActivity.class);
                // Inserisco i valori nell'intento
                i.putExtra("diff", 2);
                System.out.println("Difficolta messa a 2");
                // attivo la nuova Activity
                startActivity(i);
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creo un intento
                Intent i = new Intent(v.getContext(), MainActivity.class);
                // Inserisco i valori nell'intento
                i.putExtra("diff", 3);
                System.out.println("Difficolta messa a 3");
                // attivo la nuova Activity
                startActivity(i);
            }
        });
    }
}
