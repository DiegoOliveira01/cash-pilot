package com.diego.cashpilot.controller;

import com.diego.cashpilot.dto.TransactionRequestDTO;
import com.diego.cashpilot.dto.TransactionResponseDTO;
import com.diego.cashpilot.dto.TransactionSummaryDTO;
import com.diego.cashpilot.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public TransactionResponseDTO create(@RequestBody @Valid TransactionRequestDTO dto){
        return transactionService.create(dto);
    }

    @GetMapping
    public List<TransactionResponseDTO> getAll(){
        return transactionService.findAllByUser();
    }

    @GetMapping("/summary")
    public TransactionSummaryDTO getSummary(){
        return transactionService.getSummary();
    }
}
