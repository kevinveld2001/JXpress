package JXpress.callback;

import JXpress.routing.Request;
import JXpress.routing.Response;

public interface RequestHandler {
    Response run(Request req);
}
