package cl.intelidata.appwhatsapp.exception.error;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ExceptionResponse {

	private Date timestamp;
	private int status;
	private HttpStatus error;
	private String message;
	private String path;
	
	public ExceptionResponse(Date timestamp, int status, HttpStatus error, String mensaje, String detalles) {
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.message = mensaje;
		this.path = detalles;
	}
}
