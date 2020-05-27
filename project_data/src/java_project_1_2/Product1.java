package java_project_1_2;

public class Product1 {
    
    private int id;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String eMail;
    private String contactno;
    private String joiningDate;
    private String DOB;
    private String gender;
    private String branch;
    private byte[] picture;
    
    public Product1(int pid, String fname, String lname, String kname, String mname, String prollno, String ladmission, String pAddDate,String pgender,String pbranch, byte[] pimg)
    {
        this.id = pid;
        this.firstName = fname;
        this.lastName = lname;
        this.fatherName = kname;
        this.eMail = mname;
        this.contactno = prollno;
        this.joiningDate = ladmission;
        this.DOB = pAddDate;
        this.gender = pgender;
        this.branch = pbranch;
        this.picture = pimg;
    }
    
    public int getId()
    {
        return id;
    }
    
    public String getFirstName()
    {
        return firstName;
    }
    
        public String getLastName()
    {
        return lastName;
    }
        
        public String getFatherName()
    {
        return fatherName;
    }
        
        public String getEmail()
    {
        return eMail;
    }    
    
    public String getContactno()
    {
        return contactno;
    }
    
    public String getJoiningDate()
    {
        return joiningDate;
    }
    
    public String getDOB()
    {
        return  DOB;
    }
 
    public String getGender()
    {
        return  gender;
    }
    
    public String getBranch()
    {
        return  branch;
    }
    
    public byte[] getImage()
    {
        return picture;
    }
    
}
