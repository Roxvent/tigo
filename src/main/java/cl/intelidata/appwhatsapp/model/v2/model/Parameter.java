package cl.intelidata.appwhatsapp.model.v2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Parameter {

    private String type;
    private Value value;
    private String text;
}
