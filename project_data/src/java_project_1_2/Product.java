package java_project_1_2;

public class Product {
    
    private int id;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String motherName;
    private String rollno;
    private String admissionDate;
    private String DOB;
    private String gender;
    private String branch;
    private byte[] picture;
    
    public Product(int pid, String fname, String lname, String kname, String mname, String prollno, String ladmission, String pAddDate,String pgender,String pbranch, byte[] pimg)
    {
        this.id = pid;
        this.firstName = fname;
        this.lastName = lname;
        this.fatherName = kname;
        this.motherName = mname;
        this.rollno = prollno;
        this.admissionDate = ladmission;
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
        
        public String getMotherName()
    {
        return motherName;
    }    
    
    public String getRollno()
    {
        return rollno;
    }
    
    public String getAdmissionDate()
    {
        return admissionDate;
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
