package cl.intelidata.appwhatsapp;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilsTest {

    //@Test
    void testConvertDate() {


        DecodedJWT jwt = JWT.decode("eyJhbGciOiJIUzUxMiJ9.eyJyb2xlc05hbWVzIjpbIk1BU1NJVkVfQVBJIl0sInN1YiI6ImFwaUBtaXRzdWkiLCJyb2xlIjpbIjEiXSwiaWRDbGllbnQiOjM0MSwiY2FtcGFpbmdzIjpbXSwicHJvZmlsZSI6IjEiLCJpZFVzdWFyaW8iOjE5MzkyLCJzZXNzaW9uSWQiOiI4MmZiODZkMWZiZWM0ZmEzYjU2ZWNlMjg5Y2Y0MTU5OCIsInBlcm1pc3Npb25zTmFtZSI6W10sImlkVXNlckFwaSI6MjEyNjgsImlkc2VzaW9uIjowLCJlbmFibGUiOnRydWUsIm5hbWUiOiJBUEkgIE9NTklDSEFUIE1JVFNVSSIsImV4cCI6MTY0MTU0NjIwNCwiaWF0IjoxNjQxNTAzMDA0fQ.4cRrQxyFzR_Vfqi4866bALzXc_FXmEHpD0NG7HOp_ZUpVTCkPv18broKhSve__SiwlKsduCIPd4TasqLxI-nsQ");
        if( jwt.getExpiresAt().before((new Date()))) {
            System.out.println("token is expired");
        }

        Date fechaToken = null;
        try {
            fechaToken = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("1641546204");
        } catch (ParseException e) {
            System.out.println("Error al convertir la fecha");
            assert (false);
        }
        if (new Date().before(fechaToken)) {
            System.out.println("Token válido");
            assert (true);
        } else {
            System.out.println("Token expirado");
            assert (false);
        }

    }
}
