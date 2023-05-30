import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class StartWindow extends JFrame {

	public StartWindow(){
		setSize(600,400);
		getContentPane().setBackground(Color.black);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		ImageIcon logo = new ImageIcon();
		try {
			logo = new ImageIcon(ImageIO.read(this.getClass().getResource("/pacman_logo1.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		//Register Custom fonts
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream("crackman.ttf")));
		} catch (IOException|FontFormatException e) {
			e.printStackTrace();
		}

		setLayout(new BorderLayout());
		getContentPane().add(new JLabel(logo),BorderLayout.NORTH);

		JPanel buttonsC = new JPanel();
		buttonsC.setBackground(Color.black);
//        buttonsC.setLayout(new FlowLayout(FlowLayout.LEADING,20,10));
		buttonsC.setLayout(new BoxLayout(buttonsC,BoxLayout.Y_AXIS));
		FansyButton startButton = new FansyButton("Start Game");
		FansyButton mini = new FansyButton("Whack a mole");
		FansyButton exit = new FansyButton("Exit");

		startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		mini.setAlignmentX(Component.CENTER_ALIGNMENT);
		exit.setAlignmentX(Component.CENTER_ALIGNMENT);

		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Menu("Game beta");
				dispose();
			}
		});


		mini.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Whack();
				dispose();
			}
		});

		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		buttonsC.add(startButton);
		buttonsC.add(mini);
		buttonsC.add(exit);

		getContentPane().add(buttonsC);

		System.out.print('\n');
		System.out.println("Mineswapeers   Developed By : Group 30 FLop");
		System.out.println("-----------------------------------------");
		setVisible(true);
	}


}
