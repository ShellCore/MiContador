package mx.shellcore.android.micontador.model;

public class Category {

    public static final int CAT_INCOME = 1;
    public static final int CAT_EXPENSE = 2;

    private int id = 0;
    private String name;
    private CategoryImage logo;
    private int type;

    public Category() {
    }

    public Category(int id, String name, CategoryImage logo, int type) {
        this.id = id;
        this.name = name;
        this.logo = logo;
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

    public CategoryImage getLogo() {
        return logo;
    }

    public void setLogo(CategoryImage logo) {
        this.logo = logo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
