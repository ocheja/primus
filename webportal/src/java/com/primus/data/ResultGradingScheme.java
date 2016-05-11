/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Olisa
 */
@Entity
public class ResultGradingScheme implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    
  @NotNull  private Map<String, Float> gradeLettersAndMinimumMarks;
   @NotNull  private Map<String, Float> gradeLettersAndGradePoints;
   @NotNull  private Map<String, String> gradeLetterDescriptions;
   @NotNull private Map<Float, String> gradePointsDescriptions;
    private Date dateInitiated = new Date();
    @NotNull private AcademicSession academicSession;
    private String passGradeLetter;
    private int maximumUnitLoad;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ResultGradingScheme)) {
            return false;
        }
        ResultGradingScheme other = (ResultGradingScheme) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.primus.serviceBean.ResultGradingScheme[ id=" + id + " ]";
    }

    /**
     * @return the gradeLettersAndMinimumMarks
     */
    @ElementCollection(targetClass = Float.class,fetch = FetchType.LAZY)
    @JoinTable(name = "gradeletters_marks", joinColumns =
            @JoinColumn(name = "resultGradingScheme_id"))
    // @MapKey(targetElement = String.class,type = @Type(type="") ,columns = @Column(name = "gradeletter"))
    @Column(name = "mark")
    public Map<String, Float> getGradeLettersAndMinimumMarks() {
        return gradeLettersAndMinimumMarks;
    }

    /**
     * @param gradeLettersAndMinimumMarks the gradeLettersAndMinimumMarks to set
     */
    public void setGradeLettersAndMinimumMarks(Map<String, Float> gradeLettersAndMinimumMarks) {
        this.gradeLettersAndMinimumMarks = gradeLettersAndMinimumMarks;
    }

    /**
     * @return the gradeLettersAndGradePoints
     */
    @ElementCollection(targetClass = Float.class,fetch = FetchType.LAZY)
    @JoinTable(name = "gradeletter_gradepoint", joinColumns =
            @JoinColumn(name = "resultGradingScheme_id"))
    //@MapKey(targetElement = String.class, columns = @Column(name = "gradeletter"))
    @Column(name = "gradepoint")
    public Map<String, Float> getGradeLettersAndGradePoints() {
        return gradeLettersAndGradePoints;
    }
//
//    /**
//     * @param gradeLettersAndGradePoints the gradeLettersAndGradePoints to set
//     */

    public void setGradeLettersAndGradePoints(Map<String, Float> gradeLettersAndGradePoints) {
        this.gradeLettersAndGradePoints = gradeLettersAndGradePoints;
    }
//
//    /**
//     * @return the gradeLetterDescriptions
//     */

    @ElementCollection(targetClass = String.class,fetch = FetchType.LAZY)
    @JoinTable(name = "gradeletter_description", joinColumns =
            @JoinColumn(name = "resultGradingScheme_id"))
    //@MapKey(targetElement = String.class, columns = @Column(name = "gradeletter"))
    @Column(name = "description")
    public Map<String, String> getGradeLetterDescriptions() {
        return gradeLetterDescriptions;
    }
//
//    /**
//     * @param gradeLetterDescriptions the gradeLetterDescriptions to set
//     */

    public void setGradeLetterDescriptions(Map<String, String> gradeLetterDescriptions) {
        this.gradeLetterDescriptions = gradeLetterDescriptions;
    }

//    /**
//     * @return the gradePointsDescriptions
//     */
    @ElementCollection(targetClass = String.class,fetch = FetchType.LAZY)
    @JoinTable(name = "gradepoints_descriptions", joinColumns =
            @JoinColumn(name = "resultGradingScheme_id"))
    //@MapKey(targetElement = String.class, columns = @Column(name = "gradepoints"))
    @Column(name = "descriptions")
    public Map<Float, String> getGradePointsDescriptions() {
        return gradePointsDescriptions;
    }
//
//    /**
//     * @param gradePointsDescriptions the gradePointsDescriptions to set
//     */

    public void setGradePointsDescriptions(Map<Float, String> gradePointsDescriptions) {
        this.gradePointsDescriptions = gradePointsDescriptions;
    }

    /**
     * @return the dateInitiated
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getDateInitiated() {
        return dateInitiated;
    }

    /**
     * @param dateInitiated the dateInitiated to set
     */
    public void setDateInitiated(Date dateInitiated) {
        this.dateInitiated = dateInitiated;
    }

    /**
     * @return the academicSession
     */
    @OneToOne
    public AcademicSession getAcademicSession() {
        return academicSession;
    }

    /**
     * @param academicSession the academicSession to set
     */
    public void setAcademicSession(AcademicSession academicSession) {
        this.academicSession = academicSession;
    }
    
     public String getGradeLetter(Float totalGrade) throws Exception {
       Set<String> GradeLetters = this.gradeLettersAndMinimumMarks.keySet();
        Object[] gradeLetterArray = GradeLetters.toArray();
        Arrays.sort(gradeLetterArray);
       for (int i = 0; i < gradeLetterArray.length; i++) {
            if (totalGrade >= this.gradeLettersAndMinimumMarks.get((String) gradeLetterArray[i])) {
                return (String) gradeLetterArray[i];
            }
        }
        throw new Exception("Unable to get grade Letter for grade: " + totalGrade);
    }
     
     public float getGradePoint(Float totalGrade) throws Exception{
       return getGradePoint(getGradeLetter(totalGrade));
     }

//     public Float getMaximumScore(String GradeLetter)throws Exception{
//         float maximumScore=0;
//           Set<String> GradeLetters = this.gradeLettersAndMinimumMarks.keySet();
//        String[] gradeLetterArray = (String[])GradeLetters.toArray();
//        Arrays.sort(gradeLetterArray);
//        for(int i=0;i<gradeLetterArray.length;i++){
//            if(gradeLetterArray[i].equalsIgnoreCase(GradeLetter)){
//               maximumScore = gradeLettersAndMinimumMarks.get(gradeLetterArray[i+1]);
//            }
//        }
//        return maximumScore;
//     }
     
     public Float getGradePoint(String GradeLetter){
      return this.gradeLettersAndGradePoints.get(GradeLetter);
    }
      public String getGradeLetterDescription(String GradeLetter){
      return this.gradeLetterDescriptions.get(GradeLetter);
    }

    /**
     * @return the passGradeLetter
     */
    public String getPassGradeLetter() {
        return passGradeLetter;
    }

    /**
     * @param passGradeLetter the passGradeLetter to set
     */
    public void setPassGradeLetter(String passGradeLetter) {
        this.passGradeLetter = passGradeLetter;
    }

    /**
     * @return the maximumUnitLoad
     */
    public int getMaximumUnitLoad() {
        return maximumUnitLoad;
    }

    /**
     * @param maximumUnitLoad the maximumUnitLoad to set
     */
    public void setMaximumUnitLoad(int maximumUnitLoad) {
        this.maximumUnitLoad = maximumUnitLoad;
    }
      
    
}
