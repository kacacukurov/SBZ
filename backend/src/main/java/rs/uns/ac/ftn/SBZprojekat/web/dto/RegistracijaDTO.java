package rs.uns.ac.ftn.SBZprojekat.web.dto;

public class RegistracijaDTO {

    private LoginDTO loginAccount;

    private String ime;

    private String prezime;

    public RegistracijaDTO(){}

    public RegistracijaDTO(LoginDTO loginAccount, String ime, String prezime) {
        this.loginAccount = loginAccount;
        this.ime = ime;
        this.prezime = prezime;
    }

    public LoginDTO getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(LoginDTO loginAccount) {
        this.loginAccount = loginAccount;
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
