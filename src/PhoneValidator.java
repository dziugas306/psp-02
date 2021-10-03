import java.util.HashMap;

public class PhoneValidator {

    private HashMap<String, CountryRule> countryRules = new HashMap<>();

    public PhoneValidator() {
        CountryRule countryRule = new CountryRule("+370", 12);
        countryRules.put("LT", countryRule);
    }

    public boolean validatePhone(String phoneNumber) {
        if (isStringEmptyOrNull(phoneNumber)) {
            return false;
        }
        CountryRule countryRule = findCountryRule(phoneNumber);
        if (countryRule == null) {
            return false;
        }
        boolean hasCorrectLength = phoneNumber.length() > countryRule.length;
        return hasCorrectLength && onlyContainsNumbers(withoutPrefix(phoneNumber));
    }

    public String changeNumber(String phoneNumber) {
        boolean hasCorrectLength = phoneNumber.length() == 9;
        boolean hasCorrectPrefix = phoneNumber.substring(0, 2).equals("86");
        if (onlyContainsNumbers(phoneNumber) && hasCorrectLength && hasCorrectPrefix) {
            return changePrefix(phoneNumber);
        }
        return phoneNumber;
    }

    public boolean addNewValidationRules(String countryCode, int length, String prefix) {
        if (isStringEmptyOrNull(countryCode) || isStringEmptyOrNull(prefix) || doesRuleExist(countryCode) || isPrefixInUse(prefix)){
            return false;
        }
        CountryRule countryRule = new CountryRule(countryCode, length);
        countryRules.put(countryCode, countryRule);
        return true;

    }

    private boolean isStringEmptyOrNull(String string) {
        return string == null || string.equals("");
    }

    private boolean doesRuleExist(String countryCode) {
        CountryRule countryRule = this.countryRules.get(countryCode);
        if (countryRule == null) {
            return false;
        }
        return true;

    }

    private boolean isPrefixInUse( String prefix){
        CountryRule countryRule = findCountryRule(prefix);
        if(countryRule == null){
            return false;
        }
        return true;
    }

    private CountryRule findCountryRule(String phoneNumber) {
        return this.countryRules.values()
                .stream()
                .filter(countryRule -> isMatchingPrefix(countryRule.getPrefix(), phoneNumber))
                .findFirst()
                .orElse(null);

    }

    private boolean isMatchingPrefix(String prefix, String phoneNumber) {
        if (prefix.length() > phoneNumber.length()) {
            return false;
        }
        return phoneNumber.substring(0, prefix.length()).equals(prefix);
    }


    private String withoutPrefix(String phoneNumber) {
        return phoneNumber.substring(1);
    }

    private boolean onlyContainsNumbers(String number) {
        try {
            Integer.parseInt(number);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private String changePrefix(String phoneNumber) {
        return "+3706" + phoneNumber.substring(2);
    }

    private class CountryRule {
        private String prefix;
        private int length;

        public CountryRule(String prefix, int length) {
            this.prefix = prefix;
            this.length = length;
        }

        public String getPrefix() {
            return prefix;
        }

        public int getLength() {
            return length;
        }
    }

}


