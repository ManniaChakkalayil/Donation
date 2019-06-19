package com.example.donation;

import android.os.Parcel;
import android.os.Parcelable;

public class donation implements Parcelable
{
    private  String name;
    private  int amount;

    public donation(String name, int amount){
        this.name = name;
        this.amount = amount;
    }

    protected donation(Parcel in) {
        name = in.readString();
        amount = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(amount);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<donation> CREATOR = new Creator<donation>() {
        @Override
        public donation createFromParcel(Parcel in) {
            return new donation(in);
        }

        @Override
        public donation[] newArray(int size) {
            return new donation[size];
        }
    };
    public String getName(){
        return name;
    }
    public int getAmount()
    {
        return amount;
    }
}
