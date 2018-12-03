package designpattern.behavioral.chainofresponsibility;

import designpattern.behavioral.chainofresponsibility.middleware.Middleware;
import designpattern.behavioral.chainofresponsibility.middleware.RoleCheckMiddleWare;
import designpattern.behavioral.chainofresponsibility.middleware.ThrottlingMiddleware;
import designpattern.behavioral.chainofresponsibility.middleware.UserExistsMiddleWare;
import designpattern.behavioral.chainofresponsibility.server.Server;

public class Demo {
    private static Server server;

    private static void init() {
        server = new Server();
        server.register("admin@example.com", "admin_pass");
        server.register("user@example.com", "user_pass");

        Middleware middleware = new ThrottlingMiddleware(2);
        middleware.linkWith(new UserExistsMiddleWare(server))
                  .linkWith(new RoleCheckMiddleWare());

        server.setMiddleware(middleware);
    }

    public static void main(String[] args)  {
        init();

        server.logIn("admin@example.com", "user_pass");
    }
}
