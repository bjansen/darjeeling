import ceylon.json {
    jsonParse=parse,
    JsonObject=Object
}
import ceylon.logging {
    logger
}
import ceylon.net.http.server {
    Request
}
import ceylon.net.uri {
    parse
}

import com.github.bjansen.darjeeling.dao {
    userDao
}
import com.github.bjansen.gyokuro {
    controller,
    route
}

route("auth")
controller
class AuthenticationController() {
    
    value googleApiUrl = "https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=";
    value log = logger(`module com.github.bjansen.darjeeling`);
    
    route("login/google")
    shared Boolean loginUsingGoogleToken(String googleToken, Request req) {
        value response = parse(googleApiUrl + googleToken).get().execute();

        if (response.status == 200,
            is JsonObject contents = jsonParse(response.contents),
            exists user = userDao.findUserByUid("google_oauth2", contents.getString("sub"))) {

            log.info("Logging user ``user.id``");
            req.session.put("userId", user.id);
            req.session.put("userUid", user.uid);
            
            return true;
        }
        
        return false;
    }
    
    route("logout")
    shared Boolean logout(Request req) {
        // TODO find a way to invalidate the session
        req.session.remove("userId");
        req.session.remove("userUid");
        
        return true;
    }
}