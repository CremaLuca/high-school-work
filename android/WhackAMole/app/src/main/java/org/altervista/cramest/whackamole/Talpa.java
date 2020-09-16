package org.altervista.cramest.whackamole;

import android.app.usage.UsageEvents;
import android.support.v4.content.ContextCompat;
import android.widget.ImageButton;

import java.util.EventListener;
import java.util.EventObject;

/**
 * Created by cremaluca on 09/03/2016.
 */
public class Talpa {

    public OnStateChangedListener onStateChangedListener;
    ImageButton bottone;
    float timeLeft;
    private boolean hoAggiornatoState = true;

    public Talpa(ImageButton bottone, float timeLeft){
        this.bottone = bottone;
        this.timeLeft = timeLeft;
    }

    public void Update(float deltaTime){
        if(timeLeft > 0) {
            timeLeft -= deltaTime;
        }else{
            if(!hoAggiornatoState){
                chiamaOnStateChanged();
                hoAggiornatoState = true;
            }
        }
    }

    public float getTimeLeft(){
        return timeLeft;
    }

    public void Muori(){
        timeLeft = 0;
        chiamaOnStateChanged();
    }

    public ImageButton getButton(){
        return bottone;
    }

    public boolean isAlive(){
        return (timeLeft> 0) ? true : false;
    }

    public void inVita(){
        hoAggiornatoState = false;
        timeLeft = 0.7f;
        chiamaOnStateChanged();
    }

    private void chiamaOnStateChanged(){
        onStateChangedListener.OnStateChangedOccurred(new OnStateChanged(this));
    }

}



class OnStateChanged extends EventObject {
    public OnStateChanged(Talpa source){
        super(source);
    }
}

interface OnStateChangedListener extends EventListener{
    public void OnStateChangedOccurred(OnStateChanged event);
}