Tests - Justina Šalukaitė 5gr.

Implementation - Dziugas Untulis 3gr.

Tests were clear, however, some of them implied questionable implementation, for example:

```
@Test
public void checkPassword_withoutSpecialSymbol_returnsFalse() {
    assertFalse(passwordChecker.checkPassword("Password", 2));
}

```

Not sure if requiring minimum password length every time `passwordChecker.checkPassword()` is called is a good idea. Personally I prefer setting it with constructor.


```
@Test
public void addNewValidationRules_withExistingPrefix_returnsFalse() {
    assertFalse(phoneValidator.addNewValidationRules("RU",11, "+370"));
}
```
Not sure if the country code is really needed with current business requirements.


When it comes to test coverage, there were few test holes left. For example, there weren't any tests asserting a correct password or phone number. Thus, following tests were added:

```
@Test
public void checkPassword_withNeededRequirements_returnsTrue() {
    assertFalse(passwordChecker.checkPassword("Password_306"));
}

```

```
@Test
public void validatePhone_withNeededRequirements_returnsTrue() {
    assertTrue(phoneValidator.validatePhone("+37065656874"));
}
```
In addition few more tests were added to test validators configuration capabilities:

```
@Test
public void validatePhone_withCustomCountryRule_ReturnsTrue(){
    PhoneValidator customPhoneValidator = new PhoneValidator();
    customPhoneValidator.addNewValidationRules("LA", 12, "+371");
    assertTrue(customPhoneValidator.validatePhone("+37165656874"));
}
```

```
@Test
public void checkPassword_withCustomSpecialCharacters_returnsTrue() {
    char [] customSpecialSymbols ={'÷'};
    PasswordChecker customPasswordChecker = new PasswordChecker(customSpecialSymbols);
    assertTrue(customPasswordChecker.checkPassword("Password÷306"));
}

@Test
public void checkPassword_withCustomLength_returnsTrue() {
    int customMinLength = 5;
    PasswordChecker customPasswordChecker = new PasswordChecker(customMinLength);
    assertTrue(customPasswordChecker.checkPassword("Pa_30"));
}
```

