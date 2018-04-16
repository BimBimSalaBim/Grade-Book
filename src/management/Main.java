package management;
//all of the imports needed for the program
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.awt.EventQueue;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.BorderLayout;
import javax.swing.border.LineBorder;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Insets;
import net.miginfocom.swing.MigLayout;
import javax.swing.JList;
import javax.swing.JTable;
import java.awt.SystemColor;
import org.apache.commons.io.FilenameUtils;

	
public class Main {

	private JFrame frame;
	 //stores names of classes used to get how many classes are and the name of the files they are stored in
	  ArrayList ClassList = new ArrayList();
	  ArrayList StudentList = new ArrayList();
	  String [] Classes;
	  String [] Students;
	  String [] Assignments;
	  String [] Grades;
	  JPanel ClassesPanel = 	new JPanel();
	  JPanel Studentpanel = new JPanel();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(
			new Runnable() 
			{
				public void run() 
				{
					try 
						{
							Main window = new Main();
							window.frame.setVisible(true);
						} 
					catch (Exception e) 
						{
							e.printStackTrace();
						}
				}
			}
		);
		//used to call non-static methods
		Main read = new Main();
		read.numberofclasses();
		if(read.ClassList != null) 
			{
				read.ReadFile();
				System.out.println("1");
			}
		read.csvToArray();
		read.ClassArray();
		
		
	}
	
	public void createClassLable(String name) {
		GridBagConstraints lable = new GridBagConstraints();
		lable.anchor = GridBagConstraints.NORTH;
		lable.fill = GridBagConstraints.BOTH;
		lable.gridwidth = 4;
		lable.gridx = 3;
		lable.gridy = 13;
		JLabel lblNewLabe = new JLabel("Classes");
		lblNewLabe.setFont(new Font("Tahoma", Font.PLAIN, 20));
		ClassesPanel.add(lblNewLabe,lable);
		ClassesPanel.revalidate();
	    frame.repaint();
	}
	//just gets the the names of all of the classes files and puts it in the Array list
	public void numberofclasses() {
		//gets the file that has the names of other files used for classes
		File file = new File("classes.csv");
		System.out.println("8");
		//adds names to list
		try {
			Scanner inputStream = new Scanner(file);
			
			while(inputStream.hasNext()) {
				String data = inputStream.next();
				System.out.println("9");
				ClassList.add(data);			
				System.out.println(data);
				String basename = FilenameUtils.getBaseName(data);
				
				
			}
			
			inputStream.close();
		} catch (FileNotFoundException e) {
			// 
			e.printStackTrace();
		}
	}
	public void ClassArray()
		{
		String[] Classes = new String[ClassList.size()];
		File file = new File("classes.csv");
		try {
			Scanner inputStream = new Scanner(file);
			
			for(int i = 0; i < ClassList.size(); i++) {
				String data = inputStream.next();
				String basename = FilenameUtils.getBaseName(data);
				Classes[i] = basename;
			}
		} catch (FileNotFoundException e) {
			// 
			e.printStackTrace();
		}
		}
	

	public void ReadFile() {
		System.out.println("6");
		for (int i = 0; i < ClassList.size(); i++) 
		{
			System.out.println("7");
			File file = new File((String) ClassList.get(i));
			System.out.println("2");
			try {
				Scanner inputStream = new Scanner(file);
				while(inputStream.hasNext()) {
					String data = inputStream.next();
					System.out.println("3");
					System.out.println(data);
				}
				inputStream.close();
			} 
			catch (FileNotFoundException e){ 
				e.printStackTrace();
			}
		}	
	}

	public void csvToArray()
	{
		for (int i = 0; i < ClassList.size(); i++) 
		{
			try 
			{
				String Name;
				String filename = (String) ClassList.get(i);
				System.out.println(filename);
				Reader in = new FileReader(filename);
				try {
					Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
					int j = 0;
					for (CSVRecord record : records) {
					    String id = record.get(0);
					    Name = record.get(1);
					    StudentList.add(Name);
					    j++;
					  //  String students[] = new students[] {record.get(0), record.get(1)};
					    System.out.println(id+" "+ Name);
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			} 
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			}		
		}			
		System.out.println("ClassList"+ClassList);
	}
	public void NumOfColumns() 
	{
		
	}
	
	public String[][] newStudents()
	{	
		String className = JOptionPane.showInputDialog(frame,
				        "[what is the name of the class?]");
		
		createClassLable(className);
		System.out.println("4");
		//add to list of the number of classes and their names
		 try{
			    
			 Writer out;
			    //FileWriter fstream = new FileWriter(System.currentTimeMillis() + "out123.txt");
			         out = new BufferedWriter(new FileWriter("Classes"+".csv", true));
			         out.append(className+".csv");
			         ((BufferedWriter) out).newLine();
			    //out.write(Arrays.toString(nums));
			    //Close the output stream
			         out.close();
			    }catch (Exception e){//Catch exception if any
			      System.err.println("Error: " + e.getMessage());
			    }
		    
		    System.out.println("5");
		//make new file per class
		 try{
			    
			 Writer out;
			    //FileWriter fstream = new FileWriter(System.currentTimeMillis() + "out123.txt");
			         out = new BufferedWriter(new FileWriter(className+".csv", true));
			         out.append("ID,Name,");
			         
			    //out.write(Arrays.toString(nums));
			    //Close the output stream
			         out.close();
			    }catch (Exception e){//Catch exception if any
			      System.err.println("Error: " + e.getMessage());
			    }
		int NumOfStudents = Integer.parseInt(JOptionPane.showInputDialog(frame,
		        "How many students are in "+className+"?"));
		
		String[] Name = new String[NumOfStudents];
		String[] Id = new String[NumOfStudents];
		
		

		for(int i = 0; i < NumOfStudents; i++) {

			Name[i] = JOptionPane.showInputDialog(frame,
			        "[what is the name of student "+(i + 1)+"]");
			if(Name[i] != null ) 
			{
				Id[i] = Integer.toString(i+1);
 

			}
			else
			{
				Name[i] = JOptionPane.showInputDialog(frame,
				        "[what is the name of student "+(i + 1)+"]");
			}
			
			
			FileOut(Name[i],Id[i], className);
			
		}
		JOptionPane.showInputDialog(frame,
		        "All of the loop works");
		

		return null;
		
	}
	
	

	//outputs file
	public static void FileOut(String name, String id, String className)
	{

		 try{
			    // Create file 
			 Writer out;
			    //FileWriter fstream = new FileWriter(System.currentTimeMillis() + "out123.txt");
			         out = new BufferedWriter(new FileWriter(className+".csv", true));
			         ((BufferedWriter) out).newLine();
			         out.append(id.toString()+",");
			         out.append(name.toString()+",");
			    //out.write(Arrays.toString(nums));
			    //Close the output stream
			         out.close();
			    }catch (Exception e){//Catch exception if any
			      System.err.println("Error: " + e.getMessage());
			    }
	}
		/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 720, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[200.00px][180.00px,grow]", "[52.00px][1920.00px,grow]"));
		
		
		JLabel lblNewLabel = new JLabel("Classes");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(lblNewLabel, "cell 0 0,alignx center,aligny center");
		
		JLabel lblNewLabel_1 = new JLabel("Students");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		frame.getContentPane().add(lblNewLabel_1, "flowx,cell 1 0,alignx center,growy");

		
		ClassesPanel.setBackground(Color.WHITE);
		ClassesPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		frame.getContentPane().add(ClassesPanel, "cell 0 1,grow");
		GridBagLayout gbl_ClassesPanel = new GridBagLayout();
		gbl_ClassesPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 10};
		gbl_ClassesPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5};
		gbl_ClassesPanel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_ClassesPanel.rowWeights = new double[]{1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		ClassesPanel.setLayout(gbl_ClassesPanel);
		JButton NewClassButton = new JButton("New");
		NewClassButton.setBackground(Color.GRAY);
		NewClassButton.setToolTipText("Click here to add a new class.");
		NewClassButton.setVerticalAlignment(SwingConstants.BOTTOM);
		NewClassButton.addActionListener(new ActionListener() {
			/// what the "new" button does
			public void actionPerformed(ActionEvent arg0) {
				newStudents();
				//JOptionPane.showInputDialog("Enter Employee name. Ex. \"Faizan\" ");
			}
		});
		GridBagConstraints gbc_NewClassButton = new GridBagConstraints();
		gbc_NewClassButton.anchor = GridBagConstraints.SOUTH;
		gbc_NewClassButton.fill = GridBagConstraints.BOTH;
		gbc_NewClassButton.gridwidth = 4;
		gbc_NewClassButton.gridx = 3;
		gbc_NewClassButton.gridy = 13;
		ClassesPanel.add(NewClassButton, gbc_NewClassButton);
		
		
		Studentpanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		frame.getContentPane().add(Studentpanel, "cell 1 1,grow");
		Studentpanel.setLayout(new GridLayout(1, 0, 0, 0));
	}
}
