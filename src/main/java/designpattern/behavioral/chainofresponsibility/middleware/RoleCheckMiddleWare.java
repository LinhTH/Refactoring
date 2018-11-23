package designpattern.behavioral.chainofresponsibility.middleware;

public class RoleCheckMiddleWare extends Middleware{
    @Override
    public boolean check(String email, String password) {
        if (email.equals("admin@example.com")) {
            System.out.println("Hello, admin");
            return true;
        }

        System.out.println("hello, user");
        return checkNext(email, password);
    }
}
