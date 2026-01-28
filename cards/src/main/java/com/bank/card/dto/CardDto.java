package com.bank.card.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Schema(name = "card",
        description = "Schema to hold card information"
)
@Data
public class CardDto {

    @Schema(
            description = "Mobile Number of Customer", example = "4365327698"
    )
    @NotEmpty(message = "Mobile Number can not be a null or empty")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")
    private String mobileNumber;

    @Schema(
            description = "card Number of the customer", example = "548732457654"
    )
    @NotEmpty(message = "Mobile Number can not be a null or empty")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")
    private String cardNumber;

    @Schema(
            description = "Type of the card", example = "Cread card"
    )
    private String cardType;
    @Schema(
            description = "Limited Amout"
    )
    private int totalLimit;
    @Schema(
            description = "how many amount used"
    )
    private int amountUse;
    @Schema(
            description = "Balance Amount"
    )
    private int availableAmount;

}
