package cl.intelidata.appwhatsapp.model.v1.other;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DominioWhatsApp implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	private String telefono;
	private long idEmpresa;
	private String apiUrl;
	private String apiToken;
	private String apiAccountId;
	private String credenciales;
	private String proveedor;
	private String tipo;
	private String canal;
	private String idUsuario;
	private String namespace;
	private String idApi;



}
