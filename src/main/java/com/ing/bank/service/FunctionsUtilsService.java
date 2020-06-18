package com.ing.bank.service;

import org.springframework.stereotype.Service;

@Service
public class FunctionsUtilsService {
	
	private static final int REFERENCE_LENGTH = 10;

	public String getAlphaNumericString() {

		// chose a Character random from this String
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(REFERENCE_LENGTH);
		for (int i = 0; i < REFERENCE_LENGTH; i++) {
			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (AlphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}

}
