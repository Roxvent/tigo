package cl.intelidata.appwhatsapp.service.impl.v2.api;

import cl.intelidata.appwhatsapp.model.v1.request.MessageClientTokenRequest;
import cl.intelidata.appwhatsapp.model.v1.response.MessageClientTokenResponse;
import cl.intelidata.appwhatsapp.model.v2.request.ApiWhatsAppRequestV2;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MessageWhatsAppServiceApiV2 {

    @POST("/message/inbound")
    Call<Void> sendMessage(@Body ApiWhatsAppRequestV2 request);

    @POST("/message/login")
    Call<MessageClientTokenResponse> getToken(@Body MessageClientTokenRequest request);
}
