package service.impl;

import common.constants.BookStatus;
import common.constants.UserRole;
import common.constants.UserStatus;
import common.utils.PasswordUtil;
import dao.BookDao;
import dao.UserDao;
import model.Book;
import model.User;
import service.AdminService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminServiceImpl extends BaseServiceImpl implements AdminService {
    private UserDao userDao;
    private BookDao bookDao;
    private String defaultPassword;

    public UserDao getUserDao() {
        return userDao;
    }
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    public BookDao getBookDao() {
		return bookDao;
	}
	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}
	public String getDefaultPassword() {
        return defaultPassword;
    }
    public void setDefaultPassword(String defaultPassword) {
        this.defaultPassword = defaultPassword;
    }

    @Override
    public List<User> showAllUserList(){
        List<User> userList = this.userDao.getAllUsers();
        return userList;
    }

    @Override
    public boolean deleteUser(int userID){
        User user = this.userDao.getUserById(userID);
        return this.userDao.deleteUser(user);
    }

    @Override
    public boolean resetPassword(int userID){
        User user = this.userDao.getUserById(userID);
        user.setPassword(PasswordUtil.getEncryptedPassword(defaultPassword));
        return this.userDao.update(user);
    }

    @Override
    public boolean addUser(String email,String password,int credit){
        if(this.userDao.getUserByEmail(email) != null) {
            return false;
        }
        User user = new User();
        Map userProfile = new HashMap();

        userProfile.put("name", "");
        userProfile.put("gender", "");
        userProfile.put("mobile", "");
        userProfile.put("deliveryAddress", new ArrayList());

        String profileID = this.userDao.saveUserProfileInMongo(userProfile);
        user.setProfileID(profileID);
        user.setEmail(email);
        user.setNickName("");
        user.setProvince("请选择");
        user.setCity("请选择");
        user.setDistrict("请选择");
        user.setAddress("");
        user.setImageID("");
        user.setPassword(PasswordUtil.getEncryptedPassword(password));
        user.setCredit(credit);
        user.setRole(UserRole.COMMON);
        user.setStatus(UserStatus.ACTIVATED);

        return this.userDao.save(user);
    }
	@Override
	public boolean deleteBook(int bookID)
	{
		Book book = bookDao.getBookByID(bookID);
		if(book.getStatus()!=BookStatus.IDLE && book.getStatus()!=BookStatus.BORROWED)
			return false;
		book.setStatus(BookStatus.DELETED);
		bookDao.update(book);
		return true;
	}

}
