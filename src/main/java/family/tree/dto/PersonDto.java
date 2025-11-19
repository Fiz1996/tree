package family.tree.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class PersonDto {
    public Long id;
    public String fullName;
    public LocalDate birthDate;
    public String gender;
    public Long motherId;
    public Long fatherId;
    public Long spouseId;
}
