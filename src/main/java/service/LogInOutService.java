package service;

public interface LogInOutService {
    public Boolean isLogined();
    public Boolean login(String username, String plainPassword);
    public Boolean logout();
}