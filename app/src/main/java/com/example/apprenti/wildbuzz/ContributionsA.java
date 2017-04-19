package com.example.apprenti.wildbuzz;

import android.os.Parcel;
import android.os.Parcelable;

public class ContributionsA implements Parcelable {

    private String mImgContributionsA;
    private String mIdUser;
    private String mDisplayNameUser;
    private String mIdContributionsA;


    private ContributionsA() {
    }

    public ContributionsA (String urlContributionsA, String idContributionsA, String idUser, String displayNameUser) {

        mImgContributionsA = urlContributionsA;
        mIdContributionsA = idContributionsA;
        mIdUser = idUser;
        mDisplayNameUser = displayNameUser;

    }


    protected ContributionsA(Parcel in) {
        mImgContributionsA = in.readString();
        mIdUser = in.readString();
        mDisplayNameUser = in.readString();
        mIdUser = in.readString();

    }

    public static final Creator<ContributionsA> CREATOR = new Creator<ContributionsA>() {
        @Override
        public ContributionsA createFromParcel(Parcel in) {
            return new ContributionsA(in);
        }

        @Override
        public ContributionsA[] newArray(int size) {return new ContributionsA[size];}
    };

    public String getmImgContributionsA() {
        return mImgContributionsA;
    }

    public void setmImgContributionsA(String mImgContributionsA) {
        this.mImgContributionsA = mImgContributionsA;
    }

    public String getmIdUser() {
        return mIdUser;
    }

    public void setmIdUser(String mIdUser) {
        this.mIdUser = mIdUser;
    }

    public String getmDisplayNameUser() {
        return mDisplayNameUser;
    }

    public void setmDisplayNameUser(String mDisplayNameUser) {
        this.mDisplayNameUser = mDisplayNameUser;
    }

    public String getmIdContributionsA() {
        return mIdContributionsA;
    }

    public void setmIdContributionsA(String mIdConstributionsA) {
        this.mIdContributionsA = mIdConstributionsA;
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mImgContributionsA);
        dest.writeString(mIdUser);
        dest.writeString(mDisplayNameUser);
        dest.writeString(mIdContributionsA);

    }
}

