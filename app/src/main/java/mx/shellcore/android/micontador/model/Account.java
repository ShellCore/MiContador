package mx.shellcore.android.micontador.model;

import java.math.BigDecimal;
import java.util.Date;

public class Account {
    public static final int OTHER = 0;
    public static final int CREDIT = 1;

    private int id;
    private String name;
    private int type;
    private int currency;
    private BigDecimal beginning_balance;
    private Date courtDate;
    private int limitPayDays;

    public Account() {
    }

    public Account(int id, String name, int type, int currency, BigDecimal beginning_balance, Date courtDate, int limitPayDays) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.currency = currency;
        this.beginning_balance = beginning_balance;
        this.courtDate = courtDate;
        this.limitPayDays = limitPayDays;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public BigDecimal getBeginning_balance() {
        return beginning_balance;
    }

    public void setBeginning_balance(BigDecimal beginning_balance) {
        this.beginning_balance = beginning_balance;
    }

    public Date getCourtDate() {
        return courtDate;
    }

    public void setCourtDate(Date courtDate) {
        this.courtDate = courtDate;
    }

    public int getLimitPayDays() {
        return limitPayDays;
    }

    public void setLimitPayDays(int limitPayDays) {
        this.limitPayDays = limitPayDays;
    }
}
