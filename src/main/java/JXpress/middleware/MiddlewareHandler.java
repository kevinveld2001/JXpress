package JXpress.middleware;

import JXpress.callback.RequestHandler;
import JXpress.routing.Request;
import JXpress.routing.Response;

import java.util.ArrayList;
import java.util.List;

public class MiddlewareHandler {
    List<RequestHandler> globalMiddleware = new ArrayList<>();

    public Response run(RequestHandler route, Request req) {
        for (RequestHandler middleware:globalMiddleware) {
            Response res = middleware.run(req);
            if (res != null) return res;
        }
        return route.run(req);
    }

    public void addGlobalMiddleware(RequestHandler globalMiddleware) {
        this.globalMiddleware.add(globalMiddleware);
    }
}
