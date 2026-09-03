package com.dimihris.cardsservice.controller;

import com.dimihris.cardsservice.constants.CardConstants;
import com.dimihris.cardsservice.dto.CardDto;
import com.dimihris.cardsservice.dto.response.ResponseDto;
import com.dimihris.cardsservice.service.CardService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class CardController {

    private CardService cardService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCard(
            @Valid @RequestParam
            @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
            String mobileNumber) {

        cardService.createCard(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(CardConstants.STATUS_201, CardConstants.MESSAGE_201));
    }

    @GetMapping("/find")
    public ResponseEntity<CardDto> fetchCardDetails(
            @RequestParam
            @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
            String mobileNumber) {

        CardDto cardsDto = cardService.getCardDetails(mobileNumber);

        return ResponseEntity.status(HttpStatus.OK).body(cardsDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCardDetails(@Valid @RequestBody CardDto cardsDto) {
        boolean isUpdated = cardService.updateCard(cardsDto);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CardConstants.STATUS_200, CardConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(CardConstants.STATUS_417, CardConstants.MESSAGE_417_UPDATE));
        }
    }

}
