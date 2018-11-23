package designpattern.behavioral.chainofresponsibility.middleware;

import designpattern.behavioral.chainofresponsibility.server.Server;

public class UserExistsMiddleWare extends Middleware {
    private Server server;

    public UserExistsMiddleWare(Server server) {
        this.server = server;
    }

    @Override
    public boolean check(String email, String password) {
        if (!server.hasEmail(email)) {
            System.out.println("this email is not registered");
            return false;
        }

        if (!server.isValidPassword(email, password)) {
            System.out.println("Wrong password");
            return false;
        }

        return checkNext(email, password);
    }
}
