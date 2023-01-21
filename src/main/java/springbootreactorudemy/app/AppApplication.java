package springbootreactorudemy.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class AppApplication implements CommandLineRunner {

	Logger log = LoggerFactory.getLogger(AppApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Flux<String> nombres = Flux.just("Alberto", "Luis", "Roberto", "Daniel")
						.doOnNext(nombre -> {
							if (nombre.isEmpty()){
								throw new RuntimeException("Name can not be empty.");
							}
							System.out.println(nombre);
						});

		nombres.subscribe(e->log.info(e),
						error -> log.error(error.getMessage()),
						new Runnable() {
							@Override
							public void run() {
								log.info("Suscriber executed completely.");
							}
						});
	}
}
