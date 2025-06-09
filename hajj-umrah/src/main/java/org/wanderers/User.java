package org.wanderers;

public class User extends AbstractUser {

    private int passportID;
<<<<<<< HEAD
    private int visaID;
    private boolean medicalApprovalStatus;
    private String packageName;

    public User() {
    }

    public User(String userID, String name, String password, int noPhone, String email) {
        this.userID = userID;
        this.name = name;
        this.password = password;
        this.noPhone = noPhone;
        this.email = email;
    }

    public int getPassportID() {
        return passportID;
    }

    public void setPassportID(int passportID) {
        this.passportID = passportID;
    }

    public int getVisaID() {
        return visaID;
    }

    public void setVisaID(int visaID) {
        this.visaID = visaID;
    }

    public boolean getMedicalApprovalStatus() {
        return medicalApprovalStatus;
    }

    public void setMedicalApprovalStatus(boolean medicalApprovalStatus) {
        this.medicalApprovalStatus = medicalApprovalStatus;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }


    public String toFileFormat() {

        return "USER|" +
               userID + "|" +
               name + "|" +
               password + "|" +
               Integer.toString(noPhone) + "|" +
               email + "|" +
=======
    private int visaID; 
    private boolean medicalApprovalStatus; 
    private String packageName;
    private String gender;  

    public User() {

    }

    public User(String userID, String name, String password, int noPhone, String email, String gender) {
        this.userID = userID;
        this.name = name; 
        this.password = password; 
        this.noPhone = noPhone; 
        this.email = email; 
        this.gender = gender;  
    }

    public int getPassportID() {

        return passportID;  
    }

    public int getVisaID() {

        return visaID; 
    }

    public boolean getMedicalApprovalStatus() {

        return medicalApprovalStatus; 
    }

    public String getPackageName() {

        return packageName; 
    }

    public void setVisaID(int visaID) {
        this.visaID = visaID; 
    }

    public String toFileFormat() {
        
        return "USER|" + 
               userID + "|" +
               name + "|" + 
               password + "|" + 
               Integer.toString(noPhone) + "|" +
               email + "|" +
               gender + "|" +
>>>>>>> 2f35afc741aabe6bddf2795e06a9cfbcdf2e9903
               Integer.toString(passportID) + "|" +
               Integer.toString(visaID) + "|" +
               Boolean.toString(medicalApprovalStatus) + "|" +
               packageName + "|";
        //format for storing in Data.txt
<<<<<<< HEAD
        //USER|userID|name|password|noPhone|email|passportID|visaID|medicalApprovalStatus|packageName
    }

   
    public static User fromFileFormat(String line) {
        String[] data = line.split("\\|"); 
        if (data.length >= 6 && data[0].trim().equals("USER")) { // Ensure enough elements and deosnt mix up with tracking and user
            String userID = data[1].trim();
            String name = data[2].trim();
            String password = data[3].trim();
            int noPhone = Integer.parseInt(data[4].trim());
            String email = data[5].trim();

            return new User(userID, name, password, noPhone, email);
        }
        return null; 
    }
}
=======
        //USER|userID|name|password|noPhone|email|passportID|visaID|medicalApprovalStatus|packageName 
    }

    public User fromFileFormat(String line) {
        
        String[] data = line.split("|");

        String userID = data[1].trim(); 
        String name = data[2].trim(); 
        String password = data[3].trim(); 
        int noPhone = Integer.parseInt(data[4]); 
        String email = data[5].trim(); 
        String gender = data[6].trim(); 


        return new User(userID, name, password, noPhone, email, gender); 
       //return Account instance 
    }
   

}
>>>>>>> 2f35afc741aabe6bddf2795e06a9cfbcdf2e9903
