package com.josephcalver.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.josephcalver.model.SiteUser;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, SiteUser> {

	@Override
	public void initialize(PasswordMatch constraintAnnotation) {

	}

	@Override
	public boolean isValid(SiteUser siteUser, ConstraintValidatorContext c) {

		String plainPassword = siteUser.getPlainPassword();
		String repeatPassword = siteUser.getRepeatPassword();

		if (plainPassword == null || plainPassword.length() == 0) {
			return true;
		}
		
		if (plainPassword == null || !plainPassword.equals(repeatPassword)) {
			return false;
		}

		return true;
	}

}
