import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSlider;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.AttributeSet;

import java.awt.Color;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextPane;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import java.awt.Font;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JButton;

// import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;

import javax.swing.JTextField;

import java.awt.Canvas;

import javax.swing.JTabbedPane;
import javax.swing.JSeparator;
import javax.swing.JLayeredPane;

import java.awt.Panel;

public class GUI extends JFrame {

 /**
  * Fingerprint file reader/comparator
  * v1.0
  * Program will load a fingerprint text file and compare it to
  * the fingerprint01.txt file. 
  **/
 private static final long serialVersionUID = 1L;
 private JPanel contentPane;
 private static JTextArea textArea = new JTextArea();
 private JFileChooser fc = new JFileChooser();
 private FingerPrint FPnew;
 private File file;
 private JTextField accuracyField;
 private static JTextArea textArea2 = new JTextArea();

 /**
  * Launch the application.
  */
 public static void main(String[] args) throws IOException {
  EventQueue.invokeLater(new Runnable() {
   public void run() {
    try {
     GUI frame = new GUI();
     frame.setVisible(true);
    } catch (Exception e) {
     e.printStackTrace();
    }
   }
  });
 }

 /**
  * Create the frame.
  */
 @SuppressWarnings("unused")
 public GUI() throws IOException {
  setTitle("Fingerprint Processor");
  setResizable(false);
  
  textArea = new JTextArea();
  textArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
  textArea.setEditable(false);
  textArea.setWrapStyleWord(true);
  textArea.setLineWrap(true);
  fc = new JFileChooser();
  textArea2.setFont(new Font("Monospaced", Font.PLAIN, 6));
  
     // creating 6 fingerprint objects using 3 files
     final FingerPrint FP1 = new FingerPrint("C:/Users/Chris/Desktop/New Folder/fingerprint01.txt");
     final FingerPrint FP2 = new FingerPrint("C:/Users/Chris/Desktop/New Folder/fingerprint02.txt");
     final FingerPrint FP3 = new FingerPrint("C:/Users/Chris/Desktop/New Folder/fingerprint03.txt");
     final FingerPrint FP4 = new FingerPrint("C:/Users/Chris/Desktop/New Folder/fingerprint04.txt");
     final FingerPrint FP5 = new FingerPrint("C:/Users/Chris/Desktop/New Folder/fingerprint05.txt");
     final FingerPrint FP6 = new FingerPrint("C:/Users/Chris/Desktop/New Folder/fingerprint06.txt");
     
     // creating array of FingerPrint object arrays
     FingerPrint[] fpArray = {FP5, FP4, FP3, FP2, FP1};
     
     // creating linked list with FingerPrint object array
     LinkedList Fp_Database = new LinkedList(fpArray);
     
     textArea.append("Please select a fingerprint file to load and process. ");
     
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  setBounds(100, 100, 630, 400);
  
  fc.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
  
  
  JMenuBar menuBar = new JMenuBar();
  setJMenuBar(menuBar);
  
  JMenu mnFileMenu = new JMenu("File");
  menuBar.add(mnFileMenu);
  
  contentPane = new JPanel();
  contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
  setContentPane(contentPane);
  contentPane.setLayout(null);
  
  final JSlider accuracySlider = new JSlider();
  try{
  accuracySlider.addChangeListener(new ChangeListener() {
   public void stateChanged(ChangeEvent arg0) {
    int acc = accuracySlider.getValue();
    textArea.append("Accuracy set to "+acc+"%.\n");
   }
  });}
  catch(NullPointerException e){
   // do nothing
  }
  
  accuracySlider.setPaintTicks(true);
  accuracySlider.setSnapToTicks(true);
  accuracySlider.setFont(new Font("Tahoma", Font.PLAIN, 10));
  accuracySlider.setPaintLabels(true);
  accuracySlider.setBorder(new LineBorder(new Color(0, 0, 0)));
  accuracySlider.setToolTipText("Move slider to adjust accuracy threshold.");
  accuracySlider.setValue(90);
  accuracySlider.setMajorTickSpacing(15);
  accuracySlider.setMinorTickSpacing(1);
  accuracySlider.setBounds(107, 260, 400, 56);
  contentPane.add(accuracySlider);
  
  JScrollPane scrollPane = new JScrollPane();
  scrollPane.setBounds(107, 31, 400, 200);
  contentPane.add(scrollPane);
  
  scrollPane.setViewportView(textArea);
  
  JButton resetButton = new JButton("Reset");
  resetButton.setToolTipText("Click to reset all to default settings.");
  resetButton.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent arg0) {
    accuracySlider.setValue(90);
    accuracyField.setText("90");
    textArea.setText("");
    FPnew = null;
    textArea.append("Screen cleared. Accuracy reset to "+accuracySlider.getValue()+"%.\n");
   }
  });
  resetButton.setBounds(25, 260, 72, 56);
  contentPane.add(resetButton);
  
  final JMenuItem mntmOpen = new JMenuItem("Open");
  mntmOpen.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent arg0) {
    int returnVal = fc.showOpenDialog(mntmOpen);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
           file = (File)fc.getSelectedFile();
           textArea.append(""+file+"\n");
           String temp = ""+file+"";
      try {
       FPnew = new FingerPrint(temp);
       textArea.append("Owner of fingerprint: "+FPnew.getName()+"\n");
      } catch (IOException e) {
       e.printStackTrace();
      }
    }
    
   }
  });
  mnFileMenu.add(mntmOpen);
  
  JMenuItem mntmExit = new JMenuItem("Exit");
  mntmExit.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent arg0) {
    System.exit(0);
   }
  });
  mnFileMenu.add(mntmExit);
  
  JMenu mnHelp = new JMenu("Help");
  menuBar.add(mnHelp);
  
  final JMenuItem mntmAbout = new JMenuItem("About");
  mntmAbout.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent arg0) {
    JOptionPane.showMessageDialog(mntmAbout, "Fingerprint Processor v1.0\nProgrammed by: Christopher Soto, 2014");
   }
  });
  mnHelp.add(mntmAbout);
  
  final JButton processButton = new JButton("Process");
  processButton.setToolTipText("Click to compare fingerprint file to default user.");
  processButton.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent arg0) {
    try{
     textArea.append(FP1.accuracy(FPnew, accuracySlider.getValue())+"\n");
     UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Verdana", Font.PLAIN, 8)));
     textArea2.append(FPnew.printImage());
     int input = JOptionPane.showOptionDialog(null, textArea2, FPnew.getName(), JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
     
     if(input == JOptionPane.OK_OPTION)
     {
         textArea2.setText("");
     }
     else
     {
      textArea2.setText("");
     }
     
    }catch(NullPointerException e){
     JOptionPane.showMessageDialog(null, "Please select a fingerprint file before processing", "Error", JOptionPane.ERROR_MESSAGE, null);
    }
   }
  });
  processButton.setBounds(517, 142, 87, 56);
  contentPane.add(processButton);
  
  accuracyField = new JTextField();
  accuracyField.setText("90");
  accuracyField.setToolTipText("Enter accuracy (0-100).");
  accuracyField.setBounds(533, 260, 36, 20);
  contentPane.add(accuracyField);
  accuracyField.setColumns(10);
  
  JButton setButton = new JButton("Set");
  setButton.setToolTipText("Click to set accuracy.");
  setButton.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
    try{
    int temp = Integer.parseInt(accuracyField.getText());
    if(temp < 0 || temp > 100)
    {
     JOptionPane.showMessageDialog(null, "Error: Accuracy must be a number between 0-100.", "Error", JOptionPane.ERROR_MESSAGE, null);
    }
    else
     accuracySlider.setValue(temp);}
    catch(NumberFormatException e1){
     JOptionPane.showMessageDialog(null, "Error: Accuracy must be a number between 0-100.", "Error", JOptionPane.ERROR_MESSAGE, null);
    }
   }
  });
  setButton.setBounds(522, 289, 59, 23);
  contentPane.add(setButton);
  
 // setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{menuBar, mnFileMenu, mntmOpen, mntmExit, contentPane, accuracySlider, scrollPane, textArea, resetButton, processButton}));
  
 }
}
