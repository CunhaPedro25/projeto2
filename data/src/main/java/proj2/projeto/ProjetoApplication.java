package proj2.projeto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import proj2.projeto.entities.Cliente.Cliente;
import proj2.projeto.entities.Cliente.ClienteRepository;

@SpringBootApplication
public class ProjetoApplication {

	@Autowired
 private ClienteRepository clienteRepository;



	public static void main(String[] args) {
		SpringApplication.run(ProjetoApplication.class, args);

	}


	@Bean
	public CommandLineRunner commandLineRunner1(ClienteRepository clienteRepository){
		return (args ->{
			var cliente = new Cliente();
			clienteRepository.save(cliente);//CRIAR OU ATUALIZAR
			clienteRepository.delete(cliente);
			clienteRepository.findById(cliente.getId());
		});
	}

}
