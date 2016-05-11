/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.util;

import com.primus.data.AcademicSession;
import com.primus.data.ResultGradingScheme;
import com.primus.serviceBean.ResultGradingSchemeServiceBean;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Olisa
 */
@Component
public class GradingSchemeUtil {

    @Autowired
    ResultGradingSchemeServiceBean resultGradingSchemeServiceBean;
    private Logger LOG = Logger.getLogger(GradingSchemeUtil.class);

    public String getGradeLetter(Float totalGrade, AcademicSession academicSession) throws Exception {
        ResultGradingScheme resultGradingScheme = new ResultGradingScheme();
        try {
            resultGradingScheme = resultGradingSchemeServiceBean.findResultGradingScheme(academicSession);
        } catch (Exception ex) {
            LOG.error(ex);
        }
         if (resultGradingScheme == null) {
            throw new Exception("No Grading Scheme for the Academic Session " + academicSession.getStartYear() + "/" + academicSession.getEndYear());
        }
        Map<String, Float> GradeLettersAndMinimumMarks = resultGradingScheme.getGradeLettersAndMinimumMarks();
        Set<String> GradeLetters = GradeLettersAndMinimumMarks.keySet();
        Object[] gradeLetterArray = GradeLetters.toArray();
        Arrays.sort(gradeLetterArray);
        LOG.info(gradeLetterArray[0] + gradeLetterArray[GradeLetters.size() - 1].toString());
        for (int i = 0; i < gradeLetterArray.length; i++) {
            if (totalGrade >= GradeLettersAndMinimumMarks.get((String) gradeLetterArray[i])) {
                return (String) gradeLetterArray[i];
            }
        }
        LOG.error("Unable to get grade Letter for grade: " + totalGrade);
        throw new Exception("Unable to get grade Letter for grade: " + totalGrade);
    }

    
}
