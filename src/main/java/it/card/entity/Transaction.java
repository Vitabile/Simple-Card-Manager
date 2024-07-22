package it.card.entity;

import java.time.LocalDateTime;

import it.card.dto.TransactionDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="transactions")

public class Transaction
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private float amount;

    @Column(nullable=false)
    private LocalDateTime timestamp;

	@ManyToOne
    @JoinColumn(name = "card_id", nullable = false, referencedColumnName = "id")
    private Card card;

	@ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
	private User user;

    public TransactionDto toDto(){
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setCardNumber(card.getNumber());
        transactionDto.setAmount(amount);
        transactionDto.setTimestamp(timestamp);
        return transactionDto;
    }
    

}