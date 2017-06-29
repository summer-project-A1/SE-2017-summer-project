package dao;

import java.io.File;

import com.mongodb.gridfs.GridFSDBFile;

public interface ImageDao {
    public GridFSDBFile getImageById(String id);
    public String saveImage(File image);
    public Boolean deleteImageById(String id);
}