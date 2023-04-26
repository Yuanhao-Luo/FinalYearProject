package com.example.watchvideo;

import com.example.watchvideo.Controller.HomePageController;
import com.example.watchvideo.DAO.UserModelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class WatchVideoApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new HomePageController()).build();
    }

    @Test
    void homePage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("MainPage"));
    }

    @Test
    void loginPage() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login_content"));
    }

    @Test
    void registerPage() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register_content"));
    }

    @Test
    void musicPage() throws Exception {
        mockMvc.perform(get("/component/music"))
                .andExpect(status().isOk())
                .andExpect(view().name("music_content"));
    }

    @Test
    void videoPage() throws Exception {
        mockMvc.perform(get("/video"))
                .andExpect(status().isOk())
                .andExpect(view().name("video_content"));
    }

    @Test
    void uploadPage() throws Exception {
        mockMvc.perform(get("/component/upload"))
                .andExpect(status().isOk())
                .andExpect(view().name("upload_content"));
    }

}
