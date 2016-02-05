package mx.shellcore.android.micontador.model;

public class Image {

    public static final int IMG_CATEGORY = 1;
    public static final int IMG_ACCOUNT = 2;

    private int id = 0;
    private String path;
    private int type;

    public Image() {
    }

    public Image(String path, int type) {
        setPath(path);
        setType(type);
    }

    public Image(int id, String path, int type) {
        setId(id);
        setPath(path);
        setType(type);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String image) {
        this.path = image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
