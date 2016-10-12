package com.example.l400.xmlparsing;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by l400 on 10/3/2016.
 */
public class Flower implements Parcelable{
    String name;
    long id;
    String department;
    String type;
    String email;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return id + "\n" +name +"\n" + department + " - "+ type + "\n"+ email;   }
    public Flower(){

    }
    public Flower(Parcel in){
        name = in.readString();
        id = in.readLong();
        department = in.readString();
        type = in.readString();
        email = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
      parcel.writeLong(id);
        parcel.writeString(department);
        parcel.writeString(type);
        parcel.writeString(email);
    }
    public static final Parcelable.Creator<Flower> CREATOR = new Parcelable.Creator<Flower>(){
        @Override
        public Flower createFromParcel(Parcel parcel) {
            return new Flower(parcel);
        }

        @Override
        public Flower[] newArray(int i) {
            return new Flower[i];
        }
    };


}
