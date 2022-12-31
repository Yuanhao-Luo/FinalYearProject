package com.example.watchvideo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MusicConrtoller {
    String startPath = "F:\\video\\music\\2022_09_02\\「Cover」The Forgotten Song - Nanashi Mumei\\";

    @RequestMapping("music")
    public String showDir(HttpSession session){
        String currentPath = startPath;
        if (session.getAttribute("currentPath") == null){
            session.setAttribute("currentPath", startPath);
        }else{
            currentPath = (String) session.getAttribute("currentPath");
        }
        File folder = new File(currentPath);
        String[] musics = folder.list();
        List<String> musicList = new ArrayList<String>();
        for (int i = 0; i < musics.length; i++) {
            String tmp = musics[i];
            tmp = Toolkit.HttpTranslate(tmp);


            musics[i] = tmp;
            musicList.add(musics[i]);
        }
        session.setAttribute("musicname", musics[0]);
        session.setAttribute("musicList", musicList);
        return "music";
    }
}
