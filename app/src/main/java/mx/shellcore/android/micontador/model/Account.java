package mx.shellcore.android.micontador.model;

import java.math.BigDecimal;

public class Account {
    public static final int OTHER = 0;
    public static final int CREDIT = 1;

    private int id;
    private String name;
    private int type;
    private int currency;
    private BigDecimal beginning_balance;

    public Account() {
    }

    public Account(int id, String name, int type) {
        this.id = id;
        this.name = name;
        this.type = type;
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
}
