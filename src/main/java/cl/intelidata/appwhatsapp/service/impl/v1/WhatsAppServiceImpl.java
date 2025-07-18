package cl.intelidata.appwhatsapp.service.impl.v1;

import cl.intelidata.appwhatsapp.config.PropertiesConfig;
import cl.intelidata.appwhatsapp.exception.error.BadRequestException;
import cl.intelidata.appwhatsapp.model.v1.other.Destination;
import cl.intelidata.appwhatsapp.model.v1.other.DominioWhatsApp;
import cl.intelidata.appwhatsapp.model.v1.other.Hsm;
import cl.intelidata.appwhatsapp.model.v1.request.ApiWhatsAppRequest;
import cl.intelidata.appwhatsapp.model.v1.request.MessageClientTokenRequest;
import cl.intelidata.appwhatsapp.model.v1.request.SendWhatsAppRequest;
import cl.intelidata.appwhatsapp.model.v1.response.ApiWhatsAppResponse;
import cl.intelidata.appwhatsapp.model.v1.response.MessageClientTokenResponse;
import cl.intelidata.appwhatsapp.model.v2.model.Attends;
import cl.intelidata.appwhatsapp.service.impl.v1.api.MessageWhatsAppServiceApi;
import cl.intelidata.appwhatsapp.service.impl.v1.api.MessageWhatsAppServiceGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.util.Base64;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Slf4j
public class WhatsAppServiceImpl implements IWhatsAppService {

    @Autowired
    private PropertiesConfig propertiesConfig;

    @Override
    public ApiWhatsAppResponse sendMessage(String authorization, SendWhatsAppRequest request) throws Exception {

        log.info("Enviando mensaje a WhatsApp " + request.getDestinatarios().get(0));

        log.info("Obteniendo token de la api de chattigo");
        final MessageClientTokenResponse token = getTokenApi(request.getDominioWhatsApp());

        log.info("Token generado correctamente");
        MessageWhatsAppServiceApi service = MessageWhatsAppServiceGenerator
                .createService(
                        propertiesConfig,
                        MessageWhatsAppServiceApi.class,
                        request.getDominioWhatsApp(),
                        token.getAccess_token()
                );

        log.info("Creando request para Chattigo");
        ApiWhatsAppRequest apiWhatsAppRequest = new ApiWhatsAppRequest();

        apiWhatsAppRequest.setId(request.getDominioWhatsApp().getIdApi());
        apiWhatsAppRequest.setDid(request.getDominioWhatsApp().getTelefono());
        apiWhatsAppRequest.setType(request.getDominioWhatsApp().getTipo());
        apiWhatsAppRequest.setChannel(request.getDominioWhatsApp().getCanal());

        final Stream<Destination> destinations = request.getDestinatarios()
                .stream()
                .map(Destination::new);

        Hsm hsm = new Hsm();
        hsm.setLanguageCode(request.getLenguaje());
        hsm.setBotAttention(request.getBotAttention() != null ? request.getBotAttention(): false);

        if(request.getBotAttention()){
            hsm.setAttends(new Attends(propertiesConfig.getWaitTime()));
        }

        hsm.setTemplate(request.getTemplateId());
        hsm.setNamespace(request.getDominioWhatsApp().getNamespace());
        hsm.setDestinations(destinations.collect(Collectors.toList()));
        if(request.getParametros() != null){
            hsm.setParameters(request.getParametros());
        }

        apiWhatsAppRequest.setHsm(hsm);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValueAsString(apiWhatsAppRequest);

        log.info("Enviando mensaje a la api de chattigo");
        log.info("Request: " + mapper.writeValueAsString(apiWhatsAppRequest));

        Response<Void> response = service
                .sendMessage(apiWhatsAppRequest)
                .execute();

        if (response.isSuccessful()) {
            log.info("Mensaje enviado correctamente");
            response.body();
            return new ApiWhatsAppResponse(response.code() + "");

        } else {
            log.info("Error al enviar mensaje a la api de chattigo");
            assert response.errorBody() != null;
            return new ApiWhatsAppResponse(response.code() + "");
        }

    }

    private MessageClientTokenResponse getTokenApi(DominioWhatsApp dominio) throws Exception {
        return generateTokenApi(dominio);
    }

    private MessageClientTokenResponse generateTokenApi(DominioWhatsApp dominio) throws Exception {

        log.info("Generando token para la api de chattigo");
        if (dominio.getCredenciales() == null) {
            log.error("No se encontraron credenciales para la empresa: <" + dominio.getIdEmpresa() + ">.");
            throw new BadRequestException("No se encontraron credenciales para la empresa: <" + dominio.getIdEmpresa() + ">.");
        }

        byte[] decodedCredenciales          = Base64.getDecoder().decode(dominio.getCredenciales());
        String decodedCredencialesString    = new String(decodedCredenciales);
        String[] credenciales               = decodedCredencialesString.split(":");

        MessageClientTokenRequest messageClientTokenRequest = new MessageClientTokenRequest();

        messageClientTokenRequest.setUsername(credenciales[0]);
        messageClientTokenRequest.setPassword(credenciales[1]);

        log.info("creación de request para la api de chattigo -- OK");

        MessageWhatsAppServiceApi service = MessageWhatsAppServiceGenerator.createServiceToken(propertiesConfig, MessageWhatsAppServiceApi.class, dominio);

        final Response<MessageClientTokenResponse> respToken = service.getToken(messageClientTokenRequest).execute();

        if (respToken.isSuccessful()) {
            log.info("token generado correctamente");
            final MessageClientTokenResponse token = respToken.body();
            String expiration = token.getAccess_token().split("\\.")[1];
            byte[] decodeToken = Base64.getDecoder().decode(expiration);
            expiration = new String(decodeToken);
            JSONObject object = new JSONObject(expiration);
            expiration = object.toMap().get("exp").toString();
            propertiesConfig.getTokenApiChattigo().put(dominio.getIdEmpresa(), new MessageClientTokenResponse(token.getAccess_token(), expiration));
            return respToken.body();
        } else {
            log.error("Error al generar token para la api de chattigo: " + respToken.code() + " - " + respToken.errorBody().string());
            assert respToken.errorBody() != null;
            throw new BadRequestException(respToken.errorBody().string());
        }

    }

}
