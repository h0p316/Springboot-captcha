package com.captcha.captcha.controller;

import com.captcha.captcha.dto.CaptchaResponseDto;
import com.captcha.captcha.service.CaptchaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {CaptchaController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class CaptchaControllerDiffblueTest {
    @Autowired
    private CaptchaController captchaController;

    @MockBean
    private CaptchaService captchaService;

    /**
     * Method under test: {@link CaptchaController#generateCaptcha()}
     */
    @Test
    void testGenerateCaptcha() throws Exception {
        // Arrange
        CaptchaResponseDto buildResult = CaptchaResponseDto.builder().image("Image").build();
        when(captchaService.generateCaptcha()).thenReturn(buildResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/generate-captcha");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(captchaController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"image\":\"Image\"}"));
    }
}
