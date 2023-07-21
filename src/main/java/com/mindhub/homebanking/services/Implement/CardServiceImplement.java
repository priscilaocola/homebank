package com.mindhub.homebanking.services.Implement;


import com.mindhub.homebanking.dtos.CardDTO;
import com.mindhub.homebanking.dtos.ClientDTO;
import com.mindhub.homebanking.models.Card;
import com.mindhub.homebanking.repositories.CardRepository;
import com.mindhub.homebanking.repositories.ClientRepository;
import com.mindhub.homebanking.services.CardService;
import com.mindhub.homebanking.utils.UtilsCards;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardServiceImplement implements CardService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CardRepository cardRepository;

    @Override
    public List<CardDTO> getCard(Authentication authentication) {
        return new ClientDTO(clientRepository.findByEmail(authentication.getName())).getCards().stream().collect(Collectors.toList());
    }

    @Override
    public Card findByNumber(String number) {
        return cardRepository.findByNumber(number);
    }
    @Override
    public Card findByCvv(int cvv) {

        return cardRepository.findByCvv(cvv);
    }

    @Override
    public String randomNumberCards() {
        String randomCards = "";
        String randomCardsEnd = "";
        for (int i = 0; i < 4; i++){
            randomCards += UtilsCards.randomNumberC() + "-";
        }
        randomCardsEnd = randomCards.substring(0, randomCards.length()-1);
        return randomCardsEnd;
    }
    @Override
    public int randomCvv() {
        return UtilsCards.randomNumberCvv();
    }

    @Override
    public String cardNumberNotRepeat() {
        String cardsNumber;
        do {
            cardsNumber = randomNumberCards();
        }while(findByNumber(cardsNumber) != null);
        return cardsNumber;
    }

    @Override
    public Card findById(long id) {
        return cardRepository.findById(id).orElse(null);
    }

    @Override
    public void saveCard(Card card) {
        cardRepository.save(card);
    }
}
