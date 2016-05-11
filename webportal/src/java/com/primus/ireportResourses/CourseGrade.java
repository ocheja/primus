/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.ireportResourses;

/**
 *
 * @author Olisa
 */
public class CourseGrade {

    private String courseTitle;
    private String courseCode;
    private int courseUnitLoad;
    private float CA;
    private float examScore;
    private float totalScore;
    private String gradeLetter;

    /**
     * @return the courseTitle
     */
    public String getCourseTitle() {
        return courseTitle;
    }

    /**
     * @param courseTitle the courseTitle to set
     */
    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    /**
     * @return the courseCode
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * @param courseCode the courseCode to set
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * @return the courseUnitLoad
     */
    public int getCourseUnitLoad() {
        return courseUnitLoad;
    }

    /**
     * @param courseUnitLoad the courseUnitLoad to set
     */
    public void setCourseUnitLoad(int courseUnitLoad) {
        this.courseUnitLoad = courseUnitLoad;
    }

    /**
     * @return the CA
     */
    public float getCA() {
        return CA;
    }

    /**
     * @param CA the CA to set
     */
    public void setCA(float CA) {
        this.CA = CA;
    }

    /**
     * @return the examScore
     */
    public float getExamScore() {
        return examScore;
    }

    /**
     * @param examScore the examScore to set
     */
    public void setExamScore(float examScore) {
        this.examScore = examScore;
    }

    /**
     * @return the totalScore
     */
    public float getTotalScore() {
        return totalScore;
    }

    /**
     * @param totalScore the totalScore to set
     */
    public void setTotalScore(float totalScore) {
        this.totalScore = totalScore;
    }

    /**
     * @return the gradeLetter
     */
    public String getGradeLetter() {
        return gradeLetter;
    }

    /**
     * @param gradeLetter the gradeLetter to set
     */
    public void setGradeLetter(String gradeLetter) {
        this.gradeLetter = gradeLetter;
    }
}
