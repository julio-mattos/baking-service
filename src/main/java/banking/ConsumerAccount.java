package banking;

/**
 * Account implementation for consumer (domestic) customers.
 * The account's holder is a {@link Person}.
 */
public class ConsumerAccount extends Account{

    private Person person;
    private double currentBalance;

    public ConsumerAccount(Person person, Long accountNumber, int pin, double currentBalance) {
        super(person, accountNumber, pin, currentBalance);
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    @Override
    protected void addAuthorizedUser(Person person) {
        this.person = person;
    }

    @Override
    public boolean isAuthorizedUser(Person person) {
        return person.equals(this.person);
    }
}
