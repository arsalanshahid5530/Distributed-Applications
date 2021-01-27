package com.distributed.applications.BMI_App.ejb;


import javax.ejb.Stateless;

@Stateless
public class BMICalcImpl implements IBMICalc{
    @Override
    public float calculate(float weight, float height) {
        return (weight / height / height) * 10000;
    }
}
