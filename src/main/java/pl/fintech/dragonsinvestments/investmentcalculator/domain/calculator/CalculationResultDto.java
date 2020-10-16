package pl.fintech.dragonsinvestments.investmentcalculator.domain.calculator;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
class CalculationResultDto {
    private BigDecimal finalValue;
    private BigDecimal estimatedProfit;
    private BigDecimal depositValue;
    private List<PeriodCalculationDto> chartData;
}

@Data
@AllArgsConstructor
class PeriodCalculationDto {
    private int key;
    private BigDecimal investmentValue;
    private BigDecimal profit;
    private BigDecimal deposit;
}


