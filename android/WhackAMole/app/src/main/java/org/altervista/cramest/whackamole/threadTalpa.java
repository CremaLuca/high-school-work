package org.altervista.cramest.whackamole;

/**
 * Created by cremaluca on 12/03/2016.
 */
public class threadTalpa extends  Thread{

    public static threadTalpa instance;
    Talpa[] talpe;
    public threadState stato;
    private float gameTime;

    public threadTalpa(Talpa[] talpe){
        this.talpe = talpe;
        System.out.println("Thread creato");
        instance = this;
    }

    public void blocca(){
        stato = threadState.spento;
        uccidiTalpe();
        System.out.println("Thread bloccato");
    }

    public boolean eVivo(){
        return (stato == threadState.attivo) ? true : false;
    }

    public void run(){
        stato = threadState.attivo;

        int freq = 100; //ogni quanti millesimi deve andare il thread
        long lastFrameTime = System.currentTimeMillis();
        long nextCheck = System.currentTimeMillis();
        float deltaTime = freq; //boh tanto dopo si setta da solo
        int gameTime = 0;
        int nextSpawn = 300;
        System.out.println("Thread partito");

        while(stato == threadState.attivo){
            if(System.currentTimeMillis() >= nextCheck) {
                deltaTime = (int)(System.currentTimeMillis() - lastFrameTime);
                gameTime += deltaTime;
                Update(deltaTime/1000);
                //cose iniziali

                if(gameTime >= nextSpawn){

                    talpe[talpaRandom()].inVita();

                    nextSpawn = gameTime +  300;
                }

                //cose finali
                lastFrameTime = System.currentTimeMillis();
                nextCheck = System.currentTimeMillis() + freq;
            }
        }
        System.out.println("Thread fermato");
        this.interrupt();
    }

    private void uccidiTalpe(){
        for(int i=0;i<talpe.length;i++){
            if(talpe[i].isAlive()){
                talpe[i].Muori();
            }
        }
    }

    public int talpaRandom(){
        int n = (int)(Math.random() * talpe.length);
        //mettiamo un limite alle volte che controlliamo se la talpa e' viva
        for(int i=0;i<=talpe.length+10 && talpe[n].isAlive();i++){
            n = (int)(Math.random() * talpe.length);
        }
        return n;
    }

    public void Update(float deltaTime){

        for(int i=0;i<talpe.length;i++){
            talpe[i].Update(deltaTime);
        }
    }

    public enum threadState{
        attivo,
        spento
    };
}