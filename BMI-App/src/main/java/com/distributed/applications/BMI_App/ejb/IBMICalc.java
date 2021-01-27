package com.distributed.applications.BMI_App.ejb;

import javax.ejb.Local;

@Local
public interface IBMICalc {
    float calculate(float weight, float height);
}
