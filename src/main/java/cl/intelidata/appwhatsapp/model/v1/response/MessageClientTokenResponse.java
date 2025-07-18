package cl.intelidata.appwhatsapp.model.v1.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageClientTokenResponse {

    private String access_token;
    private String expiration;
}
