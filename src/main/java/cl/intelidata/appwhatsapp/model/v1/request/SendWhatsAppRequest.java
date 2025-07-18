package cl.intelidata.appwhatsapp.model.v1.request;

import cl.intelidata.appwhatsapp.model.v1.other.DominioWhatsApp;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Request para enviar WhatsApp")
public class SendWhatsAppRequest {

	private DominioWhatsApp dominioWhatsApp;
	private String templateId;
	private List<String> parametros;
	private List<String> destinatarios;
	private String lenguaje;
	private Boolean botAttention;
}
