package mx.shellcore.android.micontador.model;

public class Currency {

    private int id;
    private String currency;
    private String symbol;

    public Currency() {}

    public Currency(int id, String currency, String symbol) {
        this.id = id;
        this.currency = currency;
        this.symbol = symbol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return currency;
    }
}
