package cl.intelidata.appwhatsapp.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MailLinkType {

	DELIVERY("Specific delivery");

	private final String value;

}