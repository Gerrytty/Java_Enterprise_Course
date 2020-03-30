package servlets.exceptions;

public class MailException extends RuntimeException {

    public MailException() {
        super("Exception in email sending");
    }

}
