package ma.enset.streamservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Operation {
    private long numero;
    private Date date;
    private double montant;
    private String type;
    private long compte;
}
