package gradeBook;
//all of the imports needed for the program
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.BorderLayout;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
import javax.swing.ListSelectionModel;
import java.awt.Dimension;
import java.awt.Cursor;
import java.awt.ComponentOrientation;



public class Main {

	private JFrame frame;
	EditAddClasses EditAddClasses = new EditAddClasses();
	EditAddAssignment EditAddAssignment = new EditAddAssignment();
	 //stores names of classes used to get how many classes are and the name of the files they are stored in
	  ArrayList<String> ClassList = new ArrayList<String>();
	  static ArrayList<String> StudentList = new ArrayList<String>();
	  static String [] Classes;
	  static String [] Students;
	  static String [] Assignments;
	  static double[] Grades;
	  JPanel ClassesPanel = 	new JPanel();
	  static DefaultTableModel model = new DefaultTableModel();
	  static DefaultListModel<String> dlm = new DefaultListModel<String>();
	  JPanel Studentpanel = new JPanel();
	  static JTable table = new JTable(model);
	  static JList<String> list = new JList<String>(dlm);
	  static String SelectedTab;
	  String NewClassName;
	  static int ColumnCount;
	  static JScrollPane scrollPane_table = new JScrollPane();
	  private final JButton DeleteClassButton = new JButton("Delete Selected Class");
	  
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
	

/*
 * Removes any list that might have already been creaded
 * Replaces it with a new one grabbed from the Classes Array
 * Also updates the table when one is selected
 */
	public void SetList() {
		//Set model
		list.setModel(dlm);
		//Clean-Up before showing the list
		ClassesPanel.remove(list);
		dlm.removeAllElements();
		//Makes sure there are classes that can be added
		if(Classes != null)
		{
			//Adds classes to list model
		for(int i = 0; i < Classes.length; i++) {
		dlm.addElement(Classes[i]);
		}
		
		}else {new print("we null"); }
		//dlm.addElement("none");
	   //listens for new item selected
		list.addListSelectionListener(new ListSelectionListener() {
			
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                try {
					if (!arg0.getValueIsAdjusting()) {
						//Makes sure something is selected 
						if(list.getSelectedValue() != null) {
						SelectedTab = list.getSelectedValue().toString();
						}
						else {
							new print("error");
						}
						try {
							//runs the update table method
							UpdateTable();
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
	/*
	 * Creates the Jtable that displays the Name of the students and their grades
	 */
	public static  void SetTable() {
		//checks if there is at leat a name in the Database
		if(ColumnCount>1) {
			//stest the number of columns in the Jtable
        Object[] columnsName = new Object[ColumnCount+2];
        //sets columns 1 and 2 to id and name
        columnsName[0] = "ID";
        columnsName[1] = "Name";
        //grabs data for other column names and adds it to the array
        for(int i = 1; i < ColumnCount-1; i++) {
        	if(Assignments[i] != null) {
        	columnsName[i+1] = Assignments[i].toString();
        	new print(Assignments[i].toString()+ " is the name of the column ");
        	}
        	else {
        		//columnsName[i] = "Null";
        	}
        		
        }
        //sets the last 2 columns
        columnsName[ColumnCount] = "total";
        columnsName[ColumnCount+1] = "Grade";
        //adds the column data to the model
        model.setColumnIdentifiers(columnsName);
        //sets number of rows for jtable
        Object[] rowData = new Object[ColumnCount+2];
        //insets data into rowmodel
        if(Students != null) {
        for(int i = 0; i < StudentList.size(); i++){
            try {
					SetGrades(SelectedTab,Students[i]);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            rowData[1] = Students[i];
            for( int j = 1; j < ColumnCount; j++)
            {
            	
            	new print(Arrays.toString(Grades)+" grades");
            	rowData[j+1] = Grades[j-1];
            }
            double total = 0;
            for(int k = 0; k < Grades.length; k++)
            {
            	total += Grades[k];
            	
            	new print(total+"is the toat");
            	
            	
            }
            rowData[ColumnCount] = Math.round(total* 100.0) / 100.0;
            
            try {
				GetTotal(SelectedTab);
				double pos = 0;
	            for(int k = 0; k < Grades.length; k++)
	            {
	            	pos += Grades[k];
	            	new print(pos+"is the class total");
	            	
	            	
	            }
	            
	            String rounded = String.format("%.2f", (((double)total/pos)*100));
	            rowData[ColumnCount+1] = rounded+"%";
	            rowData[0] = i+1;
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
              model.addRow(rowData);
              
        }
        }
        /*
         *SET MIN SIZE FOR TABLE COLUMNS 
         *
        *for(int i = 0; i < ColumnCount-1; i++)
        {
        	table.getColumnModel().getColumn(i).setMinWidth(70);
        }*/
        scrollPane_table.add(table);
        
        AbstractAction action = new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                TableCellListener tcl = (TableCellListener)e.getSource();
                
                
                try {
					Class.forName("org.h2.Driver");
					Connection conn = DriverManager.
                    getConnection("jdbc:h2:C:/Management/Classes", "sa", "");
                // add application code here
                String statement = "UPDATE \""+SelectedTab+"\" SET \""+tcl.getColumnName()+"\" = \'"+tcl.getNewValue()+"\' WHERE NAME = \'"+tcl.getFirstName()+"\';";
                Statement st = conn.createStatement();
                int rs = st.executeUpdate(statement);
                new print("\""+tcl.getColumnName() +"\" for \""+ tcl.getFirstName()+"\" has been changed to \""+tcl.getNewValue()+"\"");
                UpdateTable();
                } catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

                
            }
        };
        TableCellListener tcl = new TableCellListener(table, action);
	  }
	}
	

	
	public static void SetGrades(String name, String firstName) throws ClassNotFoundException, SQLException
	{
		Class.forName("org.h2.Driver");

		
        Connection conn = DriverManager.
            getConnection("jdbc:h2:C:/Management/Classes", "sa", "");

        
        //new print(Classes+" these are the classes");
        if(Classes != null) {
        
        	if(name == "NULL" || name == "null" || name == null) {
        		new print("we null fam");
        	}
        	else {
        		
        String statement = "SELECT * FROM "+name+" WHERE NAME = \'"+firstName+"\';";
        
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(statement);
        ResultSetMetaData rsmd = rs.getMetaData();
        ColumnCount = rsmd.getColumnCount();  
        Grades = new double[ColumnCount];

        
        // Iterate through the data in the result set and display it. 
        
        while (rs.next()) 
	        {
	        	for(int i = 3, k = 0; i <= ColumnCount; i++, k++)
	        	{
	        		
	        		if(rs.getString(i) != null) {
	        		Grades[k] = Double.parseDouble(rs.getString(i));
	        		 //new print(Grades[k]+" is the grade of the assingmet");
	        		}
	        	}
	        	
	             
	              
	          new print("");           
	
	         }
       // new print(Arrays.toString(Grades)+" grades set");
        	
        }
        }
        
       
        conn.close();
		
	}
	public static void UpdateTable() throws Exception
	{
		if(list.getSelectedValue() != null) {
			SelectedTab = list.getSelectedValue().toString();
			}
			else {
				SelectedTab = Classes[0];
			}
		studentsArray(SelectedTab);
		//SetList();
		//scrollPane_table.remove(table);
		model.setRowCount(0);
		//model = new DefaultTableModel();
		table = new JTable();	
		SetTable();
		table.setModel(model);
		
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
        int ColumnCount = rsmd1.getColumnCount();                     
        
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
        new print(count+" tables");
        Classes = new String[count];
        int j = 1;
        while (rs.next()) {
        //Print one row          
        for(int i = 1 ; i <= ColumnCount; i++){
        	if(rs.getString(i).equals("PUBLIC") || rs.getString(i).equals("null") || rs.getString(i).equals(null))
        	{}
        	else {
        	Classes[j-1] =rs.getString(i);
        	new print(Classes[j-1] + " This has been added to the classes array at "+ (j-1));
              //new print(rs.getString(i)); //Print one element of a row
              new print(j);
              j++;
        	}
        }
          new print();//Move to the next line to print the next row.           
          SelectedTab = Classes[0];
            }
       // new print(rs);
        conn.close();
		}


	public static void studentsArray(String name)
			  throws Exception {
        Class.forName("org.h2.Driver");

        StudentList.removeAll(StudentList);
        Connection conn = DriverManager.
            getConnection("jdbc:h2:C:/Management/Classes", "sa", "");

        // add application code here
        //new print(Classes+" these are the classes");
        if(Classes != null) {
        
        	if(name == "NULL" || name == "null" || name == null) {
        		new print("we null fam");
        	}
        	else {
        		
        String statement = "SELECT * FROM "+name/*+" WHERE ID NOT = \'999\'"*/;
        
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(statement);
        ResultSetMetaData rsmd = rs.getMetaData();
        ColumnCount = rsmd.getColumnCount();  
        Assignments = new String[ColumnCount];
        
        Connection conn1 = DriverManager.
                getConnection("jdbc:h2:C:/Management/Classes", "sa", "");
        Statement st1 = conn1.createStatement();
        ResultSet rs1 = st1.executeQuery("SELECT COUNT (*) FROM "+name/*+" WHERE ID NOT = \'999\'"*/);
        int count = -1;
        while (rs1.next())
        {
          count = rs1.getInt(1);
          //count = count+1;
        }
        
        new print(ColumnCount+" number of assingments");
        Students = new String[count];
        // Iterate through the data in the result set and display it. 
        int k = 0;
        while (rs.next()) 
	        {
	        //new print("worsk");
	        //Print one row 
        	
	        	String regex = rs.getString("NAME");
	        	Students[k]= regex;
	        	StudentList.add(regex);
	        	for(int i = (k+3); i <= ColumnCount; i++)
	        	{
	        		new print(rsmd.getColumnName(i)+" worsk");
	        		Assignments[i-2] = rsmd.getColumnName(i);
	        	}
	        	//StudentList.add(regex);
	              new print(Students[k] +" This name has been added to Student at "+k); //Print one element of a row
	              k++;
	          new print();//Move to the next line to print the next row.           
	
	         }
        	
        }
        }
        
       // new print(rs);
        conn.close();
        
    }


	public static void GetTotal(String name) throws SQLException, ClassNotFoundException
	{
Class.forName("org.h2.Driver");

		
        Connection conn = DriverManager.
            getConnection("jdbc:h2:C:/Management/Classes", "sa", "");

        
        //new print(Classes+" these are the classes");
        if(Classes != null) {
        
        	if(name == "NULL" || name == "null" || name == null) {
        		new print("we null fam");
        	}
        	else {
        		
        String statement = "SELECT * FROM "+name+" WHERE ID = \'999\';";
        
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(statement);
        ResultSetMetaData rsmd = rs.getMetaData();
        ColumnCount = rsmd.getColumnCount();  
        Grades = new double[ColumnCount];

        
        // Iterate through the data in the result set and display it. 
        
        while (rs.next()) 
	        {
	        	for(int i = 3, k = 0; i <= ColumnCount; i++, k++)
	        	{
	        		
	        		if(rs.getString(i) != null) {
	        		Grades[k] = Double.parseDouble(rs.getString(i));
	        		
	        		}
	        	}
	        	
	             
	              
	          new print();           
	
	         }
        	
        }
        }
        
       
        conn.close();
		
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
		frame.setMinimumSize(new Dimension(580, 480));
		frame.setBounds(100, 100, 881, 647);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[200.00px][180.00px,grow]", "[52.00px][1920.00px,grow][][]"));
		
		
		JLabel lblNewLabel = new JLabel("Classes");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(lblNewLabel, "cell 0 0,alignx center,aligny center");
		
		JLabel lblNewLabel_1 = new JLabel("Students");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		frame.getContentPane().add(lblNewLabel_1, "flowx,cell 1 0,alignx center,growy");



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
			studentsArray(SelectedTab);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
					for (int i = 0; i < Classes.length; i++)
					{
						new print(Classes[i] + " Name of class "+ i);
					}
		SetList();
		
		SetTable();
		}
		else{
			JOptionPane.showMessageDialog(frame, "Please Create a new class to start");
		}
				ClassesPanel.setBackground(Color.WHITE);
				ClassesPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
				frame.getContentPane().add(ClassesPanel, "cell 0 1,grow");
				ClassesPanel.setLayout(new MigLayout("", "[grow][200.00px][180.00px,grow]", "[grow][98px][98px][][][][][][][][][][][][][]"));
				list.setForeground(Color.DARK_GRAY);
				list.setBackground(Color.WHITE);
				JScrollPane scrollPane = new JScrollPane(list);
				scrollPane.setPreferredSize(new Dimension(0, 0));
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				ClassesPanel.add(scrollPane, "cell 0 0 3 13,grow");
				JButton NewClassButton = new JButton("ADD NEW CLASS");
				ClassesPanel.add(NewClassButton, "cell 0 13 2 1,grow");
				NewClassButton.setBackground(new Color(100, 149, 237));
				NewClassButton.setToolTipText("Click here to add a new class.");
				NewClassButton.setVerticalAlignment(SwingConstants.BOTTOM);
				NewClassButton.addActionListener(new ActionListener() {
					/// what the "new" button does
					public void actionPerformed(ActionEvent arg0) {
						try {
							EditAddClasses.setVisible(true);

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
				DeleteClassButton.setVerticalAlignment(SwingConstants.BOTTOM);
				DeleteClassButton.setToolTipText("Click here to delete selected class.");
				DeleteClassButton.setBackground(new Color(100, 149, 237));
				
				ClassesPanel.add(DeleteClassButton, "cell 0 14 2 1");
				DeleteClassButton.addActionListener(new ActionListener() {
					/// what the "new" button does
					public void actionPerformed(ActionEvent arg0) {
						String message = "Are you sure you want to delete "+SelectedTab;
						int Confirm = JOptionPane.showConfirmDialog(null, message, null, JOptionPane.YES_NO_OPTION);
				        if (Confirm == JOptionPane.YES_OPTION) {
				        	try {
								Class.forName("org.h2.Driver");

						        Connection conn = DriverManager.
						            getConnection("jdbc:h2:C:/Management/Classes", "sa", "");
						        // add application code here
						        String statement = "DROP TABLE "+SelectedTab;
						        Statement st = conn.createStatement();
						        boolean rs = st.execute(statement);
						        dlm.removeElement(SelectedTab);
						        String ran = null;
						        if(Classes[0] != SelectedTab) {
						         ran = Classes[0];
						         list.setSelectedValue(ran, true);
						        }
						        else {
						        	if(Classes.length > 1) {
								         ran = Classes[1];
								         list.setSelectedValue(ran, true);
								        }
						        	else {
						        		JOptionPane.showInputDialog("Please add a class to start");
						        		scrollPane_table.remove(table);
						        		scrollPane_table.repaint();
						        		model.setRowCount(0);
						        		model.setColumnCount(0);
						        		
						        	}
						         
						        }

						        new print("done ...");
							} 
							 catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
				            
				          }
				          else {
				             JOptionPane.showMessageDialog(null, "GOODBYE");
				             System.exit(0);
				          }
					}
				});
		
		Studentpanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		frame.getContentPane().add(Studentpanel, "cell 1 1,grow");
		Studentpanel.setLayout(new MigLayout("", "[grow][200.00px][180.00px,grow]", "[grow][75.00px][1920.00px,grow][][][]"));
		
		
		Studentpanel.add(scrollPane_table, "cell 0 0 3 3,grow");
		table.setAutoCreateRowSorter(true);
		table.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		table.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		table.setMinimumSize(new Dimension(720, 400));
		table.setMaximumSize(new Dimension(99999, 99999));
		scrollPane_table.setViewportView(table);
		table.setCellSelectionEnabled(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// table value changed

		
		Button AddAssignmentBtn = new Button("Add Assignmenssst");
		AddAssignmentBtn.setForeground(new Color(0, 0, 0));
		AddAssignmentBtn.setBackground(new Color(100, 149, 237));
		Studentpanel.add(AddAssignmentBtn, "cell 2 3,grow");
		AddAssignmentBtn.addActionListener(new ActionListener() {
			/// what the "new" button does
			public void actionPerformed(ActionEvent arg0) {
				try {
					//EditAddAssignment.setVisible(true);
					AddAssignment.frame.setVisible(true);
					list.setSelectedValue(SelectedTab, true);

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
        	
	}
}