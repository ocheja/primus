/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primusdesktopclient.service;

import com.google.gson.Gson;
import com.primusdesktopclient.gsondata.Lecture;
import com.primusdesktopclient.gsondata.LectureList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Olisa
 */
public class GsonTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Gson gson = new Gson();
        LectureList lectureList = new LectureList();
        List<Lecture> lecs = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Lecture Lecture = new Lecture();
             Lecture.setAction(String.valueOf(i));
              Lecture.setDepartmentName(String.valueOf(i));
            lecs.add(Lecture);
        }
        lectureList.setLectures(lecs);
        String meg = gson.toJson(lectureList);
        System.out.println(((LectureList)gson.fromJson(meg, LectureList.class)).getLectures());
    }
}
