package mx.shellcore.android.micontador.model;

public class Image {

    public static final int IMG_CATEGORY = 1;
    public static final int IMG_ACCOUNT = 2;

    private int id = 0;
    private String image;
    private int type;

    public Image() {
    }

    public Image(String image, int type) {
        setImage(image);
        setType(type);
    }

    public Image(int id, String image, int type) {
        setId(id);
        setImage(image);
        setType(type);
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
