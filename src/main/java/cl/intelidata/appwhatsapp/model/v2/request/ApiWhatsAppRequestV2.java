package cl.intelidata.appwhatsapp.model.v2.request;

import cl.intelidata.appwhatsapp.model.v2.model.MessageTemplate;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Request para enviar WhatsApp")
public class ApiWhatsAppRequestV2 {
	public String id;
	public String did;
	public String type;
	public String channel;
	public MessageTemplate messageTemplate;
}
