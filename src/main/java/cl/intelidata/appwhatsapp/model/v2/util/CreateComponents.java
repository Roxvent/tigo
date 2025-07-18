package cl.intelidata.appwhatsapp.model.v2.util;

import cl.intelidata.appwhatsapp.model.v2.model.Component;
import cl.intelidata.appwhatsapp.model.v2.model.Destination;
import cl.intelidata.appwhatsapp.model.v2.model.Parameter;
import cl.intelidata.appwhatsapp.model.v2.model.Value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@org.springframework.stereotype.Component
public class CreateComponents {

    public List<Component> create(String imagenEncabezado, List<String> parametros) {

        List<Component> listComponents = new ArrayList<>();
        if (imagenEncabezado != null) {

            Component component = new Component();
            component.setType("header");
            component.setSub_type("image");
            component.setParameters(Collections.singletonList(new Parameter("image", new Value(imagenEncabezado), "")));
            listComponents.add(component);
        }

        if (parametros != null) {
            Component component = new Component();
            component.setType("body");

            component.setParameters(parametros.stream().map(parametro -> {
                Parameter parameter = new Parameter();
                parameter.setType("text");
                parameter.setText(parametro);
                return parameter;
            }).collect(java.util.stream.Collectors.toList()));
            listComponents.add(component);
        }
        return listComponents;


    }
}
