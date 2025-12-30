
package Group4_3w7;

public class Customer {
   // Passenger Class properties :
    private String FullName;
    private int ID;
    private int Age;
    private String Gender;
    private String Email;
    private String phoneNumber;
    private SinglyLinkedList<Customer> CustomerSLL = new SinglyLinkedList<>();

    public Customer(String FullName, int ID, int Age, String Gender, String Email, String phoneNumber) {
        this.FullName = FullName;
        this.ID = ID;
        this.Age = Age;
        this.Gender = Gender;
        this.Email = Email;
        this.phoneNumber = phoneNumber;
    }


    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public SinglyLinkedList<Customer> getCustomerSLL() {
        return CustomerSLL;
    }

    public void setCustomerSLL(SinglyLinkedList<Customer> CustomerSLL) {
        this.CustomerSLL = CustomerSLL;
    }
   
    
    @Override
    public String toString() {
        return "\n-------------------------------------" 
                + "\nCustomer: " 
                + "\nFullName:" + FullName 
                + "\nID:" + ID 
                + "\nAge:" + Age 
                + "\nGender:" + Gender 
                + "\nEmail:" + Email 
                + "\nphoneNumber: " + phoneNumber
                + "\n-------------------------------------\n";
    }

}
