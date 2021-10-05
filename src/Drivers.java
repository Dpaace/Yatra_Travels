public class Drivers {
    String fullname;
    String email;
    String dob;
    String gender;
    String address;
    String experience;

    public Drivers(String fullname, String email, String dob, String gender, String address, String experience) {
        this.fullname = fullname;
        this.email = email;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.experience = experience;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }
}
