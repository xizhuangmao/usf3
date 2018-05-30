package hitaii.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hitaii.constant.Constant;


/**
 * Copy Right Information : hitaii
 *
 * Date : 20160111
 *
 * Author : zw
 *
 * Version : 1.0
 *
 * Modification history :
 */
public class ImageUtil implements Constant{

    private static int WIDTH = 90;
    private static int HEIGHT = 20;
    private static int LENGTH = 6;

    public static void makeValCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        response.setContentType("image/jpeg");

        ServletOutputStream sos = response.getOutputStream();

        //设置浏览器不要缓存此图片   
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        //创建内存图象并获得其图形上下文   
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        Graphics g = image.getGraphics();

        //产生随机的认证码   
        char[] rands = generateCheckCode();

        //产生图像   
        drawBackground(g);

        drawRands(g, rands);

        //结束图像的绘制过程，完成图像   
        g.dispose();

        //将图像输出到客户端   
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        ImageIO.write(image, "JPEG", bos);

        byte[] buf = bos.toByteArray();

        response.setContentLength(buf.length);

        //下面的语句也可写成：bos.writeTo(sos);   
        sos.write(buf);
        bos.close();
        sos.close();

        //PrintWriter pw=  response.getWriter();


        //将当前验证码存入到Session或者数据库中,下一个表单处理中验证客户端提交的验证码是否正确   
        session.setAttribute(Constant.VALIDATECODE_KEY, new String(rands));
        //request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    private static char[] generateCheckCode() {
        //定义验证码的字符表   
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        char[] rands = new char[LENGTH];
        for (int i = 0; i < LENGTH; i++) {
            int rand = (int) (Math.random() * 36);
            rands[i] = chars.charAt(rand);
        }

        return rands;
    }

    private static void drawRands(Graphics g, char[] rands) {
        g.setColor(Color.BLACK);
        g.setFont(new Font(null, Font.ITALIC | Font.BOLD, 18));

        //在不同的高度上输出验证码的每个字符            
        g.drawString("" + rands[0], 1, 17);
        g.drawString("" + rands[1], 16, 15);
        g.drawString("" + rands[2], 31, 18);
        g.drawString("" + rands[3], 46, 16);
        g.drawString("" + rands[4], 61, 14);
        g.drawString("" + rands[5], 76, 19);
    }

    private static void drawBackground(Graphics g) {
        //画背景   
        g.setColor(new Color(0xDCDCDC));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        //随机产生120个干扰点   
        for (int i = 0; i < 120; i++) {
            int x = (int) (Math.random() * WIDTH);

            int y = (int) (Math.random() * HEIGHT);

            int red = (int) (Math.random() * 255);

            int green = (int) (Math.random() * 255);

            int blue = (int) (Math.random() * 255);

            g.setColor(new Color(red, green, blue));

            g.drawOval(x, y, 1, 0);

        }

        //加两条干扰线   
//        g.drawLine(0, 5, 90, 5);
//        g.drawLine(0, 15, 90, 15);

    }


    public static void main(String args[]) {
        char[] chars = generateCheckCode();

        System.out.println(chars);
    }
}

