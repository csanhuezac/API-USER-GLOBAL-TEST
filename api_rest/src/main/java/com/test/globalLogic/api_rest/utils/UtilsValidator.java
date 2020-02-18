package com.test.globalLogic.api_rest.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.test.globalLogic.api_rest.model.UserResquestModel;

public class UtilsValidator {

	private static final Logger LOGGER = LoggerFactory.getLogger(UtilsValidator.class);

	private final String regexEmail = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
	private final String regexPassword = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d{2})[a-zA-Z\\d]{4,}$";
	private final String msgErrMail = "Error al enviar Email debe ingresar un mail valido : aaaa@aaa.cl";
	private final String msgErrPass = "Error al enviar Password debe ingresar al menos : 1"
			+ " Mayuscula, 1 Minúsculas, y 2 Números)";

	public void validate(UserResquestModel user) throws Exception {
		LOGGER.info(" :: Init validate user ");
		try {
			validateEmail(user.getEmail());
			validatePass(user.getPassword());
		} catch (Exception e) {
			LOGGER.error("Error validate user input : " + e.getMessage());
			throw new Exception(e.getMessage());
		}

	}
	
	public String formatDate(Date date) throws Exception {
		try {
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			String requiredDate = df.format(date);
			
			return requiredDate;
		} catch (Exception e) {
			LOGGER.error("Error parse date");
			throw new Exception(e.getMessage());
		}
		
	}

	private void validateEmail(String email) throws Exception {
		LOGGER.info(" :: Validate email: " + email);
		if (!validateRegex(email, regexEmail)) {
			throw new Exception(msgErrMail);
		}
	}

	private void validatePass(String pass) throws Exception {
		LOGGER.info(" :: Validate password: " + pass);
		if (!validateRegex(pass, regexPassword)) {
			throw new Exception(msgErrPass);
		}
	}

	private boolean validateRegex(String input, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);

		return matcher.matches();
	}

}
