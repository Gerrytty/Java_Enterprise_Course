package servlets.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import servlets.models.FileInfo;
import servlets.repositories.interfaces.FilesRepository;
import servlets.services.interfaces.FilesService;

@Service
public class FilesServiceImpl implements FilesService {

    @Autowired
    FilesRepository filesRepository;

    @Override
    public FileInfo getFileInfoByName(String name) {
        return filesRepository.findByName(name).get();
    }
}
