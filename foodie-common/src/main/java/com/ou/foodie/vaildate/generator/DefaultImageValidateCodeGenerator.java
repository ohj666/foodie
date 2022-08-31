package com.ou.foodie.vaildate.generator;

import com.ou.foodie.properties.ImageValidateCodeProperties;
import com.ou.foodie.properties.ProjectProperties;
import com.ou.foodie.vaildate.code.ImageValidateCode;
import com.ou.foodie.vaildate.code.ValidateCode;
import lombok.Data;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
@Data
public class DefaultImageValidateCodeGenerator implements ImageValidateCodeGenerator{
    private ProjectProperties properties;

    public ImageValidateCode createImageCode() {
        ImageValidateCodeProperties imageValidateCode = properties.getValidateCode().getImageValidateCode();

        int width = imageValidateCode.getWidth();
        int height = imageValidateCode.getHeight();

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();

        Random random = new Random();

        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        StringBuilder sRand = new StringBuilder();
        for (int i = 0; i < imageValidateCode.getLength(); i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand.append(rand);
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 13 * i + 6, 16);
        }

        g.dispose();

        return new ImageValidateCode(sRand.toString(), imageValidateCode.getExpireIn(), image);
    }

    /**
     * 生成随机背景条纹
     *
     * @param fc 前景色
     * @param bc 背景色
     * @return RGB颜色
     */
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);

    }

    @Override
    public ValidateCode generator() {
        return createImageCode();
    }
}
