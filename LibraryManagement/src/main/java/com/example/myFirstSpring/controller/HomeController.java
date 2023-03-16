package com.example.myFirstSpring.controller;


import com.example.myFirstSpring.model.Librarian;
import com.example.myFirstSpring.service.LibrarianService;
import com.example.myFirstSpring.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
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
    public String getLogin() {
        return "login/login";
    }

    @RequestMapping("/loginUser")
    public String getLoginUser(Model model) {
        model.addAttribute("users", userService.getAllUsers().values());
        return "login/loginUser";
    }

    @RequestMapping("/loginLibrarian")
    public String getLoginLibrarian(Model model) {
        model.addAttribute("librarians", librarianService.getAllLibrarians().values());
        return "login/loginLibrarian";
    }

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public String getHome(HttpSession session,Model model, HttpServletRequest request,
                     @RequestParam("librarianIdFromSelect") String librarianId,
                    @RequestParam("userIdFromSelect") String userId){

        session.setAttribute("librarianId", librarianId);
        session.setAttribute("userId", userId);
        session.setAttribute("session-creation-timestamp", new Date().toString());
        model.addAttribute("todayDate", new Date().toString());

        this.sessionIds.add(session.getId());

        model.addAttribute("sessionId", session.getId());
        model.addAttribute("sessionType", session.getClass().getName());
        model.addAttribute("sessionCount", this.sessionIds.size());
        model.addAttribute("requestCount", getRequestCount(session));

        Map<String, String> map = new HashMap<String, String>();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        model.addAttribute("httpServletRequest", map);

        return "home";
    }

    private Object getRequestCount(HttpSession session) {

    Integer requestCount = (Integer) session.getAttribute("requestCount");

    requestCount = requestCount != null ? requestCount : 0;
    requestCount++;

    session.setAttribute("requestCount", requestCount);

    return requestCount;
    }

}
