package service;

public interface ExchangeService extends BaseService {
    public Boolean applyExchange(int userID, int bookID);
    public Boolean confirmExchange(int exchangeID);
}