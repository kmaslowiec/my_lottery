package service;

import model.LotteryReport;
import model.Ticket;
import model.WinNumbers;
import repository.TicketRepository;

import javax.ejb.Singleton;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Singleton
public class LotteryService {

    @Inject
    TicketRepository repository;

    public WinNumbers getWinningNumbers() {
        drawNumbers();
        return repository.getWinNumbers();

    }

    public Ticket getCurrentTicket() {
        return repository.getCurrentTicket();
    }

    public void addTicket(Ticket ticket) {
        repository.setCurrentTicket(ticket);
    }

    public LotteryReport getReport(){
        LotteryReport report = new LotteryReport();
        report.setWinningNumbers(repository.getWinNumbers().getResult());
        report.setTicketNumbers(repository.getCurrentTicket().getNumbers());
        report.setResult(printReport());
        return report;
    }

    private String printReport() {
        return report(checkTicket());
    }

    private void drawNumbers() {
        Random random = new Random();
        WinNumbers numbers = new WinNumbers();
        List<Integer> winNumbers = random.ints(1, 50).distinct()
                .limit(6).boxed().collect(Collectors.toList());
        numbers.setResult(winNumbers);
        repository.setWinNumbers(numbers);
    }

    private Integer checkTicket() {
        Set<Integer> winNumbers = new HashSet<>(repository.getWinNumbers().getResult());
        Set<Integer> one = new HashSet<>(repository.getCurrentTicket().getNumbers());
        winNumbers.addAll(one);
        return winNumbers.size();
    }

    private String report(int size) {
        String info;
        switch (size) {
            case 9:
                info = printInto(10);
                break;
            case 8:
                info = printInto(9000);
                break;
            case 7:
                info = printInto(15000);
                break;
            case 6:
                info = printInto(1000000);
                break;
            default:
                info = "You lost";
        }
        return info;
    }

    private String printInto(int prize) {
        return "You won " + prize + " PLN";
    }
}
