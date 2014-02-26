// Young Guo
// 5/22/12
// Game.java
// This is a java game created by Young Guo
// Based on the popular iPhone game Jetpack Joyride,
// I present you with JAVA Joyride

// Orignial Ideas and Credit goes to HalfBrick Studios

import java.awt.CardLayout; //all imports
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Game extends JApplet implements MouseListener, KeyListener,  MouseMotionListener {

	private JPanel startPanel, instructionPanel, gamePanel, shopPanel,characterPanel, difficultyPanel, powerPanel, gameButtonPanel,gameOverPanel, purchasePanel,boostNotifPanel;
	private JPanel questionPanel; //all panels
	private int purchaseChar; //int for which character was purchased
	private int purchasePower; //int for which powerup was purchased
	private boolean purchasedSheep, purchasedPik, purchasedPac, purchasedMar; //booleans check if purchased which char
	private boolean purchasedShield, purchasedHand, purchasedBoost; //booleans check if puchased which powerup
	private CardLayout cards; //the cardlayout
	private int randQuestion; //random int to determine which question is asked
	private Container c; //container
	private boolean tempShield; //temporary shield for when boosting 
	private boolean purchasingChar, purchasingPower; //purchased a char
	private int rocketMagic, wallMagic, laserMagic; //purchased a powerup
	private int rocketDifficulty; //how many rocketes will be shot( 1,2, or 3)
	private int powerBoxX = 1100; //starting X position of the boostBox
	private int powerBoxY; //starting Y position of the boostBox
	private int boostCount = 1; //int to determine how long the boost lasts
	private boolean boost = false; //if boosting or not
	public boolean canBoost = false; //can only boost if powerup was purchased
	private int goFont,goX,goY; //things for intro at beginning of the game
	private boolean showingExclam; //if showing exclam for the rocket
	private int goCounter = 3; //countdown of 3,2,1 for go
	private String goString; //string of the intro
	private boolean go = true; //true in beginning of game, false after it has shown
	private int tempScoreSpeed; //temporary score while boosting
	private final String[] questions = new String[20]; //string of all questions
	private JRadioButton answer1, answer2, answer3, answer4,clearAnswer; //answers for the questions
	private int charY = 150; //starting Y position of character
	private boolean boxIsMoving; //if the boostBox is moving
 	private int backgroundX; //x position of the background
	private int downmomentum = 0; //momentum downwards
	private int upmomentum = 0; //momentum upwards
	private int tempSpeed; //temporary speed
	private int shieldBlink = 1; //shield blinks when hit, then fades
	private int score = 0; //score starts at 0 
	private int speed = 0; //speed starts at 0
	private boolean rocketTwo,rocketThree; //if shoot 1,2, or 3 rockets
	private final int shieldSize = 100; //size of the shield
	private int rocketX = 0; //x position of the rocket 
	private int randRocket; //randomly creates a rocket position
	private int rocketTimer = 0; //timer for the rocket
	private int scoreSpeed; //rate in which score increases
	private int tempY; //temporary y poistion
	private boolean shield = false; //shield or not
	private boolean dispShield = false; //disp shield or not (false when boosting)
	private boolean godHand = false; //power up god hand (use mouse to move instead of space)
	private int randWall; //vertical or horizontal wall 
	private int WallX; //x position of the wall
	private int randY; //random y position of the wlal
	private int totalScore = 0; //start with 0 score
	private final int[] boostX = new int[3]; //the 3 X and Y position of the triangle
	private final int[] boostY = new int[3]; //which make up the yellow triangle boost
	private int boostMagic; //random number for boost boxes
	private int randDir; //random direction of the wall
	private boolean rocketIsMoving; // if rocket is moving or not
	private int circleSize = 50;
	private int tempCircleSize; //temporar circle size
	private int charX = 100; //characters X position
	private boolean laser = false; 
	private boolean paused = false; //paused or not
	private int laserY = 90;  //location for the laser
	private int laserCounter = 0; 
	private int laserNotifY = 70;
	private boolean showLaserNotif = true; //shows the laser notif
	private boolean dead = false; //start of as NOT dead 
	
	private final Image startScreen = new ImageIcon("javaimage.JPG").getImage();
	private final Image clouds = new ImageIcon("clouds.jpg").getImage();
	private final Image rainbowsheep = new ImageIcon("rainbowsheep.png").getImage();
	private final Image rocket = new ImageIcon("Rocket-ship-icon.png").getImage();
	private final Image exclam = new ImageIcon("exclam.png").getImage();
	private final Image exclam2 = new ImageIcon("exclam2.png").getImage();
	private final Image nyan = new ImageIcon("nyan.png").getImage();
	private final Image pikachu = new ImageIcon("pikachu.png").getImage();
	private final Image pacman = new ImageIcon("pacman.png").getImage();
	private final Image laserBeam = new ImageIcon("lightsaber.png").getImage();
	private final Image shoopright = new ImageIcon("shoopright.png").getImage();
	private final Image shoopleft = new ImageIcon("shoopleft.png").getImage();
	private final Image trollfaceright = new ImageIcon("trollfaceright.png").getImage();
	private final Image trollfaceleft = new ImageIcon("trollfaceleft.png").getImage();
	private final Image mario = new ImageIcon("mario.png").getImage();
	private final Image fire = new ImageIcon("fire.png").getImage();
	private final Image godHandpic = new ImageIcon("godhand.png").getImage();
	
	private Image finalChar = nyan; //default character is nyan cat
	private final int difficultyInt = 5; //default difficulty is 5
	private final JLabel difficultyLabel = new JLabel("Difficulty Level: " + difficultyInt + " (Average)");
	//label that changes depending on difficulty selected
	private double speedMult = 1; //multiplier for speed is default 1
	//multiplier changes depending on difficulty

	Up action = new Up(); // up
	Timer timer = new Timer(10, action); //timer to go up

	Down action2 = new Down(); // timer to go down
	Timer downTimer = new Timer(10, action2);

	DownMomentum action3 = new DownMomentum(); // downwards momentum
	Timer dMomentumTimer = new Timer(400, action3);

	UpMomentum action4 = new UpMomentum(); // upwards momentum
	Timer uMomentumTimer = new Timer(400, action4);

	MovingBackground action5 = new MovingBackground(); // downwards momentum
	Timer movingBackground = new Timer(25, action5);

	Score action6 = new Score(); // score
	Timer scoreTimer = new Timer(50, action6);

	Speed action7 = new Speed(); // speed
	Timer speedTimer = new Timer(500, action7);

	Rocket action8 = new Rocket(); // shoot the rocket
	Timer rocketShot = new Timer(5, action8);

	Exclam action9 = new Exclam(); // display the exclam
	Timer exclamTimer = new Timer(500, action9);

	Wall action10 = new Wall(); // wall
	Timer wallTimer = new Timer(1000, action10);

	WallX action11 = new WallX(); // x position of the wall
	Timer wallXTimer = new Timer(25, action11);

	laserCircle action12 = new laserCircle(); // circle notification for the
	Timer circleTimer = new Timer(35, action12);

	shield action13 = new shield(); // timer so you dont die right after shield
	Timer shieldTimer = new Timer(250, action13);

	boost action14 = new boost(); //the boost, how long it lasts
	Timer boostTimer = new Timer(250, action14);

	boostMagic action15 = new boostMagic(); //randomly creates boost Boxes, if powerup was purchased
	Timer boostMagik = new Timer(500, action15);
	
	Go action16 = new Go(); //the intro
	Timer goTimer = new Timer(1380,action16);
	
	fontBig action17 = new fontBig(); //makes the intro font bigger
	Timer fontBigTimer = new Timer(20,action17);
	
	fontSmall action18 = new fontSmall(); //makes the intro font smaller
	Timer fontSmallTimer = new Timer(20,action18);
	
	movingBox action19 = new movingBox(); //moves the X position of the box
	Timer powerBoxTimer = new Timer(25,action19);
	
	rocketMagik action20 = new rocketMagik(); //randomly creates rockets
	Timer rocketMagik = new Timer(500,action20);
	
	rocketDifficulty action21 = new rocketDifficulty();
	Timer rDifficultyTimer = new Timer(500,action21); //1,2,or 3 rockets

	@Override
	public void init() { //init method
		c = this.getContentPane(); //container
		setSize(1000, 500); //for some reason i need this at home (for eclipse)
		cards = new CardLayout(); //the cardlayout
		c.setLayout(cards);

		startPanel = new StartPanel(); //starting panel
		startPanel.setBackground(Color.white);
		c.add(startPanel, "StartPanel");
		startPanel.addMouseListener(this);

		instructionPanel = new instructionPanel(); //instruction panel
		instructionPanel.setBackground(Color.white);
		c.add(instructionPanel, "instructionPanel");
		instructionPanel.addMouseListener(this);

		gamePanel = new gamePanel(); //game panel, where all the gameplay happens
		gamePanel.setBackground(Color.white);
		c.add(gamePanel, "gamePanel");
		gamePanel.addMouseListener(this);
		gamePanel.addKeyListener(this);
		gamePanel.addMouseMotionListener(this);

		shopPanel = new shopPanel(); //shop panel, goes into character or powerup panels
		shopPanel.setBackground(Color.white);
		shopPanel.addMouseListener(this);
		c.add(shopPanel, "shopPanel");

		characterPanel = new characterPanel(); //where you purchase characters
		characterPanel.setBackground(Color.white);
		characterPanel.addMouseListener(this);
		c.add(characterPanel, "characterPanel");

		difficultyPanel = new difficultyPanel();
		difficultyPanel.setBackground(Color.white); //place to choose difficulty level
		difficultyPanel.addMouseListener(this);
		c.add(difficultyPanel, "difficultyPanel");

		powerPanel = new powerPanel();
		powerPanel.setBackground(Color.white);
		powerPanel.addMouseListener(this); //where you purchase powerups
		c.add(powerPanel, "powerPanel");

		questionPanel = new questionPanel();
		questionPanel.addMouseListener(this);
		c.add(questionPanel, "questionPanel"); //where all the questions are asked 

		gameOverPanel = new gameOverPanel(); //shows up when you lose and answer question wrong
		gameOverPanel.addMouseListener(this);
		c.add(gameOverPanel, "gameOverPanel");

		purchasePanel = new purchasePanel();
		c.add(purchasePanel, "purchasePanel"); //shows up when you purchase
		
		boostNotifPanel = new boostNotifPanel();
		c.add(boostNotifPanel, "boostNotifPanel"); //small notification after you buy boost

		questions[0] = "During which phase of Mitosis do the chromosomes line up in the center of the cell?";
		questions[1] = "How many phases are in meiosis?";
		questions[2] = "Which of these is in the Digestive System?";
		questions[3] = "What is the formula for the Area of a right triangle?";
		questions[4] = "Which of these does not guarentee triangle congruency?";														
		questions[5] = "Find X in X = log10";
		questions[6] = "What animal does Odysseus hide under to escape the cyclops";
		questions[7] = "Name the main protagonist in TKAM";
		questions[8] = "What are the two quarreling houses in Romeo and Juliet?";
		questions[9] = "What is the monomer for Proteins"; //string array of all the questions
		questions[10] = "Which Company created JAVA";
		questions[11] = "What biomolecule does the Stomach digest?";
		questions[12] = "What is the temperature at which books burn?";
		questions[13] = "What is the main character's wife's name in Fahrenheit 451";
		questions[14] = "What is the hypotenuse in a triangle with legs 3 and 4?";
		questions[15] = "What is the largest classification group in taxonomy?";

		goFont = 50; //font, x and y positions of intro message
		goX = 325;
		goY = 260;
		goString = "JAVA JOYRIDE";
		
		this.requestFocus();
	}

	@Override
	public void mousePressed(MouseEvent e) {
	} //not used

	@Override
	public void mouseExited(MouseEvent e) {
	} //not used

	@Override
	public void mouseClicked(MouseEvent e) {
	} //not used

	@Override
	public void mouseReleased(MouseEvent e) {
	} //not used

	@Override
	public void mouseEntered(MouseEvent e) {
	} //not used

	@Override
	public void mouseDragged(MouseEvent e) { //when got hand is enabled, moving the mouse moves the character
		if (godHand == true && dead == false) {
			charY = e.getY() - 25;
			downTimer.stop();
			dMomentumTimer.stop();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) { //when got hand is enabled, moving the mouse moves the character
		if (godHand == true && dead == false) {
			charY = e.getY() - 25;
			downTimer.stop();
			dMomentumTimer.stop();
		}
	}

	public class StartPanel extends JPanel implements ActionListener { // start // screen

		private final JButton button1, button2, button3; //buttons in the start panel
		
		public StartPanel() {
			this.setLayout(null);
			button1 = new JButton("Start"); // start the game
			button1.addActionListener(this);
			button1.setBounds(100, 425, 200, 50);
			this.add(button1);

			button2 = new JButton("Instructions"); //go to instructions
			button2.addActionListener(this);
			button2.setBounds(700, 425, 200, 50);
			this.add(button2);

			button3 = new JButton("Shop"); //go to the shop
			button3.addActionListener(this);
			button3.setBounds(400, 425, 200, 50);
			this.add(button3);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			if (command.equals("Start")) { //go to appropriate panel depending on which button 
				cards.show(c, "difficultyPanel"); //is pressed
			} else if (command.equals("Instructions")) {
				cards.show(c, "instructionPanel");
			} else if (command.equals("Shop")) {
				cards.show(c, "shopPanel");
			}
		}
		public void paintComponent(Graphics g){
			g.setColor(Color.white);
			g.fillRect(0,0,1000,500); //draw the images for the start screen
			g.drawImage(startScreen,0,-30,null);
			g.setFont(new Font("Serif", Font.BOLD, 30));
			g.setColor(Color.black);
			g.drawString("Total Score: " + totalScore,415,55);
		}
	}

	public class instructionPanel extends JPanel implements ActionListener { // instructions

		private final JButton button3;
		private JButton button4;
		private final JTextArea instructionArea;
		private JScrollPane scroller;

		public instructionPanel() {
			this.setLayout(null);
			button3 = new JButton("Back"); //button to go back to start panel
			button3.addActionListener(this);
			button3.setBounds(400, 425, 200, 50);
			this.add(button3);

			instructionArea = new JTextArea("GOAL OF THE GAME: Get the highest score\n\nPress the Spacebar to rise\nRelease to fall\n'P' to pause\n'O' for Menu\n\nThere are 3 types of Obstacles:\nTouching any of these means death\n\n-THE LASER\n      -An indicator will appear with a red circle and a face\n        Once the red circle has dissapeared\n        The laser will fire\n-THE ROCKET\n      -An exclamation will appear to the right\n        After a short while, the exclamation will become red\n        Then the rocket will fire\n-THE WALL\n      -Randomly, walls will come from the right\n        Walls can either be vertical or horizontal\n\nUPON DEATH: you will be prompted with a question\nAnswer Correctly, and revive with half your score\nAnswer Incorrectly, and you will have to Start Over COMPLETELY\n\nCharacters and Powerups may be purchased in the SHOP");
			JScrollPane scroller = new JScrollPane(instructionArea); //text area with scroller for instructions
			instructionArea.setFont(new Font("Serif", Font.BOLD, 20));
			instructionArea.setEditable(true);
			instructionArea.setLineWrap(true);
			scroller.setBounds(75, 50, 600, 350);
			this.add(scroller);
		}

		@Override
		public void paintComponent(Graphics g) { //paint component
			g.drawImage(clouds, 0, 0, null);
			g.drawImage(clouds, 533, 0, null);
			g.setColor(Color.pink);
			g.fillRect(690, 50, 295, 350);

			g.setColor(Color.black);
			g.fillRect(690, 50, 5, 350);
			g.fillRect(690, 50, 295, 5); 
			g.fillRect(980, 50, 5, 350);
			g.fillRect(690, 395, 295, 5);

			g.drawImage(trollfaceright, 705, 80, null);
			g.drawImage(trollfaceleft, 780, 80, null);
			g.drawImage(shoopright, 855, 80, null);
			g.drawImage(shoopleft, 920, 80, null); //shows what laser is
			g.drawImage(laserBeam, 720, 155, 235, 20, null);
			g.setColor(Color.black);
			g.fillRect(690, 195, 295, 5);

			g.drawImage(exclam, 735, 210, null);
			g.drawImage(exclam2, 810, 210, null);
			g.drawImage(rocket, 885, 210, null); //shows what the rocket looks like
			g.setColor(Color.black);
			g.fillRect(690, 275, 295, 5);

			g.setColor(Color.magenta); //shows what the wall looks like
			g.fillRect(745, 330, 75, 15); // horizontal
			g.fillRect(905, 300, 15, 75); // vertical
		}

		@Override
		public void actionPerformed(ActionEvent evt) {
			String command = evt.getActionCommand();
			if (command.equals("Back")) {
				cards.show(c, "StartPanel");
			}
		}
	}

	public class gamePanel extends JPanel implements ActionListener {
		@Override
		public void paintComponent(Graphics g) {
			this.requestFocusInWindow();
			for (int i = 0; i < 500; i++) {
				g.drawImage(clouds, 533 * i - backgroundX, 0, null);
			}
			if(go == false){ //THE ROCKET
				if(rocketMagic == 1){
					rocketIsMoving = true;
					if (rocketTimer == 4) { //FOR ROCKET TIMER
											// 1 = first exclam
						 				     // 2 & 3 = second exclam
											// 4 = shoot the rocket
						showingExclam = false;
						rocketShot.start();
						exclamTimer.stop();
						if(rocketDifficulty == 1){
							g.drawImage(rocket, 1000 - rocketX, tempY+50, null); // rocket
						}
						if(rocketDifficulty == 10){
							g.drawImage(rocket, 1000 - rocketX, tempY+50, null); // rocket
							g.drawImage(rocket, 1000 - rocketX, tempY-50, null); // rocket
						}
						g.drawImage(rocket, 1000 - rocketX, tempY, null); // rocket
					} else if (rocketTimer == 2 || rocketTimer == 3) {
						g.drawImage(exclam2, 945, charY, null); // exclam
						if(rocketDifficulty == 1){
							g.drawImage(exclam2, 945, charY + 50, null); //exclam
						}
						if(rocketDifficulty == 10){
							g.drawImage(exclam2, 945, charY + 50, null); //exclam
							g.drawImage(exclam2, 945, charY - 50, null); //exclam
						}
						showingExclam = true;
					} else if(rocketTimer == 1 || rocketTimer == 0)	{
						if(rocketDifficulty == 1){
							g.drawImage(exclam, 945, charY + 50, null); // exclam
						}
						if(rocketDifficulty == 10){
							g.drawImage(exclam, 945, charY + 50, null); // exclam
							g.drawImage(exclam, 945, charY - 50, null); // exclam
						}
						g.drawImage(exclam, 945, charY, null); // exclam
						showingExclam = true;
						rocketShot.stop();
						exclamTimer.start();
					}
				}
			}
			if(go == false){
				if (randWall == 1) { // vertical wall
					wallXTimer.start();
					wallTimer.stop();
					g.setColor(Color.magenta);
					if (randDir == 0) {
						g.fillRect(1000 - WallX, randY, 20, 250); // vertical wall
						if ((1000 - WallX) <= 120 && (1000 - WallX) >= 80 && (randY <= charY && randY + 250 >= charY)) { //collision for the vertical wall
							if (shield == false && boost == false)
								dead = true; //if not shielding and not boosting, you die
							shieldTimer.start();
						}
					} else if (randDir == 1) {
						g.fillRect(1000 - WallX, randY, 250, 20); // horizontal wall
						if ((1000 - WallX) <= 100 && (randY >= charY && randY <= (charY + 50))) { //collision for the horizontal wall
							if (shield == false && boost == false)
								dead = true; //if not shielding and not boosting, you die
							shieldTimer.start();
						}
					}
				} else {
					wallXTimer.stop(); 
					wallTimer.start();
				}
			}
			if (dead == true) {
				g.drawImage(fire, charX - 25, charY - 50, null); // fire if dead
			}
			if (dispShield == true) { // shield powerup
				g.setColor(Color.green);
				g.fillOval(charX - 25 + Math.abs(100 - shieldSize) / 2, charY- 20 + Math.abs(100 - shieldSize) / 2, shieldSize, shieldSize); //draw the shield
				g.setColor(Color.blue);
				g.drawOval(charX - 25 + Math.abs(100 - shieldSize) / 2, charY - 20 + Math.abs(100 - shieldSize) / 2, shieldSize, shieldSize);
			}
			if (boost == true) {
				boostX[0] = charX + 80;
				boostX[1] = charX - 10;
				boostX[2] = charX - 10;
				boostY[0] = charY + 25; //array of ints to make the boosting triangle
				boostY[1] = charY - 30;
				boostY[2] = charY + 80;
				g.setColor(Color.yellow);
				g.fillPolygon(boostX, boostY, 3);
			}
			g.drawImage(finalChar, charX, charY, null); // the character

			g.setColor(Color.white);
			g.setFont(new Font("Serif", Font.BOLD, 30));
			g.setColor(Color.black);
			g.drawString("SCORE: " + score + "", 25, 35); // draw the score
			
			if(go == false){ //THE LASER
				if (showLaserNotif == true) {
					g.setColor(Color.red);
					g.drawImage(trollfaceright, 70, laserNotifY, null); //the laser notification (circle and face)
					g.drawImage(trollfaceleft, 880, laserNotifY, null);
					g.drawOval((75 + tempCircleSize), (laserNotifY + tempCircleSize), circleSize, circleSize);
					g.drawOval((875 + tempCircleSize), (laserNotifY + tempCircleSize), circleSize, circleSize);
				}
				if (laser == true) {
					g.setColor(Color.ORANGE);
					g.drawImage(shoopright, 70, laserNotifY, null); //shooting the laser
					g.drawImage(shoopleft, 880, laserNotifY, null);
					g.drawImage(laserBeam, 100, laserY, null);
					if (laserY >= charY - 10 && laserY <= (charY + 50)) { // laser
						if (shield == false && boost == false) // collision
							dead = true;
						shieldTimer.start();
					}
				}
			}
			if (boost == true) {
				boostTimer.start(); //start the boosttimer if boost == true
			}
			if (dead == true) {
				charY++; //moves the character when dead
				charX++;
			}
			if (paused == false) { //resume other timers if paused == false 
				movingBackground.start();
				if(go == false){
					circleTimer.start(); //start the circle Timer for the laser
					if(showingExclam == true){
						exclamTimer.start(); //if not showing exclam, then start exclam timer
					}
					if(rocketIsMoving == false){ //start rocketMagik if a rocket isnt moving
						rocketMagik.start();
					}
					speedTimer.start(); //resume speed to speed up 
				}
			}
			if (charY >= 500 && charY <= 550 && dead == true) { // when i die
				paused = true;  //paused the game
				movingBackground.stop(); //stop repainting
				downmomentum = 0;
				charY = 150;
				randQuestion = (int) (Math.random() * 16); //gets a new question
				cards.show(c, "questionPanel"); //shows the question panel
			}
			if(go == true){ //the introduction font
				goTimer.start();
				g.setFont(new Font("Serif", Font.BOLD, goFont));
				g.setColor(Color.black);
				g.drawString(goString + "", goX, goY);
			}
			if(boostMagic == 1){ //random boost
				powerBoxTimer.start(); //box X position
				boxIsMoving  = true;
				g.setColor(Color.green);
				g.fillRect(powerBoxX,powerBoxY,50,50);
				g.setColor(Color.blue);
				g.drawRect(powerBoxX,powerBoxY,50,50);
				
				boostX[0] = 1045 - (1000 -powerBoxX); //array to draw the triangle
				boostX[1] = 1005 - (1000 -powerBoxX);
				boostX[2] = 1005 - (1000 -powerBoxX);
				boostY[0] = powerBoxY + 25;
				boostY[1] = powerBoxY + 5;
				boostY[2] = powerBoxY + 45;
			
				g.setColor(Color.yellow);
				g.fillPolygon(boostX,boostY,3);
				g.setColor(Color.blue);
				g.drawPolygon(boostX,boostY,3);
				if(powerBoxX <= 140 && powerBoxY >= charY - 5 && powerBoxY <= charY + 50){ //if you touch the boostBox
					if(dead == false && boost == false){
						boost = true;
						tempSpeed = speed; //sets the temporary speed to 1000
						tempScoreSpeed = scoreSpeed; //set the temporary score speed
					}
				}
			}
		}
		@Override
		public void actionPerformed(ActionEvent e) {
		}
	}

	public class shopPanel extends JPanel implements ActionListener {  //the shop panel

		private final JButton Characters, PowerUps, Back;

		public shopPanel() {
			this.setLayout(null);
			Characters = new JButton("Characters");
			Characters.addActionListener(this); //button to the character panel
			Characters.setBounds(50, 200, 300, 150);
			this.add(Characters);

			PowerUps = new JButton("Power Ups");
			PowerUps.addActionListener(this);
			PowerUps.setBounds(650, 200, 300, 150); //button to the power up panel
			this.add(PowerUps);

			Back = new JButton("Back");
			Back.addActionListener(this); //button to go back to the start panel
			Back.setBounds(400, 425, 200, 50);
			this.add(Back);
		}

		@Override
		public void paintComponent(Graphics g) { //draws the clouds and score
			g.drawImage(clouds, 0, 0, null);
			g.drawImage(clouds, 533, 0, null);
			g.setFont(new Font("Serif", Font.BOLD, 20));
			g.drawString("Points to spend: " + totalScore, 415, 250);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			if (command.equals("Characters")) {
				cards.show(c, "characterPanel"); //goes to corresponding panel depending on which button pressed
			} else if (command.equals("Power Ups")) {
				cards.show(c, "powerPanel");
			} else if (command.equals("Back")) {
				cards.show(c, "StartPanel");
			}
		}
	}

	public class characterPanel extends JPanel implements ActionListener { //character panel 
		private final JRadioButton rainbowsheeps, pikachus, nyans, pacmans, marios; //radio button to select characters
		private final ButtonGroup characterGroup; //all characters in this button group (only 1 character may be selected at a time)
		private final JButton back, unlockSheep, unlockPikachu, unlockNyan, unlockPac, unlockMario; //buttons for unlocking characters

		public characterPanel() {
			this.setLayout(null);
			characterGroup = new ButtonGroup();
			rainbowsheeps = new JRadioButton("Rainbow Sheep");
			rainbowsheeps.setBounds(70, 400, 150, 25); //the sheep 
			unlockSheep = new JButton("Unlock Sheep");
			unlockSheep.addActionListener(this);
			unlockSheep.setEnabled(true);
			unlockSheep.setBounds(70, 440, 150, 50);
			this.add(unlockSheep);

			pikachus = new JRadioButton("Pikachu"); //pikachu
			pikachus.setBounds(245, 400, 150, 25);
			unlockPikachu = new JButton("Unlock Pikachu");
			unlockPikachu.addActionListener(this);
			unlockPikachu.setBounds(245, 440, 150, 50);
			this.add(unlockPikachu);

			nyans = new JRadioButton("Nyan Cat");
			nyans.setBounds(420, 400, 150, 25);
			unlockNyan = new JButton("Unlocked Nyan"); //nyan cat
			unlockNyan.setBounds(420, 440, 150, 50);
			unlockNyan.setEnabled(false);
			this.add(unlockNyan);

			pacmans = new JRadioButton("Pacman");
			pacmans.setBounds(595, 400, 150, 25); //pacman
			unlockPac = new JButton("Unlock Pacman");
			unlockPac.addActionListener(this);
			unlockPac.setBounds(595, 440, 150, 50);
			unlockPac.setEnabled(true);
			this.add(unlockPac);

			marios = new JRadioButton("Mario");
			marios.setBounds(770, 400, 150, 25); //mario
			unlockMario = new JButton("Unlock Mario");
			unlockMario.setBounds(770, 440, 150, 50);
			unlockMario.addActionListener(this);
			unlockMario.setEnabled(true);
			this.add(unlockMario);

			characterGroup.add(rainbowsheeps);
			characterGroup.add(pikachus);
			characterGroup.add(nyans); //add all JRadioButtons to the button group
			characterGroup.add(pacmans);
			characterGroup.add(marios);
 
			rainbowsheeps.addActionListener(this);
			pikachus.addActionListener(this);
			nyans.addActionListener(this); //adding actionListener
			pacmans.addActionListener(this);
			marios.addActionListener(this);

			back = new JButton("Back"); //back to go back to the shop panel
			back.addActionListener(this);
			back.setBounds(10, 10, 100, 50);
			this.add(back);

			nyans.setSelected(true); //by default, the default character is nyan cat

			rainbowsheeps.setEnabled(false);
			pikachus.setEnabled(false); //rest of buttons not nyan cat default not enabled (must purchase them first)
			pacmans.setEnabled(false);
			marios.setEnabled(false);

			this.add(rainbowsheeps);
			this.add(pikachus); //add all JRadioButtons to the panel
			this.add(nyans);
			this.add(pacmans);
			this.add(marios);
		}

		@Override
		public void paintComponent(Graphics g) {
			g.drawImage(clouds, 0, 0, null);
			g.drawImage(clouds, 533, 0, null);
			if (purchasedSheep == false) {
				g.setColor(new Color(210, 180, 55)); // the lock
				g.fillRect(85, 300, 120, 90);
				g.setColor(new Color(100, 100, 100)); 
				g.fillRect(95, 230, 90, 20);
				g.fillRect(95, 230, 20, 70);
				g.fillRect(175, 230, 20, 70);
			}
			g.drawImage(rainbowsheep, 125, 325, null); //draws the sheep

			if (purchasedPik == false) {
				g.setColor(new Color(210, 180, 55)); // the lock
				g.fillRect(260, 300, 120, 90);
				g.setColor(new Color(100, 100, 100));
				g.fillRect(270, 230, 90, 20);
				g.fillRect(270, 230, 20, 70);
				g.fillRect(350, 230, 20, 70);
			}
			g.drawImage(pikachu, 290, 325, null); //draws pikachu

			g.drawImage(nyan, 475, 325, null); //draws nyan (no lock because unlocked by default)

			if (purchasedPac == false) {
				g.setColor(new Color(210, 180, 55)); // the lock
				g.fillRect(610, 300, 120, 90);
				g.setColor(new Color(100, 100, 100));
				g.fillRect(620, 230, 90, 20);
				g.fillRect(620, 230, 20, 70);
				g.fillRect(700, 230, 20, 70);
			}
			g.drawImage(pacman, 650, 325, null); //draws pacman

			if (purchasedMar == false) {
				g.setColor(new Color(210, 180, 55)); // the lock
				g.fillRect(785, 300, 120, 90);
				g.setColor(new Color(100, 100, 100));
				g.fillRect(795, 230, 90, 20);
				g.fillRect(795, 230, 20, 70);
				g.fillRect(875, 230, 20, 70);
			}
			g.drawImage(mario, 815, 325, null); //draws mario

			g.setColor(new Color(75, 75, 75));
			g.fillRect(265, 125, 500, 50);
			g.setColor(Color.white);
			g.setFont(new Font("Serif", Font.BOLD, 30));
			g.drawString("Points to spend: " + totalScore, 375, 160);
			if (purchasedSheep == true) {
				rainbowsheeps.setEnabled(true); //enables sheep to be chosen if purchased
				unlockSheep.setText("Unlocked Sheep");
				unlockSheep.setEnabled(false);
			}
			if (purchasedPik == true) {
				pikachus.setEnabled(true);
				unlockPikachu.setText("Unlocked Pikachu");
				unlockPikachu.setEnabled(false); //enables pikachu to be chosen if purchased
			}
			if (purchasedPac == true) {
				pacmans.setEnabled(true);
				unlockPac.setText("Unlocked Pacman"); //enables pacman to be chosen if purchased
				unlockPac.setEnabled(false);
			}
			if (purchasedMar == true) {
				marios.setEnabled(true);
				unlockMario.setText("Unlocked Mario"); //enables mario to be chosen if purchased
				unlockMario.setEnabled(false);
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String evt = e.getActionCommand();
			if (evt.equals("Back")) {
				cards.show(c, "shopPanel"); //back button goes back to the shop panel
			}
			if (evt.equals("Unlock Sheep")) { 
				purchaseChar = 1;
				purchasePower = 0;
				purchasingPower = false;
				purchasingChar = true;
				cards.show(c, "purchasePanel");
			}
			if (evt.equals("Unlock Pikachu")) {
				purchaseChar = 2;
				purchasePower = 0; 
				purchasingPower = false;
				purchasingChar = true;
				cards.show(c, "purchasePanel");
			}
			if (evt.equals("Unlock Pacman")) {    // unlocking the characters
													// sets purchaseChar depending on which character was purchased
				purchaseChar = 3;					
				purchasePower = 0;					//FOR PURCHASECHAR
				purchasingPower = false;			// 1 = sheep	
				purchasingChar = true;				// 2 = pikachu
				cards.show(c, "purchasePanel");		// 3 = pacman
			}										// 4 = mario
			if (evt.equals("Unlock Mario")) {
				purchaseChar = 4;
				purchasePower = 0;
				purchasingPower = false;
				purchasingChar = true;
				cards.show(c, "purchasePanel");
			}
			if (rainbowsheeps.isSelected()) {
				finalChar = rainbowsheep; //sets the character depending on which radiobutton is selected
			} else if (pikachus.isSelected()) {
				finalChar = pikachu;
			} else if (nyans.isSelected()) {
				finalChar = nyan;
			} else if (pacmans.isSelected()) {
				finalChar = pacman;
			} else if (marios.isSelected()) {
				finalChar = mario;
			}
			repaint(); //repaint so the locks dissapear
		}
	}

	public class difficultyPanel extends JPanel implements ActionListener, ChangeListener { //panel to set difficulty

		private final JButton begin; //button to begin
		private final JSlider difficultySlider; //jslider for difficulty

		public difficultyPanel() {
			this.setLayout(null);
			difficultySlider = new JSlider(0, 10, 5); //the jslider ranges from 0-10
			difficultySlider.setBounds(100, 300, 800, 50);
			difficultySlider.setMajorTickSpacing(5); //major ticks every 5
			difficultySlider.setMinorTickSpacing(1);  //minor ticks every 1
			difficultySlider.addChangeListener(this);
			difficultySlider.setPaintTicks(true); //paint the ticks
			difficultySlider.setPaintLabels(true); //paint the labels
			this.add(difficultySlider);

			begin = new JButton("Begin");
			begin.addActionListener(this); //button to begin and start the game
			begin.setBounds(350, 400, 300, 50);
			this.add(begin);

			difficultyLabel.setBounds(375, 200, 500, 50); //label to show a message corresponding to which difficulty is chosen
			difficultyLabel.setFont(new Font("Serif", Font.BOLD, 20));
			this.add(difficultyLabel);
		}

		@Override
		public void paintComponent(Graphics g) {
			g.drawImage(clouds, 0, 0, null); //fill the background with clouds
			g.drawImage(clouds, 533, 0, null);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String evt = e.getActionCommand();
			if (evt.equals("Begin")) {
				cards.show(c, "gamePanel");
				paused = false; //start the game when the user presses begin
				downTimer.start();
				movingBackground.start();
			}
		}

		@Override
		public void stateChanged(ChangeEvent e) {
			int difficultyInt = difficultySlider.getValue(); //int depending on the slider 
			if (difficultyInt == 10) {
				difficultyLabel.setText("Difficulty Level: " + difficultyInt + " (only4pr0s)"); //A BUNCH OF MESSAGES DEPENDING ON WHICH DIFFICULTY
				speedMult = 128; //multiplier for speed increases as difficulty # goes up
			}
			if (difficultyInt == 9) {
				difficultyLabel.setText("Difficulty Level: " + difficultyInt + " (brave2themax)");
				speedMult = 16;
			}
			if (difficultyInt == 8) {
				difficultyLabel.setText("Difficulty Level: " + difficultyInt + " (do you dare!?!?)"); 
				speedMult = 8; 
			}
			if (difficultyInt == 7) {
				difficultyLabel.setText("Difficulty Level: " + difficultyInt + " (wow your brave)");
				speedMult = 4;
			}
			if (difficultyInt == 6) {
				difficultyLabel.setText("Difficulty Level: " + difficultyInt + " (harder)");
				speedMult = 2;
			}
			if (difficultyInt == 5) {
				difficultyLabel.setText("Difficulty Level: " + difficultyInt + " (Average)");
				speedMult = 1;
			}
			if (difficultyInt == 4) {
				difficultyLabel.setText("Difficulty Level: " + difficultyInt + " (easier)");
				speedMult = 0.5;
			}
			if (difficultyInt == 3) {
				difficultyLabel.setText("Difficulty Level: " + difficultyInt + " (really....)");
				speedMult = .25;
			}
			if (difficultyInt == 2) {
				difficultyLabel.setText("Difficulty Level: " + difficultyInt + " (what a chicken)");
				speedMult = .125;
			}
			if (difficultyInt == 1) {
				difficultyLabel.setText("Difficulty Level: " + difficultyInt + " (maybe easiest....)");
				speedMult = .0625;
			}
			if (difficultyInt == 0) {
				difficultyLabel.setText("Difficulty Level: " + difficultyInt + " (only4n00bs)");
				speedMult = 0;
			}
		}
	}

	public class powerPanel extends JPanel implements ActionListener { //power up panel
		private final JButton back;
		private final JRadioButton shieldButton, handButton, boostButton; //radio buttons to select power ups
		private final JButton unlockShield, unlockHand, unlockBoost; //jbuttons to unlock each power up

		public powerPanel() {
			this.setLayout(null);

			back = new JButton("Back"); //back to return to the shop panel
			back.addActionListener(this);
			back.setBounds(10, 10, 100, 50);
			this.add(back);

			shieldButton = new JRadioButton("Shield");
			shieldButton.setBounds(80, 405, 150, 25);
			shieldButton.setEnabled(false);
			this.add(shieldButton); //the shield radio button 
			unlockShield = new JButton("Unlock Shield"); //button to unlock shield
			unlockShield.addActionListener(this);
			unlockShield.setBounds(80, 440, 150, 50);
			this.add(unlockShield);

			handButton = new JRadioButton("God Hand");
			handButton.setBounds(280, 405, 150, 25); //hand radio button
			handButton.setEnabled(false);
			this.add(handButton);
			unlockHand = new JButton("Unlock Hand"); //button to unlock hand
			unlockHand.addActionListener(this);
			unlockHand.setBounds(280, 440, 150, 50);
			this.add(unlockHand);

			boostButton = new JRadioButton("Boost");
			boostButton.setBounds(480, 405, 150, 25); //boost radio button
			boostButton.setEnabled(false);
			this.add(boostButton);
			unlockBoost = new JButton("Unlock Boost");
			unlockBoost.addActionListener(this); //button to unlock boost
			unlockBoost.setBounds(480, 440, 150, 50);
			this.add(unlockBoost);
		}

		@Override
		public void paintComponent(Graphics g) {
			g.drawImage(clouds, 0, 0, null); //fills the background
			g.drawImage(clouds, 533, 0, null);

			g.setFont(new Font("Serif", Font.BOLD, 30));
			g.drawString("Points to spend: " + totalScore, 375, 50);

			if (purchasedShield == false) {
				g.setColor(new Color(210, 180, 55)); // the lock
				g.fillRect(95, 300, 120, 90);
				g.setColor(new Color(100, 100, 100));
				g.fillRect(105, 230, 90, 20);
				g.fillRect(105, 230, 20, 70);
				g.fillRect(185, 230, 20, 70);
			}
			g.setColor(Color.green);
			g.fillOval(125, 320, 60, 60);
			g.setColor(Color.blue); // the shield
			g.drawOval(125, 320, 60, 60);

			if (purchasedHand == false) {
				g.setColor(new Color(210, 180, 55)); // the lock
				g.fillRect(295, 300, 120, 90);
				g.setColor(new Color(100, 100, 100));
				g.fillRect(305, 230, 90, 20);
				g.fillRect(305, 230, 20, 70);
				g.fillRect(385, 230, 20, 70);
			}
			g.drawImage(godHandpic,300,315,null); //godhand

			if (purchasedBoost == false) {
				g.setColor(new Color(210, 180, 55)); // the lock
				g.fillRect(495, 300, 120, 90);
				g.setColor(new Color(100, 100, 100));
				g.fillRect(505, 230, 90, 20);
				g.fillRect(505, 230, 20, 70);
				g.fillRect(585, 230, 20, 70);
			}
			boostX[0] = 595;
			boostX[1] = 525;
			boostX[2] = 525; //array of values to create the triangle for boost
			boostY[0] = 345;
			boostY[1] = 305;
			boostY[2] = 385;
			g.setColor(Color.yellow);
			g.fillPolygon(boostX, boostY, 3); //draws the polygon (triangle)
			g.setColor(Color.blue);
			g.drawPolygon(boostX, boostY, 3);

			if (purchasedShield == true) {
				unlockShield.setEnabled(false);
				unlockShield.setText("Unlocked Shield");
				shieldButton.setEnabled(true); //enables shield to be chosen if purchased
			}
			if (purchasedHand == true) {
				unlockHand.setEnabled(false); //enables hand to be chosen if purchased 
				unlockHand.setText("Unlocked Hand");
				handButton.setEnabled(true);
			}
			if (purchasedBoost == true) {
				unlockBoost.setEnabled(false);
				unlockBoost.setText("Unlocked Boost"); //enables boost to be chosen if purchased
				boostButton.setEnabled(true);
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String evt = e.getActionCommand();
			if (evt.equals("Back")) {
				cards.show(c, "shopPanel"); //go  back to the shop panel
			}
			if (evt.equals("Unlock Shield")) {
				purchasePower = 1;
				purchaseChar = 0;
				purchasingPower = true;   			//FOR PURCHASE POWER
				purchasingChar = false;				//1 = shield
				cards.show(c, "purchasePanel");		//2 = hand
			}										// 3 = boost
													// this int will determine what shows up in purchase panel and what you unlock
			if (evt.equals("Unlock Hand")) {
				purchasePower = 2;
				purchaseChar = 0;
				purchasingPower = true;
				purchasingChar = false;
				cards.show(c, "purchasePanel"); //shows purchase panel, which shows the transaction and allows you to purchase
			}
			if (evt.equals("Unlock Boost")) {
				purchasePower = 3; //unlocking boost 
				purchaseChar = 0;
				purchasingPower = true;
				purchasingChar = false;
				cards.show(c, "purchasePanel");
			}
			if (shieldButton.isSelected()) {
				shield = true; //enables the shield 
				dispShield = true;
			} else {
				shield = false;
				dispShield = false;
			}
			if (handButton.isSelected()) { //enables the hand
				godHand = true;
			} else {
				godHand = false;
			}
			if (boostButton.isSelected()) { //enables boostBoxes to appear 
				canBoost = true;
			}
			repaint(); //repaint to remove the locks 
		}
	}

	public class questionPanel extends JPanel implements ActionListener {
		private final ButtonGroup questionGroup;

		public questionPanel() {
			this.setLayout(null);
			questionGroup = new ButtonGroup();
			
			answer1 = new JRadioButton(); //4 answers to each question
			answer1.setBounds(100, 200, 800, 25);
			answer1.addActionListener(this);

			answer2 = new JRadioButton();
			answer2.setBounds(100, 270, 800, 25);
			answer2.addActionListener(this);

			answer3 = new JRadioButton();
			answer3.setBounds(100, 340, 800, 25);
			answer3.addActionListener(this);

			answer4 = new JRadioButton();
			answer4.setBounds(100, 410, 800, 25);
			answer4.addActionListener(this);

			clearAnswer = new JRadioButton();
			clearAnswer.addActionListener(this);
			
			questionGroup.add(answer1); //all in one question group
			questionGroup.add(answer2);
			questionGroup.add(answer3);
			questionGroup.add(answer4);
			questionGroup.add(clearAnswer); //since you cannot deselect radiobuttons in a button group
											// and invisible radio button is used and selected, to clear the rest of the radiobuttons
			
			this.add(answer1);
			this.add(answer2);
			this.add(answer3); //adds all the buttons to the screen
			this.add(answer4);
			this.add(clearAnswer); //NOTICE: though this is added, clearAnswer was never given a setBounds (meaning it is invisible, yet still functions)
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (answer1.isSelected()) { //for questions 9 and 13, answer1 is correct 
				if (randQuestion == 9 || randQuestion == 13) {
					cards.show(c, "gamePanel"); //goes back to the game
					dead = false;
					paused = false;
					score = score / 2;
					circleTimer.start(); //resets many values to keep the game running
					timer.stop();
					charX = 100;
					downTimer.start();
					exclamTimer.start();
					charY = 150;
					circleSize = 50;
					WallX = 0;
				} else {
					cards.show(c, "gameOverPanel"); //if answered incorrectly, GAME OVER
				}
			} else if (answer2.isSelected()) {  //for questions 0,3,6,8,10,11, and 15, answer2 is the correct answer
				if (randQuestion == 0 || randQuestion == 3 || randQuestion == 6 || randQuestion == 8 || randQuestion == 10 || randQuestion == 11 || randQuestion == 15) {
					cards.show(c, "gamePanel");
					dead = false;
					paused = false; //resets many values to keep the game running
					score = score / 2;
					charX = 100;
					timer.stop();
					circleSize = 50;
					circleTimer.start();
					downTimer.start();
					charY = 150;
					WallX = 0;
					exclamTimer.start();
				} else {
					cards.show(c, "gameOverPanel");
				}
			} else if (answer3.isSelected()) { //for questions 2,5,7,12, and 14, answer3 is correct
				if (randQuestion == 2 || randQuestion == 5 || randQuestion == 7 || randQuestion == 12 || randQuestion == 14) {
					cards.show(c, "gamePanel");
					dead = false;
					paused = false;
					exclamTimer.start();
					circleTimer.start();
					downTimer.start();
					score = score / 2;
					timer.stop();
					circleSize = 50;//resets many values to keep the game running
					charX = 100;
					charY = 150;
					WallX = 0;
				} else {
					cards.show(c, "gameOverPanel");
				}
			} else if (answer4.isSelected()) { //for questions 1 and 4, answer4 is correct
				if (randQuestion == 1 || randQuestion == 4) {
					cards.show(c, "gamePanel");
					dead = false;
					timer.stop();
					paused = false;
					charX = 100;
					downTimer.start();
					circleTimer.start();//resets many values to keep the game running 
					circleSize = 50;
					score = score / 2;
					charY = 150;
					WallX = 0;
					exclamTimer.start();
				} else {
					cards.show(c, "gameOverPanel");
				}
			}
			clearAnswer.setSelected(true);
		}

		@Override
		public void paintComponent(Graphics g) {
			g.drawImage(clouds, 0, 0, null);
			g.drawImage(clouds, 533, 0, null);
			g.fillRect(100, 100, 800, 50);
			g.setColor(Color.white);

			g.setFont(new Font("Serif", Font.BOLD, 15));
			g.drawString(questions[randQuestion] + "", 125, 130); //draws the question
			
			if (randQuestion == 0) { // During which phase of Mitosis do the chromosomes line up in the center of the cell?
				answer1.setText("Prophase");
				answer2.setText("Metaphase"); // correct
				answer3.setText("Anaphase");
				answer4.setText("Telophase");
			} else if (randQuestion == 1) { // how many phases are in meiosis
				answer1.setText("2");
				answer2.setText("4");
				answer3.setText("6");
				answer4.setText("8"); // correct
			} else if (randQuestion == 2) { // Which of these is in the
											// Digestive System?
				answer1.setText("Larynx");
				answer2.setText("Heart");
				answer3.setText("Stomach"); // correct
				answer4.setText("Pericardium");
			} else if (randQuestion == 3) { // what is the formula for the area
											// of a triangle
				answer1.setText("b*h");
				answer2.setText("(1/2)*b*h"); // correct
				answer3.setText("pi*r^2*h");
				answer4.setText("(4/3)*pi*r^3");
			} else if (randQuestion == 4) { // which of these do not guarentee
											// triangle congruency
				answer1.setText("SAS");
				answer2.setText("ASA");
				answer3.setText("SSS");
				answer4.setText("SSA"); // correct
			} else if (randQuestion == 5) { // Find X in X = log10
				answer1.setText("0");
				answer2.setText("8");
				answer3.setText("1"); // correct
				answer4.setText("10");
			} else if (randQuestion == 6) { // What animal does Odysseus hide
											// under to escape the cyclops
				answer1.setText("bear");
				answer2.setText("sheep"); // correct
				answer3.setText("cow");
				answer4.setText("pig");
			} else if (randQuestion == 7) { // Name the main protagonist in
											// TKAM?
				answer1.setText("Jem");
				answer2.setText("Mayella");
				answer3.setText("Scout"); // correct
				answer4.setText("Ryan Yue");
			} else if (randQuestion == 8) { // What are the two quarreling
											// houses in Romeo and Juliet?
				answer1.setText("Yue and Guo");
				answer2.setText("Montague and Capulet"); // correct
				answer3.setText("Ching and Chong");
				answer4.setText("Caesar and Brutus");
			} else if (randQuestion == 9) { // What is the monomer for Proteins
				answer1.setText("Amino Acids"); // correct
				answer2.setText("Nucleotides");
				answer3.setText("Fatty Acids");
				answer4.setText("Starch");
			} else if (randQuestion == 10) { // Which Company created JAVA
				answer1.setText("Riot Games");
				answer2.setText("Sun Microsystems(Oracle)"); // correct
				answer3.setText("Apple");
				answer4.setText("Dell");
			} else if (randQuestion == 11) { // What biomolecule does the
												// Stomach digest
				answer1.setText("Lipids");
				answer2.setText("Proteins"); // correct
				answer3.setText("Nucleic Acids");
				answer4.setText("Carbohydrates");
			} else if (randQuestion == 12) { // What is the temperature at which
												// books burn
				answer1.setText("100 degrees Fahrenheit");
				answer2.setText("0 degrees Fahrenheit");
				answer3.setText("451 degrees Fahrenheit"); // correct
				answer4.setText("587 degrees Fahrenheit");
			} else if (randQuestion == 13) { // What is the main character's
												// wife's name in Fahrenheit 451
				answer1.setText("Mildred"); // correct
				answer2.setText("Clarisse");
				answer3.setText("Faber");
				answer4.setText("Tiffany");
			} else if (randQuestion == 14) { // What is the hypotenuse in a
												// triangle with legs 3 and 4
				answer1.setText("6");
				answer2.setText("4");
				answer3.setText("5"); // correct
				answer4.setText("3");
			} else if (randQuestion == 15) { // What is the largest
												// classification group in
												// taxonomy?
				answer1.setText("Kingdom");
				answer2.setText("Domain"); // correct
				answer3.setText("Phylum");
				answer4.setText("Genus");
			}
		}
	}

	public class purchasePanel extends JPanel implements ActionListener {
		private final JButton purchaseButton, backButton; //place where you purchase 

		public purchasePanel() {
			this.setLayout(null);

			purchaseButton = new JButton("Confirm"); //confirm the purchase
			purchaseButton.addActionListener(this);
			purchaseButton.setBounds(535, 375, 130, 50);
			this.add(purchaseButton);

			backButton = new JButton("Back"); //go back to the panel you were previously at (either character or power)
			backButton.addActionListener(this);
			backButton.setBounds(335, 375, 130, 50);
			this.add(backButton);
		}

		@Override
		public void paintComponent(Graphics g) {
			g.setColor(new Color(150, 150, 150));
			g.fillRect(300, 50, 400, 400);
			g.setColor(Color.black);
			g.setFont(new Font("Serif", Font.BOLD, 15));
			g.drawString("Would you like to confirm this purchase?", 345, 115); //message 

			g.setColor(new Color(100, 100, 100));
			g.fillRect(315, 150, 170, 200);
			g.fillRect(515, 150, 170, 200);

			if (purchaseChar == 1) {
				g.drawImage(rainbowsheep, 355, 190, 100, 100, null); //draws a different image depending on which button was pressed 
			} else if (purchaseChar == 2) {
				g.drawImage(pikachu, 355, 190, 100, 100, null);
			} else if (purchaseChar == 3) {
				g.drawImage(pacman, 355, 190, 100, 100, null);
			} else if (purchaseChar == 4) {
				g.drawImage(mario, 355, 190, 100, 100, null);
			}
			if (purchasePower == 1) {
				g.setColor(Color.green);
				g.fillOval(350, 200, 100, 100); 
				g.setColor(Color.blue); // the shield
				g.drawOval(350, 200, 100, 100);
			} else if (purchasePower == 2) {
				g.drawImage(godHandpic,350,200,100,100,null); 
			} else if (purchasePower == 3) {
				boostX[0] = 445;
				boostX[1] = 365;
				boostX[2] = 365;
				boostY[0] = 255;
				boostY[1] = 215;
				boostY[2] = 295;//array of values to draw a triangle
				g.setColor(Color.yellow);
				g.fillPolygon(boostX, boostY, 3);
				g.setColor(Color.blue);
				g.drawPolygon(boostX, boostY, 3);
			}

			g.setColor(Color.white);
			g.setFont(new Font("Serif", Font.BOLD, 15));
			g.drawString("Score Tracker", 545, 180);
			g.drawString("BEFORE: ", 525, 250);
			g.setColor(Color.green);
			g.drawString(totalScore + "", 595, 250); //draws SCORE BEFORE, THE COST, and SCORE AFTER
			g.setColor(Color.white);
			if (purchasingChar == true) {
				g.drawString("COST: 1000", 525, 275);
				g.drawString("AFTER: ", 525, 300);
				if ((totalScore - 1000) < 0) { //characters cost 1000 points 
					g.setColor(Color.red);
					purchaseButton.setEnabled(false);
				} else {
					purchaseButton.setEnabled(true);
				}
				g.drawString((totalScore - 1000) + "", 585, 300); //subtracts 1000 from your totalscore
				g.setColor(Color.white);
			} else if (purchasingPower == true) {
				g.drawString("COST: 2500", 525, 275); //powerups cost 2500 points
				g.drawString("AFTER: ", 525, 300);
				if ((totalScore - 2500) < 0) {
					g.setColor(Color.red);
					purchaseButton.setEnabled(false);
				}
				g.drawString((totalScore - 2500) + "", 585, 300); //subtracts 2500 from your totalscore
				g.setColor(Color.white);
			}
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String evt = e.getActionCommand();
			if (evt.equals("Confirm")) {
				if (purchaseChar == 1) {
					purchasedSheep = true; //sets a different boolean depending on which character you purchased 
				} else if (purchaseChar == 2) {
					purchasedPik = true;
				} else if (purchaseChar == 3) {
					purchasedPac = true;
				} else if (purchaseChar == 4) {
					purchasedMar = true;
				}
				if (purchasePower == 1) {
					purchasedShield = true;
					cards.show(c, "powerPanel"); //shows the power panel for shield and hand 
					totalScore -= 2500;
				} else if (purchasePower == 2) {
					purchasedHand = true;
					cards.show(c, "powerPanel");
					totalScore -= 2500;
				} else if (purchasePower == 3) {
					purchasedBoost = true;
					cards.show(c, "boostNotifPanel"); //but before boost goes back, shows a little notification
					totalScore -= 2500;				// so the user knows what to do
				}
				if (purchasingChar == true) {
					cards.show(c, "characterPanel");
					totalScore -= 1000;
				}
			} else if (evt.equals("Back")) { //back to the previous panel 
				if (purchaseChar == 1) {
					purchasedSheep = false;
				} else if (purchaseChar == 2) {
					purchasedPik = false;
				} else if (purchaseChar == 3) { //transaction is not made
					purchasedPac = false;		// you do not unlock anything
				} else if (purchaseChar == 4) { 
					purchasedMar = false;
				} 

				if (purchasePower == 1) {
					purchasedShield = false;
				} else if (purchasePower == 2) {
					purchasedHand = false;
				} else if (purchasePower == 3) {
					purchasedBoost = false;
				}
				if (purchasingChar == true)
					cards.show(c, "characterPanel"); //goes back to character panel if you came from characterpanel
				if (purchasingPower == true) // goes back to powerPanel if you came from there 
					cards.show(c, "powerPanel");
			}
		}
	}

	public class gameOverPanel extends JPanel implements ActionListener {
		private final JButton startOver; //panel for game over

		public gameOverPanel() {
			this.setLayout(null);
			startOver = new JButton("Main Menu"); //only 1 choice but to start over and go back to the main menu 
			startOver.setBounds(400, 425, 200, 50);
			startOver.addActionListener(this);
			this.add(startOver);
		}

		@Override
		public void paintComponent(Graphics g) {
			g.drawImage(clouds, 0, 0, null);
			g.drawImage(clouds, 533, 0, null);
			g.setColor(Color.black);
			g.setFont(new Font("Serif", Font.BOLD, 50));
			g.drawString("GAME OVER", 335, 275); //little message to make the user feel bad :D
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String evt = e.getActionCommand();
			if (evt.equals("Main Menu")) { //RESETS VALUES BACK TO ORIGNIAL SO GAME MAY BE STARTED AGAIN
				cards.show(c, "StartPanel");
				charY = 100; 
				charX = 100;
				score = 0;
				
				rocketX = 0; //resets the rocket
				rocketTimer = 0;
				
				WallX = 0; //resets the wall
				
				downTimer.start();
				randQuestion = (int) (Math.random() * 10);
				
				score = 0;
				scoreSpeed = 0;
				speed = 0;
				dead = false;
				
				goCounter = 3;
				goString  = "JAVA JOYRIDE";	
				go = true;
				goFont = 50; //go
				goX = 325;
				goY = 260;
				
				laser = false; //resets the laser
				showLaserNotif = true;
				circleSize = 50;
				laserCounter = 0;
			}
		}
	}
	public class boostNotifPanel extends JPanel implements ActionListener { //notification panel that shows after you purchase boost 
		private JButton back;
		
		public boostNotifPanel(){
			this.setLayout(null);
			back = new JButton("Back");
			back.addActionListener(this);
			back.setBounds(450,350,100,50); //button to go back
			this.add(back);	
		}	
		public void paintComponent(Graphics g){
			g.fillRect(400,75,200,350);
			
			g.setColor(Color.green); //draws the box
			g.fillRect(475,180,50,50);
			g.setColor(Color.blue);
			g.drawRect(475,180,50,50);
			
			boostX[0] = 520;
			boostX[1] = 480;
			boostX[2] = 480; //array of values to draw the triangle
			boostY[0] = 205;
			boostY[1] = 185;
			boostY[2] = 225;
			
			g.setColor(Color.yellow);
			g.fillPolygon(boostX,boostY,3);
			g.setColor(Color.blue); //dras the triangle 
			g.drawPolygon(boostX,boostY,3);
			
			g.setColor(Color.white);
			g.setFont(new Font("Serif", Font.BOLD, 13));
			g.drawString("YOU MAY NOW COLLECT",420,145);
			g.drawString("TO RECEIVE A BOOST",430,275); //message to inform the user
		}
		public void actionPerformed(ActionEvent e){
			String evt = e.getActionCommand();
			if(evt.equals("Back")){ //return to the powerPanel
				cards.show(c,"powerPanel");
			}	
		}
	}
	@Override
	public void keyPressed(KeyEvent e) { 
		if (e.getKeyCode() == KeyEvent.VK_P) { //pressing 'P' will pause the game
			paused = !paused;
			if (paused == false)
				movingBackground.start();
			if (paused == true)
				dMomentumTimer.stop();
		}
		if (e.getKeyCode() == KeyEvent.VK_O) { //pressing 'O' will return to the main menu
			paused = true;
			cards.show(c, "StartPanel");
		}
		if (paused == false && dead == false && godHand == false && e.getKeyCode() == KeyEvent.VK_SPACE) { //pressing 'space'
			timer.start();																					// will cause you to:
			uMomentumTimer.start();																			// go up
			downTimer.stop();																				// stop going down
			dMomentumTimer.stop();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE){
			if (paused == false && dead == false && godHand == false) {
				dMomentumTimer.start();
				downTimer.start();
				timer.stop(); // when you release space, you stop going up, and start going down
				uMomentumTimer.stop();
			}
		}
	}

	public class Up implements ActionListener { // moving up
		@Override
		public void actionPerformed(ActionEvent e) {
			if (paused == false && dead == false) {
				downmomentum = 0; 
				if (charY >= 0) {
					charY -= (3 + upmomentum); //moves your character upwards
				}
				if (charY < 0) {
					charY = 0;
				}
				if (paused == true)
					timer.stop(); //stops if the game is paused 
			}
		}
	}

	public class UpMomentum implements ActionListener { // upwards momentum
		@Override
		public void actionPerformed(ActionEvent e) {
			upmomentum += 1; //move faster up if continuously moving up
			if (paused == true)
				uMomentumTimer.stop(); //stop if paused
		}
	}

	public class Down implements ActionListener { // moving down
		@Override
		public void actionPerformed(ActionEvent e) {
			if (paused == false && dead == false) {
				upmomentum = 0; //stop moving up
				if (charY <= 460) {
					charY += (3 + downmomentum); //start moving down
				}
				if (paused == true)
					dMomentumTimer.stop(); //stop if paused 
			}
		}
	}

	public class DownMomentum implements ActionListener { // downwards momentum
		@Override
		public void actionPerformed(ActionEvent e) {
			downmomentum += 2; //go down faster if continuously going down
			if (paused == true)
				dMomentumTimer.stop(); //stop if paused
			if (dead == true) 
				dMomentumTimer.stop(); //stop if dead
		}
	}

	public class MovingBackground implements ActionListener { // moves the
																// background
		@Override
		public void actionPerformed(ActionEvent e) {
			backgroundX += 5 + (speed / 30); //moves the x position of the background images backwards, creating the effect of you moving forward
												//but in fact you arent moving forward, the background is just moving backwards
			repaint();//the only timer that repaints(to remove lag)
			if (paused == true)
				movingBackground.stop(); //pauses if paused
		}
	}

	public class Score implements ActionListener { // changes the score
		@Override
		public void actionPerformed(ActionEvent e) {
			if (dead == false && paused == false) {
				score += 1 + (scoreSpeed / 10); //increases score (resets once you die)
				totalScore += 1 + (scoreSpeed / 10); //increases your totalScore (retains even if you die and start over)
			}
			if (paused == true)
				speedTimer.stop(); //stops if paused
		}
	}

	public class Speed implements ActionListener { // speed of the background
		@Override
		public void actionPerformed(ActionEvent e) {
			speed += (int) (1 + (1 * speedMult)); //speed increases and increases faster if at a higher difficulty
			scoreSpeed += (int) (speed + 1 + (1 * speedMult)) / 50; //increases the score
			if (paused == true) //stops if paused 
				speedTimer.stop();
		}
	}

	public class Rocket implements ActionListener { // moving the rocket
		@Override
		public void actionPerformed(ActionEvent e) {
			rocketX += 5 + (speed / 50);
			if (rocketX >= 1000) { //when the rocket leaves the screen, reset values
				rocketIsMoving = false;
				rocketX = 0;
				randRocket = (int) (Math.random() * 500);
				rocketMagic = (int)(Math.random()*5);
				rocketMagik.start();
				rDifficultyTimer.start();
				rocketTimer = -1;
				rocketShot.stop();
			}
			if (((1000 - rocketX) <= 100)) { // rocket collision for 1 rocket
				if ((tempY >= charY - 10) && (tempY + 25 <= (charY + 50))) {
					if (shield == false && boost == false) {
						dead = true;
					}
					shieldTimer.start();
				}
				if(rocketDifficulty == 1 || rocketDifficulty == 10){
					if ((tempY + 50 >= charY - 10) && (tempY + 75 <= (charY + 50))) { //rocket collision for the second rocket
						if (shield == false && boost == false) {
							dead = true;
						}
						shieldTimer.start();
					}
				}
				if(rocketDifficulty == 10){
					if ((tempY - 50 >= charY - 10) && (tempY - 25 <= (charY + 50))) { //rocket collision for the third rocket
						if (shield == false && boost == false) {
							dead = true;
						}
						shieldTimer.start();
					}
				}
			}
			if (paused == true) //stops if paused 
				rocketShot.stop();
		}
	}

	public class Exclam implements ActionListener { // moving the rocket
		@Override
		public void actionPerformed(ActionEvent e) {
			if (paused == false)
				rocketTimer++; //timer for the rocket, explained in the beginning of gamePanel
			tempY = charY;
			if (rocketTimer == 4) { //at 4, the rocket will fire, 1,2,& 3 display the exclamation
				rocketShot.start();
			}
			if(paused == true){ //stops the timer if paused
				exclamTimer.stop();
			}
		}
	}

	public class Wall implements ActionListener { // moving the rocket
		@Override
		public void actionPerformed(ActionEvent e) {
			randWall = (int) (Math.random() * 4); //randomly creates a wall
			randY = (int) (Math.random() * 500); //random y position
			randDir = (int) (Math.random() * 2); //random direction, either horizontal or vertical
			if (paused == true)
				wallTimer.stop(); //stop the timer if paused
		}
	}

	public class WallX implements ActionListener { // moves the background
		@Override
		public void actionPerformed(ActionEvent e) {
			WallX += 5 + (speed / 30); //moves the wall at the same speed as the background
			if (WallX >= 1250) {
				WallX = 0; //sets the xposition = 0	
				randWall = 4;
				wallTimer.start(); //starts the walltimer when the wall reaches the end of the screen
			}
			if (paused == true)
				wallXTimer.stop(); //stops the timer if paused
		}
	}

	public class laserCircle implements ActionListener { // moving the rocket
		@Override
		public void actionPerformed(ActionEvent e) {
			circleSize--; //circle notification gets smaller
			if (circleSize <= 0) { //once it reaches 0
				laser = !laser; //shoot laser
				showLaserNotif = !showLaserNotif; //no more notification 
				circleSize = 50; //reset the size to 50 
				laserCounter++; 
				if (laserCounter % 2 == 0) { //laser lasts for this long
					laserNotifY = (int) (Math.random() * 500);
					laserY = laserNotifY + 20;
				}
			}
			tempCircleSize = (Math.abs(50 - circleSize) / 2); //makes the circle stay in the same place, instead of moving left when getting smaller
			if (paused == true)
				circleTimer.stop(); //stops the timer when paused
		}
	}

	public class shield implements ActionListener { // moving the rocket
		@Override
		public void actionPerformed(ActionEvent e) {
			shieldBlink++; //counter 
			if (shieldBlink % 2 == 0) { //every other increment shield will blink (display and not display)
				if (shield == true && boost == false)
					dispShield = !dispShield; //display the shield on and off
			}
			if (shieldBlink % 10 == 0) { //when blinking ends, shield is gone
				dispShield = false;
				shield = false;
				shieldTimer.stop(); //stop the timer
			}
		}
	}

	public class boost implements ActionListener { // boost
		@Override
		public void actionPerformed(ActionEvent e) {
			boostCount++;
			shield = true;//when boosting you cant die
			dispShield = false;
			speed = 1000;
			if (boostCount % 12 == 0) { //lasts until boostCount reaches 12
				speed = tempSpeed;
				scoreSpeed = tempScoreSpeed;
				boost = false;
				shield = false; 
				if (tempShield == true) {
					shield = true;
					dispShield = true;
				}
				boostTimer.stop(); //stops the boost 
			}
		}
	}

	public class boostMagic implements ActionListener { // where the magic happens for boost
		@Override
		public void actionPerformed(ActionEvent e) {
			if(boxIsMoving == false && canBoost == true){
				boostMagic = (int)(Math.random()*25); //random chance for a boostBox to appear
			}
		}
	}
	public class Go implements ActionListener{ //intro font
		public void actionPerformed(ActionEvent e){
			if(goCounter == 3){
				goX = 400;
				goString = "READY?"; //first string says "READY?"
				fontBigTimer.start();
			}
			if(goCounter == 2){
				goX = 455;
				goString = "SET"; //second string says "SET"
			}
			if(goCounter == 1){
				goX = 400;
				goString = "GOOOO!"; //third string says "GOOOO!"
			}
			if(goCounter == -1){
				go = false; //once it ends 
				goTimer.stop();
				scoreTimer.start(); //start the game
				speedTimer.start();
				boostMagik.start();
				rocketMagik.start();
				wallTimer.start();
			}
			goCounter--;
		}
	}
	public class fontBig implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(go == true){
				goFont++;; //makes font bigger
				goX -=2; //moves back x positoin to compensate
				if(goFont >= 75){
					fontSmallTimer.start(); //if at 75, stop getting bigger and start getting smaller
					fontBigTimer.stop();
				}
			}else{
				fontBigTimer.stop(); //or else stop
				goFont = 50;
			}
		}
	}
	public class fontSmall implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(go == true){
				goFont--; //makes the font smaller
				goX +=2;  //increases x position to compensate
				if(goFont <= 40){
					fontBigTimer.start(); //when gets smaller than 40, start getting bigger, stop getting smaller
					fontSmallTimer.stop();
				}
			}else{
				fontSmallTimer.stop(); //or else stop
				goFont = 50;
			}
		}	
	}
	public class movingBox implements ActionListener{
		public void actionPerformed(ActionEvent e){
			powerBoxX -= 5 + (speed / 30); //moves the x position of the box
			if(powerBoxX >= 1000){
				powerBoxY = (int)(Math.random()*450); //new y position if the box reaches the end of the screen
			}
			if(powerBoxX <= -50){ //resets if the box leaves the screen 
				powerBoxX = 1100;
				boostMagic = (int)(Math.random()*25);
				boxIsMoving = false;                      
				boostMagik.start();
				powerBoxTimer.stop();
			}
		}
	}
	public class rocketMagik implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(rocketIsMoving == false){
				rocketMagic = (int)(Math.random()*5); //will randomly create rockets
			}				
			if(rocketMagic == 1 && go == false && paused == false){
				exclamTimer.start(); //stops if paused 
			}
		}
	}
	public class rocketDifficulty implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(speed >= 100  && rocketIsMoving == false){
				rocketDifficulty = (int)(Math.random()*10) + 1; //creates random numbers 2-9 are normal 1 & 10 are double and triple rocketse
			}
			if(rocketDifficulty == 1 || rocketDifficulty == 10){
				rDifficultyTimer.stop(); //stops creating new ints, if either  1 or 10 (double and triple rocketes)
			}
		}
	}
	@Override
	public void keyTyped(KeyEvent e) { // not used
	}
}