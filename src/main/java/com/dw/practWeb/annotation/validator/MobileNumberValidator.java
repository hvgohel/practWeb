package com.dw.practWeb.annotation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.dw.practWeb.annotation.MobileNumber;

public class MobileNumberValidator implements ConstraintValidator<MobileNumber, String> {

  @Override
  public void initialize(MobileNumber mobileNumber) {

  }

  @Override
  public boolean isValid(String mobileNumber, ConstraintValidatorContext constraintValidatorContext) {
    if (mobileNumber == null || mobileNumber.length() == 10) {
      return true;
    }

    return false;
  }

}
