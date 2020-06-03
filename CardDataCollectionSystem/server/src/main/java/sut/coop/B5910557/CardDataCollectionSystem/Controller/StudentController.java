package sut.coop.B5910557.CardDataCollectionSystem.Controller;

import java.util.List;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept.PIX;
import org.bytedeco.javacpp.tesseract.TessBaseAPI;
import static org.bytedeco.javacpp.lept.pixReadMem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import sut.coop.B5910557.CardDataCollectionSystem.Entity.Student;
import sut.coop.B5910557.CardDataCollectionSystem.Repository.StudentRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController

public class StudentController {
    @Autowired 
    private StudentRepository studentRepository;

    @GetMapping("/student")
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable long id) {
        return studentRepository.findById(id);
    }

    @GetMapping("/studentId/{sid}")
    public Student getStudent(@PathVariable String sid) {
        return studentRepository.findBySid(sid);
    }

    @GetMapping("/student/{name}")
    public Student getStudentName(@PathVariable String name) {
        return studentRepository.findByName(name);
    }

    @PostMapping("/student/{sid}/{name}")
    public Student newStudent( Student newStudent,
        @PathVariable String sid,
        @PathVariable String name) {
            newStudent.setSid(sid);
            newStudent.setName(name);

        return studentRepository.save(newStudent);
    }

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public String handleFileUpload(@RequestParam("file") final MultipartFile file) {

        String message = "";
        try {

            String outpuString = tesseract_ocr(file);  
             
            message = "You successfully uploaded " + file.getOriginalFilename() + "!";
            return outpuString;

        } catch (final Exception e) {
            message = "FAIL to upload " + file.getOriginalFilename() + "!";
            return message;
        }
    }

    public String tesseract_ocr(final MultipartFile file) throws Exception {
        BytePointer outText;
        final TessBaseAPI api = new TessBaseAPI();

        // Initialize tesseract-ocr with English, without specifying tessdata path
        api.Init("./tessdata", "tha+eng");

        // Open input image with leptonica library
        final PIX image = pixReadMem(file.getBytes(), file.getSize());
        api.SetImage(image);
        // Get OCR result
        outText = api.GetUTF8Text();
        final String string = outText.getString();
        System.out.println("OCR output:\n" + string);
        return string;
    }
}