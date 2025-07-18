package cl.intelidata.appwhatsapp.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProcessType {

	FIRMA_INICIAL("1"), CAMPANAS("2"), FIRMA_RECORDATORIO("3"), FIRMA_EXPIRACION("4"), FIRMA_FIN("5"),
	FIRMA_RECHAZO("6"), MAIL_INTEGRACION("7");

	private final String value;

}