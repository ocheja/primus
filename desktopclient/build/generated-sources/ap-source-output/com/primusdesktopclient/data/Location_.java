package com.primusdesktopclient.data;

import com.primusdesktopclient.data.FingerPrintModule;
import com.primusdesktopclient.enums.DepartmentNameEnum;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-09-03T15:59:08")
@StaticMetamodel(Location.class)
public class Location_ { 

    public static volatile SingularAttribute<Location, Long> id;
    public static volatile SingularAttribute<Location, String> address;
    public static volatile SingularAttribute<Location, FingerPrintModule> fingerPrintModule;
    public static volatile SingularAttribute<Location, DepartmentNameEnum> departmentName;

}