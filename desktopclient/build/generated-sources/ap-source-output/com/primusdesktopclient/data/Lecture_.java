package com.primusdesktopclient.data;

import java.util.Calendar;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-09-03T15:59:08")
@StaticMetamodel(Lecture.class)
public class Lecture_ { 

    public static volatile SingularAttribute<Lecture, Long> id;
    public static volatile SingularAttribute<Lecture, Calendar> startTime;
    public static volatile SingularAttribute<Lecture, Integer> timeElasped;
    public static volatile SingularAttribute<Lecture, Long> lecturerFingerPrintId;
    public static volatile SingularAttribute<Lecture, Long> lecturerId;
    public static volatile SingularAttribute<Lecture, Long> courseId;
    public static volatile SingularAttribute<Lecture, Calendar> endTime;
    public static volatile SingularAttribute<Lecture, Boolean> inprogress;
    public static volatile SingularAttribute<Lecture, byte[]> lecturerFingerPrintTemplate;

}