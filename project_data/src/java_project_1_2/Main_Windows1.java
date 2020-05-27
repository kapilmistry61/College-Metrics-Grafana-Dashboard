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
public class Main_Windows1 extends javax.swing.JFrame {

    /**
     * Creates new form Main_Windows
     */
    public Main_Windows1() {
        initComponents();
        Show_Faculty_In_JTable();
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
            Logger.getLogger(Main_Windows1.class.getName()).log(Level.SEVERE, null, ex);
         return null; 
        }
        
    }
    
     // Check Input Fields
    public boolean checkInputs()
    {
        if(
              txt_firstName.getText() == null
           || txt1_contactno.getText() == null
           || txt1_joiningDate.getDate() == null
           || txt_DOB.getDate() == null
           
          ){
            return false;
        }
        else{
            try{
                txt1_contactno.getText();
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
    
    public ArrayList<Product1> getProduct1List()
    {
        ArrayList<Product1> product1List  = new ArrayList<Product1>();
            Connection con = getConnection();
            String query = "SELECT * FROM faculty";
            
            Statement st;
            ResultSet rs;
        try {
            
            st = con.createStatement();
            rs = st.executeQuery(query);
            Product1 product1;
            
            while(rs.next())
            {
                product1 = new Product1(rs.getInt("id"),rs.getString("firstName"),rs.getString("lastName"),rs.getString("fatherName"),rs.getString("eMail"),rs.getString("contactno"),rs.getString("joiningDate"),rs.getString("DOB"),rs.getString("gender"),rs.getString("branch"),rs.getBytes("image"));
                product1List.add(product1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Main_Windows1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return product1List;
         
    }
   
     //      2 - Populate The JTable
    
      public void Show_Faculty_In_JTable()
    {
        ArrayList<Product1> list = getProduct1List();
        DefaultTableModel model = (DefaultTableModel)JTable_Faculty.getModel();
        //clear jtable content
        model.setRowCount(0);
        Object[] row = new Object[10];
        for(int i = 0; i < list.size(); i++)
        {
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getFirstName();
            row[2] = list.get(i).getLastName();
            row[3] = list.get(i).getFatherName();
            row[4] = list.get(i).getEmail();
            row[5] = list.get(i).getContactno();
            row[6] = list.get(i).getJoiningDate();
            row[7] = list.get(i).getDOB();
            row[8] = list.get(i).getGender();
            row[9] = list.get(i).getBranch();
            
            model.addRow(row);
        }
    
    }
          // Show Data In Inputs
    public void ShowItem(int index)
    {
            txt_id.setText(Integer.toString(getProduct1List().get(index).getId()));
            txt_firstName.setText(getProduct1List().get(index).getFirstName());
            txt_lastName.setText(getProduct1List().get(index).getLastName());
            txt_eMail.setText(getProduct1List().get(index).getFatherName());
            txt1_eMail.setText(getProduct1List().get(index).getEmail());
            txt1_contactno.setText(getProduct1List().get(index).getContactno());
            combo_gender.setSelectedItem(getProduct1List().get(index).getGender());
            combo1_department.setSelectedItem(getProduct1List().get(index).getBranch());
        try {

            Date joiningDate = null;
            joiningDate = new SimpleDateFormat("yyyy-MM-dd").parse((String)getProduct1List().get(index).getJoiningDate());
            txt1_joiningDate.setDate(joiningDate);
            
            Date DOB = null;
            DOB = new SimpleDateFormat("yyyy-MM-dd").parse((String)getProduct1List().get(index).getDOB());
            txt_DOB.setDate(DOB);
            
        } catch (ParseException ex) {
            Logger.getLogger(Main_Windows1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        lbl_image.setIcon(ResizeImage(null, getProduct1List().get(index).getImage()));
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
        txt1_contactno = new javax.swing.JTextField();
        txt_DOB = new com.toedter.calendar.JDateChooser();
        lbl_image = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTable_Faculty = new javax.swing.JTable();
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
        combo1_department = new javax.swing.JComboBox<>();
        txt_branch = new javax.swing.JLabel();
        txt_lastName = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_eMail = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt1_joiningDate = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        txt1_eMail = new javax.swing.JTextField();
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
        jLabel3.setText("Dath Of Birth:");

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Contact No:");

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

        txt1_contactno.setBackground(new java.awt.Color(102, 102, 102));
        txt1_contactno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt1_contactno.setForeground(new java.awt.Color(255, 255, 255));
        txt1_contactno.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        txt1_contactno.setCaretColor(new java.awt.Color(255, 255, 255));
        txt1_contactno.setPreferredSize(new java.awt.Dimension(59, 50));

        txt_DOB.setBackground(new java.awt.Color(102, 102, 102));
        txt_DOB.setDateFormatString("yyyy-MM-dd");
        txt_DOB.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        lbl_image.setBackground(new java.awt.Color(16, 29, 37));
        lbl_image.setForeground(new java.awt.Color(51, 0, 102));
        lbl_image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java_project_1_2/profileimage.png"))); // NOI18N
        lbl_image.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbl_image.setOpaque(true);

        JTable_Faculty.setBackground(new java.awt.Color(102, 102, 102));
        JTable_Faculty.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        JTable_Faculty.setForeground(new java.awt.Color(255, 255, 255));
        JTable_Faculty.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "First Name", "Last Name", "Father Name", "E - Mail", "Contact No", "Joining Date", "DOB", "Gender", "Department"
            }
        ));
        JTable_Faculty.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTable_FacultyMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTable_Faculty);

        Btn_Choose_Image.setBackground(new java.awt.Color(204, 204, 204));
        Btn_Choose_Image.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btn_Choose_Image.setIcon(new javax.swing.ImageIcon(getClass().getResource("/java_project_1_2/image.png"))); // NOI18N
        Btn_Choose_Image.setText(" Choose Image");
        Btn_Choose_Image.setBorder(null);
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
        txt_gender.setText("Gender:");

        combo_gender.setBackground(new java.awt.Color(153, 153, 153));
        combo_gender.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        combo_gender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));
        combo_gender.setToolTipText("");
        combo_gender.setBorder(null);

        combo1_department.setBackground(new java.awt.Color(153, 153, 153));
        combo1_department.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        combo1_department.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CS", "ME", "EEE", "Civil" }));
        combo1_department.setBorder(null);

        txt_branch.setBackground(new java.awt.Color(255, 255, 255));
        txt_branch.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_branch.setForeground(new java.awt.Color(255, 255, 255));
        txt_branch.setText("Department:");

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

        txt_eMail.setBackground(new java.awt.Color(102, 102, 102));
        txt_eMail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_eMail.setForeground(new java.awt.Color(255, 255, 255));
        txt_eMail.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        txt_eMail.setCaretColor(new java.awt.Color(255, 255, 255));
        txt_eMail.setPreferredSize(new java.awt.Dimension(59, 50));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Father Name:");

        txt1_joiningDate.setBackground(new java.awt.Color(102, 102, 102));
        txt1_joiningDate.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        txt1_joiningDate.setDateFormatString("yyyy-MM-dd");
        txt1_joiningDate.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Joining Date:");

        txt1_eMail.setBackground(new java.awt.Color(102, 102, 102));
        txt1_eMail.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt1_eMail.setForeground(new java.awt.Color(255, 255, 255));
        txt1_eMail.setText(" ex: myname@example.com");
        txt1_eMail.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));
        txt1_eMail.setCaretColor(new java.awt.Color(255, 255, 255));
        txt1_eMail.setPreferredSize(new java.awt.Dimension(59, 50));
        txt1_eMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt1_eMailActionPerformed(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("E - Mail:");

        Btn_First1.setBackground(new java.awt.Color(255, 255, 255));
        Btn_First1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Btn_First1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/JAVA_VIDEOS_TUTORIALS/icons/Webp.net-resizeimage (9).png"))); // NOI18N
        Btn_First1.setBorder(null);
        Btn_First1.setIconTextGap(15);
        Btn_First1.setPreferredSize(new java.awt.Dimension(89, 100));
        Btn_First1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_First1ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
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
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(191, 191, 191)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(combo_gender, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txt_lastName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txt_eMail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txt1_eMail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txt1_contactno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txt1_joiningDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txt_DOB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lbl_image)
                                        .addComponent(combo1_department, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(Btn_Choose_Image, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGap(106, 106, 106)
                            .addComponent(txt_firstName, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(28, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addComponent(jLabel2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Btn_First1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel9)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel3)
                                        .addComponent(txt_gender)
                                        .addComponent(txt_branch)
                                        .addComponent(jLabel5)))
                                .addGap(1, 1, 1)))
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Btn_Insert, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Btn_First, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Btn_Next, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Btn_Previous, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Btn_Last, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 678, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_firstName, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_lastName, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_eMail, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt1_eMail, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt1_contactno, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt1_joiningDate, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_DOB, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_gender)
                            .addComponent(combo_gender, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(combo1_department, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_branch))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_image, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addComponent(Btn_Choose_Image, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Btn_Last, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Btn_Next, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Btn_Previous, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Btn_First, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Btn_Insert, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(Btn_First1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                PreparedStatement ps = con.prepareStatement("INSERT INTO faculty(firstName,lastName,fatherName,eMail,contactno,joiningDate,DOB,gender,branch,image)"
                        + "values(?,?,?,?,?,?,?,?,?,?) ");
                
                ps.setString(1, txt_firstName.getText());
                ps.setString(2, txt_lastName.getText());
                ps.setString(3, txt_eMail.getText());
                ps.setString(4, txt1_eMail.getText());
                ps.setString(5, txt1_contactno.getText());
                
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String joiningDate = dateFormat.format(txt1_joiningDate.getDate());
                ps.setString(6, joiningDate);
               
               
                String DOB = dateFormat.format(txt_DOB.getDate());
                ps.setString(7, DOB);
                
                String value=combo_gender.getSelectedItem().toString();
                ps.setString(8, value);
                
                String valueb=combo1_department.getSelectedItem().toString();
                ps.setString(9, valueb);
               
                InputStream img = new FileInputStream(new File(ImgPath));
             ps.setBlob(10, img);
             ps.executeUpdate();
             Show_Faculty_In_JTable();
             
             JOptionPane.showMessageDialog(null, "Record Inserted");
             
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }else{
            JOptionPane.showMessageDialog(null, "One Or More Field Are Empty");
            
        }
        
        System.out.println("Namef => "+txt_firstName.getText());
        System.out.println("Namel => "+txt_lastName.getText());
        System.out.println("Namelf => "+txt_eMail.getText());
        System.out.println("Namelm => "+txt1_eMail.getText());
        System.out.println("Contactno => "+txt1_contactno.getText());
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
                    UpdateQuery = "UPDATE faculty SET firstName = ?, lastName = ?, fatherName = ?, eMail = ?, contactno = ?"
                            + ", joiningDate = ?,DOB = ? WHERE id = ?";
                    ps = con.prepareStatement(UpdateQuery);
                    
                    ps.setString(1, txt_firstName.getText());
                    ps.setString(2, txt_lastName.getText());
                    ps.setString(3, txt_eMail.getText());
                    ps.setString(4, txt1_eMail.getText());
                    ps.setString(5, txt1_contactno.getText());
                   
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String joiningDate = dateFormat.format(txt1_joiningDate.getDate());
                    String DOB = dateFormat.format(txt_DOB.getDate());
                   
                    ps.setString(6, joiningDate);
                    ps.setString(7, DOB);
                   
                    ps.setInt(8, Integer.parseInt(txt_id.getText()));
                    
                    ps.executeUpdate();
                    Show_Faculty_In_JTable();
                    JOptionPane.showMessageDialog(null, "Record Updated");
                    
                } catch (SQLException ex) {
                    Logger.getLogger(Main_Windows1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                  // update With Image
            else{
                try{
                InputStream img = new FileInputStream(new File(ImgPath));
               
                 UpdateQuery = "UPDATE faculty SET firstName = ?, lastName = ?, fatherName = ?, eMail = ?, contactno = ?"
                            + ", joiningDate = ?,DOB = ?, image = ? WHERE id = ?";
               
                  ps = con.prepareStatement(UpdateQuery);
                   
                    ps.setString(1, txt_firstName.getText());
                    ps.setString(2, txt_lastName.getText());
                    ps.setString(3, txt_eMail.getText());
                    ps.setString(4, txt1_eMail.getText());
                    ps.setString(5, txt1_contactno.getText());
                   
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String joiningDate = dateFormat.format(txt1_joiningDate.getDate());
                    String DOB = dateFormat.format(txt_DOB.getDate());
                   
                    ps.setString(6, joiningDate);
                    ps.setString(7, DOB);
                    
                    ps.setBlob(8, img);
                  
                    ps.setInt(9, Integer.parseInt(txt_id.getText()));
                    
                    ps.executeUpdate();
                    Show_Faculty_In_JTable();
                    JOptionPane.showMessageDialog(null, "Product1 Updated");
                                  
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
                 PreparedStatement ps = con.prepareStatement("DELETE FROM faculty WHERE id = ?");
                 int id = Integer.parseInt(txt_id.getText());
                 
                ps.setInt(1, id);
                ps.executeUpdate();
                Show_Faculty_In_JTable();
                JOptionPane.showMessageDialog(null, "Record Deleted");
             } catch (SQLException ex) {
                 Logger.getLogger(Main_Windows1.class.getName()).log(Level.SEVERE, null, ex);
                 JOptionPane.showMessageDialog(null, "Product1 Not Deleted");
             }
            }else{
            JOptionPane.showMessageDialog(null, "Product1 Not Deleted : No Id To Delete");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void JTable_FacultyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTable_FacultyMouseClicked
        
        int index = JTable_Faculty.getSelectedRow();
        ShowItem(index);
    }//GEN-LAST:event_JTable_FacultyMouseClicked

    private void Btn_FirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_FirstActionPerformed
       
        pos = 0;
        ShowItem(pos);
    }//GEN-LAST:event_Btn_FirstActionPerformed

    private void Btn_LastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_LastActionPerformed
        
        pos = getProduct1List().size()-1;
        ShowItem(pos);
    }//GEN-LAST:event_Btn_LastActionPerformed

    private void Btn_NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_NextActionPerformed
       
        pos++;
        
        if(pos >= getProduct1List().size())
        {
            pos = getProduct1List().size()-1;
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

    private void txt1_eMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt1_eMailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt1_eMailActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        Main_Windows1 obj=new Main_Windows1();
        obj.setVisible(true);
        dispose();
        
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Main_Windows1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main_Windows1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main_Windows1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main_Windows1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main_Windows1().setVisible(true);
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
    private javax.swing.JTable JTable_Faculty;
    private javax.swing.JComboBox<String> combo1_department;
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
    private javax.swing.JTextField txt1_contactno;
    private javax.swing.JTextField txt1_eMail;
    private com.toedter.calendar.JDateChooser txt1_joiningDate;
    private com.toedter.calendar.JDateChooser txt_DOB;
    private javax.swing.JLabel txt_branch;
    private javax.swing.JTextField txt_eMail;
    private javax.swing.JTextField txt_firstName;
    private javax.swing.JLabel txt_gender;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_lastName;
    // End of variables declaration//GEN-END:variables
}
