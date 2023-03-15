package com.example.myFirstSpring.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
public class HomeController {

    private final Set<String> sessionIds = Collections.synchronizedSet(new HashSet<>());
    @RequestMapping("/home")
    public String gethome(HttpSession session,Model model, HttpServletRequest request){

       session.setAttribute("user", "user java object");

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
