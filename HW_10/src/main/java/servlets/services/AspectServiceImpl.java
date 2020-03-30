package servlets.services;

import org.springframework.stereotype.Service;
import servlets.models.FileInfo;
import servlets.services.interfaces.AspectService;

@Service
public class AspectServiceImpl implements AspectService {

    private String email;
    private FileInfo fileInfo;

    @Override
    public FileInfo getFileInfo() {
        return fileInfo;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setFileInfo(FileInfo fileInfo) {
        this.fileInfo = fileInfo;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }
}
