package com.earl.cas.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

/**
 * 生成图片验证码工具类.
 * 
 * @author 宋文光
 * @since 1.0.0
 */
public class ImgVerifyCodeUtil {

	private int w = 70;
	private int h = 35;
	private Random r = new Random();
	// {"宋体", "华文楷体", "黑体", "华文新魏", "华文隶书", "微软雅黑", "楷体_GB2312"}
	private String[] fontNames = { "宋体", "华文楷体", "黑体", "微软雅黑", "楷体_GB2312" };
	private String codes = "23456789abcdefghjkmnopqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ";
	private Color bgColor = new Color(255, 255, 255);
	private String text;

	public String getVerifyCode(HttpServletRequest request) {
		String path = request.getSession().getServletContext().getRealPath("/")
				+ "verifyCode/" + getName() + ".jpg";
		try {
			output(getImage(), new FileOutputStream(path));
		} catch (Exception e) {
			System.out.println("生成图片验证码失败");
			e.printStackTrace();
		}
		return "verifyCode/" + getName() + ".jpg";
	}

	// 获取验证码字符
	public String getText() {
		return text;
	}

	private String getName() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(date);
	}

	// 将图片写入磁盘
	private void output(BufferedImage image, OutputStream out)
			throws IOException {
		ImageIO.write(image, "JPEG", out);
		out.close();
	}

	// 设置颜色
	private Color randomColor() {
		int red = r.nextInt(150);
		int green = r.nextInt(150);
		int blue = r.nextInt(150);
		return new Color(red, green, blue);
	}

	private Font randomFont() {
		int index = r.nextInt(fontNames.length);
		String fontName = fontNames[index];
		int style = r.nextInt(4);
		int size = r.nextInt(5) + 24;
		return new Font(fontName, style, size);
	}

	// 画出验证码
	private void drawLine(BufferedImage image) {
		int num = 3;
		Graphics2D g2 = (Graphics2D) image.getGraphics();
		for (int i = 0; i < num; i++) {
			int x1 = r.nextInt(w);
			int y1 = r.nextInt(h);
			int x2 = r.nextInt(w);
			int y2 = r.nextInt(h);
			g2.setStroke(new BasicStroke(1.5F));
			g2.setColor(Color.BLUE);
			g2.drawLine(x1, y1, x2, y2);
		}
	}

	private char randomChar() {
		int index = r.nextInt(codes.length());
		return codes.charAt(index);
	}

	// 生成图片
	private BufferedImage createImage() {
		BufferedImage image = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = (Graphics2D) image.getGraphics();
		g2.setColor(this.bgColor);
		g2.fillRect(0, 0, w, h);
		return image;
	}

	// 生成验证码图片
	private BufferedImage getImage() {
		BufferedImage image = createImage();
		Graphics2D g2 = (Graphics2D) image.getGraphics();
		StringBuilder sb = new StringBuilder();
		// 向图片中画4个字符
		for (int i = 0; i < 4; i++) {
			String s = randomChar() + "";
			sb.append(s);
			float x = i * 1.0F * w / 4;
			g2.setFont(randomFont());
			g2.setColor(randomColor());
			g2.drawString(s, x, h - 5);
		}
		this.text = sb.toString();
		drawLine(image);
		return image;
	}

}
