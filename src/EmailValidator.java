public class EmailValidator {
    public boolean validateEmail(String email) {
        return isNotNullOrEmpty(email) && hasEta(email) && hasValidUsername(email) && hasValidDomain(email) && hasValidTLD(email);
    }

    private boolean hasEta(String email) {
        return email.contains("@");
    }

    private boolean hasValidUsername(String email) {
        String userName = email.substring(0, email.indexOf('@'));
        return isNotNullOrEmpty(userName) && startsAndEndsWithLetterOrDigit(userName);
    }

    private boolean hasValidDomain(String email) {
        int indexOfLastDot = email.lastIndexOf('.');
        int indexOfEta = email.indexOf('@');
        if (indexOfLastDot == -1 || indexOfLastDot < indexOfEta) {
            return false;
        }
        String domain = email.substring(indexOfEta + 1, indexOfLastDot);
        return isNotNullOrEmpty(domain) && startsAndEndsWithLetterOrDigit(domain);
    }

    private boolean hasValidTLD(String email){
        String tld = email.substring(email.lastIndexOf('.') + 1);
        return isNotNullOrEmpty(tld) && tld.length() >= 2 && startsAndEndsWithLetterOrDigit(tld);
    }

    private boolean isNotNullOrEmpty(String email) {
        return email != null && !email.equals("");
    }

    private boolean startsAndEndsWithLetterOrDigit(String string) {
        return isLetterOrNumber(string.charAt(0)) && isLetterOrNumber(string.charAt(string.length() - 1));
    }

    private boolean isLetterOrNumber(char character) {
        return Character.isLetter(character) || Character.isDigit(character);
    }
}
