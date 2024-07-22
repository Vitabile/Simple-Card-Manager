package it.card.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto
{

    private Long id;
	private Integer cardNumber;
    private Float amount;
	private LocalDateTime timestamp;
}