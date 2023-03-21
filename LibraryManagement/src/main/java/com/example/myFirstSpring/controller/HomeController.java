package com.example.myFirstSpring.controller;

import com.example.myFirstSpring.service.LibrarianService;
import com.example.myFirstSpring.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.*;

@Controller
public class HomeController {

    @Autowired
    LibrarianService librarianService;
    @Autowired
    UserService userService;
    private final Set<String> sessionIds = Collections.synchronizedSet(new HashSet<>());


    @RequestMapping({"/","/login"})
    public String getLogin(Model model, HttpSession session) {
        // to fake a login we need all Librarian and Users
        model.addAttribute("users", userService.getAllUsers().values());
        model.addAttribute("librarians", librarianService.getAllLibrarians().values());

        // default site to load when request is done
        // it load login/login.html with a conditional render
        // upon session.isLogin
        // first time we CREATE the variable and isLogin = false
        if ( session.getAttribute("isLogin") == null )
            session.setAttribute("isLogin", false);
        return "login/login";
    }

    @RequestMapping("/home")
    public String getHome(Model model, HttpSession session) {
        // we need a GET home endpoint to render home when request is done
        // whenever and wherever is done if it does NOT come from a
        // POST request from LOG IN
        session.setAttribute("requestCount", getRequestCount(session));
        return "home";
    }

    @RequestMapping("/logout")
    public String logout(Model model, HttpSession session) {
        // ee invalidate session and redirect to login.html
        // login.html is our default site
        session.invalidate();
        return "redirect:login";
    }


    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public String login(HttpSession session, HttpServletRequest request,
                     @RequestParam("librarianIdFromSelect") String librarianId,
                    @RequestParam("userIdFromSelect") String userId){

        session.setAttribute("requestCount", getRequestCount(session));

        // the first time, the fake login POST with isLogin = false
        // will comply the condition, so (1) we will create all variables
        // (2) it will be redirected to home and
        // (3) isLogin = true
        // NEXT POST attempt to re-login will fail the condition,
        // and it will be redirected to LOG IN where the
        // conditional RENDER will show a LOGOUT because
        // isLogin = true
        if (!((boolean) session.getAttribute("isLogin"))) {
            this.sessionIds.add(session.getId());

            session.setAttribute("librarianId", librarianId);
            session.setAttribute("userId", userId);
            session.setAttribute("session-creation-timestamp", new Date().toString());
            session.setAttribute("todayDate", new Date().toString());

            session.setAttribute("sessionId", session.getId());
            session.setAttribute("sessionCount", this.sessionIds.size());

            Map<String, String> headerNamesMap = new HashMap<String, String>();

            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String key = (String) headerNames.nextElement();
                String value = request.getHeader(key);
                headerNamesMap.put(key, value);
            }

            session.setAttribute("httpServletRequestHeaders", headerNamesMap);
            session.setAttribute("isLogin", true);

            return "redirect:home";
        } else {
            return "redirect:/";
        }

    }

    private Object getRequestCount(HttpSession session) {

    Integer requestCount = (Integer) session.getAttribute("requestCount");

    requestCount = requestCount != null ? requestCount : 0;
    requestCount++;

    session.setAttribute("requestCount", requestCount);

    return requestCount;
    }

}
