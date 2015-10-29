package mx.shellcore.android.micontador.model;

import java.util.Date;

public class CreditAccount {

    private int id;
    private Account account;
    private Date courtDate;
    private int limitPayDays;
    private Double creditLimit;

    public CreditAccount() {
    }

    public CreditAccount(int id, Account account, Date courtDate, int limitPayDays, Double creditLimit) {
        this.id = id;
        this.account = account;
        this.courtDate = courtDate;
        this.limitPayDays = limitPayDays;
        this.creditLimit = creditLimit;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Date getCourtDate() {
        return courtDate;
    }

    public void setCourtDate(Date courtDate) {
        this.courtDate = courtDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLimitPayDays() {
        return limitPayDays;
    }

    public void setLimitPayDays(int limitPayDays) {
        this.limitPayDays = limitPayDays;
    }

    public Double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Double creditLimit) {
        this.creditLimit = creditLimit;
    }
}
