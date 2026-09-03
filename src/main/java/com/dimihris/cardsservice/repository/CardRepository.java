package com.dimihris.cardsservice.repository;

import com.dimihris.cardsservice.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
