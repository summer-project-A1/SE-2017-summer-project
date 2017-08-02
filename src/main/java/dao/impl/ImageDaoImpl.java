package dao.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

import dao.BaseDao;
import dao.ImageDao;

public class ImageDaoImpl extends BaseDaoImpl implements ImageDao {
    
    @Override
    public GridFSDBFile getImageById(String id) {
        if(id==null || id.equals("")) {
            return null;
        }
        DB db = getMongoDb();
        GridFS gridFS = new GridFS(db);
       
        //查找条件
        DBObject query=new BasicDBObject("_id", new ObjectId(id));
        
        //查询的结果：
        GridFSDBFile gridFSDBFile = gridFS.findOne(query);
        
        //获得其中的文件名
        //注意 ： 不是fs中的表的列名，而是根据调试gridDBFile中的属性而来
        //String fileName=(String)gridDBFile.get("filename");

        //return gridFSDBFile.getInputStream();
        return gridFSDBFile;
    }

    @Override
    public String saveImage(File image) {
        DB db = getMongoDb();  
        GridFS gridFS = new GridFS(db);  
        GridFSInputFile mongofile;
        try {
            mongofile = gridFS.createFile(image);
            //mongofile.setId(id);  
            //mongofile.setFilename(fileName);
            //mongofile.setChunkSize();  
            //mongofile.setContentType();  
            //mongofile.setMetaData();  
            mongofile.save();
            return ((ObjectId)mongofile.getId()).toString();
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }  
        return null;
    }

    @Override
    public Boolean deleteImageById(String id) {
        DB db = getMongoDb();
        GridFS gridFS = new GridFS(db);
        DBObject query=new BasicDBObject("_id", new ObjectId(id));
        GridFSDBFile gridFSDBFile = gridFS.findOne(query);
        if(gridFSDBFile != null) {
            gridFS.remove(gridFSDBFile);
            return true;
        }
        else {
            return false;
        }
    }
    
}