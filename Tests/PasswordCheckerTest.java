import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PasswordCheckerTest {

    private PasswordChecker passwordChecker;

    @BeforeAll
    void setup() {
        passwordChecker = new PasswordChecker();
    }

    @Test
    public void checkPassword_withNullValue_returnsFalse() {
        assertFalse(passwordChecker.checkPassword(null, 10));
    }

    @Test
    public void checkPassword_withEmptyString_returnsFalse() {
        assertFalse(passwordChecker.checkPassword("", 10));
    }

    @Test
    public void checkPassword_shorterThanMinValue_returnsFalse() {
        assertFalse(passwordChecker.checkPassword("password", 10));
    }

    @Test
    public void checkPassword_withoutUppercaseLetter_returnsFalse() {
        assertFalse(passwordChecker.checkPassword("password", 2));
    }

    @Test
    public void checkPassword_withoutSpecialSymbol_returnsFalse() {
        assertFalse(passwordChecker.checkPassword("Password", 2));
    }
}
