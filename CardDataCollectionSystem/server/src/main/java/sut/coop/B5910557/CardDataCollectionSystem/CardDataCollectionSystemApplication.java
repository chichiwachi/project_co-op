package sut.coop.B5910557.CardDataCollectionSystem;

import java.util.Collections;
import java.util.stream.Stream;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import sut.coop.B5910557.CardDataCollectionSystem.Entity.Location;
import sut.coop.B5910557.CardDataCollectionSystem.Entity.Student;
import sut.coop.B5910557.CardDataCollectionSystem.Repository.LocationRepository;
import sut.coop.B5910557.CardDataCollectionSystem.Repository.StudentRepository;

@SpringBootApplication
public class CardDataCollectionSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardDataCollectionSystemApplication.class, args);
	}

	@Bean
	ApplicationRunner init( final LocationRepository locationRepository,
							final StudentRepository studentRepository) {

		return args -> {
			Stream.of("Classroom Building 1","Classroom Building 2","Equipment Building 1",
					"Equipment Building 2","Equipment Building 3","Equipment Building 4",
					"Equipment Building 5","Equipment Building 6","Equipment Building 7",
					"Equipment Building 8","Equipment Building 9","Equipment Building 10",
					"Equipment Building 11","Academic Building 1","Academic Building 2").forEach(newLocation -> {
				final Location location = new Location();
				location.setLocation(newLocation);
				locationRepository.save(location);
			});
			Stream.of(1).forEach(newStudent -> {
				Student student = new Student();
				//student.setId(Long.valueOf(1));
				student.setSid("B5910555");
				student.setName("wachi chai");
				studentRepository.save(student);
			});

			locationRepository.findAll().forEach(System.out::println);
			studentRepository.findAll().forEach(System.out::println);
		};
	}

	@Bean
	public FilterRegistrationBean simpleCorsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		// *** URL below needs to match the Vue client URL and port ***
		config.setAllowedOrigins(Collections.singletonList("http://localhost:8080"));
		config.setAllowedMethods(Collections.singletonList("*"));
		config.setAllowedHeaders(Collections.singletonList("*"));
		source.registerCorsConfiguration("/**", config);
		final FilterRegistrationBean bean = new FilterRegistrationBean<>(new CorsFilter(source));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
}
