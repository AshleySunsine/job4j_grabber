package ru.job4j.tdd;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.Assert;
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

    @Test
    public void addWhenNoTime() {
        Cinema cinema = new Cinema3D();
        Session session = new Session3D();
        boolean ex = cinema.add(session);
        Assert.assertFalse(ex);
    }

    @Test
    public void whenPlaceIsNotEmpty() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        boolean validate = cinema.placeValidate(1, 1, date);
        Assert.assertFalse(validate);
    }

    @Test
    public void whenCinemaIsFull() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D(1);
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        boolean validate = cinema.allPlacesValidate(date);
        Assert.assertFalse(validate);
    }

}