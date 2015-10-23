package mx.shellcore.android.micontador.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Category implements Parcelable {

    public static final int CAT_INCOME = 1;
    public static final int CAT_EXPENSE = 2;

    private int id = 0;
    private String name;
    private String logo;
    private int type;

    public Category() {
    }

    public Category(int id, String name, String logo, int type) {
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(logo);
        dest.writeInt(type);
    }

    public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() {

        @Override
        public Category createFromParcel(Parcel source) {
            return new Category(source);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    private Category(Parcel source) {
        id = source.readInt();
        name = source.readString();
        logo = source.readString();
        type = source.readInt();
    }

    @Override
    public String toString() {
        return "category {"
                + getId() + ", "
                + getName() + ", "
                + getType()
                + "}";
    }
}
