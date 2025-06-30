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

public class MainActivity extends AppCompatActivity {

    Button btnIniciar, btnPausar, btnBaixar;
    TextView txtCentral;
    ImageView imgNet, imgNet2;
    private boolean flag = true; // controla o loop de execução
    private Handler handler = new Handler(); // permite atualizar a UI a partir da thread secundária

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Conecta os elementos da tela com as variáveis do Java
        btnIniciar = findViewById(R.id.btnIniciar);
        btnPausar = findViewById(R.id.btnPausar);
        btnBaixar = findViewById(R.id.btnBaixar);
        txtCentral = findViewById(R.id.txtCentral);
        imgNet = findViewById(R.id.imgNet);
        imgNet2 = findViewById(R.id.imgNet2);
    }

    // Inicia um loop em background que atualiza o texto a cada segundo
    public void iniciar(View v) {
        Toast.makeText(this, "Iniciado!", Toast.LENGTH_SHORT).show(); // feedback inicial
        flag = true; // ativa a flag de controle

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (flag) { // enquanto a flag for true
                    Log.i("APPxt", "Executando"); // imprime no logcat

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            txtCentral.append("\nExecutando..."); // atualiza o texto na tela
                            Toast.makeText(MainActivity.this, "Rodando!", Toast.LENGTH_SHORT).show(); // toast a cada ciclo
                        }
                    });

                    try {
                        Thread.sleep(1000); // espera 1 segundo
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start(); // inicia a thread
    }

    // Para o loop de execução
    public void pausar(View v) {
        flag = false; // desativa o loop
        Toast.makeText(this, "Pausado!", Toast.LENGTH_SHORT).show(); // feedback
    }

    // Baixa imagem da internet usando uma Thread + ProgressDialog
    public void cargaImage(View v) {
        final ProgressDialog progressDialog = new ProgressDialog(this); // cria o dialog
        progressDialog.setTitle("Download");
        progressDialog.setMessage("Carregando Imagem...");
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show(); // exibe

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // URL da imagem que será baixada
                    URL url = new URL("https://i0.wp.com/assets.b9.com.br/wp-content/uploads/2025/05/google-novo-logo-g.jpg?fit=1920%2C1080&ssl=1");
                    InputStream is = url.openStream(); // abre a conexão
                    final Bitmap bitmap = BitmapFactory.decodeStream(is); // converte para imagem
                    is.close(); // fecha o stream

                    Log.i("APPxt", "Imagem carregada!");

                    // Atualiza a UI na thread principal
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imgNet.setImageBitmap(bitmap); // exibe a imagem
                            progressDialog.dismiss(); // fecha o dialog
                        }
                    });

                } catch (final MalformedURLException e) {
                    e.printStackTrace();
                    // erro de URL inválida
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "URL inválida!", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    });
                } catch (final IOException e) {
                    e.printStackTrace();
                    // erro ao conectar ou ler a imagem
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "Erro ao baixar imagem!", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    });
                }
            }
        }).start(); // inicia a thread
    }

    // Baixa a segunda imagem usando AsyncTask (classe separada)
    public void cargaImage2(View v) {
        CarregarImagemTask carregarImagemTask = new CarregarImagemTask(this, imgNet2); // instancia a task
        carregarImagemTask.execute("https://i0.wp.com/assets.b9.com.br/wp-content/uploads/2025/05/google-novo-logo-g.jpg?fit=1920%2C1080&ssl=1"); // executa com a URL
    }
}
