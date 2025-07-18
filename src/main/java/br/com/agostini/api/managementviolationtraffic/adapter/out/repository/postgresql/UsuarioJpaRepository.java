package br.com.agostini.api.managementviolationtraffic.adapter.out.repository.postgresql;

import br.com.agostini.api.managementviolationtraffic.application.domain.model.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, UUID> {
    Optional<UsuarioEntity> findByEmail(String email);
}