package com.mtl.async_and_thread;

import android.graphics.Bitmap;
import android.os.AsyncTask;

/**
 * @author Matheus Martinelli
 * Created on *30-06-2025
 * martinelli.matheus2@gmail.com
 */
public class CarregarAssyncTask extends AsyncTask<String, Void, Bitmap>{
    //o primeiro parametro é o tipo de entrada e o ultimo é o de saida do doinbackground

    @Override
    protected Bitmap doInBackground(String... strings) {
        return null;
    }
}

