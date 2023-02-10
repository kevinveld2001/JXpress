package JXpress.middleware;

import JXpress.callback.RequestHandler;
import JXpress.enums.HttpStatusCode;
import JXpress.routing.Request;
import JXpress.routing.Response;

import java.util.ArrayList;
import java.util.List;

public class MiddlewareHandler {
    List<RequestHandler> globalMiddleware = new ArrayList<>();

    public Response run(RequestHandler[] routes, Request req) {
        for (RequestHandler middleware:globalMiddleware) {
            Response res = middleware.run(req);
            if (res != null) return res;
        }
        for (RequestHandler route:routes) {
            Response res = route.run(req);
            if (res != null) return res;
        }
        return new Response().setHttpStatusCode(HttpStatusCode.INTERNAL_SERVER_ERROR);
    }

    public void addGlobalMiddleware(RequestHandler globalMiddleware) {
        this.globalMiddleware.add(globalMiddleware);
    }
}
