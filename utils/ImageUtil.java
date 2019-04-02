import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 图片工具类
 *
 * @author qiyu
 */
public class ImageUtil {

    /**
     * 需求：将原800*800的图片的图片内容缩小0.8倍，大小不变(即还是800*800),原图背景透明,新的图片背景仍然要保持透明
     */
    public static void main(String[] args) throws Exception {
        String id = "102738";

        // 图片压缩为0.8倍
        File fromFile = new File("D:/" + id + ".png");
        File toFile = new File("D:/" + id + "After.png");
        resizePng(fromFile, toFile, 0.8);

        // 创造800*800的白色背景图
        File background = new File("D:/background.png");
        BufferedImage img = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = img.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, img.getWidth(), img.getHeight());
        ImageIO.write(img, "png", background);

        // 合并两张图片
        BufferedImage bufferedImage = watermark(background, toFile, 80, 80, 1f);

        // 白色背景透明化
        bufferedImage = process(bufferedImage);
        ImageIO.write(bufferedImage, "png", toFile);
    }

    private static BufferedImage process(BufferedImage image) throws Exception {
        // 高度和宽度
        int height = image.getHeight();
        int width = image.getWidth();

        // 生产背景透明和内容透明的图片
        ImageIcon imageIcon = new ImageIcon(image);
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        // 获取画笔
        Graphics2D g2D = (Graphics2D) bufferedImage.getGraphics();
        // 绘制Image的图片
        g2D.drawImage(imageIcon.getImage(), 0, 0, null);

        // 图片透明度
        int alpha = 0;
        // 外层遍历是Y轴的像素
        for (int y = bufferedImage.getMinY(); y < bufferedImage.getHeight(); y++) {
            // 内层遍历是X轴的像素
            for (int x = bufferedImage.getMinX(); x < bufferedImage.getWidth(); x++) {
                int rgb = bufferedImage.getRGB(x, y);
                // 对当前颜色判断是否在指定区间内
                if (colorInRange(rgb)) {
                    alpha = 0;
                } else {
                    // 设置为不透明
                    alpha = 255;
                }
                // #AARRGGBB 最前两位为透明度
                rgb = (alpha << 24) | (rgb & 0x00ffffff);
                bufferedImage.setRGB(x, y, rgb);
            }
        }
        // 绘制设置了RGB的新图片
        g2D.drawImage(bufferedImage, 0, 0, null);

        return bufferedImage;
    }

    /**
     * 判断是背景还是内容
     */
    private static boolean colorInRange(int color) {
        // 色差范围0~255,白色255-255-255
        int backgroundRGB = 255;

        // 获取color(RGB)中R位
        int red = (color & 0xff0000) >> 16;
        // 获取color(RGB)中G位
        int green = (color & 0x00ff00) >> 8;
        // 获取color(RGB)中B位
        int blue = (color & 0x0000ff);
        // 通过RGB三分量来判断当前颜色是否在指定的颜色区间内
        if (red >= backgroundRGB && green >= backgroundRGB && blue >= backgroundRGB) {
            return true;
        }
        return false;
    }


    /**
     * 裁剪PNG图片工具类
     *
     * @param fromFile 源文件
     * @param toFile   裁剪后的文件
     */
    public static void resizePng(File fromFile, File toFile, double scale) {
        try {
            BufferedImage bi2 = ImageIO.read(fromFile);
            int newWidth;
            int newHeight;
            // 判断是否是等比缩放
            // 为等比缩放计算输出的图片宽度及高度
            // 根据缩放比率大的进行缩放控制
            newWidth = (int) (((double) bi2.getWidth(null)) * scale);
            newHeight = (int) (((double) bi2.getHeight(null)) * scale);

            BufferedImage to = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = to.createGraphics();
            to = g2d.getDeviceConfiguration().createCompatibleImage(newWidth, newHeight,
                    Transparency.TRANSLUCENT);
            g2d.dispose();
            g2d = to.createGraphics();
            Image from = bi2.getScaledInstance(newWidth, newHeight, Image.SCALE_AREA_AVERAGING);
            g2d.drawImage(from, 0, 0, null);
            g2d.dispose();
            ImageIO.write(to, "png", toFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage watermark(File file, File waterFile, int x, int y, float alpha) throws IOException {
        // 获取底图
        BufferedImage buffImg = ImageIO.read(file);
        // 获取层图
        BufferedImage waterImg = ImageIO.read(waterFile);
        // 创建Graphics2D对象，用在底图对象上绘图
        Graphics2D g2d = buffImg.createGraphics();
        // 获取层图的宽度
        int waterImgWidth = waterImg.getWidth();
        // 获取层图的高度
        int waterImgHeight = waterImg.getHeight();
        // 在图形和图像中实现混合和透明效果
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
        // 绘制
        g2d.drawImage(waterImg, x, y, waterImgWidth, waterImgHeight, null);
        g2d.dispose();// 释放图形上下文使用的系统资源
        return buffImg;
    }
}
