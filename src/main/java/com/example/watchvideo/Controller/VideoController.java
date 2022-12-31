package com.example.watchvideo.Controller;

import com.example.watchvideo.DAO.VideoTableDAO;
import com.example.watchvideo.Model.VideoTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
//
//    String selectedVideo = "";
//    String startPath = "F:\\bangumi\\";
//    String currentPath = "F:\\bangumi\\";
//
//    @RequestMapping("/folder")
//    public String showMusic(HttpSession session){
//        String currentPath = startPath;
//        if (session.getAttribute("currentPath") == null){
//            session.setAttribute("currentPath", startPath);
//        }else{
//            currentPath = (String) session.getAttribute("currentPath");
//        }
//        File folder = new File(currentPath);
//        String[] musics = folder.list();
//        List<String> musicList = new ArrayList<String>();
//        for (int i = 0; i < musics.length; i++) {
//            String tmp = musics[i];
//            tmp = Toolkit.HttpTranslate(tmp);
//
//
//            musics[i] = tmp;
//            musicList.add(musics[i]);
//        }
//        session.setAttribute("musicname", musics[0]);
//        session.setAttribute("musicList", musicList);
//        return "folder";
//    }
//
//    @RequestMapping("/m/video")
//    public String selectMusic(String title, HttpSession session) throws IOException {
////        while (currentPath.contains("#")){
////            currentPath = currentPath.replace("#", "%23");
////        }
//        String currentPath = (String) session.getAttribute("currentPath");
//        File file = new File(currentPath + title);
//        if (file.isFile()){
//            System.out.println(title + " is a File");
//            selectedVideo = currentPath + title;
//            System.out.println("m video:  " + selectedVideo);
//            session.setAttribute("title", title);
//
//            Path path = Paths.get(selectedVideo);
//            String mimeType = Files.probeContentType(path);
//            session.setAttribute("mimeType", mimeType);
//
//            return "video";
//        }else if (file.isDirectory()){
//            System.out.println(title + " is directory");
//            currentPath = currentPath + title + "\\";
//            session.setAttribute("currentPath", currentPath);
//            return "forward:/folder";
//        }
//        System.out.println(title + "is not file or direcory");
//        return "forward:/folder";
//    }
//
//    @RequestMapping("/backToIndex")
//    public String backToIndex(HttpSession session){
//        session.setAttribute("currentPath", startPath);
//        return "forward:/folder";
//    }

//    @RequestMapping("getvideo")
//    public String getvideo(HttpServletRequest re, HttpServletResponse rs){
//        try{
//            FileInputStream file = null;
//            OutputStream out = null;
//            file = new FileInputStream("G:\\video\\music\\");
//            int size = file.available();
//            byte[] data = new byte[size];
//            file.read(data);
//            file.close();
//            file = null;
//            rs.setContentType("video/mp4");
//            out = rs.getOutputStream();
//            out.write(data);
//            out.flush();
//            out.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return "";
//
//    }

    @Autowired
    private VideoHttpRequestHandler videoHttpRequestHandler;

    @GetMapping("/player")
    @ResponseBody
    public void getPlayResource(HttpServletRequest request, HttpServletResponse response, String id) throws Exception {
        VideoTable videoTable = videoDAO.findByIdEquals(Integer.parseInt(id));
        System.out.println(videoTable.getFileName());
        String detectPath = videoTable.getFilePath() + "\\" + videoTable.getFileName();
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
    private VideoTableDAO videoDAO;

    @RequestMapping("/watch")
    public String videoPage(String video, HttpSession session, Model model){

        VideoTable videoTable = videoDAO.findByIdEquals(Integer.parseInt(video));
        if (videoTable.getSeries() != null){
            List<VideoTable> eps = videoDAO.findBySeries_IdEquals(videoTable.getSeries().getId());
            List<Video> episodeList = new ArrayList<>();
            for (VideoTable v:
                    eps) {
                episodeList.add(new Video(v.getId(), v.getTitle()));
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
        User user = new User();
        model.addAttribute("user", user);
        return "watch";
    }

}
