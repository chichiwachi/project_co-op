package com.project.CardDataCollectionSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.Collections;
import java.util.stream.Stream;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.project.CardDataCollectionSystem.Entity.Location;
import com.project.CardDataCollectionSystem.Entity.Time;
import com.project.CardDataCollectionSystem.Repository.TimeRepository;
import com.project.CardDataCollectionSystem.Repository.LocationRepository;

@SpringBootApplication
public class CardDataCollectionSystemApplication {

	public static void main(final String[] args) {
		SpringApplication.run(CardDataCollectionSystemApplication.class, args);
	}

	@Bean
	ApplicationRunner init( final LocationRepository locationRepository,
							final TimeRepository timeRepository) {

		return args -> {
			Stream.of("อาคารเรียนรวม 1","อาคารเรียนรวม 2","อาคารเครื่องมือ 1",
					"อาคารเครื่องมือ 2","อาคารเครื่องมือ 3","อาคารเครื่องมือ 4",
					"อาคารเครื่องมือ 5","อาคารเครื่องมือ 6","อาคารเครื่องมือ 7",
					"อาคารเครื่องมือ 8","อาคารเครื่องมือ 9","อาคารเครื่องมือ 10",
					"อาคารเครื่องมือ 11","อาคารวิชาการ 1","อาคารวิชาการ 2").forEach(newLocation -> {
				final Location location = new Location();
				location.setLocation(newLocation);
				locationRepository.save(location);
			});
			Stream.of("00:00-03:59",
					"04:00-07:59",
					"08:00-11:59",
					"12:00-15:59",
					"16:00-19:59",
					"20:00-23:59").forEach(newTime -> {
				final Time time = new Time(); 
				time.setTime(newTime); 
				timeRepository.save(time); 
			});

			locationRepository.findAll().forEach(System.out::println);
			timeRepository.findAll().forEach(System.out::println);
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
