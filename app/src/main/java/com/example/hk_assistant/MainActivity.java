package com.example.hk_assistant;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int[] images = {R.drawable.ic_quimera, R.drawable.ic_corporacion, R.drawable.ic_acracia, R.drawable.ic_abismales, R.drawable.ico_skull}, auxImages= {R.drawable.ic_quimera, R.drawable.ic_corporacion, R.drawable.ic_acracia, R.drawable.ic_abismales, R.drawable.ico_skull}, valores = {-2, -1, 0, 1, 2}, auxValores = {-2, -1, 0, 1, 2};
    int[] backgrounds = {R.drawable.anita, R.drawable.bar_lugura, R.drawable.campeones_hk, R.drawable.demonoide, R.drawable.ira_corporativa, R.drawable.los_gemelos, R.drawable.orden_y_caos_hk, R.drawable.petrov, R.drawable.silverbullet};
    private TextView textVida, textVoluntad, textValor;
    private int vida, voluntad, contador = 0, faccionBorrada = 0, pivoteFacciones = images.length, valorBorrado = 0, pivoteValores = valores.length;
    private ImageView imgFaccion;
    private ConstraintLayout fondo;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        cambiarBackground();
    }

    //Método para salir de la app

    public void onBackPressed() {

        if(contador == 0){
            Toast.makeText(this,"Presione nuevamente para salir",Toast.LENGTH_SHORT).show();
            contador++;
        }
        else{
            finishAffinity();
        }

        new CountDownTimer(3000,1000){

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                contador = 0;
            }
        }.start();

    }

    //Métodos suma y resta vida

    public void sumarVida(View view){
        textVida = (TextView)findViewById(R.id.textView_Vida);
        vida = Integer.parseInt(textVida.getText().toString());

        textVida.setText(String.valueOf(vida+1));
    }

    public void restarVida(View view){
        textVida = (TextView)findViewById(R.id.textView_Vida);
        vida = Integer.parseInt(textVida.getText().toString());

        if(vida-1 >= 0) {
            textVida.setText(String.valueOf(vida - 1));
        }
        else{
            textVida.setText("0");
        }
    }

    //Métodos suma y resta voluntad

    public void sumarVoluntad(View view){
        textVoluntad = (TextView)findViewById(R.id.textView_Voluntad);
        voluntad = Integer.parseInt(textVoluntad.getText().toString());

        textVoluntad.setText(String.valueOf(voluntad+1));
    }
    public void restarVoluntad(View view){
        textVoluntad = (TextView)findViewById(R.id.textView_Voluntad);
        voluntad = Integer.parseInt(textVoluntad.getText().toString());

        if(voluntad-1 >= 0) {
            textVoluntad.setText(String.valueOf(voluntad - 1));
        }
        else{
            textVoluntad.setText("0");
        }
    }

    //Métodos Botones Virtud

    public void generarValor(View view){
        textValor = (TextView)findViewById(R.id.textView_resultadoValor);

        if( valorBorrado == 5){
            llenarArrayValores(valores);
            valorBorrado = 0;
            pivoteValores = valores.length;
        }
        final int random = new Random().nextInt(pivoteValores);

        textValor.setText(String.valueOf(valores[random]));
        removeIndex(valores, random);
        valorBorrado++;
        pivoteValores--;
    }

    public void generarFaccion(View view){
        imgFaccion = (ImageView)findViewById(R.id.imageView_faccion);

        if (faccionBorrada == 5){
            llenarArrayFacciones(images);
            faccionBorrada = 0;
            pivoteFacciones = images.length;
        }
        Random rand = new Random();
        int obtenido = rand.nextInt(pivoteFacciones);
        imgFaccion.setImageResource(images[obtenido]);
        removeIndex(images, obtenido);
        faccionBorrada++;
        pivoteFacciones--;
    }

    public void llenarArrayFacciones(int[] array){
       for (int i=0; i<auxImages.length; i++) {
           array[i] = auxImages[i];
       }
    }

    public void llenarArrayValores(int[] array){
        for(int i=0; i<auxValores.length; i++){
            array[i] = auxValores[i];
        }
    }

    private static void removeIndex(int[] array, int index) {
        int i = index;
        for (; i < array.length - 1; i++) {
            array[i] = array[i + 1];
        }
        array[i] = 0;

    }

    public void resetearVirtud(View view){
        imgFaccion = (ImageView)findViewById(R.id.imageView_faccion);
        textValor = (TextView)findViewById(R.id.textView_resultadoValor);

        if((imgFaccion.getDrawable()!=null) || (textValor.getText()!="")){
            for(int i=0; i<5; i++){   //Se vacían ambos arrays con los que trabaja el sistema de virtud de la app
                removeIndex(images, i);
                removeIndex(valores, i);
            }
            //Se llenan los arrays con los que trabaja el sistema de virtud de la app
            llenarArrayFacciones(images);
            faccionBorrada = 0;
            pivoteFacciones = images.length;
            imgFaccion.setImageDrawable(null);

            llenarArrayValores(valores);
            valorBorrado = 0;
            pivoteValores = valores.length;
            textValor.setText("");
        }
    }

    public void cambiarBackground(View view){
        fondo = (ConstraintLayout)findViewById(R.id.act_Main);

        Random rand = new Random();
        int obtenido = rand.nextInt(backgrounds.length);

        fondo.setBackgroundResource(backgrounds[obtenido]);
    }

    public void cambiarBackground(){
        fondo = (ConstraintLayout)findViewById(R.id.act_Main);

        Random rand = new Random();
        int obtenido = rand.nextInt(backgrounds.length);

        fondo.setBackgroundResource(backgrounds[obtenido]);
    }
}
