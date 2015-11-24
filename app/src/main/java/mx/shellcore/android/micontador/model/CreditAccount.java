package mx.shellcore.android.micontador.model;

public class CreditAccount {

    private int id;
    private Account account;
    private int courtDay;
    private int limitPayDay;
    private Double creditLimit;

    public CreditAccount() {
    }

    public CreditAccount(int id, Account account, int courtDay, int limitPayDay, Double creditLimit) {
        this.id = id;
        this.account = account;
        this.courtDay = courtDay;
        this.limitPayDay = limitPayDay;
        this.creditLimit = creditLimit;
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

    public Double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Double creditLimit) {
        this.creditLimit = creditLimit;
    }
}
