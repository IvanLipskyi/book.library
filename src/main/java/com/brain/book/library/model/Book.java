package com.brain.book.library.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Year;

@Entity
@Table(name = "book")
@NoArgsConstructor
@Data
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "pages", nullable = false)
    private Integer pagesCount;

    @Column
    private Year releaseDate;

    @Column
    private String booking;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @Column (name = "genre", nullable = false)
    @Enumerated(EnumType.STRING)
    private GenreEnum genre;

}
