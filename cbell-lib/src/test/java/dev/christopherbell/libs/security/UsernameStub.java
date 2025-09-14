package dev.christopherbell.libs.security;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UsernameStub {

  public static String getValidAlreadyCleanStub() {
    return "user_name-01.test";
  }

  public static String getWithLeadingTrailingWhitespaceStub() {
    return "   user   ";
  }

  public static String getWithInvalidCharsStub() {
    return "u$er na!me";
  }

  public static String getWithDuplicateSpecialsStub() {
    return "a..__--b";
  }

  public static String getBelowMinRawStub() {
    return "ab";
  }

  public static String getBelowMinAfterSanitizationStub() {
    return " a ";
  }

  public static String getOnlyInvalidCharsStub() {
    return "  $$  ";
  }

  public static String getAtMinLengthStub() {
    return "abc";
  }

  public static String getAtMaxLengthStub() {
    return "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
  } // 32 'a'

  public static String getAboveMaxLengthStub() {
    return "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
  } // 33 'a'

  public static String getWithUnicodeAndEmojiStub() {
    return "用户😊name";
  }

  public static String getWithMixedRunsOfSpecialsStub() {
    return "a..--__b";
  }

  public static String getWithInternalSpacesTooShortStub() {
    return "a b";
  }

  public static String getWithInternalSpacesValidStub() {
    return "a b c";
  }

  public static String getWithLeadingTrailingSpecialRunsStub() {
    return "__user__";
  }
}
