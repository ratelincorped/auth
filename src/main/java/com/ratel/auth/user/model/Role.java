package com.ratel.auth.user.model;

import com.ratel.auth.common.BaseEntitty;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Role extends BaseEntitty {
    @Column(unique = true)
    private String name;
    @ManyToMany(mappedBy = "roles")
    private List<UserCredential> users;
}
