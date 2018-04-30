package management;
//all of the imports needed for the program
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.BorderLayout;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
//import javafx.scene.control.Label;
import org.h2.command.dml.Select;



public class Main {

	private JFrame frame;
	 //stores names of classes used to get how many classes are and the name of the files they are stored in
	  ArrayList<String> ClassList = new ArrayList<String>();
	  ArrayList<String> StudentList = new ArrayList<String>();
	  String [] Classes;
	  String [] Students;
	  String [] Assignments = new String[] {"test1","test2","test3","test4"};
	  String [] Grades = new String[] {"Grade1","Grade2","Grade3","Grade4"};
	  JPanel ClassesPanel = 	new JPanel();
	  JPanel Studentpanel = new JPanel();
	  private JTable table;
	  JList<?> list;
	  
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
	/*	Main read = new Main();
		//read.numberofclasses();
		if(read.ClassList != null) 
			{
//				read.ReadFile();
				System.out.println("1");
			}
		try {
			read.csvToArray();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			read.ClassArray();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		read.SetList();
		//System.out.print(read.StudentList.get(1));
*/		
	}
	
	////user arraylist

	public void DBconn() throws Exception 
		{
		        Class.forName("org.h2.Driver");

		        DriverManager.
		            getConnection("jdbc:h2:C:/Management/Classes", "sa", "");
		      
		       // System.out.print(rs);

		        
	}
	public void SetList() {
		JList<String> list = new JList<String>();
		DefaultListModel<String> dlm = new DefaultListModel<String>();
		ClassesPanel.remove(list);
		dlm.removeAllElements();
		if(Classes != null)
		{
		for(int i = 0; i < Classes.length; i++) {
		dlm.addElement(Classes[i]);
		}
		
		}else {System.out.print("we null"); }
		//dlm.addElement("none");
		list.setModel(dlm);
		list.addListSelectionListener(new ListSelectionListener() {
			
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                	System.out.print(list.getSelectedValue().toString());
                }
                return;
            }
        });
		ClassesPanel.add(list);
		
		ClassesPanel.revalidate();
	    ClassesPanel.repaint();
	    
		
	}
	
	public void SetTable() {
		table = new JTable();
		DefaultTableModel model = new DefaultTableModel();
        
        Object[] columnsName = new Object[4];
        
        columnsName[0] = "Id";
        columnsName[1] = "Fname";
        columnsName[2] = "Lname";
        columnsName[3] = "Age";
        Object[] rowData = new Object[Students.length];
        model.setColumnIdentifiers(columnsName);
        if(Students != null) {
        for(int i = 0; i < Students.length; i++){
            
            rowData[0] = Students[i];
            if(i>0) {
            	if(i < 4)
            rowData[i] = Grades[i-1];}
              model.addRow(rowData);
        }
        }
        model.addColumn(columnsName);
        table.setModel(model);
		Studentpanel.add(table);


		
	}
	
	

	//just gets the the names of all of the classes files and puts it in the Array list
	/*public void numberofclasses() {
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
	}*/
	public void ClassArray() throws Exception {
		
        Class.forName("org.h2.Driver");

        Connection conn = DriverManager.
            getConnection("jdbc:h2:C:/Management/Classes", "sa", "");
        // add application code here
        String statement = "SHOW TABLES";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(statement);
        rs.getMetaData();

        
        
        
        ResultSetMetaData rsmd1 = rs.getMetaData();
        int columnsNumber1 = rsmd1.getColumnCount();                     
        Classes = new String[columnsNumber1];
        // Iterate through the data in the result set and display it. 
        int j = 1;
        while (rs.next()) {
        //Print one row          
        for(int i = 1 ; i <= columnsNumber1; i++){
        	//String regex = rs.getString(i);
        	//regex = regex.replaceAll("PUBLIC", "");
        	if(rs.getString(i).equals("PUBLIC"))
        	{}
        	else {
        	Classes[j-1] =rs.getString(i);
        	System.out.println(Classes[j-1] + " This has been added to the classes array at "+ (j-1));
              //System.out.println(rs.getString(i)); //Print one element of a row
              System.out.println(j);
              j++;
        	}
        }
          System.out.println();//Move to the next line to print the next row.           

            }
      
       // System.out.print(rs);
        conn.close();
        
    
		}
	

/*	public void ReadFile() {
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
					//StudentList.add(data);
				}
				inputStream.close();
			} 
			catch (FileNotFoundException e){ 
				e.printStackTrace();
			}
		}	
	}*/

	public void csvToArray()
			  throws Exception {
        Class.forName("org.h2.Driver");

        Connection conn = DriverManager.
            getConnection("jdbc:h2:C:/Management/Classes", "sa", "");
        // add application code here
        //System.out.println(Classes+" these are the classes");
        if(Classes != null) {
        for(int i = 0; i < Classes.length; i++) {
        String statement = "SELECT NAME FROM "+Classes[i];
        
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(statement);
        ResultSetMetaData rsmd = rs.getMetaData();

        
        rsmd.getColumnCount();
        
        ResultSetMetaData rsmd1 = rs.getMetaData();
        int columnsNumber1 = rsmd1.getColumnCount();                     
        Students = new String[columnsNumber1+4];
        // Iterate through the data in the result set and display it. 
        int k = 0;
        while (rs.next()) {
        //Print one row          
        for(int j = 1 ; j <= columnsNumber1; j++){
        	String regex = rs.getString(j);
        	Students[k]= regex;
        	//StudentList.add(regex);
        	
              System.out.print(Students[k] +" This name has been added to Student at "+k); //Print one element of a row
              k++;
        }

          System.out.println();//Move to the next line to print the next row.           

            }
        }
        }
       // System.out.print(rs);
        conn.close();
        
    }


	
	public String[][] newStudents() throws Exception
	{	
        Class.forName("org.h2.Driver");

        Connection conn = DriverManager.
            getConnection("jdbc:h2:C:/Management/Classes;DB_CLOSE_DELAY=-1", "sa", "");
        
		String className = JOptionPane.showInputDialog(frame,
				        "[what is the name of the class? (No Spaces)]");
		String CreateTable = "CREATE TABLE "+className+" ( ID int, Name varchar(255));";
		Statement st = conn.createStatement();
        st.execute(CreateTable);
		
		System.out.println("4");
		//add to list of the number of classes and their names
/*		 try{
			    
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
			 out = new BufferedWriter(new FileWriter(className+".csv", true));
			 out.append("ID,Name,");
			         
			    //out.write(Arrays.toString(nums));
			    //Close the output stream
			         out.close();
			    }catch (Exception e){//Catch exception if any
			      System.err.println("Error: " + e.getMessage());
			    }*/
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
			
			String AddStudent = "insert INTO "+className+" VALUES ("+Id[i]+", \'"+Name[i]+"\');";
			st.execute(AddStudent);
			
		}
		JOptionPane.showInputDialog(frame,
		        "All of the loop works");
		SetList();

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
		ClassesPanel.setLayout(new MigLayout("", "[196px]", "[60px][23px][][][][][][][][][][]"));



		File f = new File("C:\\Management\\Classes.mv.db");
		if(f.exists() && !f.isDirectory()) { 
		    // do something
		
				try {
			ClassArray();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
					try {
			csvToArray();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	
		/*numberofclasses();
		if(ClassList != null) 
			{
				ReadFile();
				System.out.println("1");
			}
		ReadFile();*/
					for (int i = 0; i < Classes.length; i++)
					{
						System.out.println(Classes[i] + " Name of class "+ i);
					}
		SetList();
		SetTable();
		}
		else{
			JOptionPane.showMessageDialog(frame, "Please Create a new class to start");
		}
		JButton NewClassButton = new JButton("New");
		NewClassButton.setBackground(Color.GRAY);
		NewClassButton.setToolTipText("Click here to add a new class.");
		NewClassButton.setVerticalAlignment(SwingConstants.BOTTOM);
		NewClassButton.addActionListener(new ActionListener() {
			/// what the "new" button does
			public void actionPerformed(ActionEvent arg0) {
				try {
					newStudents();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//JOptionPane.showInputDialog("Enter Employee name. Ex. \"Faizan\" ");
			}
		});
		ClassesPanel.add(NewClassButton, "cell 0 11,grow");
		
		
		Studentpanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		frame.getContentPane().add(Studentpanel, "cell 1 1,grow");
		Studentpanel.setLayout(new GridLayout(1, 0, 0, 0));
		
        	
	}
}
