package services.message;


import dto.Dto;

public interface AddMessageService {

    Dto save(String message, int id);

}
