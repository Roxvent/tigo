package cl.intelidata.appwhatsapp.model.v1.other;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Recipient {

    @NotNull(message = "número de celular no debe ser nulo")
    @NotBlank(message = "número de celular no puede estar vacio")
    private String to;

}
