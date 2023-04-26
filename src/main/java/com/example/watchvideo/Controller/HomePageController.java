package com.example.watchvideo.Controller;

import com.example.watchvideo.DAO.UserModelRepository;
import com.example.watchvideo.DAO.VideoTableRepository;
import com.example.watchvideo.Model.UserTable;
import com.example.watchvideo.Model.VideoTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Controller
public class HomePageController {
    @Autowired
    VideoTableRepository videoTableDAO;

    Model model;

    @GetMapping("/")
    public String homePage(HttpSession session, Model model,
                           @CookieValue(value = "username", defaultValue = "") String username){
//        List<VideoTable> homeList = videoTableDAO.findAll();
//        List<Video> videoList = new ArrayList<Video>();
//        for (VideoTable i:
//             homeList) {
//            Video nv = new Video(i.getId(), i.getTitle());
//            if (i.getImage() != null){
//                nv.setImage(i.getImage().getId());
//            }else{
//                nv.setImage(0);
//            }
//            videoList.add(nv);
//        }
//        System.out.println(username);
//        session.setAttribute("videoList", videoList);
//        session.setAttribute("username", username);
//
//        User user = new User();
//        model.addAttribute("user", user);
////        cleanErr(session);
//
//        System.out.println("request url /");


        return "MainPage";
    }
    //

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
    public void logout(HttpServletResponse response){
        System.out.println("logout");
        Cookie cookie = new Cookie("username", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        Cookie cookie2 = new Cookie("email", null);
        cookie2.setMaxAge(0);
        response.addCookie(cookie2);

        Cookie cookie3 = new Cookie("userid", null);
        cookie3.setMaxAge(0);
        response.addCookie(cookie3);
    }

    @Autowired
    UserModelRepository userR;

//    @PostMapping("/login")
//    public String login(@ModelAttribute("user")User user, HttpServletResponse response, Model model){
//        UserTable userModel = userR.findByUserName(user.getUsername());
//        if (userModel != null && userModel.getPassword().equals(user.getPassword())){
//            Cookie user_cookie = new Cookie("username", userModel.getUserName());
//            Cookie userid_cookie = new Cookie("userid", String.valueOf(userModel.getId()));
//            response.addCookie(user_cookie);
//            response.addCookie(userid_cookie);
//
//            if (userModel.getEmail() != ""){
//                Cookie email_cookie = new Cookie("email", userModel.getEmail());
//                response.addCookie(email_cookie);
//            }
//
//            return "redirect:/";
//        }else{
//            errorMsg(0, model);
//        }
//        return "index";
//
//    }

    @GetMapping("/login")
    public String loginPage(){
        return "login_content";
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(String username, String password, HttpServletResponse response){
        UserTable userModel = userR.findByUserName(username);
        if (userModel != null && userModel.getPassword().equals(password)){
            storeUserCookie(userModel, response);

            return "success";
        }else{
            return "Wrong username or password";
        }
    }

//    @PostMapping("/register")
//    public String register(@ModelAttribute("user")User user, HttpServletResponse response, Model model){
//        System.out.println(user.getPassword());
//        if (!Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", user.getPassword())){
//            errorMsg(2, model);
//            return "index";
//        }
//        if (!"".equals(user.getEmail()) && !Pattern.matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", user.getEmail())){
//            errorMsg(3, model);
//            return "index";
//        }
//
//        if (user.getEmail().equals("")){
//            UserTable userModel = new UserTable();
//            userModel.setUserName(user.getUsername());
//            userModel.setPassword(user.getPassword());
//            userModel.setEmail(user.getEmail());
//            if (userR.findByUserName(user.getUsername()) == null){
//                userR.save(userModel);
//                Cookie user_cookie = new Cookie("username", user.getUsername());
//                response.addCookie(user_cookie);
//
//                if (userModel.getEmail() != ""){
//                    Cookie email_cookie = new Cookie("email", userModel.getEmail());
//                    response.addCookie(email_cookie);
//                }
//                return "redirect:/";
//            }else{
//                errorMsg(1, model);
//                return "index";
//            }
//        }else{
//            UserTable userModel = userR.findByUserName(user.getUsername());
//            if (userModel == null){
//                UserTable newuser = new UserTable();
//                newuser.setUserName(user.getUsername());
//                newuser.setPassword(user.getPassword());
//                newuser.setEmail(user.getEmail());
//                userR.save(newuser);
//                Cookie user_cookie = new Cookie("username", user.getUsername());
//                response.addCookie(user_cookie);
//            }else{
//                if (userModel.getPassword().equals(user.getPassword())){
//                    userModel.setEmail(user.getEmail());
//                    userR.save(userModel);
//                }else{
//                    errorMsg(4, model);
//                    return "index";
//                }
//            }
//            return "redirect:/";
//        }
//    }

    @GetMapping("/register")
    public String registerPage(){
        return "register_content";
    }

    @PostMapping("/register")
    @ResponseBody
    public String register(String username, String password, String email, HttpServletResponse response){
        if (!Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", password)){
            return "Password must contain at least 8 characters, at least one uppercase letter, one lowercase letter and one number";
        }
        if (!"".equals(email) && !Pattern.matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", email)){
            return "Wrong email format";
        }

        if (email.equals("")){
            UserTable userModel = new UserTable();
            userModel.setUserName(username);
            userModel.setPassword(password);
            userModel.setEmail(email);
            if (userR.findByUserName(username) == null){
                userR.save(userModel);
                storeUserCookie(userModel, response);
                return "success";
            }else{
                return "Username already exists";
            }
        }else{
            UserTable userModel = userR.findByUserName(username);
            if (userModel == null){
                UserTable newuser = new UserTable();
                newuser.setUserName(username);
                newuser.setPassword(password);
                newuser.setEmail(email);
                userR.save(newuser);
                storeUserCookie(newuser, response);
            }else{
                if (userModel.getPassword().equals(password)){
                    userModel.setEmail(email);
                    userR.save(userModel);
                    storeUserCookie(userModel, response);
                }else{
                    return "Wrong password";
                }
            }
            return "success";
        }
    }

    @GetMapping("/mainpage")
    public String mainPage(){
        return "MainPage";
    }

    @GetMapping("/home_content")
    public String homeContent(){
        return "home";
    }

    @GetMapping("/component/header")
    public String header(){
        return "header_content";
    }

    @GetMapping("/component/music")
    public String musicPage(){
        return "music_content";
    }

    @GetMapping("/component/musicplayer")
    public String musicPlayerPage(){
        return "musicplayer_content";
    }

    @GetMapping("/component/musiclist")
    public String musiclistPage(){
        return "musiclist_content";
    }

    @GetMapping("/component/upload")
    public String uploadPage(Model model){
        Resource resource = new Resource();
        model.addAttribute("resource", resource);
        return "upload_content";
    }

    @GetMapping("/video")
    public String videopage(){
        return "video_content";
    }

    private String[] errorName = {"loginErr", "registerErr"};
    private String[] errorMsgName = {"loginErrMsg", "registerErrMsg"};


    //loginErr=0,registerErr=1
    private void errorMsg(int error_id, Model model){
        model.addAttribute("error_id", error_id);
    }

    private void storeUserCookie(UserTable user, HttpServletResponse response){
        Cookie user_cookie = new Cookie("username", user.getUserName());
        Cookie userid_cookie = new Cookie("userid", String.valueOf(user.getId()));
        response.addCookie(user_cookie);
        response.addCookie(userid_cookie);

        if (user.getEmail() != ""){
            Cookie email_cookie = new Cookie("email", user.getEmail());
            response.addCookie(email_cookie);
        }
    }
    

}
