package banking;

/**
 * Abstract bank account.
 */
public abstract class Account{
    private AccountHolder accountHolder;
    private Long accountNumber;
    private int pin;
    private double balance;

    public Account(AccountHolder accountHolder, Long accountNumber, int pin, double balance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public AccountHolder getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(AccountHolder accountHolder) {
        this.accountHolder = accountHolder;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    protected abstract void addAuthorizedUser(Person person);

    public abstract boolean isAuthorizedUser(Person person);

}
