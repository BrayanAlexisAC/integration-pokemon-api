package com.integration.pokemon.api.persistence.entity;

import com.integration.pokemon.api.persistence.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "SOAP_SERVICES_HISTORY")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SoapServicesHistoryEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "ip_origin", length = 15, nullable = false)
    private String ipOrigin;

    @Column(name = "method", length = 50, nullable = false)
    private String method;

}
