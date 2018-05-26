package management;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Window.Type;

public class AddAssignment extends JFrame {

	private JPanel contentPane;
	static AddAssignment frame = new AddAssignment();
	private JTextField txtName = new JTextField();
	private JTextField txtScore = new JTextField();
	//Main mainfile = new Main();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddAssignment frame = new AddAssignment();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public void AddNewAssingment(String ClassName) throws Exception
	{
		String AssingmentName = txtName.getText();
		Double AssingmentValue = Double.parseDouble(txtScore.getText());
			 Class.forName("org.h2.Driver");
	        Connection conn = DriverManager.
	            getConnection("jdbc:h2:C:/Management/Classes", "sa", "");
	        // add application code here
	        String AddNewCol = "ALTER TABLE \""+ClassName+"\" ADD \""+AssingmentName+"\" DECIMAL(18,2)";
	        Statement st1 = conn.createStatement();
	        int rs1 = st1.executeUpdate(AddNewCol);
	        for(int i = 0; i < Main.Students.length; i++) {
	        	if(Main.Students[i] == "Total Posible" || Main.Students[i] == "Total Posible" ) {
	        		new print("name is total posiable");
	        	}
	        	else {
	        	Class.forName("org.h2.Driver");
		        Connection conn1 = DriverManager.
		            getConnection("jdbc:h2:C:/Management/Classes", "sa", "");
		        Double grade = Double.parseDouble(JOptionPane.showInputDialog("What is the score for "+Main.Students[i]+"?"));
				 Class.forName("org.h2.Driver");
				 double percentage = (grade/AssingmentValue)*100;
	        String InsertIntoCol = "UPDATE \""+ClassName+"\" SET \""+AssingmentName+"\" ='"+grade+"' WHERE NAME='"+Main.Students[i]+"';";
	        Statement st = conn1.createStatement();
	        int rs = st.executeUpdate(InsertIntoCol);
	        	}
	        
		}
	        String InsertIntoCol = "UPDATE \""+ClassName+"\" SET \""+AssingmentName+"\" ='"+AssingmentValue+"' WHERE ID='999';";
	        Statement st = conn.createStatement();
	        int rs = st.executeUpdate(InsertIntoCol);
	       Main.UpdateTable();
	}
	public AddAssignment() {
		setAlwaysOnTop(true);
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][grow][grow]", "[][][][][][][][][]"));
		
		JLabel lblWhatIsThe = new JLabel("What is the name of the assignment?");
		contentPane.add(lblWhatIsThe, "cell 3 0");
		txtName.setToolTipText("Name");
		contentPane.add(txtName, "cell 1 1 4 1,growx");
		txtName.setColumns(10);
		
		JLabel lblWhatIsThis = new JLabel("What is this assignment out of?");
		contentPane.add(lblWhatIsThis, "cell 3 3");
		
		
		txtScore.setToolTipText("Score");
		contentPane.add(txtScore, "cell 1 4 4 1,growx");
		txtScore.setColumns(10);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.setBackground(new Color(0, 255, 255));
		btnContinue.setForeground(new Color(0, 0, 0));
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					AddNewAssingment(Main.SelectedTab);
					//dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		contentPane.add(btnContinue, "cell 3 8,growx,aligny center");
	}

}
