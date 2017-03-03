package scripts.red_CWBooster;

import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import org.tribot.api.General;

import scripts.red_CWBooster.Data.Variables;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JFormattedTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


 
public class GUI {
		private Variables variables;
        private JFrame frame;
        JCheckBox chckbxUseGe;
        private JTextField tickets;
        /**
         * Create the application.
         */
        public GUI(Variables variables) {
        		this.variables = variables;
        		initialize();
        }
 
        private void initialize() {
                frame = new JFrame();
                frame.setBounds(100, 100, 360, 420);
                frame.setResizable(false);
                frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                frame.getContentPane().setLayout(null);
               
                JLabel txtTitle = new JLabel("Red CW Booster");
                txtTitle.setFont(new Font("Arial", Font.BOLD, 30));
                txtTitle.setHorizontalAlignment(SwingConstants.CENTER);
                txtTitle.setBounds(-2, 0, 359, 50);
                frame.getContentPane().add(txtTitle);
               
                JLabel txtMouseSpeed = new JLabel("Mouse Speed");
                txtMouseSpeed.setHorizontalAlignment(SwingConstants.CENTER);
                txtMouseSpeed.setFont(new Font("Arial", Font.PLAIN, 20));
                txtMouseSpeed.setBounds(-2, 70, 359, 20);
                frame.getContentPane().add(txtMouseSpeed);
               
                JSlider sliderMouseSpeed = new JSlider();
                sliderMouseSpeed.setPaintLabels(true);
                sliderMouseSpeed.setValue(100);
                sliderMouseSpeed.setPaintTicks(true);
                sliderMouseSpeed.setMajorTickSpacing(50);
                sliderMouseSpeed.setMaximum(300);
                sliderMouseSpeed.setMinimum(50);
                sliderMouseSpeed.setMinorTickSpacing(10);
                sliderMouseSpeed.setBounds(12, 90, 330, 60);
                frame.getContentPane().add(sliderMouseSpeed);
                
                JTextField userWorlds = new JFormattedTextField();
                userWorlds.addKeyListener(new KeyAdapter() {
                	public void keyTyped(KeyEvent arg0) {
                	}
                });
                userWorlds.setBounds(20, 206, 302, 20);
                frame.getContentPane().add(userWorlds);
               
                
                
                            
                JCheckBox chckbxAdvancedPaint = new JCheckBox("Advanced Paint");
                chckbxAdvancedPaint.setToolTipText("Wil be added soon");
                chckbxAdvancedPaint.setEnabled(false);
                chckbxAdvancedPaint.setBounds(190, 315, 145, 25);
                frame.getContentPane().add(chckbxAdvancedPaint);
                
                JButton btnStart = new JButton("Start");
                btnStart.setBounds(70, 347, 97, 25);
                frame.getContentPane().add(btnStart);
                btnStart.addActionListener(new ActionListener() {
                	public void actionPerformed(ActionEvent e) {
                            variables.setMouseSpeed(sliderMouseSpeed.getValue());                        
                            variables.setWorldList(userWorlds.getText().split(" "));
                            String ticketMax = tickets.getText();
                            if(ticketMax.length() > 0){
                            	variables.setTicketMax(Integer.parseInt(ticketMax));
                            	General.println("Ticket max set to "+ Integer.parseInt(ticketMax));
                            }
                                           
                            frame.setVisible(false);
                            variables.setGUI(false);
                           
                    }
                });
                
               
                JButton btnCancel = new JButton("Cancel");
                btnCancel.setBounds(190, 347, 97, 25);
                frame.getContentPane().add(btnCancel);
                
                JLabel lblAddTheWorlds = new JLabel("Add the worlds you want to hop between(in order)");
                lblAddTheWorlds.setHorizontalAlignment(SwingConstants.CENTER);
                lblAddTheWorlds.setFont(new Font("Arial", Font.PLAIN, 10));
                lblAddTheWorlds.setBounds(-2, 160, 359, 20);
                frame.getContentPane().add(lblAddTheWorlds);
                
                JLabel lblSeperateEachWorld = new JLabel("Seperate each world by a single space");
                lblSeperateEachWorld.setHorizontalAlignment(SwingConstants.CENTER);
                lblSeperateEachWorld.setFont(new Font("Arial", Font.PLAIN, 10));
                lblSeperateEachWorld.setBounds(-2, 175, 359, 20);
                frame.getContentPane().add(lblSeperateEachWorld);
                
                JLabel lblStopAt = new JLabel("Stop at");
                lblStopAt.setBounds(20, 252, 46, 14);
                frame.getContentPane().add(lblStopAt);
                
                tickets = new JTextField();
                tickets.setBounds(59, 249, 33, 20);
                frame.getContentPane().add(tickets);
                tickets.setColumns(10);
                
                JLabel lblTickets = new JLabel("tickets ( if left blank will go on forever)");
                lblTickets.setBounds(96, 252, 191, 14);
                frame.getContentPane().add(lblTickets);
                
                JCheckBox chckbxPairHoping = new JCheckBox("Pair Hoping");
                chckbxPairHoping.setEnabled(false);
                chckbxPairHoping.setBounds(59, 316, 97, 23);
                frame.getContentPane().add(chckbxPairHoping);
                
                btnCancel.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                          //  values.setGUI(false);
                            frame.setVisible(false);
                    }
                });           
        }
 
        public void setVisible(boolean b) {
                frame.setVisible(b);
               
        }
}