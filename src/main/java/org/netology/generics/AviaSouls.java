package org.netology.generics;

import java.util.Arrays;
import java.util.Comparator;

public class AviaSouls {
    private Ticket[] tickets = new Ticket[0];


    //Вспомогательный метод для имитации добавления элемента в массив
    private Ticket[] addToArray(Ticket[] current, Ticket ticket) {
        Ticket[] tmp = new Ticket[current.length + 1];
        for (int i = 0; i < current.length; i++) {
            tmp[i] = current[i];
        }
        tmp[tmp.length - 1] = ticket;
        return tmp;
    }


    //Метод добавления билета в менеджер
    public void add(Ticket ticket) {
        tickets = addToArray(tickets, ticket);
    }

    public Ticket[] findAll() {
        return tickets;
    }


    //Метод поиска билетов по маршруту
    public Ticket[] search(String from, String to) {
        Ticket[] result = new Ticket[0]; // массив для ответа
        for (Ticket ticket : tickets) { // перебираем все билеты
            if (ticket.getFrom().equals(from)) { // совпадает аэропорт вылета
                if (ticket.getTo().equals(to)) { // совпадает аэропорт прилёта
                    result = addToArray(result, ticket); // добавляем его в массив ответа
                }
            }
        }
        Arrays.sort(result);
        return result;
    }

    // Метод расчёта времени полёта (добавил формулу [строка 52], учитывающую проблему меньшего значения "timeTo"
    // по модулю)
    // Например, (7) 24-23+6 = 7, где "TimeFrom" = 23, "TimeTo" = 6. Также, данный метод проверил отдельным тестом.
    public int timeFlight(Ticket ticket) {
        int flight;
        if (ticket.getTimeTo() > ticket.getTimeFrom()) {
            flight = ticket.getTimeTo() - ticket.getTimeFrom();
        } else {
            flight = 24 - ticket.getTimeFrom() + ticket.getTimeTo();
        }
        return flight;
    }

    // Метод поиска билетов по маршруту и сортировкой по времени полёта
    public Ticket[] searchAndSortBy(String from, String to, Comparator<Ticket> comparator) {
        Ticket[] result = new Ticket[0]; // массив для ответа
        for (Ticket ticket : tickets) { // перебираем все билеты
            if (ticket.getFrom().equals(from)) { // совпадает аэропорт вылета
                if (ticket.getTo().equals(to)) { // совпадает аэропорт прилёта
                    result = addToArray(result, ticket); // добавляем его в массив ответа
                }
            }
        }
        Arrays.sort(result, comparator);
        return result;
    }
}

