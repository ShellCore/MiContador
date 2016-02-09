package mx.shellcore.android.micontador.model;

public class Currency {

    private int id;
    private String name;
    private String symbol;

    public Currency() {}

    public Currency(String name, String symbol) {
        setName(name);
        setSymbol(symbol);
    }

    public Currency(int id, String name, String symbol) {
        setId(id);
        setName(name);
        setSymbol(symbol);
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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return name;
    }
}
