package com.mtl.async_and_thread;

/**
 * @author Matheus Martinelli
 * Created on *30-06-2025
 * martinelli.matheus2@gmail.com
 */
public class Velho {
    /*
    package com.mtl.async_and_thread;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    Button btnIniciar, btnPausar, btnBaixar;
    TextView txtCentral;
    ImageView imgNet;
    private boolean flag = true;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ligando os botões com o layout
        btnIniciar = findViewById(R.id.btnIniciar);
        btnPausar = findViewById(R.id.btnPausar);
        btnBaixar = findViewById(R.id.btnBaixar);
        txtCentral = findViewById(R.id.txtCentral);
        imgNet = findViewById(R.id.imgNet);
    }

    // Método chamado ao clicar no botão Iniciar
    public void iniciar(View v) {
        Toast.makeText(this, "xp!", Toast.LENGTH_SHORT).show();
        flag = true;
        new Thread(){
            @Override
            public void run() {
                while (flag){

                    Log.i("APPxt","Executando");
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            txtCentral.setText(txtCentral.getText()+"\nExecutando....");
                            Toast.makeText(MainActivity.this, "xp!", Toast.LENGTH_SHORT).show();
                        }
                    });
                    try{
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();



    }

    // Método chamado ao clicar no botão Pausar
    public void pausar(View v) {
        flag = false;
        Toast.makeText(this, "Pausado!", Toast.LENGTH_SHORT).show();
    }
    public void cargaImage(View v){
        try{
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Carregando Imagem...");
            progressDialog.show();



        new Thread(){
            @Override
            public void run() {
                try{
                    URL url = new URL("https://i0.wp.com/assets.b9.com.br/wp-content/uploads/2025/05/google-novo-logo-g.jpg?fit=1920%2C1080&ssl=1");
                    InputStream is = url.openStream();
                    final Bitmap bitmap = BitmapFactory.decodeStream(is);
                    Log.i("APPxt","Img Carregada");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imgNet.setImageBitmap(bitmap);
                        }
                    });
                    progressDialog.dismiss();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        }catch (Exception e){
            e.printStackTrace();
        }



     //   Toast.makeText(this, "Carregado!", Toast.LENGTH_SHORT).show();


    }
}
     */
}
