package entitiy;

public class Customer {
    private String customerId;
    private String firstName;
    private String LastName;
    private String address;
    private String email;
    private String telNo;


    public Customer(String customerId, String firstName, String lastName, String address, String email, String telNo) {
        this.customerId = customerId;
        this.firstName = firstName;
        LastName = lastName;
        this.address = address;
        this.email = email;
        this.telNo = telNo;
    }


    public Customer() {
    }


    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }
}
