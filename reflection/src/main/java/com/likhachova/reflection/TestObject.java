package com.likhachova.reflection;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TestObject implements Serializable, Runnable {

    private int age;
    private String name;
    private double salary;
    private String address;

    public double publicAddBonuses(int a, double b) {
        return a + b;
    }

    public static double publicStaticMultiplySalary(float a, long b) {
        return a * b;
    }

    private boolean privateAnd(boolean a, boolean b) {
        return a && b;
    }

    protected int protectedMax(int a, int b) {
        return a > b? a: b;
    }

    public final void publicIncreaseSalary(double salary) { }

    private void privateChangeLocation() {
        address = "New York";
    }

    private void privatePrintAddress() { }

    @Override
    public void run() { }
}
