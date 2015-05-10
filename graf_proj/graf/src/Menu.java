
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Menu extends JFrame implements ActionListener, DocumentListener{

	private JButton jButton1;
	private JButton jButton2;
    private JButton jButton3;
    private JComboBox jComboBox1;
    private JDialog jDialog1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JTextField jTextField1;
    public static int playerCount = 2;
    
    public Menu(){
    	initComponents();
    }
	
	public static void main(String[] args) {
		new Menu().setVisible(true);
	}
	
	/**
	 * Initialize the components of the Menu
	 */
	private void initComponents(){
	
		jDialog1 = new JDialog();
	    jPanel1 = new JPanel();
	    jButton2 = new JButton();
	    jButton1 = new JButton();
	    jButton3 = new JButton();
	    jPanel2 = new JPanel();
	    jLabel1 = new JLabel();
	    jPanel3 = new JPanel();
	    jLabel2 = new JLabel();
	    jTextField1 = new JTextField();
	    jPanel4 = new JPanel();
	    jLabel3 = new JLabel();
	    jComboBox1 = new JComboBox();
	    
	    jDialog1.setTitle("Hiba");
	
	    GroupLayout jDialog1Layout = new GroupLayout(jDialog1.getContentPane());
	    jDialog1.getContentPane().setLayout(jDialog1Layout);
	    jDialog1Layout.setHorizontalGroup(
	        jDialog1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addGap(0, 400, Short.MAX_VALUE)
	    );
	    jDialog1Layout.setVerticalGroup(
	        jDialog1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
	        .addGap(0, 300, Short.MAX_VALUE)
	    );
	
	    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	    setTitle("Phoebe");
	    setPreferredSize(new Dimension(500, 300));
	    setResizable(false);
	    
	    Font font1 = new Font("Tahoma", 0, 18);
	    Font font2 = new Font("Tahoma", 0, 24);
	    Font font3 = new Font("Tahoma", 0, 48);
	
	    jButton2.setFont(font1); // NOI18N
	    jButton2.setText("Eredm�nyek");
	    jPanel1.add(jButton2);
	
	    jButton1.setFont(font2); // NOI18N
	    jButton1.setText("Start");
	    jButton1.setActionCommand("start");
	    jButton1.addActionListener(this);
	    jPanel1.add(jButton1);
	
	    jButton3.setFont(font1); // NOI18N
	    jButton3.setText("Folytat�s");
	    jPanel1.add(jButton3);
	
	    jLabel1.setFont(font3); // NOI18N
	    jLabel1.setText("Be�ll�t�sok");
	    jPanel2.add(jLabel1);
	
	    jLabel2.setFont(font1); // NOI18N
	    jLabel2.setText("J�t�kosok sz�ma");
	    jPanel3.add(jLabel2);
	
	    jTextField1.setFont(font1); // NOI18N
	    jTextField1.setText("2");
	    jTextField1.setColumns(2);
	    jTextField1.setActionCommand("textfield");
	    jTextField1.getDocument().addDocumentListener(this);
	    jPanel3.add(jTextField1);
	
	    jLabel3.setFont(font1); // NOI18N
	    jLabel3.setText("P�lya kiv�laszt�sa");
	    jPanel4.add(jLabel3);
	
	    jComboBox1.setFont(font1); // NOI18N
	    jComboBox1.setModel(new DefaultComboBoxModel(new String[] { "Basic Map"}));
	    jPanel4.add(jComboBox1);
	
	    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	    getContentPane().setLayout(layout);
	    layout.setHorizontalGroup(
	        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
	        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	    );
	    layout.setVerticalGroup(
	        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	        .addGroup(layout.createSequentialGroup()
	            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	            .addContainerGap(14, Short.MAX_VALUE))
	    );
	    pack();
	}
	
	/**
	 * 
	 * @param evt
	 */
	private void jTextField1ActionPerformed(DocumentEvent evt) {                                            
        int player = Integer.parseInt(jTextField1.getText());
        if(player < 2 || player > 5){
            JOptionPane.showMessageDialog(rootPane, "Minimum 2, �s maximum 5 j�t�kos lehet ", "Hiba", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
        	playerCount = player;
        }
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("start"))
		{
			new MapFrame();
    		setVisible(false);
		}
	}
	
	//Document listener methods
	public void changedUpdate(DocumentEvent e) {
		jTextField1ActionPerformed(e);
	}
	public void removeUpdate(DocumentEvent e) {
		//don't care if removed
	}
	public void insertUpdate(DocumentEvent e) {
		jTextField1ActionPerformed(e);
	}


}
