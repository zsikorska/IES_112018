package com.lab24.quotegenerator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.*;

@RestController
@ConfigurationProperties("app")
public class QuoteGeneratorController {
    private List<Show> shows = new ArrayList<>();
    private List<Quote> quotes = new ArrayList<>();
    private final Random random = new Random();


    @GetMapping("/quote")
    public Quote randomQuote(){
        int quoteNr = random.nextInt(quotes.size());
        return quotes.get(quoteNr);
    }

    @GetMapping("/shows")
    public List<Show> allShows(){
        List<Show> showsWithQuotes = new ArrayList<>();
        Set<String> showSet = new HashSet<>();

        for(Quote quote : quotes){
            showSet.add(quote.getShow());
        }

        for(Show show : shows){
            if(showSet.contains(show.getName()))
                showsWithQuotes.add(show);
        }

        return showsWithQuotes;
    }

    @GetMapping("/quotes")
    public Quote randomQuoteFromShow(@RequestParam(value = "show", defaultValue = "0") String id) {
        int idShow = Integer.parseInt(id);
        String showName = shows.get(idShow).getName();
        List<Quote> quotesFromShow = new ArrayList<>();

        for(Quote quote : quotes){
            if(quote.getShow().equals(showName))
                quotesFromShow.add(quote);
        }

        int quoteNr = random.nextInt(quotesFromShow.size());
        return quotesFromShow.get(quoteNr);
    }


    public List<Show> getShows() {
        return shows;
    }

    public void setShows(List<Show> shows) {
        this.shows = shows;
    }

    public List<Quote> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<Quote> quotes) {
        this.quotes = quotes;
    }
}