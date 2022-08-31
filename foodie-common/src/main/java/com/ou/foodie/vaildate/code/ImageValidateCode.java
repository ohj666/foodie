package com.ou.foodie.vaildate.code;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ImageValidateCode extends ValidateCode{


    private BufferedImage bufferedImage;

    public ImageValidateCode(String code, LocalDateTime expireTime, BufferedImage bufferedImage) {
        super(code, expireTime);
        this.bufferedImage = bufferedImage;
    }

    public ImageValidateCode(String code, int expireIn, BufferedImage bufferedImage) {
        super(code, expireIn);
        this.bufferedImage = bufferedImage;
    }

    public ImageValidateCode(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }
}
