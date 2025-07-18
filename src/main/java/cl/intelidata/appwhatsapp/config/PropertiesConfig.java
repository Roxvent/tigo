package cl.intelidata.appwhatsapp.config;

import cl.intelidata.appwhatsapp.model.v1.response.MessageClientTokenResponse;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Getter
@Setter
@PropertySource(value = { "file:${properties.external.path}" })
public class PropertiesConfig {

	private Map<Long, MessageClientTokenResponse> tokenApiChattigo = new HashMap<>();

	@Value("${attends.waittime}")
	private Integer waitTime;

}
