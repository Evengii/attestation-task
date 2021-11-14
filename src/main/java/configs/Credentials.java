package configs;

public class Credentials {
    public String login;
    public String password;

    public Credentials(String login, String password){
        this.login = login;
        this.password = password;
    }

    public static Credentials fundManagerCreds(){
        return new Credentials("tstprsn1@mail.crom","qwerty");
    }

    public static Credentials companyUser1Creds(){
        return new Credentials("kathysmartpreston@gmail.com","kathysmartpreston");
    }

    public static Credentials companyUser2Creds(){
        return new Credentials("asdasd123@email.con","qwerty");
    }

    public static Credentials companyUser3Creds(){
        return new Credentials("123454@email.con","qwerty");
    }
}