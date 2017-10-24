package org.egov.works.commons.domain.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets CollectionMode
 */
public enum CollectionMode {

	CASH("CASH"),

	CHEQUE("CHEQUE"),

	DD("DD"),

	FIXED_DEPOSIT_RECEIPT("Fixed Deposit Receipt"),

	BANK_GUARANTEE("Bank Guarantee");

	private String value;

	CollectionMode(String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static CollectionMode fromValue(String text) {
		for (CollectionMode b : CollectionMode.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
