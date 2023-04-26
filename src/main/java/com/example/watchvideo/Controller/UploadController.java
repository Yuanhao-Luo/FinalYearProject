package com.example.watchvideo.Controller;

import com.example.watchvideo.DAO.*;
import com.example.watchvideo.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class UploadController {

    @Value("${file.upload.path}")
    private String path;

    @Autowired
    private VideoTableRepository videoTR;

    @Autowired
    private SeriesTableRepository seriesTR;

    @Autowired
    private CreatorTableRepository creatorTR;

    @Autowired
    private MusicTableRepository musicTR;

    @Autowired
    private ImageTableRepository imageTR;


    private String video_path = "\\video";
    private String music_path = "\\music";
    private String image_path = "\\image";

    @GetMapping("/upload")
    public String uploadpage(Model model){
        Resource resource = new Resource();
        model.addAttribute("resource", resource);
        User user = new User();
        model.addAttribute("user", user);
        return "upload";
    }



    @PostMapping("/file")
    @ResponseBody
    public void create(@RequestPart MultipartFile file, @ModelAttribute("resource")Resource resource) throws IOException {
        String fileName = file.getOriginalFilename();
        String filePath = path + video_path + "\\" + fileName;

        resource.setFile_name(fileName);

        File dest = new File(filePath);
        Files.copy(file.getInputStream(), dest.toPath());

//        return "Upload file success : " + dest.getAbsolutePath();
    }

    @PostMapping("/name")
    @ResponseBody
    public List listname(){
        List res = new ArrayList();
        res.add("asdf");
        return res;
    }

    @PostMapping("/resource")
    @ResponseBody
    public String submitRes(@RequestParam("file") MultipartFile file, @RequestParam(value="image", required = false) MultipartFile image, @ModelAttribute("resource")Resource resource, Model model) throws IOException {
        System.out.println("Resource: " + resource.getTitle());
        Resource r = (Resource) model.getAttribute("resource");
        System.out.println("R: " + r.getTitle());
        if (resource.getType() == 1){
            VideoTable video = new VideoTable();
            int video_id = (videoTR.findByOrderByIdDesc().size() == 0) ? 1 : videoTR.getMaxId() + 1;

            String fileName = video_id + "." + file.getOriginalFilename().split("\\.")[1];
            String filePath = path + video_path + "\\" + fileName;

            System.out.println("File Name: " + fileName);

            File dest = new File(filePath);
            Files.copy(file.getInputStream(), dest.toPath());

            video.setFileName(fileName);
            video.setTitle(resource.getTitle());
            video.setFilePath(video_path);
            video.setId(video_id);
            if (!resource.getDate().equals("")){
                video.setDate(LocalDate.parse(resource.getDate()));
            }
            if (!resource.getSeries().equals("")){
                video.setSeries(seriesTR.findByIdEquals(Integer.parseInt(resource.getSeries())));
            }
            if (!image.isEmpty()){
                int image_id = (imageTR.findAll().size() == 0) ? 1 : imageTR.getMaxId() + 1;
                String imageName = image_id + "." + image.getOriginalFilename().split("\\.")[1];
                String imagePath = path + image_path + "\\" + imageName;

                File ima = new File(imagePath);
                Files.copy(image.getInputStream(), ima.toPath());

                ImageTable imageTable = new ImageTable();
                imageTable.setFileName(imageName);
                imageTable.setFilePath(image_path);
                imageTable.setId(image_id);
                ImageTable saved_image = imageTR.save(imageTable);

                System.out.println("Image Id: " + saved_image.getId());

                video.setImage(saved_image);
            }
            videoTR.save(video);
        } else if (resource.getType() == 2) {
            MusicTable music = new MusicTable();

            String fileName = file.getOriginalFilename();
            String filePath = path + music_path + "\\" + fileName;

            File dest = new File(filePath);
            Files.copy(file.getInputStream(), dest.toPath());

//        String[] date = resource.getDate().split("-");
//        System.out.println(resource.getDate());
//        LocalDate ld = LocalDate.parse(resource.getDate());


            music.setFileName(fileName);
            music.setTitle(resource.getTitle());
            music.setFilePath(music_path);
            if (!resource.getDate().equals("")){
                music.setDate(LocalDate.parse(resource.getDate()));
            }
            musicTR.save(music);

            if (resource.getVocal().size() > 0){
                for (String id :
                        resource.getVocal()) {
                    CreatorTable vocal = creatorTR.findByIdEquals(Integer.parseInt(id));
                    vocal.getVocals().add(music);
                    creatorTR.save(vocal);
                }
            }
            if (resource.getComposer().size() > 0){
                for (String id :
                        resource.getComposer()) {
                    CreatorTable composer = creatorTR.findByIdEquals(Integer.parseInt(id));
                    composer.getComposers().add(music);
                    creatorTR.save(composer);
                }
            }
            if (resource.getLyricist().size() > 0){
                for (String id :
                        resource.getLyricist()) {
                    CreatorTable lyricist = creatorTR.findByIdEquals(Integer.parseInt(id));
                    lyricist.getLyricists().add(music);
                    creatorTR.save(lyricist);
                }
            }
            if (!image.isEmpty()){
                int image_id = imageTR.getMaxId() + 1;
                String imageName = image_id + "." + image.getOriginalFilename().split("\\.")[1];
                String imagePath = path + image_path + "\\" + imageName;

                File ima = new File(imagePath);
                Files.copy(image.getInputStream(), ima.toPath());

                ImageTable imageTable = new ImageTable();
                imageTable.setFileName(imageName);
                imageTable.setFilePath(image_path);
                imageTable.setId(image_id);
                ImageTable saved_image = imageTR.save(imageTable);

                System.out.println("Image Id: " + saved_image.getId());

                music.setImage(saved_image);
            }

        }


        Resource rr = new Resource();
        model.addAttribute("resource", rr);

        return "success";
    }

    @GetMapping("/search_series")
    @ResponseBody
    public List<SeriesTable> search_series(String title){
        List<SeriesTable> seriesTables = seriesTR.findByNameContainingIgnoreCase(title);
        return seriesTables;
    }

    @GetMapping("/search_creators")
    @ResponseBody
    public List<CreatorTable> search_creators(String name){
        List<CreatorTable> creators = creatorTR.findByNameContainsIgnoreCase(name);
        return creators;
    }

    @PostMapping("/save_upload")
    @ResponseBody
    public Resource save_upload(@ModelAttribute("resource")Resource resource, Model model, String composer, String date, String lyricist, String series, String title, String type, String vocal){
        System.out.println(title);
        resource.setSeries(series);
        resource.setTitle(title);
        resource.setType(Integer.parseInt(type));
        resource.setDate(date);

        resource.setVocal(Arrays.asList(vocal.split(",")));
        resource.setComposer(Arrays.asList(composer.split(",")));
        resource.setLyricist(Arrays.asList(lyricist.split(",")));

        model.addAttribute("resource", resource);

        Resource r = (Resource) model.getAttribute("resource");
        System.out.println("save_upload: " + r.getTitle());

        return resource;
    }

    @PostMapping("/new_series")
    @ResponseBody
    public void new_series(String name){
        if (name.equals("") || seriesTR.findByNameLikeIgnoreCase(name) != null){
            return;
        }
        SeriesTable seriesTable = new SeriesTable();
        seriesTable.setName(name);
        seriesTR.save(seriesTable);
    }

    @PostMapping("/new_creator")
    @ResponseBody
    public void new_vocal(String name){
        if (name.equals("") || creatorTR.findByNameLikeIgnoreCase(name) != null){
            return;
        }
        CreatorTable creatorTable = new CreatorTable();
        creatorTable.setName(name);
        creatorTR.save(creatorTable);
    }

    @GetMapping("/test_many")
    public void teset_many(){
//        MusicTable musicTable = new MusicTable();
//        musicTable
    }
}
