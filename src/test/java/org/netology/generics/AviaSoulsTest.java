package org.netology.generics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

public class AviaSoulsTest {

    @Test
    public void shouldBeLessThanTheOtherTicket() {
        Ticket ticket2 = new Ticket("Ufa", "St.Peterburg", 21_000, 20, 21);
        Ticket ticket3 = new Ticket("Novosibirsk", "St.Peterburg", 28_000, 20, 23);

        int expected = -1;
        int actual = ticket2.compareTo(ticket3);
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void shouldBeMoreThanAnyOtherTicket() {
        Ticket ticket4 = new Ticket("Krasnoyarsk", "St.Peterburg", 32_000, 20, 1);
        Ticket ticket1 = new Ticket("Saratov", "St.Peterburg", 15_000, 20, 22);

        int expected = 1;
        int actual = ticket4.compareTo(ticket1);
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void shouldBeTheSameWithDifferentTicket() {
        // Например, билет продаётся по скидке
        Ticket ticket5 = new Ticket("Khabarovsk", "St.Peterburg", 32_000, 20, 3);
        Ticket ticket4 = new Ticket("Krasnoyarsk", "St.Peterburg", 32_000, 20, 1);

        int expected = 0;
        int actual = ticket5.compareTo(ticket4);
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void shouldToFindTicketsWithASimilarRouteAndSortByPrice() {
        Ticket ticket1 = new Ticket("Saratov", "St.Peterburg", 15_000, 20, 22);
        Ticket ticket7 = new Ticket("St. Peterburg", "Arkhangelsk", 10_000, 11, 12);
        Ticket ticket2 = new Ticket("Ufa", "St.Peterburg", 21_000, 20, 21);
        Ticket ticket8 = new Ticket("St. Peterburg", "Arkhangelsk", 20_000, 15, 16);
        Ticket ticket3 = new Ticket("Novosibirsk", "St.Peterburg", 28_000, 20, 23);
        Ticket ticket9 = new Ticket("St. Peterburg", "Arkhangelsk", 18_000, 17, 18);
        Ticket ticket4 = new Ticket("Krasnoyarsk", "St.Peterburg", 32_000, 20, 1);
        Ticket ticket10 = new Ticket("St. Peterburg", "Arkhangelsk", 12_000, 20, 21);
        Ticket ticket5 = new Ticket("Khabarovsk", "St.Peterburg", 37_000, 20, 3);
        Ticket ticket6 = new Ticket("St. Peterburg", "Arkhangelsk", 8_000, 8, 9);

        AviaSouls searchTicket = new AviaSouls();

        searchTicket.add(ticket1);
        searchTicket.add(ticket7);
        searchTicket.add(ticket2);
        searchTicket.add(ticket8);
        searchTicket.add(ticket3);
        searchTicket.add(ticket9);
        searchTicket.add(ticket4);
        searchTicket.add(ticket10);
        searchTicket.add(ticket5);
        searchTicket.add(ticket6);

        Ticket[] expected = {ticket6, ticket7, ticket10, ticket9, ticket8};
        Ticket[] actual = searchTicket.search("St. Peterburg", "Arkhangelsk");
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldDetermineShorterFlightTime() {
        Ticket ticket1 = new Ticket("Saratov", "St.Peterburg", 15_000, 20, 22);
        Ticket ticket2 = new Ticket("Moscow", "Ufa", 26_000, 11, 15);

        TicketTimeComparator comparator = new TicketTimeComparator();

        int expected = -1;
        int actual = comparator.compare(ticket1, ticket2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldDetermineLongerFlightTime() {
        Ticket ticket1 = new Ticket("Moscow", "Ufa", 26_000, 11, 15);
        Ticket ticket2 = new Ticket("Saratov", "St.Peterburg", 15_000, 20, 22);

        TicketTimeComparator comparator = new TicketTimeComparator();

        int expected = 1;
        int actual = comparator.compare(ticket1, ticket2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldDetermineTheEqualityOfFlightTime() {
        Ticket ticket1 = new Ticket("St.Peterburg", "Ufa", 26_000, 11, 15);
        Ticket ticket2 = new Ticket("Krasnoyarsk", "Moscow", 29_000, 18, 22);

        TicketTimeComparator comparator = new TicketTimeComparator();

        int expected = 0;
        int actual = comparator.compare(ticket1, ticket2);
        Assertions.assertEquals(expected, actual);
    }

    // При тестировании было выявлено, что когда значение времени прибытия по модулю меньше, чем значение
    // времени отправки (по модулю), возникает ошибка, так как метод "timeFlight" в таком случае производит расчёт
    // неверно, соответственно неверно и само сравнение.
    // В связи с этим, добавил в метод расчёта времени полёта "timeFlight" изменения, учитывающие данную проблему.
    @Test
    public void shouldCorrectlyDetermineTheFlightTimeIfModTimeToLessThenModTimeFrom() {
        Ticket ticket1 = new Ticket("St.Peterburg", "Ufa", 26_000, 11, 15);
        Ticket ticket2 = new Ticket("Krasnoyarsk", "Moscow", 29_000, 23, 3);

        TicketTimeComparator comparator = new TicketTimeComparator();

        int expected = 0;
        int actual = comparator.compare(ticket1, ticket2);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldToFindTicketsWithASimilarRouteAndSortByTimeFlight() {
        Ticket ticket1 = new Ticket("Saratov", "St.Peterburg", 15_000, 20, 22);
        Ticket ticket7 = new Ticket("St. Peterburg", "Arkhangelsk", 10_000, 11, 13);
        Ticket ticket2 = new Ticket("Ufa", "St.Peterburg", 21_000, 20, 21);
        Ticket ticket8 = new Ticket("St. Peterburg", "Arkhangelsk", 20_000, 24, 4);
        Ticket ticket3 = new Ticket("Novosibirsk", "St.Peterburg", 28_000, 20, 23);
        Ticket ticket9 = new Ticket("St. Peterburg", "Arkhangelsk", 18_000, 17, 18);
        Ticket ticket4 = new Ticket("Krasnoyarsk", "St.Peterburg", 32_000, 20, 1);
        Ticket ticket10 = new Ticket("St. Peterburg", "Arkhangelsk", 12_000, 23, 1);
        Ticket ticket5 = new Ticket("Khabarovsk", "St.Peterburg", 37_000, 20, 3);
        Ticket ticket6 = new Ticket("St. Peterburg", "Arkhangelsk", 8_000, 8, 9);

        AviaSouls searchTicket = new AviaSouls();
        Comparator<Ticket> comparator = new TicketTimeComparator();

        searchTicket.add(ticket1);
        searchTicket.add(ticket7);
        searchTicket.add(ticket2);
        searchTicket.add(ticket8);
        searchTicket.add(ticket3);
        searchTicket.add(ticket9);
        searchTicket.add(ticket4);
        searchTicket.add(ticket10);
        searchTicket.add(ticket5);
        searchTicket.add(ticket6);

        Ticket[] expected = {ticket9, ticket6, ticket7, ticket10, ticket8};
        Ticket[] actual = searchTicket.searchAndSortBy("St. Peterburg", "Arkhangelsk", comparator);
        Assertions.assertArrayEquals(expected, actual);

    }
}

