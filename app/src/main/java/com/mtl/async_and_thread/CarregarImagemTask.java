package com.mtl.async_and_thread;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class CarregarImagemTask extends AsyncTask<String, Void, Bitmap> {
    // classe assíncrona que baixa uma imagem de uma URL e exibe no ImageView com um progressDialog

    private ProgressDialog progressDialog;
    private Context context;
    private ImageView imgNet2;

    // construtor que recebe o contexto da activity e o ImageView onde a imagem será exibida
    public CarregarImagemTask(Context context, ImageView imgNet2) {
        this.context = context;
        this.imgNet2 = imgNet2;
    }

    // executa antes do doInBackground (inicia o loading)
    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context); // cria a janela de progresso
        progressDialog.setTitle("Download"); // título da janela
        progressDialog.setMessage("Carregando Imagem..."); // mensagem exibida
        progressDialog.setCancelable(false); // impede que o usuário cancele
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // estilo de loading (círculo)
        progressDialog.show(); // exibe o dialog
    }

    // executado em segundo plano (faz o download da imagem)
    @Override
    protected Bitmap doInBackground(String... parans) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(parans[0]); // pega a URL da imagem passada como parâmetro
            InputStream is = url.openStream(); // abre conexão com a URL
            bitmap = BitmapFactory.decodeStream(is); // decodifica o inputStream em imagem
            is.close(); // fecha o stream (boa prática)
        } catch (final IOException e) {
            e.printStackTrace(); // imprime erro se algo falhar
        }
        return bitmap; // retorna a imagem carregada
    }

    // executado após o doInBackground terminar (na thread principal)
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        imgNet2.setImageBitmap(bitmap); // exibe a imagem no ImageView
        progressDialog.dismiss(); // fecha o dialog de progresso
    }
}
