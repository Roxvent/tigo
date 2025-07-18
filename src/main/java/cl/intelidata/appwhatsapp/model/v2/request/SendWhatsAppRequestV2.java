package cl.intelidata.appwhatsapp.model.v2.request;

import cl.intelidata.appwhatsapp.model.v1.other.DominioWhatsApp;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Request para enviar WhatsApp")
public class SendWhatsAppRequestV2 {

    private DominioWhatsApp dominioWhatsApp;
    private String templateId;
    private List<String> parametros;
    private String urlsImagenesEncabezado;
    private List<String> destinatarios;
    private String lenguajeCodigo;
    private String lenguajePolitica;
    private Boolean botAttention;
}
