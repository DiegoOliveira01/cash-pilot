package com.diego.cashpilot.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class TransactionSummaryDTO {

    private BigDecimal income;
    private BigDecimal expense;
    private BigDecimal balance;
}
