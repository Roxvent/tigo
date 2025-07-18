package cl.intelidata.appwhatsapp.service.impl.v2;

import cl.intelidata.appwhatsapp.config.PropertiesConfig;
import cl.intelidata.appwhatsapp.model.v1.response.MessageClientTokenResponse;
import cl.intelidata.appwhatsapp.model.v2.model.Attends;
import cl.intelidata.appwhatsapp.model.v2.model.Language;
import cl.intelidata.appwhatsapp.model.v2.model.MessageTemplate;
import cl.intelidata.appwhatsapp.model.v2.request.ApiWhatsAppRequestV2;
import cl.intelidata.appwhatsapp.model.v2.request.SendWhatsAppRequestV2;
import cl.intelidata.appwhatsapp.model.v2.response.ApiWhatsAppResponseV2;
import cl.intelidata.appwhatsapp.model.v2.util.ConvertStringDestinationToObjectDestination;
import cl.intelidata.appwhatsapp.model.v2.util.CreateComponents;
import cl.intelidata.appwhatsapp.model.v2.util.GenerateTokenChattigo;
import cl.intelidata.appwhatsapp.service.impl.v2.api.MessageWhatsAppServiceApiV2;
import cl.intelidata.appwhatsapp.service.impl.v2.api.MessageWhatsAppServiceGeneratorV2;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Response;

@Service
@RequiredArgsConstructor
@Slf4j
//@Transactional
public class WhatsAppServiceImplV2 implements IWhatsAppServiceV2 {

    @Autowired
    private PropertiesConfig propertiesConfig;

    @Autowired
    private ConvertStringDestinationToObjectDestination convertStringDestinationToObjectDestination;

    @Autowired
    private CreateComponents createComponents;

    @Autowired
    private GenerateTokenChattigo generateTokenChattigo;

    @Override
    public ApiWhatsAppResponseV2 sendMessageV2(String authorization, SendWhatsAppRequestV2 request) throws Exception {

        log.info("Enviando mensaje a WhatsApp " + request.getDestinatarios().get(0));

        log.info("Obteniendo token de la api de chattigo");
        final MessageClientTokenResponse token = generateTokenChattigo.generate(request.getDominioWhatsApp());

        log.info("Token generado correctamente");
        MessageWhatsAppServiceApiV2 service = MessageWhatsAppServiceGeneratorV2.createService(propertiesConfig, MessageWhatsAppServiceApiV2.class,
                request.getDominioWhatsApp(), token.getAccess_token());


        log.info("Creando request para Chattigo");
        ApiWhatsAppRequestV2 apiWhatsAppRequest = new ApiWhatsAppRequestV2();

        apiWhatsAppRequest.setId(request.getDominioWhatsApp().getIdApi());
        apiWhatsAppRequest.setDid(request.getDominioWhatsApp().getTelefono());
        apiWhatsAppRequest.setType(request.getDominioWhatsApp().getTipo());
        apiWhatsAppRequest.setChannel(request.getDominioWhatsApp().getCanal());

        MessageTemplate messageTemplate = new MessageTemplate();
        messageTemplate.setDestinations(convertStringDestinationToObjectDestination.convert(request.getDestinatarios()));
        messageTemplate.setComponents(createComponents.create(request.getUrlsImagenesEncabezado(),request.getParametros()));
        messageTemplate.setNamespace(request.getDominioWhatsApp().getNamespace());
        messageTemplate.setLanguage(new Language(request.getLenguajePolitica(), request.getLenguajeCodigo()));
        messageTemplate.setBotAttention(request.getBotAttention() != null ? request.getBotAttention() : false);

        if(request.getBotAttention()){
            messageTemplate.setAttends(new Attends(propertiesConfig.getWaitTime()));
        }

        messageTemplate.setName(request.getTemplateId());

        apiWhatsAppRequest.setMessageTemplate(messageTemplate);


        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValueAsString(apiWhatsAppRequest);

        log.info("Enviando mensaje a la api de chattigo");
        log.info("Request: " + mapper.writeValueAsString(apiWhatsAppRequest));

        Response<Void> response = service.sendMessage(apiWhatsAppRequest).execute();

        if (response.isSuccessful()) {
            log.info("Mensaje enviado correctamente");
            response.body();
            return new ApiWhatsAppResponseV2(response.code() + "");

        } else {
            log.info("Error al enviar mensaje a la api de chattigo");
            assert response.errorBody() != null;
            return new ApiWhatsAppResponseV2(response.code() + "");

        }
    }
}
