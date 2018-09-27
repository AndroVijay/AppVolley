package app.volley.response;

import android.os.Parcel;
import android.os.Parcelable;

public class Hero  implements Parcelable{

    private String name;
    private String imageurl;


    protected Hero(Parcel in) {
        name = in.readString();
        imageurl = in.readString();
    }

    public static final Creator<Hero> CREATOR = new Creator<Hero>() {
        @Override
        public Hero createFromParcel(Parcel in) {
            return new Hero(in);
        }

        @Override
        public Hero[] newArray(int size) {
            return new Hero[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageurl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(imageurl);
    }
}
