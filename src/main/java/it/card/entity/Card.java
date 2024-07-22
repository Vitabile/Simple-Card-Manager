package it.card.entity;

import it.card.dto.CardDto;
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
@Table(name="cards")

public class Card
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private int number;

    @Column(nullable=false)
    private float credit;

	@Column(nullable=false)
    private Boolean activated;


    public CardDto toDto(){
        CardDto cardDto = new CardDto(number, credit, activated);
        return cardDto;
    }
    

}