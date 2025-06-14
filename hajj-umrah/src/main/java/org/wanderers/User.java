package org.wanderers;

public class User extends AbstractUser {

    private int passportID;
    private int visaID; 
    private boolean medicalApprovalStatus; 
    private String packageName;

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
               Integer.toString(passportID) + "|" +
               Integer.toString(visaID) + "|" +
               Boolean.toString(medicalApprovalStatus) + "|" +
               packageName + "|";
        //format for storing in Data.txt
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
            String gender = data[6].trim(); 

            return new User(userID, name, password, noPhone, email, gender);
        }
        return null; 
    }
}
