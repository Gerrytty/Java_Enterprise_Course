package dispatcher;

import dto.Dto;
import protocol.Login;
import protocol.Request;
import services.login.LoginService;
import services.login.LoginServiceImpl;

public class RequestsDispatcher {

    private LoginService service;

    public RequestsDispatcher() {
        service = new LoginServiceImpl();
    }


    public Dto doDispatch(Request request) {

        if (request.getHeader().equals("signIn")) {

            Login login = ((Request<Login>) request).getPayload();

            return service.signIn(login.getEmail(), login.getPassword());
        }

        else if(request.getHeader().equals("SignUp")) {

            Login login = ((Request<Login>) request).getPayload();

            return service.signUp(login.getEmail(), login.getPassword());
        }

        else throw new IllegalArgumentException();
    }
}
