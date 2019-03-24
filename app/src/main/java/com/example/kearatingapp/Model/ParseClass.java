package com.example.kearatingapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class ParseClass implements Parcelable {

    private String username;
    private String teacher;
    private String semester;
    private String subject;


    public ParseClass(String username, String teacher, String semester, String subject) {
        this.username = username;
        this.teacher = teacher;
        this.semester = semester;
        this.subject = subject;
    }

    protected ParseClass(Parcel in) {
        username = in.readString();
        teacher = in.readString();
        semester = in.readString();
        subject = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(teacher);
        dest.writeString(semester);
        dest.writeString(subject);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ParseClass> CREATOR = new Creator<ParseClass>() {
        @Override
        public ParseClass createFromParcel(Parcel in) {
            return new ParseClass(in);
        }

        @Override
        public ParseClass[] newArray(int size) {
            return new ParseClass[size];
        }
    };

    public String getUsername() {
        return username;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getSemester() {
        return semester;
    }

    public String getSubject() {
        return subject;
    }
}
