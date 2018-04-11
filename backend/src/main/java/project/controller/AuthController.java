package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import project.service.AuthSessionBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Map;

import static project.constants.ParamNames.IS_AUTHORIZED_KEY;

@Controller
@RequestMapping("/api/v1/authManagement")
public class AuthController {

    private final AuthSessionBean authSessionBean;

    @Autowired
    public AuthController(AuthSessionBean authSessionBean) {
        this.authSessionBean = authSessionBean;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> login(HttpServletRequest request) {
        Long userId = 123L;//TODO
        if (userId != null) {
            invalidateSession(request);
            request.getSession(true);
            authSessionBean.setAuthorized(true);
            authSessionBean.setAccountId(userId);
        }

        return Collections.emptyMap();
    }

    @RequestMapping(value = "check", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> check() {
        return Collections.singletonMap(IS_AUTHORIZED_KEY, authSessionBean.isAuthorized());
    }

    @RequestMapping(value = "logout", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> logout(HttpServletRequest request) {
        invalidateSession(request);

        return Collections.emptyMap();
    }

    private void invalidateSession(HttpServletRequest request) {
        HttpSession oldSession = request.getSession(false);
        if (oldSession != null) {
            oldSession.invalidate();
        }
    }
}
