package find.itTeam.entity;

import lombok.Data;
import lombok.experimental.Accessors;


import javax.persistence.*;
@Entity
@Table(schema = "finditteam_second", name = "rating")
@Data
@Accessors(chain = true)

public class RatingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rating")
    private int rating;

    @JoinColumn(name = "user_id")
    @OneToOne(cascade = CascadeType.PERSIST)
    private UserEntity user;

    @JoinColumn(name = "dev_id")
    @OneToOne(cascade = CascadeType.PERSIST)
    private DeveloperEntity developer;
}

