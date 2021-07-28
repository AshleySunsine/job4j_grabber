package ru.job4j.tdd;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class CinemaTest {

    @Test
    public void buy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

    @Test
    public void find() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(Arrays.asList(new Session3D())));
    }

    @Test
    public void add() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        cinema.add(session);
        List<Session> sessions = cinema.find(sess -> true);
        assertThat(sessions.get(0), is(session));
    }

    /*
    Тест на добавление сеанса, когда время для сеансов кончилось.
    Все занято.
    Для этого нам нужено, чтобы метод Cinema.add() возвращал true или false
    в зависимости удачно добавился сеанс или нет.
     */

           /*
    Тест на покупку билетов. Если место уже занято.
    Нужно добавить соответствующий метод для проверки.
     */

               /*
    Тест на покупку билетов. Если свободных мест нет.
    Нужно добавить соответствующий метод для проверки.
     */

}