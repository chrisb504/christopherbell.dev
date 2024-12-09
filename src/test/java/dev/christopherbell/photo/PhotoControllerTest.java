package dev.christopherbell.photo;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(PhotoController.class)
public class PhotoControllerTest {

    @MockBean
    private PhotoService photoService;
    @Autowired
    private MockMvc mockMvc;

//    @Test
//    public void testGetImages_success() throws Exception {
//
//        when(photoService.getAllImages()).thenReturn(PhotoStub.getPhotoResponseStub());
//
//        mockMvc.perform(get("/api/photo/v1/"))
//            .andExpect(status().isOk());
//    }
}