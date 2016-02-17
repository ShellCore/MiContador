package mx.shellcore.android.micontador.model;

public class CreditAccount {

    private int id;
    private Account account;
    private int courtDay;
    private int limitPayDay;
    private double creditLimit;

    public CreditAccount() {
    }

    public CreditAccount(int id, Account account, int courtDay, int limitPayDay, double creditLimit) {
        setId(id);
        setAccount(account);
        setCourtDay(courtDay);
        setLimitPayDay(limitPayDay);
        setCreditLimit(creditLimit);
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getCourtDay() {
        return courtDay;
    }

    public void setCourtDay(int courtDay) {
        this.courtDay = courtDay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLimitPayDay() {
        return limitPayDay;
    }

    public void setLimitPayDay(int limitPayDay) {
        this.limitPayDay = limitPayDay;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }
}
