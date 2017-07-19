package service;

import java.util.Map;

public interface ExchangeService extends BaseService
{
	public Map prepareExchange(int wantedID);
    public Boolean applyExchange(int wantedID, int hadID, String address);
    public Boolean cancelExchange(int exchangeID);
    public Boolean agreeExchange(int exchangeID,String address);
    public Boolean rejectExchange(int exchangeID);
    public Boolean fh1(int exchangeID,String trackingNo);
    public Boolean fh2(int exchangeID,String trackingNo);
    public Boolean sh1(int exchangeID);
    public Boolean sh2(int exchangeID);
    public Boolean comment1(int exchangeID, int comment);
    public Boolean comment2(int exchangeID, int comment);
}