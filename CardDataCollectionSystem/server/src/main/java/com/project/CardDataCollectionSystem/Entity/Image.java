package com.project.CardDataCollectionSystem.Entity;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "IMAGE")
public class Image {

    @Id
    @SequenceGenerator(name = "Image_seq", sequenceName = "Image_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Image_seq")
    @Column(name = "IMAGE_ID", unique = true, nullable = true)
    private Long id;

    private String fileName;

    private String fileType;

    @Lob
    private byte[] data;

    public Image(String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }
}