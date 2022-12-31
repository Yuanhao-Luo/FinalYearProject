package com.example.watchvideo.Controller;

import com.example.watchvideo.DAO.UserModelRepository;
import com.example.watchvideo.DAO.VideoTableDAO;
import com.example.watchvideo.Model.UserModel;
import com.example.watchvideo.Model.VideoTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomePageController {
    @Autowired
    VideoTableDAO videoTableDAO;

    Model model;

    @RequestMapping("/")
    public String homePage(HttpSession session, Model model,
                           @CookieValue(value = "username", defaultValue = "") String username){
        List<VideoTable> homeList = videoTableDAO.findAll();
        List<Video> videoList = new ArrayList<Video>();
        for (VideoTable i:
             homeList) {
            videoList.add(new Video(i.getId(), i.getTitle()));
        }
        System.out.println(username);
        session.setAttribute("videoList", videoList);
        session.setAttribute("username", username);

        User user = new User();
        model.addAttribute("user", user);
        return "index";
    }

//    @GetMapping("/login")
//    public String loginPage(Model model){
//        User user = new User();
//        model.addAttribute("user", user);
//        return "login";
//    }
//
//    @GetMapping("/register")
//    public String registerPage(Model model){
//        User user = new User();
//        model.addAttribute("user", user);
//        return "register";
//    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response){
        System.out.println("logout");
        Cookie cookie = new Cookie("username", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }

    @Autowired
    UserModelRepository userR;




    @PostMapping("/login")
    public String login(@ModelAttribute("user")User user, HttpServletResponse response){
        UserModel userModel = userR.findByUserName(user.getUsername());
        if (userModel != null && userModel.getPassword().equals(user.getPassword())){
            Cookie user_cookie = new Cookie("username", user.getUsername());
            response.addCookie(user_cookie);
            return "redirect:/";
        }else{
            return "login";
        }

    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user")User user, HttpServletResponse response){
        UserModel userModel = new UserModel();
        userModel.setUserName(user.getUsername());
        userModel.setPassword(user.password);
        userModel.setEmail(user.email);
        if (userR.findByUserName(user.getUsername()) == null){
            userR.save(userModel);
            Cookie user_cookie = new Cookie("username", user.getUsername());
            response.addCookie(user_cookie);
            return "redirect:/";
        }else{
            return null;
        }

    }
}
