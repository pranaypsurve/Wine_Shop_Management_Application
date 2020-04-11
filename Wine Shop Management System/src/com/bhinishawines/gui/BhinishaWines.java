package com.bhinishawines.gui;


//--IMPORTS SECTION--


import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ItemEvent;
import javax.swing.DropMode;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Cursor;

public class BhinishaWines 
{
	//-------------------CLASS VARIABLES----------------------
	private JFrame frmBhinishaWines;
	private JTextField txtcname;
	private JTextField txtcmob;
	private JTextField txtq;
	private JTextField txtamt;
	private JTextField txtsingleprice;
	private JTable tbldata;
	private JButton btnprint;
	DefaultTableModel model=null;
	String str;
	JComboBox <String> combolist;
	private JLabel lblshowtot;
	private JButton btntotal;
	
	
	//--------------------------MAIN CLASS----------------------------------
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BhinishaWines window = new BhinishaWines();
					window.frmBhinishaWines.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//--------------------------MAIN CLASS ENDS----------------------------------
	
	//----OWN METHODS----
	public String getName()
	{
		return txtcname.getText(); 
	}
	
	public int brandcheck(String name)
	{
		
		if(name == "RED WINE")
		{
			return 1175;
		}else if(name == "YORK ARROS")
		{
			return 1450;	
		}else if(name == "FRATELLI SETTE")
		{
			return 890;
		}else if(name == "KINGFISHER S")
		{
			return 90;
		}else if(name == "KINGFISHER L")
		{
			return 170;
		}else if(name == "SULA RASA")
		{
			return 1250;
		}else if(name == "TUBURG")
		{
			return 160;
		}else if(name == "MYRA MISFIT")
		{
			return 1500;
		}
		 return 0;
	}
    public void resetbtn()
    {
    	txtcname.setText("");
    	txtcmob.setText("");
    	txtq.setText("");
    	txtamt.setText("");
    	txtsingleprice.setText("");
    	btnprint.setEnabled(false);
    	lblshowtot.setText("");
    	model.setRowCount(0);
    	
    	
    }
    
    public void validatechk()
    {
    	if(txtcname.getText().trim().isEmpty())
    	{
    		JOptionPane.showMessageDialog(null, "Fill Customer Name");
    		txtcname.grabFocus();
    		return ;
    	}
    	if(txtcmob.getText().trim().isEmpty())
    	{
    		JOptionPane.showMessageDialog(null, "Mobile Number!");
    		txtcmob.grabFocus();
    		return ;
    	}
    	int bb = combolist.getSelectedIndex();
		if(txtq.getText().isEmpty())
    	{
			if(bb <= 0)
			{
				JOptionPane.showMessageDialog(btntotal,"Select Brand");
				combolist.grabFocus();
				return ;
			}else if(bb > 0)
			{
				txtq.setText("1");
			}else
			{
				JOptionPane.showMessageDialog(btntotal,"Select Brand");
			}
    	}else if (txtq.getText().isEmpty() == false)
    	{
    		if(bb <= 0)
			{
				JOptionPane.showMessageDialog(btntotal,"Select Brand");
				combolist.grabFocus();
				return ;
			}
    	}
		
    }
    
    public void showd()
	{
		model =(DefaultTableModel)tbldata.getModel();	
		model.insertRow(tbldata.getRowCount(),new Object[] {
				txtcname.getText(),
				str = (String) combolist.getSelectedItem(),
				txtsingleprice.getText(),
				txtq.getText(),
				txtamt.getText()
		});
	}	
    
    public void printdata(String name,String mob,String brand,String qt,String famt) throws IOException
    {
    	File f=null;
		FileWriter fw  = null;
		PrintWriter pr = null;
		try
		{
			f=new File("E:\\Sell Details.txt");
			fw=new FileWriter(f,true);
			 pr=new PrintWriter(fw);
			   long chk = f.length();
			   if(chk == 0)
			    {
			    	pr.println("\t----Developed By Pranay----\t");
			    }
			    pr.println("Customer Name:"+name);
				pr.println("Mobile Number:"+mob);
				pr.println("Brand:"+brand);
				pr.println("Qty:"+qt);
				pr.println("Total Amount Paid:"+famt);
				pr.println("-------------------------------------");
			
		}catch(Exception e)
		{
			
		}
		finally
		{
			pr.flush();
			fw.close();
		}
		
    }
	
	//----ENDS OF OWN METHODS----
	public BhinishaWines() 
	{
		initialize();
		btnprint.setEnabled(false);
		
		lblshowtot = new JLabel("");
		lblshowtot.setBounds(612, 297, 59, 26);
		frmBhinishaWines.getContentPane().add(lblshowtot);
		
		JButton btngetfamt = new JButton("Final Amount");
		btngetfamt.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 14));
		btngetfamt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				// total amt
				int max= 1;
				int c = 0;
				int st = 0;
//				System.out.println(tbldata.getRowCount());
				
				if(c == tbldata.getRowCount())
				{
					JOptionPane.showMessageDialog(btngetfamt, "Empty Records");
				}
				while(c < tbldata.getRowCount())
				{	
					if(tbldata.getRowCount()<= max)
					{
						int hj =Integer.parseInt((String) tbldata.getValueAt(c, 4));
						st = st + hj;
						c++;
					}
					max = tbldata.getRowCount();
					
				}
				lblshowtot.setText(String.valueOf(st));
			}
		});
		btngetfamt.setBounds(475, 296, 124, 30);
		frmBhinishaWines.getContentPane().add(btngetfamt);
		
		JLabel lbltable = new JLabel("Order Items");
		lbltable.setForeground(new Color(205, 92, 92));
		lbltable.setFont(new Font("Castellar", Font.BOLD, 17));
		lbltable.setHorizontalAlignment(SwingConstants.CENTER);
		lbltable.setBounds(397, 11, 181, 29);
		frmBhinishaWines.getContentPane().add(lbltable);
		
		JLabel lbltop = new JLabel("Order Details");
		lbltop.setForeground(new Color(205, 92, 92));
		lbltop.setHorizontalAlignment(SwingConstants.CENTER);
		lbltop.setFont(new Font("Castellar", Font.BOLD, 20));
		lbltop.setBounds(10, 11, 217, 41);
		frmBhinishaWines.getContentPane().add(lbltop);
		
		JLabel lblmovtxt = new JLabel("* Any Problem with This Application Contact System Administrator *");
		lblmovtxt.setHorizontalAlignment(SwingConstants.CENTER);
		lblmovtxt.setFont(new Font("Perpetua Titling MT", Font.BOLD, 13));
		lblmovtxt.setForeground(new Color(255, 0, 0));
		lblmovtxt.setBounds(10, 350, 674, 21);
		frmBhinishaWines.getContentPane().add(lblmovtxt);;
	}
	//----- DEFAULT CONSTRUCTOR WITHOUT ARGUMENT -----
	private void initialize() 
	{
		//----FRAME CREATED-----
		frmBhinishaWines = new JFrame();
		frmBhinishaWines.getContentPane().setFont(new Font("Century", Font.BOLD, 11));
		frmBhinishaWines.getContentPane().setBackground(new Color(255, 255, 224));
		frmBhinishaWines.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Pranay Surve\\Documents\\Eclipse Workspace\\Wine Shop Management System\\winepic.png"));
		frmBhinishaWines.setResizable(false);
		frmBhinishaWines.setFont(new Font("Dialog", Font.BOLD, 12));
		frmBhinishaWines.setTitle("Vijay Wine Shop");
		frmBhinishaWines.setBounds(350, 180, 700, 411);
		frmBhinishaWines.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBhinishaWines.getContentPane().setLayout(null);
		
		//lbl CUSTOMER NAME
		JLabel lblcname = new JLabel("CUSTOMER NAME");
		lblcname.setFont(new Font("Century", Font.BOLD, 12));
		lblcname.setBounds(10, 62, 134, 21);
		frmBhinishaWines.getContentPane().add(lblcname);
		
		//lbl CUSTOMER MOBILE NO
		JLabel lblcmob = new JLabel("MOBILE NO.");
		lblcmob.setFont(new Font("Century", Font.BOLD, 12));
		lblcmob.setBounds(10, 94, 134, 21);
		frmBhinishaWines.getContentPane().add(lblcmob);
		
		//COMBOBOX LIST DROP DOWN BRANDS
		combolist = new JComboBox<String>();
		combolist.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) 
			{
				//-- item changed --
				String bname = (String) combolist.getSelectedItem();
				int price = brandcheck(bname);
				txtsingleprice.setText(String.valueOf(price));
				
			}
		});
		combolist.setModel(new DefaultComboBoxModel<String>(new String[] {"SELECT BRAND", "SULA RASA", "KINGFISHER S", "KINGFISHER L", "TUBURG", "MYRA MISFIT", "FRATELLI SETTE", "YORK ARROS", "RED WINE"}));
		combolist.setBounds(151, 123, 124, 23);
		frmBhinishaWines.getContentPane().add(combolist);
		
		JLabel lblbrand = new JLabel("BRAND");
		lblbrand.setFont(new Font("Century", Font.BOLD, 12));
		lblbrand.setBounds(10, 122, 123, 20);
		frmBhinishaWines.getContentPane().add(lblbrand);
		
		//TEXT AREA -- 
		txtcname = new JTextField();
		txtcname.setDropMode(DropMode.INSERT);
		txtcname.setBounds(151, 63, 124, 20);
		frmBhinishaWines.getContentPane().add(txtcname);
		txtcname.setColumns(10);
		
		txtcmob = new JTextField();
		txtcmob.setBounds(151, 95, 111, 20);
		frmBhinishaWines.getContentPane().add(txtcmob);
		txtcmob.setColumns(10);
		
		//lbl brand quantity
		JLabel lblq = new JLabel("QUANTITY");
		lblq.setFont(new Font("Century", Font.BOLD, 12));
		lblq.setBounds(10, 186, 134, 21);
		frmBhinishaWines.getContentPane().add(lblq);
		
		//TEXT AREA --> QUANTITY 
		txtq = new JTextField();
		txtq.setBounds(152, 186, 46, 20);
		frmBhinishaWines.getContentPane().add(txtq);
		txtq.setColumns(10);
		
		JLabel lblamt = new JLabel("AMOUNT");
		lblamt.setFont(new Font("Century", Font.BOLD, 12));
		lblamt.setBounds(10, 218, 111, 21);
		frmBhinishaWines.getContentPane().add(lblamt);
		
		//TEXT AREA NOT EDITABLE --> FINAL AMT DISPLAYED
		txtamt = new JTextField();
		txtamt.setEditable(false);
		txtamt.setBounds(151, 217, 59, 20);
		frmBhinishaWines.getContentPane().add(txtamt);
		txtamt.setColumns(10);
		
		JButton btnreset = new JButton("RESET");
		btnreset.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 14));
		btnreset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				// - - - - RESET BUTTON - - - -
				resetbtn();
				JOptionPane.showMessageDialog(btnreset, "Reset Succesfully");
			}
		});
		btnreset.setBounds(151, 262, 85, 23);
		frmBhinishaWines.getContentPane().add(btnreset);
		
		btnprint = new JButton("PRINT");
		btnprint.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 14));
		btnprint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//Print code into file
//				validatechk();
				String name = txtcname.getText();
				String mobi = txtcmob.getText();
				String b = (String) combolist.getSelectedItem();
				String q = txtq.getText();
				String f = txtamt.getText();
				try
				{
						printdata(name,mobi,b,q,f);
						JOptionPane.showMessageDialog(btnprint, "Print Sucessfully");
				} catch (IOException e1) 
				{
						e1.printStackTrace();
						JOptionPane.showMessageDialog(btnprint, "Ops Somethings Went Wrong Contact To System Admin");
				}
			}
		});
		btnprint.setBounds(91, 296, 86, 23);
		frmBhinishaWines.getContentPane().add(btnprint);
		
		btntotal = new JButton("TOTAL");
		btntotal.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 14));
		btntotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//TOTAL BUTTON CLICKED
				validatechk();
				int num =Integer.parseInt(txtq.getText());
				int amtf = Integer.parseInt(txtsingleprice.getText());				
				int finalamount = num * amtf;
				txtamt.setText(String.valueOf(finalamount));
	            btnprint.setEnabled(true);
				showd();
			}
		});
		btntotal.setBounds(22, 262, 95, 23);
		frmBhinishaWines.getContentPane().add(btntotal);
		
		JLabel lblNewLabel = new JLabel("SINGLE PRICE");
		lblNewLabel.setFont(new Font("Century", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 154, 131, 21);
		frmBhinishaWines.getContentPane().add(lblNewLabel);
		
		txtsingleprice = new JTextField();
		txtsingleprice.setEditable(false);
		txtsingleprice.setBounds(151, 154, 59, 20);
		frmBhinishaWines.getContentPane().add(txtsingleprice);
		txtsingleprice.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(285, 42, 399, 241);
		frmBhinishaWines.getContentPane().add(scrollPane);
		
		tbldata = new JTable();
		tbldata.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tbldata.setFont(new Font("Segoe UI Semilight", Font.BOLD, 13));
		tbldata.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Brand", "Price", "Qty", "Total"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false, true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tbldata.getColumnModel().getColumn(0).setResizable(false);
		tbldata.getColumnModel().getColumn(1).setResizable(false);
		tbldata.getColumnModel().getColumn(2).setResizable(false);
		tbldata.getColumnModel().getColumn(3).setResizable(false);
		tbldata.getColumnModel().getColumn(4).setResizable(false);
		scrollPane.setViewportView(tbldata);
		
		JButton btndel = new JButton("Remove Records");
		btndel.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 14));
		btndel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(tbldata.getSelectedRow() == -1)
				{
					JOptionPane.showMessageDialog(btndel, "Select Table Row");
				}
				else
				{
					model.removeRow(tbldata.getSelectedRow());
				}
			}
		});
		btndel.setBounds(295, 296, 146, 30);
		frmBhinishaWines.getContentPane().add(btndel);
	}
}
