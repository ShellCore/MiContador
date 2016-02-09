package mx.shellcore.android.micontador.model;

public class Category {

    public static final int CAT_INCOME = 1;
    public static final int CAT_EXPENSE = 2;

    private int id = 0;
    private String name;
    private Image logo;
    private int type;

    public Category() {
    }

    public Category(int id, String name, Image logo, int type) {
        setId(id);
        setName(name);
        setLogo(logo);
        setType(type);
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

    public Image getLogo() {
        return logo;
    }

    public void setLogo(Image logo) {
        this.logo = logo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
