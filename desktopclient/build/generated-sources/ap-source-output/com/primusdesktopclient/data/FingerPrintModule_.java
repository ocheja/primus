package com.primusdesktopclient.data;

import com.primusdesktopclient.data.Location;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2014-09-03T15:59:08")
@StaticMetamodel(FingerPrintModule.class)
public class FingerPrintModule_ { 

    public static volatile SingularAttribute<FingerPrintModule, Long> id;
    public static volatile SingularAttribute<FingerPrintModule, Location> location;
    public static volatile SingularAttribute<FingerPrintModule, Boolean> active;
    public static volatile SingularAttribute<FingerPrintModule, Integer> BatteryLevel;

}