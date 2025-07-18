package cl.intelidata.appwhatsapp.model.v2.util;

import cl.intelidata.appwhatsapp.config.PropertiesConfig;
import cl.intelidata.appwhatsapp.exception.error.BadRequestException;
import cl.intelidata.appwhatsapp.model.v1.other.DominioWhatsApp;
import cl.intelidata.appwhatsapp.model.v1.request.MessageClientTokenRequest;
import cl.intelidata.appwhatsapp.model.v1.response.MessageClientTokenResponse;
import cl.intelidata.appwhatsapp.service.impl.v1.api.MessageWhatsAppServiceApi;
import cl.intelidata.appwhatsapp.service.impl.v1.api.MessageWhatsAppServiceGenerator;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retrofit2.Response;

import java.util.Base64;

@Slf4j
@Component
public class GenerateTokenChattigo {
    @Autowired
    private PropertiesConfig propertiesConfig;

    public MessageClientTokenResponse generate(DominioWhatsApp dominio) throws Exception {

        log.info("Generando token para la api de chattigo");
        if (dominio.getCredenciales() == null) {
            log.error("No se encontraron credenciales para la empresa: <" + dominio.getIdEmpresa() + ">.");
            throw new BadRequestException("No se encontraron credenciales para la empresa: <" + dominio.getIdEmpresa() + ">.");
        }

        byte[] decodedCredenciales = Base64.getDecoder().decode(dominio.getCredenciales());
        String decodedCredencialesString = new String(decodedCredenciales);
        String[] credenciales = decodedCredencialesString.split(":");

        MessageClientTokenRequest messageClientTokenRequest = new MessageClientTokenRequest();

        log.info("usernamr: " + credenciales[0]);
        log.info("password: " + credenciales[1]);

        messageClientTokenRequest.setUsername(credenciales[0]);
        messageClientTokenRequest.setPassword(credenciales[1]);

        log.info("creación de request para la api de chattigo -- OK");

        MessageWhatsAppServiceApi service = MessageWhatsAppServiceGenerator.createServiceToken(propertiesConfig, MessageWhatsAppServiceApi.class, dominio);

        final Response<MessageClientTokenResponse> respToken = service.getToken(messageClientTokenRequest).execute();

        if (respToken.isSuccessful()) {
            log.info("token generado correctamente 1");

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
