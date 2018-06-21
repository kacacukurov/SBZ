package rs.uns.ac.ftn.SBZprojekat.web.dto;

public class TokenDTO {

    String value;


    public TokenDTO() {
    }

    public TokenDTO(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
