package management;
//
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Window.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

@SuppressWarnings("unused")
public class AddClass extends JFrame {

	private JPanel contentPane;
	private JTextField txtClassName = new JTextField();
	private JTextField txtNumberOfStudents = new JTextField();
	
	static AddClass frame = new AddClass();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public String[][] newStudents() throws Exception
	{	
        Class.forName("org.h2.Driver");

        Connection conn = DriverManager.
            getConnection("jdbc:h2:C:/Management/Classes;DB_CLOSE_DELAY=-1", "sa", "");
        
		String className = txtClassName.getText();
		
		String CreateTable = "CREATE TABLE "+className+" ( ID int, Name varchar(255));";
		Statement st = conn.createStatement();
        st.execute(CreateTable);
		
		System.out.println("4");

		int NumOfStudents = Integer.parseInt(txtNumberOfStudents.getText());
		
		String[] Name = new String[NumOfStudents];
		String[] Id = new String[NumOfStudents];
		
		

		for(int i = 0; i < NumOfStudents; i++) {

			Name[i] = JOptionPane.showInputDialog(
			        "[what is the name of student "+(i + 1)+"]");
			if(Name[i] != null ) 
			{
				Id[i] = Integer.toString(i+1);
 

			}
			else
			{
				Name[i] = JOptionPane.showInputDialog(
				        "[what is the name of student "+(i + 1)+"]");
			}
			
			String AddStudent = "insert INTO "+className+" VALUES ("+Id[i]+", \'"+Name[i]+"\');";
			st.execute(AddStudent);
			
		}
		String AddTotal = "insert INTO "+className+" VALUES ("+999+", \'Total Posible\');";
		st.execute(AddTotal);
		Main.dlm.addElement(className);
		JOptionPane.showMessageDialog(contentPane,
		        "All of the loop works"+ className);
		

		return null;
		
	}

	/**
	 * Create the frame.
	 */
	public AddClass() {
		setTitle("Add Class");
		setType(Type.UTILITY);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 430, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWhatIsThe = new JLabel("What is the name of the new class?");
		lblWhatIsThe.setBounds(33, 35, 347, 27);
		lblWhatIsThe.setFont(new Font("Tahoma", Font.PLAIN, 22));
		contentPane.add(lblWhatIsThe);
		
		txtClassName = new JTextField();
		txtClassName.setBounds(65, 97, 279, 20);
		txtClassName.setToolTipText("Class Name");
		contentPane.add(txtClassName);
		txtClassName.setColumns(8);
		
		JLabel lblHowManyStudents = new JLabel("How Many Students Are in the class?");
		lblHowManyStudents.setBounds(27, 152, 359, 27);
		lblHowManyStudents.setFont(new Font("Tahoma", Font.PLAIN, 22));
		contentPane.add(lblHowManyStudents);
		
		txtNumberOfStudents = new JTextField();
		txtNumberOfStudents.setBounds(65, 214, 279, 20);
		txtNumberOfStudents.setToolTipText("Number of Students");
		contentPane.add(txtNumberOfStudents);
		txtNumberOfStudents.setColumns(5);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					newStudents();
					dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnContinue.setBounds(121, 299, 148, 23);
		btnContinue.setBackground(new Color(0, 250, 154));
		contentPane.add(btnContinue);
	}

}
