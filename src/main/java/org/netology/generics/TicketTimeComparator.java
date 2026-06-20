package org.netology.generics;

import java.util.Comparator;

public class TicketTimeComparator implements Comparator<Ticket> {
    @Override
    public int compare(Ticket t1, Ticket t2) {
        AviaSouls time = new AviaSouls();
        if (time.timeFlight(t1) < time.timeFlight(t2)) {
            return -1;
        } else {
            if (time.timeFlight(t1) > time.timeFlight(t2)) {
                return 1;
            } else
                return 0;
        }
    }
}    