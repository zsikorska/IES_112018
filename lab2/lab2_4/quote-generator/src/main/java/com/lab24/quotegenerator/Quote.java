package com.lab24.quotegenerator;

public class Quote {
    private String quote;
    private String show;

    public Quote(){

    }

    public Quote(String quote, String show) {
        this.quote = quote;
        this.show = show;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }
}