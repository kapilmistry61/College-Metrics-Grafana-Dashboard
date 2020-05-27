package java_project_1_2;

import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kapil
 */
public class Main_Windows extends javax.swing.JFrame {

    /**
     * Creates new form Main_Windows
     */
    public Main_Windows() {
        initComponents();
        Show_Students_In_JTable();
    }

    String ImgPath = null;
    int pos = 0;
    
    public Connection getConnection()
    {
        Connection con = null;
        
        try {
            con = DriverManager.getConnection("jdbc:mysql://Localhost:3307/college_reporting","root","");
         return con;
        } catch (SQLException ex) {
            Logger.getLogger(Main_Windows.class.getName()).log(Level.SEVERE, null, ex);
         return null; 
        }
        
    }
    
     // Check Input Fields
    public boolean checkInputs()
    {
        if(
              txt_firstName.getText() == null
           || txt_rollno.getText() == null
           || txt_admissionDate.getDate() == null
           || txt_DOB.getDate() == null
           
          ){
            return false;
        }
        else{
            try{
                txt_rollno.getText();
                return true;
            }catch(Exception ex)
            {
                return false;
            }
        }
    }
    
//Resize Image
    
    public ImageIcon ResizeImage(String imagePath, byte[] pic)
    {
      ImageIcon myImage = null;  
      
      if(imagePath != null)
      {
          myImage = new ImageIcon(imagePath);
      }else{
          myImage = new ImageIcon(pic);
      }
      
      Image img = myImage.getImage();
      Image img2 = img.getScaledInstance(lbl_image.getWidth(), lbl_image.getHeight(), Image.SCALE_SMOOTH);
      ImageIcon image = new ImageIcon(img2);
      return image;
      
    }
    
   
     // Display Data In JTable: 
    //      1 - Fill ArrayList With The Data
    
    public ArrayList<Product> getProductList()
    {
        ArrayList<Product> productList  = new ArrayList<Product>();
            Connection con = getConnection();
            String query = "SELECT * FROM student";
            
            Statement st;
            ResultSet rs;
        try {
            
            st = con.createStatement();
            rs = st.executeQuery(query);
            Product product;
            
            while(rs.next())
            {
                product = new Product(rs.getInt("id"),rs.getString("firstName"),rs.getString("lastName"),rs.getString("fatherName"),rs.getString("motherName"),rs.getString("rollno"),rs.getString("admissionDate"),rs.getString("DOB"),rs.getString("gender"),rs.getString("branch"),rs.getBytes("image"));
                productList.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main_Windows.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return productList;
         
    }
   
     //      2 - Populate The JTable
    
      public void Show_Students_In_JTable()
    {
        ArrayList<Product> list = getProductList();
        DefaultTableModel model = (DefaultTableModel)JTable_Students.getModel();
        //clear jtable content
        model.setRowCount(0);
        Object[] row = new Object[10];
        for(int i = 0; i < list.size(); i++)
        {
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getFirstName();
            row[2] = list.get(i).getLastName();
            row[3] = list.get(i).getFatherName();
            row[4] = list.get(i).getMotherName();
            row[5] = list.get(i).getRollno();
            row[6] = list.get(i).getAdmissionDate();
            row[7] = list.get(i).getDOB();
            row[8] = list.get(i).getGender();
            row[9] = list.get(i).getBranch();
            
            model.addRow(row);
        }
    
    }
          // Show Data In Inputs
    public void ShowItem(int index)
    {
            txt_id.setText(Integer.toString(getProductList().get(index).getId()));
            txt_firstName.setText(getProductList().get(index).getFirstName());
            txt_lastName.setText(getProductList().get(index).getLastName());
            txt_fatherName.setText(getProductList().get(index).getFatherName());
            txt_motherName.setText(getProductList().get(index).getMotherName());
            txt_rollno.setText(getProductList().get(index).getRollno());
            combo_gender.setSelectedItem(getProductList().get(index).getGender());
            combo_branch.setSelectedItem(getProductList().get(index).getBranch());
        try {

            Date admissionDate = null;
            admissionDate = new SimpleDateFormat("yyyy-MM-dd").parse((String)getProductList().get(index).getAdmissionDate());
            txt_admissionDate.setDate(admissionDate);
            
            Date DOB = null;
            DOB = new SimpleDateFormat("yyyy-MM-dd").parse((String)getProductList().get(index).getDOB());
            txt_DOB.setDate(DOB);
            
        } catch (ParseException ex) {
            Logger.getLogger(Main_Windows.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        lbl_image.setIcon(ResizeImage(null, getProductList().get(index).getImage()));
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        txt_firstName = new javax.swing.JTextField();
        txt_rollno = new javax.swing.JTextField();
        txt_DOB = new com.toedter.calendar.JDateChooser();
        lbl_image = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTable_Students = new javax.swing.JTable();
        Btn_Choose_Image = new javax.swing.JButton();
        Btn_Insert = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        Btn_First = new javax.swing.JButton();
        Btn_Next = new javax.swing.JButton();
        Btn_Previous = new javax.swing.JButton();
        Btn_Last = new javax.swing.JButton();
        txt_gender = new javax.swing.JLabel();
        combo_gender = new javax.swing.JComboBox<>();
        combo_branch = new javax.swing.JComboBox<>();
        txt_branch = new javax.swing.JLabel();
        txt_lastName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_fatherName = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_admissionDate = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        txt_motherName = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        Btn_First1 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(16, 29, 37));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ID:");

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("First Name:");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Dath Of Birth");

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Rollno:");

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Image:");

        txt_id.setBackground(new java.awt.Color(102, 102, 102));
        txt_id.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_id.setForeground(new java.awt.Color(255, 255, 255));
        txt_id.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        txt_id.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_id.setEnabled(false);
        txt_id.setPreferredSize(new java.awt.Dimension(59, 50));

        txt_firstName.setBackground(new java.awt.Color(102, 102, 102));
        txt_firstName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_firstName.setForeground(new java.awt.Color(255, 255, 255));
        txt_firstName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        txt_firstName.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_firstName.setPreferredSize(new java.awt.Dimension(59, 50));

        txt_rollno.setBackground(new java.awt.Color(102, 102, 102));
        txt_rollno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_rollno.setForeground(new java.awt.Color(255, 255, 255));
        txt_rollno.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        txt_rollno.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_rollno.setPreferredSize(new java.awt.Dimension(59, 50));

        txt_DOB.setBackground(new java.awt.Color(102, 102, 102));
        txt_DOB.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        txt_DOB.setDateFormatString("yyyy-MM-dd");
        txt_DOB.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        lbl_image.setBackground(new java.awt.Color(16, 29, 37));
        lbl_image.setForeground(new java.awt.Color(204, 204, 204));
        lbl_image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java_project_1_2/profileimage.png"))); // NOI18N
        lbl_image.setOpaque(true);

        JTable_Students.setBackground(new java.awt.Color(102, 102, 102));
        JTable_Students.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        JTable_Students.setForeground(new java.awt.Color(255, 255, 255));
        JTable_Students.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "First Name", "Last Name", "Father Name", "Mother Name", "Rollno", "Admission Date", "DOB", "gender", "branch"
            }
        ));
        JTable_Students.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTable_StudentsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTable_Students);

        Btn_Choose_Image.setBackground(new java.awt.Color(204, 204, 204));
        Btn_Choose_Image.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btn_Choose_Image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java_project_1_2/image.png"))); // NOI18N
        Btn_Choose_Image.setText(" Choose Image");
        Btn_Choose_Image.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        Btn_Choose_Image.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_Choose_ImageActionPerformed(evt);
            }
        });

        Btn_Insert.setBackground(new java.awt.Color(51, 204, 0));
        Btn_Insert.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btn_Insert.setForeground(new java.awt.Color(255, 255, 255));
        Btn_Insert.setText("Insert");
        Btn_Insert.setBorder(null);
        Btn_Insert.setIconTextGap(15);
        Btn_Insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_InsertActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 153, 255));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Update");
        jButton3.setBorder(null);
        jButton3.setIconTextGap(15);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 0, 0));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Delete");
        jButton4.setBorder(null);
        jButton4.setIconTextGap(15);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        Btn_First.setBackground(new java.awt.Color(255, 255, 255));
        Btn_First.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btn_First.setIcon(new javax.swing.ImageIcon(getClass().getResource("/JAVA_VIDEOS_TUTORIALS/icons/forwardnew.png"))); // NOI18N
        Btn_First.setText("First");
        Btn_First.setBorder(null);
        Btn_First.setIconTextGap(15);
        Btn_First.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_FirstActionPerformed(evt);
            }
        });

        Btn_Next.setBackground(new java.awt.Color(255, 255, 255));
        Btn_Next.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btn_Next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/JAVA_VIDEOS_TUTORIALS/icons/nextnew.png"))); // NOI18N
        Btn_Next.setText("Next");
        Btn_Next.setBorder(null);
        Btn_Next.setIconTextGap(15);
        Btn_Next.setMaximumSize(new java.awt.Dimension(105, 41));
        Btn_Next.setMinimumSize(new java.awt.Dimension(105, 41));
        Btn_Next.setPreferredSize(new java.awt.Dimension(105, 41));
        Btn_Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_NextActionPerformed(evt);
            }
        });

        Btn_Previous.setBackground(new java.awt.Color(255, 255, 255));
        Btn_Previous.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btn_Previous.setIcon(new javax.swing.ImageIcon(getClass().getResource("/JAVA_VIDEOS_TUTORIALS/icons/backnew.png"))); // NOI18N
        Btn_Previous.setText("Previous");
        Btn_Previous.setBorder(null);
        Btn_Previous.setIconTextGap(15);
        Btn_Previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_PreviousActionPerformed(evt);
            }
        });

        Btn_Last.setBackground(new java.awt.Color(255, 255, 255));
        Btn_Last.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btn_Last.setIcon(new javax.swing.ImageIcon(getClass().getResource("/JAVA_VIDEOS_TUTORIALS/icons/fastnew.png"))); // NOI18N
        Btn_Last.setText("Last");
        Btn_Last.setBorder(null);
        Btn_Last.setIconTextGap(15);
        Btn_Last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_LastActionPerformed(evt);
            }
        });

        txt_gender.setBackground(new java.awt.Color(255, 255, 255));
        txt_gender.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_gender.setForeground(new java.awt.Color(255, 255, 255));
        txt_gender.setText("Gender");

        combo_gender.setBackground(new java.awt.Color(153, 153, 153));
        combo_gender.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        combo_gender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        combo_gender.setToolTipText("");
        combo_gender.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        combo_gender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_genderActionPerformed(evt);
            }
        });

        combo_branch.setBackground(new java.awt.Color(153, 153, 153));
        combo_branch.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        combo_branch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CS", "ME", "EEE", "Civil" }));
        combo_branch.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));

        txt_branch.setBackground(new java.awt.Color(255, 255, 255));
        txt_branch.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_branch.setForeground(new java.awt.Color(255, 255, 255));
        txt_branch.setText("Branch");

        txt_lastName.setBackground(new java.awt.Color(102, 102, 102));
        txt_lastName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_lastName.setForeground(new java.awt.Color(255, 255, 255));
        txt_lastName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        txt_lastName.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_lastName.setPreferredSize(new java.awt.Dimension(59, 50));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Last Name:");

        txt_fatherName.setBackground(new java.awt.Color(102, 102, 102));
        txt_fatherName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_fatherName.setForeground(new java.awt.Color(255, 255, 255));
        txt_fatherName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        txt_fatherName.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_fatherName.setPreferredSize(new java.awt.Dimension(59, 50));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Father Name:");

        txt_admissionDate.setBackground(new java.awt.Color(102, 102, 102));
        txt_admissionDate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        txt_admissionDate.setDateFormatString("yyyy-MM-dd");
        txt_admissionDate.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Admission Date:");

        txt_motherName.setBackground(new java.awt.Color(102, 102, 102));
        txt_motherName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_motherName.setForeground(new java.awt.Color(255, 255, 255));
        txt_motherName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        txt_motherName.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_motherName.setPreferredSize(new java.awt.Dimension(59, 50));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Mother Name:");

        Btn_First1.setBackground(new java.awt.Color(255, 255, 255));
        Btn_First1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btn_First1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/JAVA_VIDEOS_TUTORIALS/icons/Webp.net-resizeimage (9).png"))); // NOI18N
        Btn_First1.setBorder(null);
        Btn_First1.setIconTextGap(15);
        Btn_First1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_First1ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java_project_1_2/add-plus-button.png"))); // NOI18N
        jButton1.setText("New");
        jButton1.setBorder(null);
        jButton1.setIconTextGap(15);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_branch)
                            .addComponent(jLabel5)
                            .addComponent(txt_gender)
                            .addComponent(jLabel3)
                            .addComponent(jLabel8)
                            .addComponent(jLabel4)
                            .addComponent(jLabel9)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(6, 6, 6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(Btn_First1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_firstName, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_lastName, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_fatherName, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_motherName, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_rollno, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_admissionDate, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_DOB, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(combo_gender, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Btn_Choose_Image, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(combo_branch, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(63, 63, 63)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 677, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Btn_Insert, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Btn_First, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Btn_Next, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Btn_Previous, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Btn_Last, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_firstName, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(txt_lastName, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_fatherName, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_motherName, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_rollno, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(txt_admissionDate, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_DOB, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(combo_gender, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_gender))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(combo_branch, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_branch))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(143, 143, 143)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Btn_Choose_Image, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Btn_Next, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Btn_Last, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Btn_Previous, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Btn_First, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Btn_Insert, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Btn_First1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 683, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void Btn_Choose_ImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_Choose_ImageActionPerformed
        
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images", "jpg","png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        if(result == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            lbl_image.setIcon(ResizeImage(path, null));
            ImgPath = path;
        } else{
            System.out.println("No File Selected");
        }
        
        
        
    }//GEN-LAST:event_Btn_Choose_ImageActionPerformed

    private void Btn_InsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_InsertActionPerformed
        
        if(checkInputs() && ImgPath != null)
        {
            try {
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement("INSERT INTO student(firstName,lastName,fatherName,motherName,rollno,admissionDate,DOB,gender,branch,image)"
                        + "values(?,?,?,?,?,?,?,?,?,?) ");
                
                ps.setString(1, txt_firstName.getText());
                ps.setString(2, txt_lastName.getText());
                ps.setString(3, txt_fatherName.getText());
                ps.setString(4, txt_motherName.getText());
                ps.setString(5, txt_rollno.getText());
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String admissionDate = dateFormat.format(txt_admissionDate.getDate());
                ps.setString(6, admissionDate);
               
               
                String DOB = dateFormat.format(txt_DOB.getDate());
                ps.setString(7, DOB);
                
                String value=combo_gender.getSelectedItem().toString();
                ps.setString(8, value);
                
                String valueb=combo_branch.getSelectedItem().toString();
                ps.setString(9, valueb);
               
                InputStream img = new FileInputStream(new File(ImgPath));
             ps.setBlob(10, img);
             ps.executeUpdate();
             Show_Students_In_JTable();
             
             JOptionPane.showMessageDialog(null, "Record Inserted");
             
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }else{
            JOptionPane.showMessageDialog(null, "One Or More Field Are Empty");
            
        }
        
        System.out.println("Namef => "+txt_firstName.getText());
        System.out.println("Namel => "+txt_lastName.getText());
        System.out.println("Namelf => "+txt_fatherName.getText());
        System.out.println("Namelm => "+txt_motherName.getText());
        System.out.println("Rollno => "+txt_rollno.getText());
        System.out.println("Image => "+ImgPath);
    }//GEN-LAST:event_Btn_InsertActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
    if(checkInputs() && txt_id.getText() != null)
        {
            String UpdateQuery = null;
            PreparedStatement ps = null;
            Connection con = getConnection();
            
            //Update without image 
            if(ImgPath == null)
            {
                try {
                    UpdateQuery = "UPDATE student SET firstName = ?, lastName = ?, fatherName = ?, motherName = ?, rollno = ?"
                            + ", admissionDate = ?,DOB = ? WHERE id = ?";
                    ps = con.prepareStatement(UpdateQuery);
                    
                    ps.setString(1, txt_firstName.getText());
                    ps.setString(2, txt_lastName.getText());
                    ps.setString(3, txt_fatherName.getText());
                    ps.setString(4, txt_motherName.getText());
                    ps.setString(5, txt_rollno.getText());
                   
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String admissionDate = dateFormat.format(txt_admissionDate.getDate());
                    String DOB = dateFormat.format(txt_DOB.getDate());
                   
                    ps.setString(6, admissionDate);
                    ps.setString(7, DOB);
                   
                    ps.setInt(8, Integer.parseInt(txt_id.getText()));
                    
                    ps.executeUpdate();
                    Show_Students_In_JTable();
                    JOptionPane.showMessageDialog(null, "Record Updated");
                    
                } catch (SQLException ex) {
                    Logger.getLogger(Main_Windows.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                  // update With Image
            else{
                try{
                InputStream img = new FileInputStream(new File(ImgPath));
               
                 UpdateQuery = "UPDATE student SET firstName = ?, lastName = ?, fatherName = ?, motherName = ?, rollno = ?"
                            + ", admissionDate = ?,DOB = ?, image = ? WHERE id = ?";
               
                  ps = con.prepareStatement(UpdateQuery);
                   
                    ps.setString(1, txt_firstName.getText());
                    ps.setString(2, txt_lastName.getText());
                    ps.setString(3, txt_fatherName.getText());
                    ps.setString(4, txt_motherName.getText());
                    ps.setString(5, txt_rollno.getText());
                   
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String admissionDate = dateFormat.format(txt_admissionDate.getDate());
                    String DOB = dateFormat.format(txt_DOB.getDate());
                   
                    ps.setString(6, admissionDate);
                    ps.setString(7, DOB);
                    
                    ps.setBlob(8, img);
                  
                    ps.setInt(9, Integer.parseInt(txt_id.getText()));
                    
                    ps.executeUpdate();
                    Show_Students_In_JTable();
                    JOptionPane.showMessageDialog(null, "Product Updated");
                                  
                }catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "One Or More Fields Are Empty Or Wrong");
        }
           
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        
         if(!txt_id.getText().equals(""))
        {
             try {
                 Connection con = getConnection();
                 PreparedStatement ps = con.prepareStatement("DELETE FROM student WHERE id = ?");
                 int id = Integer.parseInt(txt_id.getText());
                 
                ps.setInt(1, id);
                ps.executeUpdate();
                Show_Students_In_JTable();
                JOptionPane.showMessageDialog(null, "Record Deleted");
             } catch (SQLException ex) {
                 Logger.getLogger(Main_Windows.class.getName()).log(Level.SEVERE, null, ex);
                 JOptionPane.showMessageDialog(null, "Product Not Deleted");
             }
            }else{
            JOptionPane.showMessageDialog(null, "Product Not Deleted : No Id To Delete");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void JTable_StudentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTable_StudentsMouseClicked
        
        int index = JTable_Students.getSelectedRow();
        ShowItem(index);
    }//GEN-LAST:event_JTable_StudentsMouseClicked

    private void Btn_FirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_FirstActionPerformed
       
        pos = 0;
        ShowItem(pos);
    }//GEN-LAST:event_Btn_FirstActionPerformed

    private void Btn_LastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_LastActionPerformed
        
        pos = getProductList().size()-1;
        ShowItem(pos);
    }//GEN-LAST:event_Btn_LastActionPerformed

    private void Btn_NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_NextActionPerformed
       
        pos++;
        
        if(pos >= getProductList().size())
        {
            pos = getProductList().size()-1;
        }
        
        ShowItem(pos);
    }//GEN-LAST:event_Btn_NextActionPerformed

    private void Btn_PreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_PreviousActionPerformed
       
        pos--;
        
        if(pos < 0)
        {
            pos = 0;
        }
        
        ShowItem(pos);
    }//GEN-LAST:event_Btn_PreviousActionPerformed

    private void Btn_First1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_First1ActionPerformed
       
        
       College obj=new College();
        obj.setVisible(true);
        dispose();
    }//GEN-LAST:event_Btn_First1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

   Main_Windows obj=new Main_Windows();
        obj.setVisible(true);
        dispose();

        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void combo_genderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_genderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo_genderActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main_Windows.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main_Windows.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main_Windows.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main_Windows.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main_Windows().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_Choose_Image;
    private javax.swing.JButton Btn_First;
    private javax.swing.JButton Btn_First1;
    private javax.swing.JButton Btn_Insert;
    private javax.swing.JButton Btn_Last;
    private javax.swing.JButton Btn_Next;
    private javax.swing.JButton Btn_Previous;
    private javax.swing.JTable JTable_Students;
    private javax.swing.JComboBox<String> combo_branch;
    private javax.swing.JComboBox<String> combo_gender;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_image;
    private com.toedter.calendar.JDateChooser txt_DOB;
    private com.toedter.calendar.JDateChooser txt_admissionDate;
    private javax.swing.JLabel txt_branch;
    private javax.swing.JTextField txt_fatherName;
    private javax.swing.JTextField txt_firstName;
    private javax.swing.JLabel txt_gender;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_lastName;
    private javax.swing.JTextField txt_motherName;
    private javax.swing.JTextField txt_rollno;
    // End of variables declaration//GEN-END:variables
}
