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
	/*=========================================================*/
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public UserDao getUserDao(){
		return this.userDao;
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	public BookDao getBookDao(){
		return this.bookDao;
	}

	public void setBookReleaseDao(BookReleaseDao bookReleaseDao) {
		this.bookReleaseDao = bookReleaseDao;
	}

	public BookReleaseDao getBookReleaseDao(){
		return this.bookReleaseDao;
	}

	public void setExchangeDao(ExchangeDao exchangeDao) {
		this.exchangeDao = exchangeDao;
	}


	public ExchangeDao getExchangeDao(){
		return this.exchangeDao;
	}

	public void setExchangeHistoryDao(ExchangeHistoryDao exchangeHistoryDao) {
		this.exchangeHistoryDao = exchangeHistoryDao;
	}

	public ExchangeHistoryDao getExchangeHistoryDao(){
		return this.exchangeHistoryDao;
	}

	/*==================================================================*/
	@Override
	public Map prepareExchange(int wantedBookID)
	{
		Map map = new HashMap();
		User user = this.getLoginedUserInfo();
		Book wantedBook = bookDao.getBookByID(wantedBookID);
		map.put("wantedBook", wantedBook);
		List<Book> userReleasedBookList = bookReleaseDao.getReleaseBookByUserID(user.getUserID());
		map.put("userReleasedBookList", userReleasedBookList);
		return map;
	}

	@Override
	public Boolean applyExchange(int wantedBookID, int hadBookID, String address)
	{
		User user1 = this.getLoginedUserInfo();
		Book wanted = bookDao.getBookByID(wantedBookID);
		Book had = bookDao.getBookByID(hadBookID);
		if (wanted.getStatus() != BookStatus.IDLE)
			return false;
		if (had.getStatus() != BookStatus.IDLE)
			return false;
		BookRelease bookRelease = bookReleaseDao.getReleaseBookByBookID(wanted.getBookID());
		User user2 = userDao.getUserById(bookRelease.getUserID());
		int userID1 = user1.getUserID();
		int userID2 = user2.getUserID();
		Date date = new Date();
		Exchange exchange = new Exchange(userID1,userID2,wantedBookID,hadBookID,ExchangeStatus.WAITING,date,address);
		exchangeDao.save(exchange);
		wanted.setStatus(BookStatus.EXCHANGED);
		had.setStatus(BookStatus.EXCHANGED);
		bookDao.update(wanted);
		bookDao.update(had);
		return true;
	}

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
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

	@Override
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
