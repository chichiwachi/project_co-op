package sut.coop.B5910557.CardDataCollectionSystem.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

// import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "RECORDS")

public class Records {
    @Id
    @SequenceGenerator(name = "Records_seq", sequenceName = "Records_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Records_seq")
    @Column(name = "RECORDS_ID", unique = true, nullable = true)
    private Long id;

    @Temporal(TemporalType.DATE)
    private @NotNull Date recordDate;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Student.class)
    @JoinColumn(name = "STUDENT_ID", insertable = true)
    private @NotNull Student student;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Location.class)
    @JoinColumn(name = "LOCATION_ID", insertable = true)
    private @NotNull Location location;

    public Records(Long id, Date recordDate, Student sid, Student name, Location location){
        this.id = id;
        this.recordDate = recordDate;
        this.student = sid;
        this.student = name;
        this.location = location;
    }

    public Date getRecordDate(){
        return recordDate;
    }

}