package service;

public interface ExchangeService {
    public Boolean applyExchange(int userID, int bookID);
    public Boolean confirmExchange(int exchangeID);
}