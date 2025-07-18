package cl.intelidata.appwhatsapp.model.v1.other;

import cl.intelidata.appwhatsapp.model.v2.model.Attends;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
public class Hsm {

    private List<Destination> destinations;
    private String namespace;
    private String template;
    private List<String> parameters;
    private String languageCode;
    private Boolean botAttention;
    private Attends attends;

    public Hsm(){
        this.parameters = new ArrayList<>();
    }
}
