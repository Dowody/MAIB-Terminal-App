public class User {

    private String username;
    private String password;

    private String full_name;
    private String date_of_birth;
    private String phone_number;


    public User (String username, String password, String full_name, String date_of_birth, String phone_number)
    {
        this.username = username;
        this.password = password;

        this.full_name = full_name;
        this.date_of_birth = date_of_birth;
        this.phone_number = phone_number;
    }

    public String getUsername()
    {
        return username;
    }
    public String getPassword() { return password; }
    public String getFullName() { return full_name; }
    public String getDateOfBirth() { return date_of_birth; }
    public String getPhoneNumber() { return phone_number; }
}
