package servlets.services.interfaces;

import org.apache.commons.fileupload.FileItem;
import servlets.models.FileInfo;

public interface FileUploadService {

    FileInfo upload(FileItem fileItem);
}
