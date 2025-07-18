package cl.intelidata.appwhatsapp.model.v2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Component {

    private String type;
    private String sub_type;
    private List<Parameter> parameters;
}
