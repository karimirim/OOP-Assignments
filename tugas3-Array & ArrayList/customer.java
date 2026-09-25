package tugas3;

public class customer {

    private String firstName;
    private String lastName;
    private account account;

    public customer(String f, String l) {
        firstName = f;
        lastName = l;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setAccount(account acct) {
        account = acct;
    }

    public account getAccount() {
        return account;
    }
}