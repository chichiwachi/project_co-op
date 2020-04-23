// package com.project.CardDataCollectionSystem.Controller;

// import java.util.List;
// import java.awt.Graphics2D;
// import java.awt.image.BufferedImage;
// import java.io.File;
// import java.io.FileOutputStream;
// import java.io.IOException;
// import java.io.InputStream;
// import java.io.OutputStream;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.multipart.MultipartFile;

// import net.sourceforge.tess4j.ITesseract;
// import net.sourceforge.tess4j.Tesseract;
// import net.sourceforge.tess4j.TesseractException;

// import com.project.CardDataCollectionSystem.Entity.Student;
// // import com.project.CardDataCollectionSystem.Repository.StudentRepository;

// @CrossOrigin(origins = "http://localhost:8080")
// @RestController

// public class StudentController {
//     @PostMapping("/sutdent")

// 	public String DoOCR(@RequestParam("DestinationLanguage") String destinationLanguage,
// 			@RequestParam("Image") MultipartFile image) throws IOException {

		
// 		Student request = new Student();
// 		request.setDestinationLanguage(destinationLanguage);
// 		request.setImage(image);

// 		ITesseract instance = new Tesseract();

// 		try {
			
// 			BufferedImage in = ImageIO.read(convert(image));

// 			BufferedImage newImage = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_ARGB);
            
// 			Graphics2D g = newImage.createGraphics();
// 			g.drawImage(in, 0, 0, null);
// 			g.dispose();
            
// 			instance.setLanguage(request.getDestinationLanguage());
// 			instance.setDatapath("..//tessdata");

// 			String result = instance.doOCR(newImage);

// 			return result;

// 		} catch (TesseractException | IOException e) {
// 			System.err.println(e.getMessage());
// 			return "Error while reading image";
// 		}

// 	}
	
// 	public static File convert(MultipartFile file) throws IOException {
// 	    File convFile = new File(file.getOriginalFilename());
// 	    convFile.createNewFile();
// 	    FileOutputStream fos = new FileOutputStream(convFile);
// 	    fos.write(file.getBytes());
// 	    fos.close();
// 	    return convFile;
// 	}
    
// }