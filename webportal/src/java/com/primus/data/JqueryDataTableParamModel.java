/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.primus.data;

import com.primus.enums.AcademicLevel;
import com.primus.enums.Semester;
import java.util.List;

/**
 *
 * @author Ocheja Patrick Ileanwa
 */
public class JqueryDataTableParamModel {

    /// <summary>
    /// Request sequence number sent by DataTable, same value must be returned in response
    /// </summary>       
    public String sEcho;
    /// <summary>
    /// Text used for filtering
    /// </summary>
    public String sSearch;
    /// <summary>
    /// Number of records that should be shown in table
    /// </summary>
    public int iDisplayLength;
    /// <summary>
    /// First record that should be shown(used for paging)
    /// </summary>
    public int iDisplayStart;
    /// <summary>
    /// Number of columns in table
    /// </summary>
    public int iColumns;
    /// <summary>
    /// Number of columns that are used in sorting
    /// </summary>
    public int iSortingCols;
    /// <summary>
    /// Index of the column that is used for sorting
    /// </summary>
    public int iSortColumnIndex;
    /// <summary>
    /// Sorting direction "asc" or "desc"
    /// </summary>
    public String sSortDirection;
    /// <summary>
    /// Comma separated list of column names
    /// </summary>
    public String sColumns;
    /// <summary>
    /// Comma separated list of column names
    /// </summary>
    public List aaData;
    public int iTotalDisplayRecords = 10;
    public int iTotalRecords;
    public static DepartmentName departmentName;
    public static AcademicSession academicSession;
    public static Semester semester;
    public static Course course;
    public static AcademicLevel academicLevel;
    public static boolean academicAdviserApproved = false;
    public static boolean HODApproved = false;
    public static boolean FOApproved = false;
}
