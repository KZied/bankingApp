package com.zied.bankingApp.controllers;

import com.zied.bankingApp.services.StatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/statistics")
@AllArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/sum-by-date/{user-id}")
    public ResponseEntity <Map<LocalDate, BigDecimal>> findSumTransactionsByDate(
            @PathVariable("user-id") Integer userId,
            @RequestParam("start-date") LocalDate startDate,
            @RequestParam("end-date") LocalDate endDate
    ){
        return ResponseEntity.ok(statisticsService.findSumTransactionsByDate(startDate, endDate, userId));

    }

    @GetMapping("/account-balance/{user-id}")
    public ResponseEntity <BigDecimal> getAccountBalance(@PathVariable("user-id") Integer userId){
        return ResponseEntity.ok(statisticsService.getAccountBalance(userId));
    }

    @GetMapping("/highest-transfer/{user-id}")
    public ResponseEntity <BigDecimal> highestTransfer(@PathVariable("user-id") Integer userId){
        return ResponseEntity.ok(statisticsService.highestTransfer(userId));
    }

    @GetMapping("/highest-deposit/{user-id}")
    public ResponseEntity <BigDecimal> highestDeposit(@PathVariable("user-id") Integer userId){
        return ResponseEntity.ok(statisticsService.highestDeposit(userId));
    }
}
