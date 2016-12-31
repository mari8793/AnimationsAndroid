package com.example.mari.animations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;

import static android.R.attr.button;

public class TransitionDemoActivity extends AppCompatActivity {

    ViewGroup myLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_demo);




        //seteamos el observador
        //detecte el click
        myLayout = (ViewGroup) findViewById(R.id.activity_transition_demo);
        myLayout.setOnTouchListener(
                new RelativeLayout.OnTouchListener() { //declara ontouch
                    public boolean onTouch(View v, MotionEvent m) {
                        handleTouch();
                        return true;//Sintaxis del onTouch
                    }

                }
        );// aquí termina el observador


        //seteamos observador Para el boton
        Button button = (Button) findViewById(R.id.mybutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleClick();
            }


            });



    }//metdo onCretae

    public void handleTouch() {//metodo publico no devuelve nada
        View view = findViewById(R.id.mybutton); //si es vista no necesita casteo
        //customizamos la animación
        Transition changeBounds = new ChangeBounds();
        changeBounds.setDuration(3000);
        changeBounds.setInterpolator(new OvershootInterpolator());
        TransitionManager.beginDelayedTransition(myLayout, changeBounds);//transition básica al layout
        //TransitionManager.beginDelayedTransition(myLayout);//transition básica al layout



        //seteamos los cambios en el layout
        RelativeLayout.LayoutParams params= new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT); //variable que contendra parametros de layout
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.ALIGN_END, RelativeLayout.TRUE);
        //Le pasamos los parametros al boton
                view.setLayoutParams(params);

        ViewGroup.LayoutParams lparams = view.getLayoutParams(); //sacando parametros al boton nombre, id, medidas
        lparams.width=500; //cambia ancho y alto
        lparams.height=350;
        view.setLayoutParams(lparams);
    }//handleTouch



    //Para el boton
    public void handleClick(){
        Button button = (Button) findViewById(R.id.mybutton);
        //cambiamos (seteamos) nuevos atributos
        RelativeLayout.LayoutParams params =new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        button.setLayoutParams(params);


        //Transition
        Transition changeBounds = new ChangeBounds();
        changeBounds.setDuration(3000);
        changeBounds.setInterpolator(new AccelerateInterpolator());
        TransitionManager.beginDelayedTransition(myLayout, changeBounds);


        ViewGroup.LayoutParams lparams = button.getLayoutParams(); //sacando parametros al boton nombre, id, medidas
        lparams.width=500; //cambia ancho y alto
        lparams.height=350;
        button.setLayoutParams(lparams);
    }

}
