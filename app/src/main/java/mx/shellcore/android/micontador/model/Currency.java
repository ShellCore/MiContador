package mx.shellcore.android.micontador.model;

public class Currency {

    private int id;
    private String currency;

    public Currency() {
    }

    public Currency(int id, String currency) {
        this.id = id;
        this.currency = currency;
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
}
