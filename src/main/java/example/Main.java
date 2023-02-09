package example;

import JXpress.App;
import JXpress.enums.HttpStatusCode;
import JXpress.enums.Method;
import JXpress.routing.Response;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        App app = new App();

        Counter counter = new Counter();

        app.addRoute(Method.GET, "/", req ->
                new Response().setHTML(
                        "<p>Counter: " + counter.getCounter() + "</p>" +
                        "<form action='/count' method='get'>" +
                        "<input type='number' name='num'>" +
                        "<input type='submit' value='add'/>" +
                        "</form>"
                )
        );

        app.addRoute(Method.POST, "/", req -> new Response().setHTML(req.getBody()));

        app.addRoute(Method.GET, "/count", req -> {
            try {
                int num = Integer.parseInt(req.getParams("num"));
                counter.add(num);
            } catch (NumberFormatException e) {
                counter.add();
            }

            return new Response()
                    .setHttpStatusCode(HttpStatusCode.FOUND)
                    .addHeader("Location", "/");
        });

        app.addRoute(Method.GET, "/sum", req -> {
            try {
                List<String> nums = req.getParamlists("num");
                int out = 0;
                for (String rawNum: nums) {
                    int num = Integer.parseInt(rawNum);
                    out += num;
                }
                return new Response()
                        .setBody(Integer.toString(out));

            } catch (NumberFormatException|NullPointerException ignored) {}

            return new Response()
                    .setHttpStatusCode(HttpStatusCode.BAD_REQUEST)
                    .setHTML("Add `?num[]={your num here}&num[]={your num here}` in the url. End make sure the `num`'s are numbers.");
        });

        app.listen(80);
    }
}
