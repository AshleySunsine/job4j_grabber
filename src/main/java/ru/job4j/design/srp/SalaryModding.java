package ru.job4j.design.srp;

public class SalaryModding implements SalaryModifier {
    @Override
    public double mode(double salary) {
        return salary / 100;
    }
}
