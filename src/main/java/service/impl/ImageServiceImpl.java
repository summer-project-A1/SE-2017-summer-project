package service.impl;

import java.io.File;
import com.mongodb.gridfs.GridFSDBFile;

import dao.ImageDao;
import service.ImageService;

public class ImageServiceImpl implements ImageService {
    
    private ImageDao imageDao;

    public ImageDao getImageDao() {
        return imageDao;
    }

    public void setImageDao(ImageDao imageDao) {
        this.imageDao = imageDao;
    }

    @Override
    public GridFSDBFile showImage(String id) {
        return getImageDao().getImageById(id);
    }

    @Override
    public Boolean uploadUserImage(int userID, File userImage) {
        // TODO 自动生成的方法存根
        return null;
    }

    @Override
    public Boolean uploadBookImage(int bookID, File bookImage) {
        // TODO 自动生成的方法存根
        return null;
    }
    
}