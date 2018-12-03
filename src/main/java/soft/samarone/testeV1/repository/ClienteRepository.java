package soft.samarone.testeV1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import soft.samarone.testeV1.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {}
