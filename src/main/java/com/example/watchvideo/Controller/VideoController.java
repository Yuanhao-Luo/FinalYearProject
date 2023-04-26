package com.example.watchvideo.Controller;

import com.example.watchvideo.DAO.VideoTableRepository;
import com.example.watchvideo.Model.VideoTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class VideoController {



    @Autowired
    private VideoHttpRequestHandler videoHttpRequestHandler;


    @Value("${file.upload.path}")
    private String storage_path;

    @Autowired
    private VideoTableRepository videoTR;

//    @GetMapping("/video")
//    public String videopage(HttpSession session, Model model,
//                            @CookieValue(value = "username", defaultValue = "") String username){
//        List<VideoTable> homeList = videoTR.findAll();
//        List<Video> videoList = new ArrayList<Video>();
//        for (VideoTable i:
//                homeList) {
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
//
//
//        return "video";
//    }



    @GetMapping("/video/latest")
    @ResponseBody
    public List<Video> getLatestVideo(){
        List<VideoTable> homeList = videoTR.findByOrderByIdDesc();
        List<Video> videoList = new ArrayList<Video>();

        //get first 20 video
        int count = 0;
        for (VideoTable i:
                homeList) {
            if (count >= 20){
                break;
            }
            Video nv = new Video(i.getId(), i.getTitle());
            if (i.getImage() != null){
                nv.setImage(i.getImage().getId());
            }else{
                nv.setImage(0);
            }
            videoList.add(nv);
            count++;
        }
        return videoList;
    }

    @GetMapping("/player")
    @ResponseBody
    public void getPlayResource(HttpServletRequest request, HttpServletResponse response, String id) throws Exception {
        VideoTable videoTable = videoDAO.findByIdEquals(Integer.parseInt(id));
        System.out.println(videoTable.getFileName());
        String detectPath = storage_path + videoTable.getFilePath() + "\\" + videoTable.getFileName();
        System.out.println(detectPath);
        Path path = Paths.get(detectPath);
        if (Files.exists(path)) {
            String mimeType = Files.probeContentType(path);
            if (!mimeType.isEmpty()){
                response.setContentType(mimeType);
            }
//            if (!StringUtils.isEmpty(mimeType)) {
//
//            }
            request.setAttribute(VideoHttpRequestHandler.ATTR_FILE, path);
            videoHttpRequestHandler.handleRequest(request, response);
        } else {
            System.out.println("File not exist");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        }
    }

    @Autowired
    private VideoTableRepository videoDAO;

    @RequestMapping("/watch")
    public String videoPage(String video, HttpSession session){

        VideoTable videoTable = videoDAO.findByIdEquals(Integer.parseInt(video));
        if (videoTable.getSeries() != null){
            List<VideoTable> eps = videoDAO.findBySeries_IdEquals(videoTable.getSeries().getId());
            List<Video> episodeList = new ArrayList<>();
            for (VideoTable v:
                    eps) {
                Video nv = new Video(v.getId(), v.getTitle());
                if (v.getImage() != null){
                    nv.setImage(v.getImage().getId());
                }else{
                    nv.setImage(0);
                }
                episodeList.add(nv);

            }
            session.setAttribute("episodes", episodeList);
        }

        if (videoTable.getSeries() != null){
            session.setAttribute("series", videoTable.getSeries().getName());
        }else{
            session.setAttribute("series", null);
        }
        session.setAttribute("title", videoTable.getTitle());
        session.setAttribute("player", "/player?id="+video);

        return "watch";
    }

    @GetMapping("/search_video")
    @ResponseBody
    private List<Video> search_video(String content){
        System.out.println("content from user: " + content);
        if (content.matches("[a-zA-Z0-9]+")){
            List<VideoTable> videos = videoTR.findByTitleContainsIgnoreCase(content);
            for (VideoTable v :
                    videos) {
                System.out.println(v.getTitle());
            }

            List<Video> videoList = new ArrayList<Video>();
            for (VideoTable i:
                    videos) {
                Video nv = new Video(i.getId(), i.getTitle());
                if (i.getImage() != null){
                    nv.setImage(i.getImage().getId());
                }else{
                    nv.setImage(0);
                }
                videoList.add(nv);
            }

            return videoList;
            
        }
        return null;
    }

}
