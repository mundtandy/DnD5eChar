package Interface;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BorderPanel extends JPanel{
	private JPanel inner;
	private JLabel TL,TR,BL,BR,UP,LE,RI,BO;
	private String corner = "assets/CNborder.bmp";
	private String edge = "assets/EDborder.bmp";
	BufferedImage loadCorner, loadEdge;
	
	public BorderPanel(JPanel inner){
		this.inner = inner;
		
		try {
			loadCorner = ImageIO.read(new File(corner));
			loadEdge = ImageIO.read(new File(edge));
		} catch (IOException e) {
			System.out.println("images for borders nowhere to be seen");
		}
		
		
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		loadAssets();
		
		c.gridx = 0;
		c.gridy = 0;
		this.add(TL, c);
		
		c.gridx = 2;
		this.add(TR, c);
		
		c.gridx = 1;
		c.gridy = 1;
		this.add(inner, c);
		
		c.gridx = 0;
		c.gridy = 2;
		this.add(BL, c);
		
		c.gridx = 2;
		this.add(BR);
		
		this.setVisible(true);
		
	}
	
	public void loadAssets() {
		TL = new JLabel(new ImageIcon(loadCorner));
		TR = new JLabel(new ImageIcon(loadCorner));
		BL = new JLabel(new ImageIcon(loadCorner));
		BR = new JLabel(new ImageIcon(loadCorner));
	}
}

