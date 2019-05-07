import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.Font;

public class MovieInterface {

	private JTable table;
	private String[] columnNames = { "Name", "Genre", "Year", "Actors" };
	private Object[][] data = new Object[15][4];
	private List<Movie> list = new ArrayList<Movie>();
	
	private JFrame frame;
	private JTextField addTitle;
	private JTextField addGenre;
	private JTextField addYear;
	private JTextField addActors;
	private JTextField searchTitle;
	private JTextField searchGenre;
	private JTextField searchYear;
	private JTextField searchActors;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MovieInterface window = new MovieInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MovieInterface() {
		initialize();
	}

	private void initialize() {
		setDataDefault();

		list = Movies.loadFromDb();
		frame = new JFrame();
		frame.setBounds(100, 100, 664, 561);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().setLayout(null);
		
		addTitle = new JTextField();
		addTitle.setBounds(23, 52, 111, 26);
		frame.getContentPane().add(addTitle);
		addTitle.setColumns(10);
		
		addGenre = new JTextField();
		addGenre.setColumns(10);
		addGenre.setBounds(144, 52, 111, 26);
		frame.getContentPane().add(addGenre);
		
		addYear = new JTextField();
		addYear.setColumns(10);
		addYear.setBounds(266, 52, 111, 26);
		frame.getContentPane().add(addYear);
		
		addActors = new JTextField();
		addActors.setColumns(10);
		addActors.setBounds(387, 52, 111, 26);
		frame.getContentPane().add(addActors);
		
		JLabel lbl1 = new JLabel("Title");
		lbl1.setBounds(50, 27, 46, 14);
		frame.getContentPane().add(lbl1);
		
		JLabel lbl2 = new JLabel("Genre");
		lbl2.setBounds(175, 27, 46, 14);
		frame.getContentPane().add(lbl2);
		
		JLabel lbl3 = new JLabel("Year");
		lbl3.setBounds(296, 27, 46, 14);
		frame.getContentPane().add(lbl3);
		
		JLabel lbl4 = new JLabel("Actors");
		lbl4.setBounds(415, 27, 46, 14);
		frame.getContentPane().add(lbl4);
		
		searchTitle = new JTextField();
		searchTitle.setColumns(10);
		searchTitle.setBounds(23, 156, 111, 26);
		frame.getContentPane().add(searchTitle);
		
		searchGenre = new JTextField();
		searchGenre.setColumns(10);
		searchGenre.setBounds(144, 156, 111, 26);
		frame.getContentPane().add(searchGenre);
		
		searchYear = new JTextField();
		searchYear.setColumns(10);
		searchYear.setBounds(266, 156, 111, 26);
		frame.getContentPane().add(searchYear);
		
		searchActors = new JTextField();
		searchActors.setColumns(10);
		searchActors.setBounds(387, 156, 111, 26);
		frame.getContentPane().add(searchActors);
		
		JLabel lbl5 = new JLabel("Title");
		lbl5.setBounds(50, 131, 46, 14);
		frame.getContentPane().add(lbl5);
		
		JLabel lbl6 = new JLabel("Genre");
		lbl6.setBounds(175, 131, 46, 14);
		frame.getContentPane().add(lbl6);
		
		JLabel lbl7 = new JLabel("Year");
		lbl7.setBounds(285, 131, 46, 14);
		frame.getContentPane().add(lbl7);
		
		JLabel lbl8 = new JLabel("Actors");
		lbl8.setBounds(415, 131, 46, 14);
		frame.getContentPane().add(lbl8);
//
		TableModel model = new DefaultTableModel(data, columnNames);
		table = new JTable(model);
		table.setBounds(38, 247, 572, 234);
//
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(38, 247, 572, 234);
		table.setFillsViewportHeight(true);
		frame.getContentPane().add(scroll);
		
		JButton insert = new JButton("Insert");
		insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (addTitle.getText()!=null && addGenre.getText()!=null && addYear.getText()!=null && addActors.getText()!=null) {
					Movies.insertIntoDb(addTitle.getText(),addGenre.getText(),addYear.getText(),addActors.getText());
					JOptionPane.showMessageDialog(null, "Success!");
				}
				else {
					JOptionPane.showMessageDialog(null, "All fields must have a value!");
				}
			}
		});
		insert.setBounds(508, 52, 111, 26);
		frame.getContentPane().add(insert);
		
		JButton search = new JButton("Search");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StandardCharsets.UTF_8.name();
				try{

					clearData();
					if (searchTitle.getText()!=null && searchGenre.getText()!=null && searchYear.getText()!=null && searchActors.getText()!=null) {
						for (int i = 0; i < list.size(); i++) {
							int j=0;
							if(searchTitle.getText().equals(list.get(i).getTitle())||
							   searchGenre.getText().equals(list.get(i).getGenre())||
							   searchYear.getText().equals(list.get(i).getYear())||
							   searchActors.getText().equals(list.get(i).getActors())) {
								
								table.getModel().setValueAt(list.get(i).getTitle(), j, 0);
								table.getModel().setValueAt(list.get(i).getGenre(), j, 1);
								table.getModel().setValueAt(list.get(i).getYear(), j, 2);
								table.getModel().setValueAt(list.get(i).getActors(), j, 3);
								j++;
							}
							
						}
						
					}
					else {
						JOptionPane.showMessageDialog(null, "All fields must have a value!");
					}
				} catch(IndexOutOfBoundsException e1) {
					
					e1.printStackTrace();
					
				}
			}
		});
		search.setBounds(508, 156, 111, 26);
		frame.getContentPane().add(search);
	}
	
	public void setDataDefault() {
		for(int i = 0; i<15; i++) {
			data[i][0]=null;
			data[i][1]=null;
			data[i][2]=null;
			data[i][3]=null;
		}
	}
	
	public void clearData() {
		for (int i = 0; i < 15; i++) {
			table.getModel().setValueAt("", i, 0);
			table.getModel().setValueAt("", i, 1);
			table.getModel().setValueAt("", i, 2);
			table.getModel().setValueAt("", i, 3);
		}
	}
}
