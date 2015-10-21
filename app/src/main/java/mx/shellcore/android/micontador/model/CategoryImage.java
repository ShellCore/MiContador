package mx.shellcore.android.micontador.model;

public class CategoryImage {

    private int id = 0;
    private String image;

    public CategoryImage() {
    }

    public CategoryImage(int id, String image) {
        this.id = id;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
