package com.example.watchvideo.Controller;

import com.example.watchvideo.DAO.MusicTableRepository;
import com.example.watchvideo.DAO.MusiclistTableRepository;
import com.example.watchvideo.DAO.UserModelRepository;
import com.example.watchvideo.Model.CreatorTable;
import com.example.watchvideo.Model.MusicTable;
import com.example.watchvideo.Model.MusiclistTable;
import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MusicController {
    @Autowired
    MusicTableRepository musicTR;

    @Autowired
    MusiclistTableRepository musiclistTR;

    @Autowired
    UserModelRepository userTR;

    @Value("${file.upload.path}")
    private String storage_path;


    @GetMapping("/music")
    public String music(Model model){
        Resource resource = new Resource();
        model.addAttribute("resource", resource);
        User user = new User();
        model.addAttribute("user", user);
        return "music";
    }

    @GetMapping("/music_list/new")
    @ResponseBody
    public List<Music> getNewMusic(){
        List<Music> musicList = new ArrayList<Music>();
        for (MusicTable i:
                musicTR.getNewMusic()) {
            Music m = new Music();
            m.setId(i.getId());
            m.setTitle(i.getTitle());

            //i.getvocals() is a Set of CreatorTable, change i.getvocals() to String
            if (i.getVocals().size() > 0){
                String vocals = "";
                for (CreatorTable j:
                        i.getVocals()) {
                    vocals += j.getName() + "/";
                }
                System.out.println(vocals);
                vocals = vocals.substring(0, vocals.length() - 1);
                m.setVocal(vocals);
            }

            musicList.add(m);
        }
        return musicList;
    }

    @GetMapping("/musiclist/user_list/{id}")
    @ResponseBody
    public List<Music> getUserMusicList(@PathVariable("id") int id){
        List<Music> musicList = new ArrayList<Music>();
        for (MusicTable i:
                musiclistTR.findByIdEquals(id).getMusic()) {
            Music m = new Music();
            m.setId(i.getId());
            m.setTitle(i.getTitle());

            //i.getvocals() is a Set of CreatorTable, change i.getvocals() to String
            if (i.getVocals().size() > 0){
                String vocals = "";
                for (CreatorTable j:
                        i.getVocals()) {
                    vocals += j.getName() + "/";
                }
                System.out.println(vocals);
                vocals = vocals.substring(0, vocals.length() - 1);
                m.setVocal(vocals);
            }

            musicList.add(m);
        }
        return musicList;
    }

    @GetMapping("/musiclist/info/{id}")
    @ResponseBody
    public Musiclist getMusicListInfo(@PathVariable("id") int id){
        MusiclistTable musiclist = musiclistTR.findByIdEquals(id);
        Musiclist m = new Musiclist();
        m.setId(musiclist.getId());
        m.setTitle(musiclist.getTitle());
        return m;
    }

    @GetMapping("/music_list/search_music")
    @ResponseBody
    public List<Music> searchMusic(@RequestParam("keyword") String keyword){
        List<Music> musicList = new ArrayList<Music>();
        for (MusicTable i:
                musicTR.search(keyword, keyword)) {
            Music m = new Music();
            m.setId(i.getId());
            m.setTitle(i.getTitle());

            //i.getvocals() is a Set of CreatorTable, change i.getvocals() to String
            if (i.getVocals().size() > 0){
                String vocals = "";
                for (CreatorTable j:
                        i.getVocals()) {
                    vocals += j.getName() + "/";
                }
                System.out.println(vocals);
                vocals = vocals.substring(0, vocals.length() - 1);
                m.setVocal(vocals);
            }

            musicList.add(m);
        }
        return musicList;
    }

    @GetMapping("/create_musiclist")
    @ResponseBody
    public void createMusiclist(@CookieValue(value = "userid", defaultValue = "") String userid, String title){
        MusiclistTable musiclist = new MusiclistTable();
        musiclist.setTitle(title);
        musiclist.setUser(userTR.findByIdEquals(Integer.parseInt(userid)));
        musiclistTR.save(musiclist);
    }

    @GetMapping("/music_player/{id}")
    public ResponseEntity<org.springframework.core.io.Resource> playMusic(@PathVariable("id") int id) throws IOException {
        MusicTable music = musicTR.findById(id).get();
        String path = storage_path + music.getFilePath() + "\\" + music.getFileName();
        File file = new File(path);

        org.springframework.core.io.Resource resource = new UrlResource(file.toURI());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("audio/mpeg"))
                .body(resource);
    }

    @PostMapping("/add_musiclist")
    @ResponseBody
    public void addMusicList(String music_id, String musiclist_id){
        MusiclistTable musiclist = musiclistTR.findByIdEquals(Integer.parseInt(musiclist_id));
        MusicTable music = musicTR.findByIdEquals(Integer.parseInt(music_id));
        musiclist.getMusic().add(music);
        musiclistTR.save(musiclist);
    }

    @GetMapping("/user_musiclists")
    @ResponseBody
    public List<Musiclist> getUserMusiclists(@CookieValue(value = "userid", defaultValue = "") String userid){
        List<Musiclist> musiclists = new ArrayList<Musiclist>();
        for (MusiclistTable i:
                musiclistTR.findByUserEquals(userTR.findByIdEquals(Integer.parseInt(userid)))) {
            Musiclist m = new Musiclist();
            m.setId(i.getId());
            m.setTitle(i.getTitle());
            musiclists.add(m);
        }
        return musiclists;
    }


}
