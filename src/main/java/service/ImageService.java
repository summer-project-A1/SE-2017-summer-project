package service;

import java.io.File;

import com.mongodb.gridfs.GridFSDBFile;

public interface ImageService extends BaseService {
    public GridFSDBFile showImage(String imageID);
    public Boolean uploadUserImage(int userID, File userImage);
    public Boolean uploadBookImage(int bookID, File bookImage);
}