package servlets.services;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import servlets.models.FileInfo;
import servlets.repositories.interfaces.FilesRepository;
import servlets.services.interfaces.FileUploadService;

import java.io.File;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    FilesRepository filesRepository;

    public FileInfo upload(FileItem fileItem) {

        FileInfo fileInfo = getFileInfo(fileItem);
        File uploadedFile = new File("/home/yuliya/IdeaProjects/HW_10/src/main/webapp/downloads/" + fileInfo.getStorageFileName());
        try {
            fileItem.write(uploadedFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        filesRepository.save(fileInfo);

        return fileInfo;
    }


    private FileInfo getFileInfo(FileItem item) {

        String fileName = item.getName();
        String storageFileName = createFileName(fileName);

        return FileInfo.builder().
                originalFileName(item.getName()).
                storageFileName(storageFileName).
                size(item.getSize()).
                type(item.getContentType()).
        url("/home/yuliya/IdeaProjects/HW_10/src/main/webapp/downloads/").
                build();

    }

    /***
     * creating unique fileName
     * if fileName is already exists return fileName_1
     * if fileName_1 is already exists return fileName_2
     ***/
    private String createFileName(String sourceURL) {
        String fileName = sourceURL.substring(sourceURL.lastIndexOf('/') + 1);
        fileName = fileName.replace(" ", "_");
        File file = new File("/home/yuliya/IdeaProjects/HW_10/src/main/webapp/downloads/" + fileName);
        String s;

        if(file.exists()) {

            String name = fileName.substring(0, fileName.indexOf("."));
            String extension = fileName.substring(fileName.indexOf("."));
            char c = name.charAt(name.length() - 1);

            if(c >= '0' && c <= '9') {
                int n = Integer.parseInt("" + c);
                s = name.substring(0, name.lastIndexOf(String.valueOf(n))) + (n + 1) + extension;
            }
            else {
                s = name + "_1" + extension;
            }

            return createFileName(s);

        }

        else {
            return fileName;
        }

    }

}
