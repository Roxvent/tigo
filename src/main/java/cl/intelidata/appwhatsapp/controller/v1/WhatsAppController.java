package cl.intelidata.appwhatsapp.controller.v1;
import cl.intelidata.appwhatsapp.exception.error.ExceptionResponse;
import cl.intelidata.appwhatsapp.model.v1.request.SendWhatsAppRequest;
import cl.intelidata.appwhatsapp.model.v1.response.ApiWhatsAppResponse;
import cl.intelidata.appwhatsapp.service.impl.v1.IWhatsAppService;
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
@Api(value = "microservicios", tags = "API para gestionar WhatsApp 1.1")
@RequestMapping("${application.api.path}")
@AllArgsConstructor
public class WhatsAppController {

	private final IWhatsAppService service;

	@PostMapping(value = "/v1/send-whatsapp", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "enviar WhatsApp", notes = "enviar WhatsApp.", httpMethod = "POST")
	@ApiResponses({
			@ApiResponse(code = 400, message = "El cliente envió datos incorrectos", response = ExceptionResponse.class),
			@ApiResponse(code = 200, message = "Respuesta OK.", response = Void.class) })
	public @ResponseBody
	ResponseEntity<?> sendWhatsApp(@RequestHeader(name = "authorization") String authorization,
						   @Valid @RequestBody SendWhatsAppRequest request) throws Exception {
		final ApiWhatsAppResponse apiWhatsAppResponse = service.sendMessage(authorization, request);

		return  ResponseEntity.status(Integer.parseInt(apiWhatsAppResponse.getCodigo())).body("");
	}
}
