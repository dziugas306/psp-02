public class PasswordChecker {

    private int minLength = 8;
    private char[] specialSymbols = {'\"', ' ', '!', '"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', ':', ';', '<', '=', '>', '?', '@', '[', '\\', ']', '^', '_', '`', '{', '|', '}', '~'};

    public PasswordChecker() {
    }

    public PasswordChecker(int minLength) {
        setMinLength(minLength);
    }

    public PasswordChecker(char[] specialSymbols) {
        setSpecialSymbols(specialSymbols);
    }

    public PasswordChecker(int minLength, char[] specialSymbols) {
        setMinLength(minLength);
        setSpecialSymbols(specialSymbols);
    }

    private void setMinLength(int minLength) {
        if(minLength > 0){
            this.minLength = minLength;
        }
    }

    private void setSpecialSymbols(char[] specialSymbols) {
        if( specialSymbols != null){
            this.specialSymbols = specialSymbols;
        }
    }

    public boolean checkPassword(String password, int minLength) {
        setMinLength(minLength);
        return checkPassword(password);
    }

    public boolean checkPassword(String password){
        if (password == null) {
            return false;
        }
        boolean isPasswordValid = isLongEnough(password) && hasSpecialCharacter(password) && hasUppercaseLetter(password);
        return isPasswordValid;
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
