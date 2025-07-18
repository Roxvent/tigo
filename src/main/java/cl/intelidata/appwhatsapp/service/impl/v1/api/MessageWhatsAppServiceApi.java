package cl.intelidata.appwhatsapp.service.impl.v1.api;

import cl.intelidata.appwhatsapp.model.v1.request.ApiWhatsAppRequest;
import cl.intelidata.appwhatsapp.model.v1.request.MessageClientTokenRequest;
import cl.intelidata.appwhatsapp.model.v1.response.MessageClientTokenResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MessageWhatsAppServiceApi {

    @POST("/message/inbound")
    Call<Void> sendMessage(@Body ApiWhatsAppRequest request);

    @POST("/message/login")
    Call<MessageClientTokenResponse> getToken(@Body MessageClientTokenRequest request);
}
