package com.lab33.quotegenerator.repository;

import com.lab33.quotegenerator.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {

    List<Quote> findAllByMovie_Id(long movieId);
}
