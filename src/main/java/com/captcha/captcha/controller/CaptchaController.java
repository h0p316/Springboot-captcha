package com.captcha.captcha.controller;

import com.captcha.captcha.dto.CaptchaResponseDto;
import com.captcha.captcha.service.CaptchaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CaptchaController {
    private final CaptchaService captchaService;

    @GetMapping("/generate-captcha")
    public ResponseEntity<CaptchaResponseDto> generateCaptcha() {
        try {
            CaptchaResponseDto captcha = captchaService.generateCaptcha();
            return ResponseEntity.ok(captcha);
        } catch (Exception e) {
            // Log the exception and return an appropriate error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CaptchaResponseDto("Error generating captcha"));
        }
    }
}
