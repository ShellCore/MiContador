package mx.shellcore.android.micontador.model;

public class Account {
    public static final int OTHER = 1;
    public static final int CREDIT = 2;

    private int id;
    private String name;
    private int type;
    private Currency currency;
    private double beginningBalance;
    private Image image;

    public Account() {
    }

    public Account(int id, String name, int type, Currency currency, double beginningBalance, Image image) {
        setId(id);
        setName(name);
        setType(type);
        setCurrency(currency);
        setBeginningBalance(beginningBalance);
        setImage(image);
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

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public double getBeginningBalance() {
        return beginningBalance;
    }

    public void setBeginningBalance(double beginningBalance) {
        this.beginningBalance = beginningBalance;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
