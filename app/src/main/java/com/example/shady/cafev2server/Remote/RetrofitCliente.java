package com.example.shady.cafev2server.Remote;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitCliente {
    private static Retrofit retrofit = null;

    public static Retrofit getCliente(String baseUrl)
    {
        if(retrofit== null)
        {
            retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(ScalarsConverterFactory.create())
                    .build();

        }
        return retrofit;
    }

}
