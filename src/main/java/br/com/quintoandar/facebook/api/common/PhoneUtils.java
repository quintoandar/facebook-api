package br.com.quintoandar.facebook.api.common;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

public final class PhoneUtils {

  private static final String BRAZIL_REGION = "BR";

  public static String getNumberE164Format(String phoneNumber) {
    try {
      PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
      PhoneNumber phonNumb = phoneNumberUtil.parse(phoneNumber, BRAZIL_REGION);
      return phoneNumberUtil.format(phonNumb, PhoneNumberFormat.E164);
    } catch (NumberParseException e) {
      e.printStackTrace();
    }
    return null;
  }

}
