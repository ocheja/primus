/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.util;

import java.io.Serializable;

/**
 *
 * @author Olisa
 */
public class Grade implements Serializable{

    private float continousAssesmentScore;
    private float examScore;
    private float totalScore;

    /**
     * @return the continousAssesmentScore
     */
    public float getContinousAssesmentScore() {
        return continousAssesmentScore;
    }

    /**
     * @param continousAssesmentScore the continousAssesmentScore to set
     */
    public void setContinousAssesmentScore(float continousAssesmentScore) {
        this.continousAssesmentScore = continousAssesmentScore;
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
        return totalScore = continousAssesmentScore + examScore;
    }
}
