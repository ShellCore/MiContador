package mx.shellcore.android.micontador.model;

import java.util.Date;

public class Movement {

    public static final int MOV_INGRESO = 1;
    public static final int MOV_GASTO = 2;

    private int id = 0;
    private double amount;
    private int type;
    private Date date;
    private String description;
    private Account account;
    private Category category;

    public Movement() {
    }

    public Movement(int id, double amount, int type, Date date, String description, Account account, Category category) {
        setId(id);
        setAmount(amount);
        setType(type);
        setDate(date);
        setDescription(description);
        setAccount(account);
        setCategory(category);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
