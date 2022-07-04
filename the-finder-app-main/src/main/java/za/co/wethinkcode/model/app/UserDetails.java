package za.co.wethinkcode.model.app;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserDetails {

    @NotBlank
    private String username;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private int age;

    @NotBlank
    private String contactNumber;

    @NotBlank
    private String address;

    @NotBlank
    private String lastSeen;

    @NotBlank
    private String details;


    @NotBlank
    @Size(min = 8, max = 64, message = "Password must be 8-64 char long")
    private String password;


    public String getUsername(){return username;}
    public void setUsername(String username){this.username = username;}

    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}

    public int getAge() {return age;}
    public void setAge(int age) {this.age = age;}

    public String getContactNumber() {return contactNumber;}
    public void setContactNumber(String contactNumber) {this.contactNumber = contactNumber;}

    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}

    public String getDetails() {return details;}
    public void setDetails(String details) {this.details = details;}

    public String getLastSeen() {return lastSeen;}
    public void setLastSeen(String lastSeen) {this.lastSeen = lastSeen;}

}
