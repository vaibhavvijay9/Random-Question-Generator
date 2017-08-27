import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JSpinner;
import java.awt.SystemColor;
import javax.swing.SpinnerNumberModel;

public class RandomQuestionGenerator extends JFrame implements ActionListener 
{
	
	private JPanel contentPane;
	private JTextField textField_1;
	private JLabel lblChooseInputFile;
	private JLabel lblOutputFile;
	private JTextField textField_3;
	private JButton button;
	private JButton btnGenerate;
	private JLabel label;
	
	JFileChooser ipchooser=new JFileChooser();
	JFileChooser opchooser=new JFileChooser();
	FileNameExtensionFilter filter=new FileNameExtensionFilter("Text Files", "txt");
	private JButton button_1;
	private JSpinner spinner;
	
	public static void main(String[] args) 
	{
		RandomQuestionGenerator frame = new RandomQuestionGenerator();
		frame.setVisible(true);
		
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public RandomQuestionGenerator() 
	{
		setResizable(false);
		setTitle("Question Paper Generator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 499, 300);
		setLocationRelativeTo(this);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(169, 80, 215, 23);
		textField_1.setColumns(10);
		
		JLabel lblEnterTheNumber = new JLabel("Enter the number of questions:");
		lblEnterTheNumber.setBounds(42, 133, 181, 14);
		
		lblChooseInputFile = new JLabel("Choose Input File:");
		lblChooseInputFile.setBounds(42, 30, 139, 32);
		
		lblOutputFile = new JLabel("Output File:");
		lblOutputFile.setBounds(42, 81, 139, 20);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setBounds(169, 35, 215, 23);
		textField_3.setColumns(10);
		
		label=new JLabel();
		label.setBounds(10, 240, 392, 20);
		label.setForeground(Color.RED);
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		button = new JButton("...");
		button.setFocusPainted(false);
		button.setBackground(SystemColor.control);
		button.setBounds(394, 35, 45, 23);
		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				ipchooser.addChoosableFileFilter(filter);
				ipchooser.setAcceptAllFileFilterUsed(false);
				int result=ipchooser.showOpenDialog(null);
				File obj=ipchooser.getSelectedFile();
				// how to handle file not found in system exception here
				label.setText("");
				if(result==JFileChooser.APPROVE_OPTION)
				{
					textField_3.setText(obj.getPath());
					label.setText("");
				}
				else if(result==JFileChooser.CANCEL_OPTION)
				{
					textField_3.setText("You Clicked Cancel");
					label.setText("You Clicked Cancel");
					label.setForeground(Color.RED);
				}
				
			}
		});
		
		btnGenerate = new JButton("Generate");
		btnGenerate.setFocusPainted(false);
		btnGenerate.setBackground(SystemColor.control);
		btnGenerate.setBounds(101, 189, 104, 32);
		btnGenerate.setMnemonic(KeyEvent.VK_G);
		btnGenerate.addActionListener(this);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(31, 255, 0, 0);
		contentPane.setLayout(null);
		contentPane.add(lblChooseInputFile);
		contentPane.add(lblOutputFile);
		contentPane.add(textField_1);
		contentPane.add(textField_3);
		contentPane.add(button);
		contentPane.add(lblEnterTheNumber);
		contentPane.add(btnGenerate);
		contentPane.add(label_1);
		contentPane.add(label);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setFocusPainted(false);
		btnReset.setBackground(SystemColor.control);
		btnReset.setMnemonic(KeyEvent.VK_R);
		btnReset.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				label.setText(null);
				textField_1.setText(null);
				spinner.setValue(0);
				textField_3.setText(null);
			}
		});
		btnReset.setBounds(240, 189, 109, 32);
		contentPane.add(btnReset);
		
		button_1 = new JButton("...");
		button_1.setFocusPainted(false);
		button_1.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				opchooser.addChoosableFileFilter(filter);
				opchooser.setAcceptAllFileFilterUsed(false);
				int result1=opchooser.showSaveDialog(null);
				File obj=opchooser.getSelectedFile();
				
				// how to handle file not found in system exception here
				
				if(result1==JFileChooser.APPROVE_OPTION)
				{
					textField_1.setText(obj.getPath()+".txt");
					label.setText(null);
				}
				else if(result1==JFileChooser.CANCEL_OPTION)
				{
					textField_1.setText("You Clicked Cancel");
					label.setText("You Clicked Cancel");
					label.setForeground(Color.RED);
				}
			}
		});
		button_1.setBackground(SystemColor.control);
		button_1.setBounds(394, 80, 45, 23);
		contentPane.add(button_1);
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		spinner.setBounds(256, 130, 45, 20);
		contentPane.add(spinner);
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		FileReader fr = null;					//|
												//> to handle You Clicked Cancel	
		if(textField_3.getText().length()==0 || (!((textField_3.getText().substring(1, 2)).equalsIgnoreCase(":"))))
		{	
			label.setText("*Choose an input file");
			label.setForeground(Color.RED);
		}
		else if(textField_1.getText().length()==0 || (!((textField_1.getText().substring(1, 2)).equalsIgnoreCase(":"))))
		{
			label.setText("*Choose an output file");
			label.setForeground(Color.RED);
		}
		else if(String.valueOf(spinner.getValue()).equalsIgnoreCase("0"))
		{
			if(String.valueOf(spinner.getValue()).substring(0, 1).equalsIgnoreCase("-"))
			{
				label.setText("*Enter valid number of questions.");   //negative user input only
				label.setForeground(Color.RED);
			}
			else
			{
				label.setText("*Enter the number of questions.");
				label.setForeground(Color.RED);
			}
		}
		else
		{
			label.setText("");
			try 
			{
				fr = new FileReader(textField_3.getText());
			}
			
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			}
			
			BufferedReader br=new BufferedReader(fr);
			
			HashMap<Integer,String> hmap=new HashMap<>();
			
			String str="";
			StringBuilder sb=new StringBuilder();
			int j=1;
			
			try {
				while((str=br.readLine())!=null)
				{
				    if(str.startsWith("Q"+(j+1)+"."))
				    {
				    	if(sb.length()!=0)
				        {
				    		hmap.put(j,sb.toString());
				    		sb.delete(0, sb.length());
				            j++;
				        }
				  
				    	if(str.indexOf(".")==2)					//Q1.-Q9.
				    	{
				    		sb.append(str+"\n").replace(1,2, " ");	
				    		sb.deleteCharAt(1);
				    	}
				    	else if(str.indexOf(".")==3)			//Q10.-Q99.
				    	{
				    		sb.append(str+"\n").replace(1,3, " ");
				    		sb.deleteCharAt(1);
				    	}
				    	else if(str.indexOf(".")==4)			//Q100.-Q999.
				    	{
				   			sb.append(str+"\n").replace(1,4, " ");
				   			sb.deleteCharAt(1);
				   		}
				    	else									//Q1000.-Q.9999.
				    	{
				   			sb.append(str+"\n").replace(1,5, " ");
				   			sb.deleteCharAt(1);
				   		}
				    	
				        continue;
				    }
				    sb.append(str+"\n");
				}
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			sb.append(str+"\n");
			hmap.put(j,sb.toString());
			
			String first=(hmap.get(1).replace(hmap.get(1).substring(1,3),"."));
			hmap.put(1,first);
			
			
			Set<Integer> set=hmap.keySet();
			
			FileWriter fw = null;
			try 
			{
				fw = new FileWriter(textField_1.getText());
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
	       
			TreeSet<Integer> obj=new TreeSet<>();
			int n;
			int noq=Integer.parseInt(String.valueOf(spinner.getValue()));
			if(noq>j)
			{
				label.setText("*not sufficient questions in source file!!!");
				label.setForeground(Color.RED);
			}
			else
			{
				while(obj.size()!=noq)				
				{
					n=(int)(1+Math.random()*j);
					obj.add(n);
				}
				
				int i=1;
				for(int num:obj)
				{	
					StringBuffer sb1=new StringBuffer(hmap.get(num));
					
					sb1.insert(1, i);
					
					try 
					{
						fw.write(String.valueOf(sb1));
						
					}
					catch (IOException e) 
					{
						e.printStackTrace();
					}
					i++;
				}	
				
				label.setText("File Created....");
				label.setForeground(Color.BLACK);
				try 
				{
					br.close();
					fr.close();
					fw.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
	   }	
	}
}