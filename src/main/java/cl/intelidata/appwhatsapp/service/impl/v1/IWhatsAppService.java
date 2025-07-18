package cl.intelidata.appwhatsapp.service.impl.v1;

import cl.intelidata.appwhatsapp.model.v1.request.SendWhatsAppRequest;
import cl.intelidata.appwhatsapp.model.v1.response.ApiWhatsAppResponse;

public interface IWhatsAppService {

    ApiWhatsAppResponse sendMessage(String authorization, SendWhatsAppRequest request) throws Exception;

}
