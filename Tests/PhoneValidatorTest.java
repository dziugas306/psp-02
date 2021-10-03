import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PhoneValidatorTest {

    private PhoneValidator phoneValidator;

    @BeforeAll
    void setup() {
        phoneValidator = new PhoneValidator();
    }

    @Test
    public void changeNumber_withShortPrefix_returnsOfficialPrefix() {
        assertEquals("+37065656874", phoneValidator.changeNumber("865656874"));
    }

    @Test
    public void validatePhone_withNullValue_returnsFalse() {
        assertFalse(phoneValidator.validatePhone(null));
    }

    @Test
    public void validatePhone_withEmptyString_returnsFalse() {
        assertFalse(phoneValidator.validatePhone(""));
    }


    @ParameterizedTest
    @ValueSource(strings = {"+370656a953", "8645.23", "+370656+953", "++370656953"})
    public void validatePhone_withNonNumericValuesInString_returnsFalse(String invalidPhoneNumber) {
        assertFalse(phoneValidator.validatePhone(invalidPhoneNumber));
    }

    @Test
    public void addNewValidationRules_withEmptyString_returnsFalse() {
        assertFalse(phoneValidator.addNewValidationRules("", 0, ""));
    }

    @Test
    public void addNewValidationRules_withExistingCountry_returnsFalse() {
        assertFalse(phoneValidator.addNewValidationRules("LT", 11, "+44"));
    }

    @Test
    public void addNewValidationRules_withExistingPrefix_returnsFalse() {
        assertFalse(phoneValidator.addNewValidationRules("RU",11, "+370"));
    }

    @Test
    public void validatePhone_withNeededRequirements_returnsTrue() {
        assertTrue(phoneValidator.validatePhone("+37065656874"));
    }

    @Test
    public void validatePhone_withCustomCountryRule_ReturnsTrue(){
        PhoneValidator customPhoneValidator = new PhoneValidator();
        customPhoneValidator.addNewValidationRules("LA", 12, "+371");
        assertTrue(customPhoneValidator.validatePhone("+37165656874"));
    }

}
