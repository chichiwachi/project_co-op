package com.project.CardDataCollectionSystem.Entity;

// import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
// import javax.persistence.Temporal;
// import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "TIME")

public class Time {

    @Id
    @SequenceGenerator(name = "Time_seq", sequenceName = "Time_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Time_seq")
    @Column(name = "TIME_ID", unique = true, nullable = true)
    private Long id;

    private @NotNull String time;

    // @Temporal(TemporalType.DATE)
    // private @NotNull Date date;
}