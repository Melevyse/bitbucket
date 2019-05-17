package com.example.labwork3;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable{
    private int id;
    private String FullName;

    public Student(Parcel parcel){
        id = parcel.readInt();
        FullName = parcel.readString();
    }

    public Student(int id, String FullName){
        this.id = id;
        this.FullName = FullName;
    }

    public String getFullName() {
        return FullName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(FullName);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    @Override
    public String toString() {
        return "\nID = " + id + " | fullname = " + FullName;
    }
}
