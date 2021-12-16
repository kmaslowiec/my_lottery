package repository;

import lombok.Data;
import model.Ticket;
import model.WinNumbers;

import javax.ejb.Singleton;

@Singleton
@Data
public class TicketRepository {

    Ticket currentTicket;

    WinNumbers winNumbers;
}
