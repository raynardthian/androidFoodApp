package ece.np.edu.miniproject2_raynardthian;

public class Customer {
    private int id;
    private String Name;
    private String Password;
    private int Age;
    private String Address;

    public Customer(int id, String name, String password, int age, String address) {
        this.id = id;
        Name = name;
        Password = password;
        Age = age;
        Address = address;
    }
    public Customer(){} // Might not need this

    // Getter and Setters

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }
    // To String
    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", Password='" + Password + '\'' +
                ", Age=" + Age +
                ", Address='" + Address + '\'' +
                '}';
    }

    public String getName(String name){
        if(name.equals(Name)){
            return id + Name + Password + Age + Address;
        }
        else
        {
            return name; // idk why
        }
    }
}
