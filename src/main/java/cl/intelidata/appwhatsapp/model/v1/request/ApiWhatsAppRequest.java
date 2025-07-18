package cl.intelidata.appwhatsapp.model.v1.request;

import cl.intelidata.appwhatsapp.model.v1.other.Hsm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ApiWhatsAppRequest {

    private String id;
    private String did;
    private String type;
    private String channel;
    private Hsm hsm;
}
