package server.electronics.login;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Map;

@RestController
class LoginController {

    @GetMapping("/admin/token")
    public Map<String, String> tokenAdmin(HttpSession session, HttpServletRequest request) {

        if (request.isUserInRole("ADMIN")) {
            return Collections.singletonMap("token", session.getId());
        }
        return (Map<String, String>) new ResponseEntity("Wrong credentials", HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/session/verifySession")
    public ResponseEntity checkSession() {
        return new ResponseEntity("Session Active!", HttpStatus.OK);
    }

    @PostMapping(value="/panel/admin/logout")
    @ResponseStatus(code = HttpStatus.OK)
    public void logout(){
        SecurityContextHolder.clearContext();
    }

    @Transactional
    @GetMapping("/customer/session/verifySession")
    public ResponseEntity verifySession() {
        return new ResponseEntity("Session Active!", HttpStatus.OK);
    }

    @Transactional
    @GetMapping("/customer/session/getSession")
    @ResponseStatus(code = HttpStatus.OK)
    public Map<String, String> getSession(HttpServletRequest request){
        return Collections.singletonMap("sessionId", request.getSession().getId());
    }
}