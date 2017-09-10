package service;

import model.User;

import java.util.List;

public interface AdminService extends BaseService {
    public List<User> showAllUserList();
    public boolean deleteUser(int userID);
    public boolean addUser(String email,String password,int credit);
    public boolean resetPassword(int userID);
    public boolean deleteBook(int bookID);
}