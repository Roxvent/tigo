package cl.intelidata.appwhatsapp.controller.v2;
import cl.intelidata.appwhatsapp.exception.error.ExceptionResponse;
import cl.intelidata.appwhatsapp.model.v2.request.SendWhatsAppRequestV2;
import cl.intelidata.appwhatsapp.model.v2.response.ApiWhatsAppResponseV2;
import cl.intelidata.appwhatsapp.service.impl.v2.IWhatsAppServiceV2;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@Api(value = "microservicios", tags = "API para gestionar WhatsApp 2.0 (V2)")
@RequestMapping("${application.api.path}")
@AllArgsConstructor
public class WhatsAppControllerV2 {

	private final IWhatsAppServiceV2 service;


	@PostMapping(value = "/v2/send-whatsapp", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "enviar WhatsApp", notes = "enviar WhatsApp.", httpMethod = "POST")
	@ApiResponses({
			@ApiResponse(code = 400, message = "El cliente envió datos incorrectos", response = ExceptionResponse.class),
			@ApiResponse(code = 200, message = "Respuesta OK.", response = Void.class) })
	public @ResponseBody
	ResponseEntity sendWhatsApp(@RequestHeader(name = "authorization") String authorization, @Valid @RequestBody SendWhatsAppRequestV2 request) throws Exception {
		final ApiWhatsAppResponseV2 apiWhatsAppResponse = service.sendMessageV2(authorization, request);
		return  ResponseEntity.status(Integer.parseInt(apiWhatsAppResponse.getCodigo())).body("");
	}
}
