// package com.project.CardDataCollectionSystem.Entity;


// import org.springframework.web.multipart.MultipartFile;

// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.Id;
// import javax.persistence.Table;
// import javax.validation.constraints.NotNull;

// import lombok.Data;
// import lombok.NoArgsConstructor;

// @Data
// @Entity
// @NoArgsConstructor
// @Table(name = "STUDENT")

// public class Student {

//     @Id
//     @Column(name = "STUDENT_ID", unique = true, nullable = true)
//     private String id;

//     private @NotNull String name;

//     private String DestinationLanguage;

// 	private MultipartFile Image;

// 	public Student(String DestinationLanguage, MultipartFile Image) {
// 		this.DestinationLanguage = DestinationLanguage;
// 		this.Image = Image;

// 	}

// 	public String getDestinationLanguage() {
// 		return DestinationLanguage;
// 	}

// 	public void setDestinationLanguage(String destinationLanguage) {
// 		DestinationLanguage = destinationLanguage;
// 	}

// 	public MultipartFile getImage() {
// 		return Image;
// 	}

// 	public void setImage(MultipartFile image) {
// 		Image = image;
// 	}
// }