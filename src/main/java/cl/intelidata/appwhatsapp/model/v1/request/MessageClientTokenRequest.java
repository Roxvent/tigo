package cl.intelidata.appwhatsapp.model.v1.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MessageClientTokenRequest {

    private String username;
    private String password;
}
