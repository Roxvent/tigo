package cl.intelidata.appwhatsapp.service.impl.v2.api;

import cl.intelidata.appwhatsapp.config.PropertiesConfig;
import cl.intelidata.appwhatsapp.model.v1.other.DominioWhatsApp;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Slf4j
public class MessageWhatsAppServiceGeneratorV2 {

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();


    public static <S> S createService(PropertiesConfig properties, Class<S> serviceClass, DominioWhatsApp dominio, String token) {
        log.info("url de chattigo para envio de whatsapp: " + dominio.getApiUrl());
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(dominio.getApiUrl())
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        httpClient.interceptors().clear();
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder().header("Authorization", "Bearer " + token).addHeader("Content-Type", "application/json").build();

            return chain.proceed(request);
        });
        builder.client(httpClient.build());
        retrofit = builder.build();
        return retrofit.create(serviceClass);
    }



}
