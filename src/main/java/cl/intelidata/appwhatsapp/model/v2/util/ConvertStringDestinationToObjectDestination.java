package cl.intelidata.appwhatsapp.model.v2.util;

import cl.intelidata.appwhatsapp.model.v2.model.Destination;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConvertStringDestinationToObjectDestination {

    public List<Destination> convert(List<String> listDestination) {
        List<Destination> listDestinationObject = null;
        if (listDestination != null) {
            listDestinationObject = listDestination.stream().map(destination -> {
                Destination destinationObject = new Destination();
                destinationObject.setDestination(destination);
                return destinationObject;
            }).collect(java.util.stream.Collectors.toList());
        }
        return listDestinationObject;
    }


}
