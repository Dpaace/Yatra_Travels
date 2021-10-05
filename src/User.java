public class User {
    private String username;
    private String mobileNumber;
    private String userEmail;
    private String userGender;
    private String userDob;
    private String userAddress;
    private String password1;
    private String password2;

    public User(){}

    public User(String username, String mobileNumber, String userEmail, String userGender, String userDob, String userAddress, String password1, String password2) {
        this.username = username;
        this.mobileNumber = mobileNumber;
        this.userEmail = userEmail;
        this.userGender = userGender;
        this.userDob = userDob;
        this.userAddress = userAddress;
        this.password1 = password1;
        this.password2 = password2;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getMobileNumber() {
        return mobileNumber;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public String getUserGender() {
        return userGender;
    }
    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }
    public String getUserDob() {
        return userDob;
    }
    public void setUserDob(String userDob) {
        this.userDob = userDob;
    }
    public String getUserAddress() {
        return userAddress;
    }
    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
    public String getPassword1() {
        return password1;
    }
    public void setPassword1(String password1) {
        this.password1 = password1;
    }
    public String getPassword2() {
        return password2;
    }
    public void setPassword2(String password2) {
        this.password2 = password2;
    }
}