//import javax.swing.*;
//import java.awt.*;



import java.awt.BorderLayout;
import java.awt.Color;

import static java.util.Collections.reverseOrder;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.*;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;

import javax.swing.ScrollPaneConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


public class Result extends JFrame {

    private JPanel contentPane;
	 private JTable resultsTabel;
	 private JLabel lblNewLabel;
	 private JLabel lblNewLabel_1;
	 private JButton btnNewButton;
	 String filter1 = "Company Filter";

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {;
                    Result frame = new Result("english", "30 Oct 2012 ", "bar"); // TEST CASE
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

     /**
     *
     * @param lng
     * @param dte
     * @param reqUser
     */
    public Result(String lng, String dte, String reqUser) {
    
    
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1031, 673);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(88, 24, 69));
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

        Search score = new Search(lng, dte, reqUser);
        System.out.println("Total Score");
        System.out.println(score.FinalWeight);
        
        JScrollPane scrollPane = new JScrollPane(resultsTabel);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(32, 77, 943, 481);
		contentPane.add(scrollPane);
		
		resultsTabel = new JTable(25,5);          // Display Output Results in Tabular Format
		resultsTabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		resultsTabel.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Tweets", "User ID", "User", "Tweet Source"    // Columns to be displayed in new swing window
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		resultsTabel.getColumnModel().getColumn(0).setPreferredWidth(150);
		resultsTabel.getColumnModel().getColumn(0).setMinWidth(150);
		resultsTabel.getColumnModel().getColumn(1).setPreferredWidth(450);
		resultsTabel.getColumnModel().getColumn(1).setMinWidth(75);
		resultsTabel.getColumnModel().getColumn(2).setPreferredWidth(280);
		resultsTabel.getColumnModel().getColumn(2).setMinWidth(75);
		resultsTabel.getColumnModel().getColumn(3).setPreferredWidth(125);
		resultsTabel.getColumnModel().getColumn(3).setMinWidth(100);
		// !resultsTabel.getColumnModel().getColumn(4).setMinWidth(25);
		DefaultTableModel model = (DefaultTableModel)resultsTabel.getModel();
		
		Map<Integer, Double> order = score.FinalWeight.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(oldValue,newValue)->oldValue,LinkedHashMap::new));

		Iterator<Integer> k = order.keySet().iterator();
		while (k.hasNext()){
			Integer key = k.next();
			double value = score.FinalWeight.get(key);
			model.addRow(new Object [] {score.text_tweets[key],score.user_id[key],score.des_user[key],score.source_tweets[key],score.FinalWeight.get(key)});
			k.hasNext();
		}


		scrollPane.setViewportView(resultsTabel);
		
		lblNewLabel = new JLabel("Topmost matching tweets:");
		lblNewLabel.setForeground(new Color(255,255,255));
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
		lblNewLabel.setBounds(32, 19, 363, 40);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("These are all the output tweets");
		lblNewLabel_1.setForeground(new Color(255,255,255));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel_1.setBounds(432, 570, 155, 63);
		contentPane.add(lblNewLabel_1);

		btnNewButton = new JButton("H");
		btnNewButton.setBackground(new Color(88, 24, 69));
		btnNewButton.setBounds(911, 6, 78, 64);
		ImageIcon icon2 = new ImageIcon(this.getClass().getResource("/Home.jpg"));
		btnNewButton.setIcon(icon2);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new First().setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnNewButton);

		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			String comfilter;
			public void actionPerformed(ActionEvent e) {
				JComboBox comboBox = (JComboBox) e.getSource();
                Object selected = comboBox.getSelectedItem();
                String filter = selected.toString();
                if(filter=="80-60") {
                int rowCount = model.getRowCount();
                for (int i = rowCount - 1; i >= 0; i--) {
					model.removeRow(i);
				}
				
				Iterator<Integer> k = order.keySet().iterator();
				while (k.hasNext()){
					Integer key = k.next();
					double value = score.FinalWeight.get(key);
					if(score.text_tweets[key].equals(filter1)) {
					if(score.FinalWeight.get(key)<80 && score.FinalWeight.get(key)>60)
						model.addRow(new Object [] {score.text_tweets[key],score.user_id[key],score.des_user[key],score.source_tweets[key] ,score.FinalWeight.get(key)});
					}
					else if(filter1.equals("Company Filter")){
						if(score.FinalWeight.get(key)<80 && score.FinalWeight.get(key)>60)
							model.addRow(new Object [] {score.text_tweets[key],score.user_id[key],score.des_user[key],score.source_tweets[key] ,score.FinalWeight.get(key)});
					}
					k.hasNext();
				}
			}
                else if(filter=="60-40") {
                	int rowCount = model.getRowCount();
    				for (int i = rowCount - 1; i >= 0; i--) {
    					model.removeRow(i);
    				}
    				
    				Iterator<Integer> k = order.keySet().iterator();
    				while (k.hasNext()){
    					Integer key = k.next();
    					double value = score.FinalWeight.get(key);
    					if(score.text_tweets[key].equals(filter1)) {
    					if(score.FinalWeight.get(key)<60 && score.FinalWeight.get(key)>40)
    						model.addRow(new Object [] {score.text_tweets[key],score.user_id[key],score.des_user[key],score.source_tweets[key] ,score.FinalWeight.get(key)});
    					}
    					else if(filter1.equals("Search Filter")){
    						if(score.FinalWeight.get(key)<60 && score.FinalWeight.get(key)>40)
    							model.addRow(new Object [] {score.text_tweets[key],score.user_id[key],score.des_user[key],score.source_tweets[key] ,score.FinalWeight.get(key)});
    					}
    					k.hasNext();
    				}
                }
                else if(filter=="40-0") {
                	int rowCount = model.getRowCount();
    				for (int i = rowCount - 1; i >= 0; i--) {
    					model.removeRow(i);
    				}
    				
    				Iterator<Integer> k = order.keySet().iterator();
    				while (k.hasNext()){
    					Integer key = k.next();
    					double value = score.FinalWeight.get(key);
    					if(score.text_tweets[key].equals(filter1)) {
    					if(score.FinalWeight.get(key)<40)
    						model.addRow(new Object [] {score.text_tweets[key],score.user_id[key],score.des_user[key],score.source_tweets[key] ,score.FinalWeight.get(key)});
    					}
    					else if(filter1.equals("Search Filter")){
    						if(score.FinalWeight.get(key)<40)
    							model.addRow(new Object [] {score.text_tweets[key],score.user_id[key],score.des_user[key],score.source_tweets[key] ,score.FinalWeight.get(key)});
    					}
    					k.hasNext();
    				}
                }
                else if(filter=="100-80"){
                	int rowCount = model.getRowCount();
    				for (int i = rowCount - 1; i >= 0; i--) {
    					model.removeRow(i);
    				}
    				
    				Iterator<Integer> k = order.keySet().iterator();
    				while (k.hasNext()){
    					Integer key = k.next();
    					double value = score.FinalWeight.get(key);
    					if(score.text_tweets[key].equals(filter1)) {
    					if(score.FinalWeight.get(key)>80)
    						model.addRow(new Object [] {score.text_tweets[key],score.user_id[key],score.des_user[key],score.source_tweets[key] ,score.FinalWeight.get(key)});
    					}
    					else if(filter1.equals("Search Filter")){
    						if(score.FinalWeight.get(key)>80)
    							model.addRow(new Object [] {score.text_tweets[key],score.user_id[key],score.des_user[key],score.source_tweets[key] ,score.FinalWeight.get(key)});
    					}
    					k.hasNext();
    				}
                }
                else{
                	int rowCount = model.getRowCount();
    				for (int i = rowCount - 1; i >= 0; i--) {
    					model.removeRow(i);
    				}
    				Iterator<Integer> k = order.keySet().iterator();
    				while (k.hasNext()){
    					Integer key = k.next();
    					double value = score.FinalWeight.get(key);
    					if(score.text_tweets[key].equals(filter1)) {
    						model.addRow(new Object [] {score.text_tweets[key],score.user_id[key],score.des_user[key],score.source_tweets[key] ,score.FinalWeight.get(key)});
    					}
    					else{
    							model.addRow(new Object [] {score.text_tweets[key],score.user_id[key],score.des_user[key],score.source_tweets[key] ,score.FinalWeight.get(key)});
    					}
    					k.hasNext();
    				}
                }
			}
		});
		
		
		
		System.out.print(lng+"\n"+dte+"\n"+reqUser);
    }
}