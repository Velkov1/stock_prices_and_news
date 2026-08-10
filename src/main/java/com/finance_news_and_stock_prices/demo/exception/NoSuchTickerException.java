package com.finance_news_and_stock_prices.demo.exception;

public class NoSuchTickerException extends RuntimeException {
    public NoSuchTickerException(String message) {
        super(message);
    }
}
