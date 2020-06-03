package sut.coop.B5910557.CardDataCollectionSystem.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "STUDENT")

public class Student {
    @Id
    @SequenceGenerator(name = "Student_seq",sequenceName = "Student_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Student_seq")
    @Column(name = "Student_ID", unique = true, nullable = true)
    private Long id;
    
    @Column(unique = true)
    private @NotNull String sid;
    
    @Size(min = 5, max = 30)
    @Pattern(regexp = "\\D*")
    private @NotNull String name;

    public Student(Long id,String sid, String name){
        this.id = id;
        this.sid = sid;
        this.name = name;
    }

    public String getSid(){
        return sid;
    }

    public String getName(){
        return name;
    }
}