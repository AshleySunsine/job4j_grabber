package ru.job4j.ood.isp;

public class Exp3 {
    interface PowerStation {

        int getPower();

        boolean runTurbine();

        boolean runGenerator();

        boolean syncing();

        boolean runReactor();

        int heatingCore();

        boolean runHeater();

        boolean openValve();

        boolean runWindTurbine();

        /*
        Интерфейс перегружен, пытаясь угодить всем возможным ползователям.
        Не надо так.
         */
    }
}
