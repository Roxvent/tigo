package cl.intelidata.appwhatsapp.model.v2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageTemplate {

    private List<Destination> destinations;
    private String namespace;
    private Boolean botAttention;
    private Attends attends;
    private String name;
    private Language language;
    private List<Component> components;
}
