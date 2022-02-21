package com.jb.SpringProject.beans;


import lombok.*;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Scope("prototype")
@Builder
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int catId;

    @Column(nullable = false, length = 40)
    private String name;

    private float weight;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Singular
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Toy> toys;


}
