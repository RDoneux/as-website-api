package com.aswebsiteapi.util;

import com.aswebsiteapi.image.ImageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

public abstract class AbstractMockMvcTest {

    @MockBean
    protected ImageRepository imageRepository;

    @Autowired
    protected MockMvc mockMvc;

}
