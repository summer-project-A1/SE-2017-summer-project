package service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.constants.BookStatus;
import common.constants.ExchangeStatus;

import dao.BookDao;
import dao.BookReleaseDao;
import dao.ExchangeDao;
import dao.ExchangeHistoryDao;
import dao.UserDao;

import model.Book;
import model.BookRelease;
import model.Exchange;
import model.ExchangeHistory;
import model.User;

import service.ExchangeService;

public class ExchangeServiceImpl extends BaseServiceImpl implements ExchangeService
{
	private UserDao userDao;
	private BookDao bookDao;
	private BookReleaseDao bookReleaseDao;
	private ExchangeDao exchangeDao;
	private ExchangeHistoryDao exchangeHistoryDao;
	
	public Map prepareExchange(int wantedID)
	{
		Map map = new HashMap();
		User user = this.getLoginedUserInfo();
		Book book = bookDao.getBookByID(wantedID);
		map.put("wanted", book);
		List<Book> had = bookReleaseDao.getReleaseBookByUserID(user.getUserID());
		map.put("had", had);
		return map;
	}
	
	public Boolean applyExchange(int wantedID, int hadID, String address)
	{
		User user1 = this.getLoginedUserInfo();
		Book wanted = bookDao.getBookByID(wantedID);
		Book had = bookDao.getBookByID(hadID);
		if (wanted.getStatus() != BookStatus.IDLE)
			return false;
		if (had.getStatus() != BookStatus.IDLE)
			return false;
		BookRelease bookRelease = bookReleaseDao.getReleaseBookByBookID(wanted.getBookID());
		User user2 = userDao.getUserById(bookRelease.getUserID());
		int userID1 = user1.getUserID();
		int userID2 = user2.getUserID();
		Date date = new Date();
		Exchange exchange = new Exchange(userID1,userID2,wantedID,hadID,ExchangeStatus.WAITING,date,address);
		exchangeDao.save(exchange);
		wanted.setStatus(BookStatus.EXCHANGED);
		had.setStatus(BookStatus.EXCHANGED);
		bookDao.update(wanted);
		bookDao.update(had);
		return true;
	}
	
	public Boolean cancelExchange(int exchangeID)
	{
		Exchange exchange = exchangeDao.getExchangeByID(exchangeID);
		if (exchange.getStatus() != ExchangeStatus.WAITING)
			return false;
		Book wanted = bookDao.getBookByID(exchange.getWantedID());
		if (wanted.getStatus() != BookStatus.EXCHANGED)
			return false;
		Book had = bookDao.getBookByID(exchange.getHadID());
		if (had.getStatus() != BookStatus.EXCHANGED)
			return false;
		wanted.setStatus(BookStatus.IDLE);
		had.setStatus(BookStatus.IDLE);
		bookDao.update(wanted);
		bookDao.update(had);
		ExchangeHistory exchangeHistory = new ExchangeHistory(exchange);
		exchangeHistory.setStatus(ExchangeStatus.CANCELED);
		exchangeHistoryDao.save(exchangeHistory);
		exchangeDao.delete(exchange);
		return true;
	}
	
	public Boolean agreeExchange(int exchangeID,String address)
	{
		Exchange exchange = exchangeDao.getExchangeByID(exchangeID);
		if(exchange.getStatus() != ExchangeStatus.WAITING)
			return false;
		exchange.setStatus(ExchangeStatus.AGREED);
		exchange.setAddress2(address);
		exchangeDao.update(exchange);
		return true;
	}
	
	public Boolean rejectExchange(int exchangeID)
	{
		Exchange exchange = exchangeDao.getExchangeByID(exchangeID);
		if(exchange.getStatus() != ExchangeStatus.WAITING)
			return false;
		Book wanted = bookDao.getBookByID(exchange.getWantedID());
		if (wanted.getStatus() != BookStatus.EXCHANGED)
			return false;
		Book had = bookDao.getBookByID(exchange.getHadID());
		if (had.getStatus() != BookStatus.EXCHANGED)
			return false;
		wanted.setStatus(BookStatus.IDLE);
		had.setStatus(BookStatus.IDLE);
		bookDao.update(wanted);
		bookDao.update(had);
		ExchangeHistory exchangeHistory = new ExchangeHistory(exchange);
		exchangeHistory.setStatus(ExchangeStatus.REJECTED);
		exchangeHistoryDao.save(exchangeHistory);
		exchangeDao.delete(exchange);
		return true;
	}
	
	public Boolean fh1(int exchangeID,String trackingNo)
	{
		Exchange exchange = exchangeDao.getExchangeByID(exchangeID);
		if(exchange.getStatus() != ExchangeStatus.AGREED)
			return false;
		Date date = new Date();
		exchange.setTrackingNo1(trackingNo);
		exchange.setFh_date1(date);
		exchangeDao.update(exchange);
		return true;
	}
	
	public Boolean fh2(int exchangeID,String trackingNo)
	{
		Exchange exchange = exchangeDao.getExchangeByID(exchangeID);
		if(exchange.getStatus() != ExchangeStatus.AGREED)
			return false;
		Date date = new Date();
		exchange.setTrackingNo2(trackingNo);
		exchange.setFh_date2(date);
		exchangeDao.update(exchange);
		return true;
	}
	
	public Boolean sh1(int exchangeID)
	{
		Exchange exchange = exchangeDao.getExchangeByID(exchangeID);
		if(exchange.getStatus() != ExchangeStatus.AGREED)
			return false;
		Date date = new Date();
		exchange.setSh_date1(date);
		exchangeDao.update(exchange);
		return true;
	}
	
	public Boolean sh2(int exchangeID)
	{
		Exchange exchange = exchangeDao.getExchangeByID(exchangeID);
		if(exchange.getStatus() != ExchangeStatus.AGREED)
			return false;
		Date date = new Date();
		exchange.setSh_date2(date);
		exchangeDao.update(exchange);
		return true;
	}
	
	public Boolean comment1(int exchangeID, int comment)
	{
		Exchange exchange = exchangeDao.getExchangeByID(exchangeID);
		if(exchange.getStatus() != ExchangeStatus.AGREED)
			return false;
		exchange.setComment1(comment);
		User user = userDao.getUserById(exchange.getUserID2());
		user.setHonesty(user.getHonesty()+comment);
		userDao.update(user);
		if (exchange.getComment2() == null)
			exchangeDao.update(exchange);
		else
		{
			ExchangeHistory exchangeHistory = new ExchangeHistory(exchange);
			exchangeHistory.setStatus(ExchangeStatus.COMPLETED);
			exchangeHistoryDao.save(exchangeHistory);
			exchangeDao.delete(exchange);
		}
		return true;
	}
	
	public Boolean comment2(int exchangeID, int comment)
	{
		Exchange exchange = exchangeDao.getExchangeByID(exchangeID);
		if(exchange.getStatus() != ExchangeStatus.AGREED)
			return false;
		exchange.setComment1(comment);
		User user = userDao.getUserById(exchange.getUserID1());
		user.setHonesty(user.getHonesty()+comment);
		userDao.update(user);
		if (exchange.getComment1() == null)
			exchangeDao.update(exchange);
		else
		{
			ExchangeHistory exchangeHistory = new ExchangeHistory(exchange);
			exchangeHistory.setStatus(ExchangeStatus.COMPLETED);
			exchangeHistoryDao.save(exchangeHistory);
			exchangeDao.delete(exchange);
		}
		return true;
	}
}
