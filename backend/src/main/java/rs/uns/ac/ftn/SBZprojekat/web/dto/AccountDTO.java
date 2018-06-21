package rs.uns.ac.ftn.SBZprojekat.web.dto;

public class AccountDTO {

    private String username;

    private String ime;

    private String prezime;

    public AccountDTO(){}

    public AccountDTO(String username, String ime, String prezime) {
        this.username = username;
        this.ime = ime;
        this.prezime = prezime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
}
