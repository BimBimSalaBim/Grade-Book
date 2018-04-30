package management;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;

//create class and extend with JFrame
public class EditAddClasses extends JFrame 
{
	
	//declare variable
	private JPanel contentPane;
	//Main main = new Main();
	/**
	 * Launch the application.
	 */
	//main method
	public static void main(String[] args) 
	{
		EditAddClasses frame = new EditAddClasses();
		/* It posts an event (Runnable)at the end of Swings event list and is
		processed after all other GUI events are processed.*/
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				//try - catch block
				try 
				{
					//Create object of NewWindow
					
					//set frame visible true
					frame.setVisible(true);					
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EditAddClasses() //constructor
	{
		setResizable(false);
		setAlwaysOnTop(false);
		//set frame title
		setTitle("Add/Edit Classes");
		//set default close operation
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//set bounds of the frame
		setBounds(100, 100, 433, 469);
		
		//create object of JPanel
		contentPane = new JPanel();
		//set border
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//set ContentPane
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[277px][]", "[28px][][][][][][][][][]"));
		
		//label in the frame
		JLabel lblWelcome = new JLabel("Welcome this is New Frame");
		//set fore ground color to the label
		lblWelcome.setForeground(Color.RED);
		//set font to the label
	lblWelcome.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 24));
		//add label to the contentPane 
		contentPane.add(lblWelcome, "cell 0 0,alignx left,aligny top");
		
		JButton btnAddClass = new JButton("Add Class");
		btnAddClass.addActionListener(new ActionListener() {
			/// what the "new" button does
			public void actionPerformed(ActionEvent arg0) {
				try {
					/*Main rs = new Main();
					rs.newStudents();*/
					AddClass.frame.setVisible(true);
					
					
					

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//JOptionPane.showInputDialog("Enter Employee name. Ex. \"Faizan\" ");
			}
		});
		contentPane.add(btnAddClass, "cell 0 8,growx,aligny center");
	}
}