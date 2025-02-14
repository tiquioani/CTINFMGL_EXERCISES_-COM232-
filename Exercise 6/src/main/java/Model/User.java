package Model;

import javafx.beans.property.SimpleStringProperty;

public class User {
    private final SimpleStringProperty username;
    private final SimpleStringProperty password;
    private final SimpleStringProperty accountcreated;
    private final SimpleStringProperty accountstatus;

    public User(String uname, String pword, String datecreated, String astatus){
        this.username = new SimpleStringProperty(uname);
        this.password = new SimpleStringProperty(pword);
        this.accountcreated = new SimpleStringProperty(datecreated);
        this.accountstatus = new SimpleStringProperty(astatus);
    }

    //getters and setters
    public String getUsername(){
        return username.get();
    }

    public String getPassword(){
        return password.get();
    }

    public String getAccountcreated(){
        return accountcreated.get();
    }

    public String getAccountstatus(){
        return accountstatus.get();
    }
}