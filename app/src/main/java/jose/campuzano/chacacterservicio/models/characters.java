package jose.campuzano.chacacterservicio.models;

import android.os.Parcel;
import android.os.Parcelable;

public class characters implements Parcelable {
    private int id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String title;
    private String family;
    private String image;
    private String imageUrl;

    public characters(int id, String firstName, String lastName, String fullName, String title, String family, String image, String imageUrl) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = fullName;
        this.title = title;
        this.family = family;
        this.image = image;
        this.imageUrl = imageUrl;
    }

    protected characters(Parcel in) {
        id = in.readInt();
        firstName = in.readString();
        lastName = in.readString();
        fullName = in.readString();
        title = in.readString();
        family = in.readString();
        image = in.readString();
        imageUrl = in.readString();
    }

    public static final Creator<characters> CREATOR = new Creator<characters>() {
        @Override
        public characters createFromParcel(Parcel in) {
            return new characters( in );
        }

        @Override
        public characters[] newArray(int size) {
            return new characters[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public characters() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt( id );
        parcel.writeString( firstName );
        parcel.writeString( lastName );
        parcel.writeString( fullName );
        parcel.writeString( title );
        parcel.writeString( family );
        parcel.writeString( image );
        parcel.writeString( imageUrl );
    }
}

