package action;

import java.io.InputStream;

import com.mongodb.gridfs.GridFSDBFile;
import com.opensymphony.xwork2.ActionSupport;

import service.ImageService;

public class ImageAction extends ActionSupport {
    private static final long serialVersionUID = -7440777248269348459L;

    private ImageService imageService;
    
    private String imageID;
    private InputStream imageFile;
    private String imageFileName;
    private String contentType;
    
    /* ===================================================== */
    
    public ImageService getImageService() {
        return imageService;
    }

    public void setImageService(ImageService imageService) {
        this.imageService = imageService;
    }

    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }

    public InputStream getImageFile() {
        return imageFile;
    }

    public void setImageFile(InputStream imageFile) {
        this.imageFile = imageFile;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    
    /* ====================================================== */

    public String showImage() {
        GridFSDBFile gridFSDBFile = getImageService().showImage(this.imageID);
        if(gridFSDBFile == null) {
            return "showImageError";
        }
        this.imageFile = gridFSDBFile.getInputStream();
        this.imageFileName = gridFSDBFile.getFilename();
        this.contentType = "image/"+this.imageFileName.substring(this.imageFileName.lastIndexOf(".")+1,this.imageFileName.length());
        return "showImage";
    }
}