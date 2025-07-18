package cl.intelidata.appwhatsapp.service.impl.v2;

import cl.intelidata.appwhatsapp.model.v2.request.SendWhatsAppRequestV2;
import cl.intelidata.appwhatsapp.model.v2.response.ApiWhatsAppResponseV2;

public interface IWhatsAppServiceV2 {

    ApiWhatsAppResponseV2 sendMessageV2(String authorization, SendWhatsAppRequestV2 request) throws Exception;

}
