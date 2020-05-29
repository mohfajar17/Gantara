package com.gantara.mohfajar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

    Context context;

    public DownloadImageTask(Context context){
        this.context = context;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        String url = params[0];
        Log.d("Pmon","Post image url : "+url);
        return downloadImage(url);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {

        Log.d("Pmon","Post execute");
        //  save image;
        File pictureFile = getOutputMediaFile();
        if (pictureFile == null) {
            Log.d("pmon", "Error creating media file, check storage permissions: ");// e.getMessage());
            return;
        }
        try {
            if(bitmap.getWidth() >= bitmap.getHeight()){
                int targetH = 500;
                int targetW = (int) (bitmap.getWidth() * targetH / (double) bitmap.getHeight());
                bitmap = Bitmap.createScaledBitmap(bitmap, targetW,targetH,true);
            } else {
                int targetW = 500;
                int targetH = (int) ((bitmap.getHeight() * targetW) / (double) bitmap.getWidth());
                bitmap = Bitmap.createScaledBitmap(bitmap, targetW,targetH,true);
            }
            FileOutputStream fos = new FileOutputStream(pictureFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
            Log.d("Original dimensions : ", bitmap.getWidth()+"L X P"+bitmap.getHeight());
            fos.close();
        } catch (FileNotFoundException e) {
            Log.d("pmon", "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d("pmon", "Error accessing file: " + e.getMessage());
        }
    }

    private File getOutputMediaFile() {
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + context.getPackageName()
                + "/Files");
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                return null;
            }
        }
        File mediaFile;
        String mImageName="photoProfile.jpg";
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + mImageName);
        return mediaFile;
    }

    protected Bitmap downloadImage(String url){

        Bitmap bmp = null;
        try {
            URL urln = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urln.openConnection();
            InputStream inputStream = connection.getInputStream();
            bmp = BitmapFactory.decodeStream(inputStream);
            if(bmp != null)
                return bmp;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmp;
    }
}