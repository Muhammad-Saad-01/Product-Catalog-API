package net.muhammadsaad.rest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "brands")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Brand {
    @Id
    @SequenceGenerator(
            name = "brands_id_seq",
            sequenceName = "brands_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brands_id_seq")
    private long id;

    @Column(unique = true, nullable = false, length = 100)
    private String name;

    private String about;

    private String imageUri;

    private String brandWebsiteUri;

    private boolean active = true;

}
