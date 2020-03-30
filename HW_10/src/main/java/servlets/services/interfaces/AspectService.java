package servlets.services.interfaces;

import servlets.models.FileInfo;

public interface AspectService {

    FileInfo getFileInfo();

    String getEmail();

    void setFileInfo(FileInfo fileInfo);

    void setEmail(String email);
}
