package servlets.repositories.interfaces;

import servlets.models.FileInfo;

import java.util.Optional;

public interface FilesRepository extends CrudRepository<Long, FileInfo> {

    Optional<FileInfo> findByName(String name);

}
