package ru.job4j.tdd;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public interface Cinema {

    List<Session> find(Predicate<Session> filter);

    Ticket buy(Account account, int row, int column, Calendar date);

    boolean placeValidate(int row, int column, Calendar date);

    boolean allPlacesValidate(Calendar date);

    boolean add(Session session);

}