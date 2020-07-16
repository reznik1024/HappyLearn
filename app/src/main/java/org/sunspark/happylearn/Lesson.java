package org.sunspark.happylearn;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lesson {
    private String TeacherName, type, ReccomendedAge, date, time;
    private List specialization = Collections.synchronizedList(new ArrayList());
    private int length;

    public Lesson(String teacherName, String type, String reccomendedAge, String date, String time, List specialization, int length) {
        TeacherName = teacherName;
        this.type = type;
        ReccomendedAge = reccomendedAge;
        this.date = date;
        this.time = time;
        this.specialization = specialization;
        this.length = length;
    }

    public String getTeacherName() {
        return TeacherName;
    }

    public void setTeacherName(String teacherName) {
        TeacherName = teacherName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReccomendedAge() {
        return ReccomendedAge;
    }

    public void setReccomendedAge(String reccomendedAge) {
        ReccomendedAge = reccomendedAge;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List getSpecialization() {
        return specialization;
    }

    public void setSpecialization(List specialization) {
        this.specialization = specialization;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
