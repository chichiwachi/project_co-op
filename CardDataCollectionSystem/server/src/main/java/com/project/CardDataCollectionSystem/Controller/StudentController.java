// package com.project.CardDataCollectionSystem.Controller;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// // import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.project.CardDataCollectionSystem.Entity.Student;
// import com.project.CardDataCollectionSystem.Repository.StudentRepository;

// @CrossOrigin(origins = "http://localhost:8080")
// @RestController

// public class StudentController {
//     @Autowired 
//     private final  StudentRepository studentRepository;
    
//     public StudentController(  StudentRepository studentRepository) {
//         this.studentRepository = studentRepository;
//     }
    
//     @GetMapping("/student")
//     public List<Student> getStudents() {
//         return studentRepository.findAll();
//     }

//     @GetMapping("/student/{id}")
//     public  Student getStudentId(@PathVariable String id) {
//         return studentRepository.findByIdS(id);
//     }

//     @GetMapping("/student/{name}")
//     public Student getStudentName(@PathVariable String name) {
//         return studentRepository.findByName(name);
//     }

//     // @PostMapping("/student/{id}/{name}")
//     // public  Student newStudent(  Student newStudent,
//     //                     @PathVariable String id,
//     //                     @PathVariable String name) {
                            
//     //     newStudent.setId(id);
//     //     newStudent.setName(name);

//     //     return studentRepository.save(newStudent);
//     // }
// }