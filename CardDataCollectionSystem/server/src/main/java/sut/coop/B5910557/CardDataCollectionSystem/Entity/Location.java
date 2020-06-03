package sut.coop.B5910557.CardDataCollectionSystem.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "LOCATION")

public class Location {
    @Id
    @SequenceGenerator(name = "Location_seq", sequenceName = "Location_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Location_seq")
    @Column(name = "LOCATION_ID", unique = true, nullable = true)
    private Long id;

    private @NotNull String location;

    public Location(Long id, String location){
        this.id = id;
        this.location = location;
    }
    
    public String getLocation(){
        return location;
    }
}