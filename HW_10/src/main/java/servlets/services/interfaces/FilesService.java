package servlets.services.interfaces;

import servlets.models.FileInfo;

public interface FilesService {

    FileInfo getFileInfoByName(String name);
}
