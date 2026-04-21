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

    @PutMapping("/{id}")
    public TransactionResponseDTO update(@PathVariable Long id, @RequestBody @Valid TransactionRequestDTO dto){
        return transactionService.update(id, dto);
    }

    @GetMapping("/{id}")
    public TransactionResponseDTO getById(@PathVariable Long id){
        return transactionService.findById(id);
    }

    @GetMapping
    public List<TransactionResponseDTO> getAll(){
        return transactionService.findAllByUser();
    }

    @GetMapping("/summary")
    public TransactionSummaryDTO getSummary(){
        return transactionService.getSummary();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        transactionService.delete(id);
    }
}
