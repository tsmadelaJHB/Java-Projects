package za.co.wethinkcode.model.app;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class MissingDetails {

    @NotBlank
    private String Id;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

    @NotBlank
    private int age;

    @NotBlank
    private String address;

    @NotBlank
    private String lastSeen;

    @NotBlank
    private String details;

    public String getId(){
        return Id;
    }
    public void setId(String Id){
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(String lastSeen) {
        this.lastSeen = lastSeen;
    }
}