package banking;

import java.util.*;

/**
 * The Bank implementation.
 */
public class Bank implements BankInterface {
    private LinkedHashMap<Long, Account> accounts;

    private Map<String, Double> averageBalanceReport;
    private Long accountNumberHandler;

    public Bank() {
        this.accounts = new LinkedHashMap<>();
        this.accountNumberHandler = 0L;
        this.averageBalanceReport = new HashMap<>();
    }

    private Account getAccount(Long accountNumber) {
        return accounts.get(accountNumber);
    }

    private Long createAccountNumber(){
        return accountNumberHandler++;
    }

    public Long openCommercialAccount(Company company, int pin, double startingDeposit) {
        long accountNumber = createAccountNumber();
        Account  account = new CommercialAccount(company, accountNumber, pin, startingDeposit);
        accounts.put(accountNumber, account);

        computeAverageBalanceReport(account.getClass().getSimpleName(), account.getBalance());

        return accountNumber;
    }

    public Long openConsumerAccount(Person person, int pin, double startingDeposit) {
        long accountNumber =  createAccountNumber();

        Account account = new ConsumerAccount(person, accountNumber, pin, startingDeposit);
        accounts.put(accountNumber, account);

        computeAverageBalanceReport(account.getClass().getSimpleName(), account.getBalance());

        return accountNumber;
    }

    private void computeAverageBalanceReport(String key, Double value){
        if (averageBalanceReport.containsKey(key)){
            averageBalanceReport.compute(key,(k,v) -> v + value );
        }else {
            averageBalanceReport.put(key, value);
        }
    }

    public double getBalance(Long accountNumber) {
        Account account = getAccount(accountNumber);
        if (account == null) return -1.0;
        return account.getBalance();
    }

    public void credit(Long accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        account.setBalance(account.getBalance() + amount);

        averageBalanceReport.compute(account.getClass().getSimpleName(), (k, v) -> v + amount);
    }

    public boolean debit(Long accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        if (account.getBalance() - amount >= 0){
            account.setBalance(account.getBalance() - amount);
            averageBalanceReport.compute(account.getClass().getSimpleName(), (k, v) -> v - amount);
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public boolean authenticateUser(Long accountNumber, int pin) {
        Account account = getAccount(accountNumber);

        if (account.getPin() == pin){
            return Boolean.TRUE;
        }else {
           throw new AuthenticationNotValid();
        }
    }

    public void addAuthorizedUser(Long accountNumber, Person authorizedPerson) {
       Account account = getAccount(accountNumber);
       account.addAuthorizedUser(authorizedPerson);
    }

    public boolean checkAuthorizedUser(Long accountNumber, Person authorizedPerson) {
        Account account = getAccount(accountNumber);
        if (account == null) return false;
        return account.isAuthorizedUser(authorizedPerson);
    }

    public Map<String, Double> getAverageBalanceReport() {
        return averageBalanceReport;
    }
}
