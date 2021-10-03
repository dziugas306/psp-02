public class PasswordChecker {

    private int minLength = 8;
    private char[] specialSymbols = {'\"', ' ', '!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', ':', ';', '<', '=', '>', '?', '@', '[', '\\', ']', '^', '_', '`', '{', '|', '}', '~'};

    public PasswordChecker() {
    }

    public PasswordChecker(int minLength) {
        this.minLength = minLength;
    }

    public PasswordChecker(char[] specialSymbols) {
        this.specialSymbols = specialSymbols;
    }

    public PasswordChecker(int minLength, char[] specialSymbols) {
        this.minLength = minLength;
        this.specialSymbols = specialSymbols;
    }

    public boolean checkPassword(String password, int minLength) {
        this.minLength = minLength;
        return checkPassword(password);
    }

    public boolean checkPassword(String password) {
        if (password == null) {
            return false;
        }
        return isLongEnough(password) && hasSpecialCharacter(password) && hasUppercaseLetter(password);
    }

    private boolean isLongEnough(String password) {
        return password.length() >= this.minLength;
    }

    private boolean hasSpecialCharacter(String password) {
        for (char specialSymbol : this.specialSymbols) {
            if (password.contains(Character.toString(specialSymbol))) {
                return true;
            }
        }
        return false;
    }

    private boolean hasUppercaseLetter(String password) {
        for (char character : password.toCharArray()) {
            if (Character.isUpperCase(character)) {
                return true;
            }
        }
        return false;
    }
}
