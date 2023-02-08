package example;

import JXpress.App;
import JXpress.enums.Method;
import JXpress.routing.Response;

public class Main {

    public static void main(String[] args) {
        App app = new App();

        app.addRoute(Method.GET, "/", req -> {

            return new Response()
                    .setBody("hallo");
        });

        app.listen(80);
    }
}
