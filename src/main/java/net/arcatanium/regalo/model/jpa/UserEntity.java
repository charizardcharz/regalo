package net.arcatanium.regalo.model.jpa;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Data
@Table(name = UserEntity.USER_TABLE_NAME)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity extends BaseEntity{
    public static final String USER_TABLE_NAME = "USERS";

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "USER_ID")
    @EqualsAndHashCode.Include
    private UUID userId;

    @Column(name = "USERNAME")
    private String userName;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @ToString.Exclude
    private List<WishlistEntity> wishlistEntityList;

}
