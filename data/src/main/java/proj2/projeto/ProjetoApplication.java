package proj2.projeto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import proj2.projeto.entities.Cliente.Cliente;
import proj2.projeto.entities.Cliente.ClienteRepository;
import proj2.projeto.entities.Cliente.TipoCliente;
import proj2.projeto.entities.Cliente.TipoClienteRepository;
import proj2.projeto.entities.CodPostal.CodPostal;
import proj2.projeto.entities.CodPostal.CodPostalRepository;

@SpringBootApplication
public class ProjetoApplication {




	public static void main(String[] args) {
		SpringApplication.run(ProjetoApplication.class, args);

	}


	@Bean
	public CommandLineRunner commandLineRunner1(ClienteRepository clienteRepository, CodPostalRepository codPostalRepository, TipoClienteRepository tipoClienteRepository){
		return (args ->{
			var codPostal = new CodPostal(4935,"Viana");
			codPostalRepository.save(codPostal);
			var tipoCli = new TipoCliente("Lazy");
			tipoClienteRepository.save(tipoCli);
			var cliente = new Cliente("Miguel","mail.com",456789,"password","rua das ruas",69,codPostal,tipoCli);
			clienteRepository.save(cliente);//CRIAR OU ATUALIZAR
		});
	}

}
