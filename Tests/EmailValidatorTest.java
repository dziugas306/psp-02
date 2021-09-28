import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EmailValidatorTest {

    private EmailValidator emailValidator;

    @BeforeAll
    void setup() {
        emailValidator = new EmailValidator();
    }

    @Test
    public void validateEmail_withNullValue_returnsFalse() {
        assertFalse(emailValidator.validateEmail(null));
    }

    @Test
    public void validateEmail_withEmptyString_returnsFalse() {
        assertFalse(emailValidator.validateEmail(""));
    }


    @ParameterizedTest
    @ValueSource(strings = {"email@£mail.com", "¥@yahoo.com", "@gmail.com"})
    public void validateEmail_withSpecialCharactersThatAreNotAllowed_returnsFalse(String email) {
        assertFalse(emailValidator.validateEmail(email));
    }


    @ParameterizedTest
    @ValueSource(strings = {"email", "john.doe@.net", "john.doe43@domainsample"})
    public void validateEmail_withInvalidDomain_returnsFalse(String email) {
        assertFalse(emailValidator.validateEmail(email));
    }


    @Test
    public void validateEmail_withCorrectEmail_returnsTrue() {
        assertTrue(emailValidator.validateEmail("john.doe@gmail.com"));
    }

}
