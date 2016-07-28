
package J_R_C.JOGL.BaseGame;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;

public class GraphicsContextSprite {
	private double MAXIMUMSPEED = 10;

	private Image image;

	private double imageStartX;

	private double imageStartY;

	private double positionX;

	private double positionY;

	private double velocityX;

	private double velocityY;

	private double imageSizeX;

	private double imageSizeY;

	private double width;

	private double height;

	private int nAnimationInterval;

	public GraphicsContextSprite() {
		imageStartX = 0;
		imageStartY = 0;
		positionX = 0;
		positionY = 0;
		velocityX = 0;
		velocityY = 0;
		imageSizeX = 0;
		imageSizeY = 0;
		nAnimationInterval = 0;
	}

	public GraphicsContextSprite(String filename, double width, double height) {
		Image i = new Image(filename);
		setImage(i, width, height);
		imageStartX = 0;
		imageStartY = 0;
		positionX = 0;
		positionY = 0;
		velocityX = 0;
		velocityY = 0;
		nAnimationInterval = 0;
	}

	public GraphicsContextSprite(String filename, double startX, double startY, double width, double height) {
		Image i = new Image(filename);
		setImage(i, startX, startY, width, height);
		positionX = 0;
		positionY = 0;
		velocityX = 0;
		velocityY = 0;
		nAnimationInterval = 0;
	}

	public void setImage(Image i) {
		image = i;
		width = i.getWidth();
		height = i.getHeight();
		imageSizeX = width;
		imageSizeY = height;
	}

	public void setImage(Image i, double width, double height) {
		image = i;
		this.width = width;
		this.height = height;
		imageSizeX = width;
		imageSizeY = height;
	}

	public void setImage(Image i, double startX, double startY, double width, double height) {
		image = i;
		this.width = width;
		this.height = height;
		this.imageStartX = startX;
		this.imageStartY = startY;
		imageSizeX = width;
		imageSizeY = height;
	}

	public void setImage(String filename) {
		Image i = new Image(filename);
		setImage(i);
	}

	public void setImage(String filename, double width, double height) {
		Image i = new Image(filename);
		setImage(i, width, height);
	}

	public void setPosition(double x, double y) {
		positionX = x;
		positionY = y;
	}

	public void setPositionX(double x) {
		positionX = x;
	}

	public void setPositionY(double y) {
		positionY = y;
	}

	public void setVelocity(double x, double y) {
		velocityX = x;
		velocityY = y;
	}

	public void addVelocity(double x, double y) {
		velocityX += x;
		velocityY += y;
		if (velocityX > MAXIMUMSPEED)
			velocityX = MAXIMUMSPEED;
		else if (velocityX < -MAXIMUMSPEED)
			velocityX = -MAXIMUMSPEED;

		if (velocityY > MAXIMUMSPEED)
			velocityY = MAXIMUMSPEED;
		else if (velocityY < -MAXIMUMSPEED)
			velocityY = -MAXIMUMSPEED;
	}

	public void update(double time) {
		positionX += velocityX * time;
		positionY += velocityY * time;
	}

	public void render(GraphicsContext gc) {
		gc.drawImage(image, imageStartX, imageStartY, this.width, this.height, this.positionX - this.imageSizeX / 2,
				this.positionY - this.imageSizeY / 2, this.imageSizeX, this.imageSizeY);
	}

	public void render(GraphicsContext gc, double positionXX, double positionYY) {
		gc.drawImage(image, imageStartX, imageStartY, this.width, this.height, positionXX - this.imageSizeX / 2,
				positionYY - this.imageSizeY / 2, this.imageSizeX, this.imageSizeY);
	}

	public void render(GraphicsContext gc, double positionXX, double positionYY, double sizeX, double sizeY) {
		gc.drawImage(image, imageStartX, imageStartY, this.width, this.height, positionXX - sizeX / 2,
				positionYY - sizeY / 2, sizeX, sizeY);
	}

	public void renderAnimation(double time, GraphicsContext gc, AnimationManager anMng, double positionXX,
			double positionYY, double sizeX, double sizeY) {

		nAnimationInterval += time;

		if (nAnimationInterval >= anMng.getAnimationChangeTime()) {
			nAnimationInterval = 0;
			anMng.increaseIndicationSpriteNumber();
		}

		gc.drawImage(anMng.getImage(), anMng.getAnimationsprites(anMng.getnIndicationSpriteNumber()).getImageStartX(),
				anMng.getAnimationsprites(anMng.getnIndicationSpriteNumber()).getImageStartY(),
				anMng.getAnimationsprites(anMng.getnIndicationSpriteNumber()).getImageSizeX(),
				anMng.getAnimationsprites(anMng.getnIndicationSpriteNumber()).getImageSizeY(), positionXX - sizeX / 2,
				positionYY - sizeY / 2, sizeX, sizeY);

	}

	public Circle getBoundary() {
		return new Circle(positionX - this.imageSizeX / 2, positionY - this.imageSizeY / 2, this.imageSizeX);

	}

	public boolean intersects(GraphicsContextSprite s) {
		double distX = positionX - s.getPositionX();
		double distY = positionY - s.getPositionY();

		double distance = distX * distX + distY * distY;
		double radiusSum = imageSizeX / 2 + s.getImageSizeX();
		return distance <= radiusSum * radiusSum;
	}

	public String toString() {
		return " Position: [" + positionX + "," + positionY + "]" + " Velocity: [" + velocityX + "," + velocityY + "]";
	}

	public void setImageSize(double imageWidth, double imageHeight) {
		imageSizeX = imageWidth;
		imageSizeY = imageHeight;
	}

	public double getPositionX() {
		return positionX;
	}

	public double getPositionY() {
		return positionY;
	}

	public double getImageSizeX() {
		return imageSizeX;
	}

	public void setImageSizeX(double imageSizeX) {
		this.imageSizeX = imageSizeX;
	}

	public double getImageSizeY() {
		return imageSizeY;
	}

	public void setImageSizeY(double imageSizeY) {
		this.imageSizeY = imageSizeY;
	}

}
