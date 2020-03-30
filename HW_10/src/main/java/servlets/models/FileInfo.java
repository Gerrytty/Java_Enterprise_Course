package servlets.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileInfo {

    private Long id;
    // название файла в хранилище
    private String storageFileName;
    // название файла оригинальное
    private String originalFileName;
    // размер файла
    private Long size;
    // тип файла (MIME)
    private String type;
    // по какому URL можно получить файл
    private String url;
}