package net.muhammadsaad.rest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categories")
public class Category {
    @Id
    @SequenceGenerator(
            name = "categories_id_seq",
            sequenceName = "categories_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categories_id_seq")
    private long id;

    @Column(unique = true, nullable = false, length = 100)
    private String name;

    private String description;

    @Column(columnDefinition = "BOOLEAN DEFAULT true")
    private boolean active;

    private String imageUri;
}
