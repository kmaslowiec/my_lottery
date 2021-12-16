package model;

import lombok.Data;

import java.util.List;

@Data
public class LotteryReport {

    String result;
    List<Integer> winningNumbers;
    List<Integer> ticketNumbers;
}
