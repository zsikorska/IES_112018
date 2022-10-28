package com.lab33.quotegenerator.service;

import com.lab33.quotegenerator.exception.NoQuotesFoundException;
import com.lab33.quotegenerator.exception.ResourceNotFoundException;
import com.lab33.quotegenerator.model.Quote;
import com.lab33.quotegenerator.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class QuoteService {

    @Autowired
    private QuoteRepository repository;
    private final Random random = new Random();

    public Quote getQuoteById(long id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Quote not found for this id: " + id));
    }

    public List<Quote> getAllQuotes() {
        return repository.findAll();
    }

    public List<Quote> getAllQuotesFromMovie(long movieId) {
        return repository.findAllByMovie_Id(movieId);
    }

    public Quote saveQuote(Quote quote){
        return repository.save(quote);
    }

    public List<Quote> saveQuotes(List<Quote> quotes){
        return repository.saveAll(quotes);
    }

    public Quote updateQuote(long id, Quote newQuote) throws ResourceNotFoundException {
        Quote quote = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Quote not found for this id: " + id));
        quote.setQuote(newQuote.getQuote());
        quote.setMovie(newQuote.getMovie());
        return repository.save(quote);
    }

    public String deleteQuote(long id) throws ResourceNotFoundException {
        Quote quote = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Quote not found for this id: " + id));
        repository.delete(quote);
        return "Quote with the id: " + id + " - deleted";
    }
    public Quote getRandomQuote() throws NoQuotesFoundException {
        List<Quote> quotes = repository.findAll();
        if(quotes.isEmpty())
            throw new NoQuotesFoundException("There aren't any quotes");
        return quotes.get(random.nextInt(quotes.size()));
    }

    public Quote getRandomQuoteFromMovie(long movieId) throws NoQuotesFoundException {
        List<Quote> quotes = repository.findAllByMovie_Id(movieId);
        if(quotes.isEmpty())
            throw new NoQuotesFoundException("There aren't any quotes from movie with id: " + movieId);
        return quotes.get(random.nextInt(quotes.size()));
    }
}
