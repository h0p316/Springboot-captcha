package com.captcha.captcha.service;

import com.captcha.captcha.dto.CaptchaResponseDto;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class CaptchaService {

    private final DefaultKaptcha defaultKaptcha;

    public static String imgToBase64String(final RenderedImage img, final String formatName) {
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(img, formatName, os);
            return Base64.getEncoder().encodeToString(os.toByteArray());
        } catch (final IOException ioe) {
            throw new UncheckedIOException(ioe);
        }
    }

    public CaptchaResponseDto generateCaptcha() {
        //generate Captcha token
        String generateCaptcha = defaultKaptcha.createText();
        //creating buffered image using token
        BufferedImage image = defaultKaptcha.createImage(generateCaptcha);
        //image to base64 converting
        String base64Img = imgToBase64String(image, "jpg");
        return CaptchaResponseDto.builder().image(base64Img).build();
    }
}
