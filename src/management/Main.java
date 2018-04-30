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
import javax.swing.JScrollPane;
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
import org.apache.commons.lang3.ArrayUtils;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javafx.scene.control.Label;
import org.h2.command.dml.Select;


import javax.swing.ScrollPaneConstants;
import java.awt.Button;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.LayoutStyle.ComponentPlacement;



public class Main {

	private JFrame frame;
	EditAddClasses EditAddClasses = new EditAddClasses();
	 //stores names of classes used to get how many classes are and the name of the files they are stored in
	  ArrayList<String> ClassList = new ArrayList<String>();
	  ArrayList<String> StudentList = new ArrayList<String>();
	  String [] Classes;
	  String [] Students;
	  String [] Assignments;
	  int[] Grades;
	  JPanel ClassesPanel = 	new JPanel();
	  DefaultTableModel model = new DefaultTableModel();
	  DefaultListModel<String> dlm = new DefaultListModel<String>();
	  JPanel Studentpanel = new JPanel();
	  JTable table = new JTable(model);
		JList<String> list = new JList<String>(dlm);
	  String SelectedTab;
	  String NewClassName;
	  int columnsNumber1;
	  
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(
			new Runnable() {
				public void run() {
					try{
							Main window = new Main();
							window.frame.setVisible(true);
						} 
					catch (Exception e){
							e.printStackTrace();
						}
				}
			}
		);
	}
	

	public void DBconn() throws Exception 
		{
		        Class.forName("org.h2.Driver");

		        DriverManager.
		            getConnection("jdbc:h2:C:/Management/Classes", "sa", "");   
		       // System.out.print(rs); 
	}
	public void SetList() {
		
		
			
		ClassesPanel.remove(list);
		dlm.removeAllElements();
		if(Classes != null)
		{
		for(int i = 0; i < Classes.length; i++) {
		dlm.addElement(Classes[i]);
		}
		
		}else {System.out.print("we null"); }
		//dlm.addElement("none");
		
		list.addListSelectionListener(new ListSelectionListener() {
			
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                try {
					if (!arg0.getValueIsAdjusting()) {
						if(list.getSelectedValue().toString() != null) {
						SelectedTab = list.getSelectedValue().toString();
						}
						try {
							csvToArray(SelectedTab);
							Studentpanel.remove(table);
							model.setRowCount(0);
							Studentpanel.repaint();
							SetTable();
							model.fireTableDataChanged();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
            }
        });
		
	}
	
	public void AddToList(String Name) {
		dlm.addElement(Name);
	}
	
	public void SetTable() {
		table = new JTable();
		
        
        Object[] columnsName = new Object[columnsNumber1];
        columnsName[0] = "Name";
        for(int i = 0; i < columnsNumber1; i++) {
        	columnsName[i] = Assignments[i];
        }

        Object[] rowData = new Object[columnsNumber1];
        model.setColumnIdentifiers(columnsName);
        model.addRow(columnsName);
        if(Students != null) {
        for(int i = 0; i < StudentList.size(); i++){
            
            rowData[0] = Students[i];
            for( int j = 1; j < columnsNumber1; j++)
            {
            	System.out.println(columnsNumber1+" Number of col");
            	rowData[j] = Grades[j-1];
            }
              model.addRow(rowData);
        }
        }
        model.addColumn(columnsName);
        table.setModel(model);
		Studentpanel.add(table, "cell 0 0 2 1,grow");
	}
	public void AddNewAssingment(String ClassName) throws Exception
	{
		String AssingmentName = JOptionPane.showInputDialog("What is the name of the new assinment?");
		 Class.forName("org.h2.Driver");
			int AssingmentValue = Integer.parseInt(JOptionPane.showInputDialog("What is "+AssingmentName+" Out of?"));
			 Class.forName("org.h2.Driver");
	        Connection conn = DriverManager.
	            getConnection("jdbc:h2:C:/Management/Classes", "sa", "");
	        // add application code here
	        String AddNewCol = "ALTER TABLE "+ClassName+" ADD "+AssingmentName +" INT NULL;";
	        Statement st1 = conn.createStatement();
	        int rs1 = st1.executeUpdate(AddNewCol);
	        for(int i = 0; i < Students.length; i++) {
	        	Class.forName("org.h2.Driver");
		        Connection conn1 = DriverManager.
		            getConnection("jdbc:h2:C:/Management/Classes", "sa", "");
		        int grade = Integer.parseInt(JOptionPane.showInputDialog("What is the score for "+Students[i]+"?"));
				 Class.forName("org.h2.Driver");
				 double percentage = (grade/AssingmentValue)*100;
	        String InsertIntoCol = "UPDATE "+ClassName+" SET "+AssingmentName+" ='"+grade+"' WHERE NAME='"+Students[i]+"';";
	        Statement st = conn1.createStatement();
	        int rs = st.executeUpdate(InsertIntoCol);
	        
		}
	    	csvToArray(SelectedTab);
			Studentpanel.remove(table);
			model.setRowCount(0);
			SetTable();
			model.fireTableDataChanged();
	}
	public void ClassArray() throws Exception {
		 
        Class.forName("org.h2.Driver");

        Connection conn = DriverManager.
            getConnection("jdbc:h2:C:/Management/Classes", "sa", "");
        // add application code here
        String statement = "SHOW TABLES";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(statement);
        ResultSetMetaData rsmd1 = rs.getMetaData();
        int columnsNumber1 = rsmd1.getColumnCount();                     
        
        // Iterate through the data in the result set and display it. 
        Connection conn1 = DriverManager.
                getConnection("jdbc:h2:C:/Management/Classes", "sa", "");
        Statement st1 = conn1.createStatement();
        ResultSet rs1 = st1.executeQuery("SELECT COUNT(*) FROM INFORMATION_SCHEMA.tables WHERE TABLE_TYPE = 'TABLE'");
        int count = 0;
        while (rs1.next())
        {
          count = rs1.getInt(1);
          //count = count+1;
        }
        System.out.println(count+" tables");
        Classes = new String[count];
        int j = 1;
        while (rs.next()) {
        //Print one row          
        for(int i = 1 ; i <= columnsNumber1; i++){
        	if(rs.getString(i).equals("PUBLIC") || rs.getString(i).equals("null") || rs.getString(i).equals(null))
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
          SelectedTab = Classes[0];
            }
       // System.out.print(rs);
        conn.close();
		}


	public void csvToArray(String name)
			  throws Exception {
        Class.forName("org.h2.Driver");

        StudentList.removeAll(StudentList);
        Connection conn = DriverManager.
            getConnection("jdbc:h2:C:/Management/Classes", "sa", "");

        // add application code here
        //System.out.println(Classes+" these are the classes");
        if(Classes != null) {
        
        	if(name == "NULL" || name == "null" || name == null) {
        		System.out.println("we null fam");
        	}
        	else {
        		
        String statement = "SELECT * FROM "+name;
        
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(statement);
        ResultSetMetaData rsmd = rs.getMetaData();
        columnsNumber1 = rsmd.getColumnCount();  
        Assignments = new String[columnsNumber1*2];
        Grades = new int[columnsNumber1*2];
        Connection conn1 = DriverManager.
                getConnection("jdbc:h2:C:/Management/Classes", "sa", "");
        Statement st1 = conn1.createStatement();
        ResultSet rs1 = st1.executeQuery("SELECT COUNT (*) FROM "+name);
        int count = -1;
        while (rs1.next())
        {
          count = rs1.getInt(1);
          //count = count+1;
        }
        
        System.out.println(columnsNumber1+" number of assingments");
        Students = new String[count];
        // Iterate through the data in the result set and display it. 
        int k = 0;
        while (rs.next()) 
	        {
	        //System.out.println("worsk");
	        //Print one row          
	        	String regex = rs.getString("NAME");
	        	Students[k]= regex;
	        	StudentList.add(regex);
	        	for(int i = (k+3); i <= columnsNumber1; i++)
	        	{
	        		System.out.println(rs.getString(i)+" worsk");
	        		Assignments[k] = rsmd.getColumnName(i);
	        		if(rs.getString(i) != null) {
	        		Grades[k] = Integer.parseInt(rs.getString(i));
	        		 System.out.println(Grades[k]+" is the name of the assingmet");
	        		}
	        	}
	        	//StudentList.add(regex);
	              System.out.println(Students[k] +" This name has been added to Student at "+k); //Print one element of a row
	              k++;
	          System.out.println();//Move to the next line to print the next row.           
	
	         }
        	
        }
        }
        
       // System.out.print(rs);
        conn.close();
        
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
		frame.setBounds(100, 100, 881, 647);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[200.00px][180.00px,grow]", "[52.00px][1920.00px,grow][][]"));
		
		
		JLabel lblNewLabel = new JLabel("Classes");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(lblNewLabel, "cell 0 0,alignx center,aligny center");
		
		JLabel lblNewLabel_1 = new JLabel("Students");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		frame.getContentPane().add(lblNewLabel_1, "flowx,cell 1 0,alignx center,growy");

		
		ClassesPanel.setBackground(Color.WHITE);
		ClassesPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		frame.getContentPane().add(ClassesPanel, "cell 0 1,grow");



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
			csvToArray(SelectedTab);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
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
		ClassesPanel.setLayout(new MigLayout("", "[196px]", "[98px][98px][][][][][][][]"));
		list.setForeground(Color.DARK_GRAY);
		list.setBackground(Color.WHITE);
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		ClassesPanel.add(scrollPane, "cell 0 0 1 8,grow");
		JButton NewClassButton = new JButton("ADD NEW CLASS");
		NewClassButton.setBackground(new Color(100, 149, 237));
		NewClassButton.setToolTipText("Click here to add a new class.");
		NewClassButton.setVerticalAlignment(SwingConstants.BOTTOM);
		NewClassButton.addActionListener(new ActionListener() {
			/// what the "new" button does
			public void actionPerformed(ActionEvent arg0) {
				try {
					//newStudents();
					
					EditAddClasses.setVisible(true);
					//set default close operation
					//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//JOptionPane.showInputDialog("Enter Employee name. Ex. \"Faizan\" ");
			}
		});
		ClassesPanel.add(NewClassButton, "cell 0 8,grow");
		
		Studentpanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		frame.getContentPane().add(Studentpanel, "cell 1 1,grow");
		Studentpanel.setLayout(new MigLayout("", "[200.00px][180.00px,grow]", "[52.00px][1920.00px,grow][][][]"));
		Studentpanel.add(table, "cell 0 1 2 1,grow");
		
		Button AddAssignmentBtn = new Button("Add New Assignment");
		AddAssignmentBtn.setForeground(new Color(0, 0, 0));
		AddAssignmentBtn.setBackground(new Color(100, 149, 237));
		Studentpanel.add(AddAssignmentBtn, "cell 1 2,grow");
		AddAssignmentBtn.addActionListener(new ActionListener() {
			/// what the "new" button does
			public void actionPerformed(ActionEvent arg0) {
				try {
					AddNewAssingment(SelectedTab);


				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//JOptionPane.showInputDialog("Enter Employee name. Ex. \"Faizan\" ");
			}
		});
        	
	}
}