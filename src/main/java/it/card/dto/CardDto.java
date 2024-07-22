package it.card.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CardDto
{
    public CardDto(Integer number, float credit, Boolean activated){
        this.credit = credit;
        this.number = number;
        this.activated = activated;
    }

    private Long id;
    private float credit;
    private Integer number;
    private Boolean activated;

}