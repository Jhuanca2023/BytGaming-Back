package gaming.pe;

import gaming.pe.Entity.PermissionEntity;
import gaming.pe.Entity.RoleEntity;
import gaming.pe.Entity.UserEntity;
import gaming.pe.Enums.RoleEnum;
import gaming.pe.Repository.UsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class BytGaminGApplication {

	public static void main(String[] args) {
		SpringApplication.run(BytGaminGApplication.class, args);
	}
}
