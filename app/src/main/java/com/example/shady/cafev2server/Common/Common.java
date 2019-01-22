package com.example.shady.cafev2server.Common;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.example.shady.cafev2server.Model.Request;
import com.example.shady.cafev2server.Model.User;
import com.example.shady.cafev2server.Remote.GeoCoordinates;
import com.example.shady.cafev2server.Remote.RetrofitCliente;

public class Common {
    public static User currentUser;
    public static Request currentRequest;
    public static String PHONE_TEXT= "userPhone";
    public static final String UPDATE = "Update";
    public static final String DELETE = "Delete";
    public static final int PICK_IMAGE_REQUEST = 71;
    private static final String baseUrl= "https://maps.googleapis.com";

    public static String convertCodeToStatus(String code)
    {
        if(code.equals("0"))
            return "Encomendado";
        else if(code.equals("1"))
            return "A Preparar";
        else
            return "Enviado";
    }
    public static GeoCoordinates getCodeService(){
        return RetrofitCliente.getCliente(baseUrl).create(GeoCoordinates.class);
    }

    public static Bitmap scaleBitmap (Bitmap bitmap,int newWidth,int newHeight){
        Bitmap scaledBitmap = Bitmap.createBitmap(newWidth,newHeight,Bitmap.Config.ARGB_8888);

        float scaleX = newWidth/(float) bitmap.getWidth();
        float scaleY = newHeight/ (float) bitmap.getHeight();
        float pivotX = 0, pivotY=0;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(scaleX,scaleY,pivotX,pivotY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bitmap,0,0,new Paint(Paint.FILTER_BITMAP_FLAG));

        return scaledBitmap;
    }

}
