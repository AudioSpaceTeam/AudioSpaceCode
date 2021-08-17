package com.audiospace.demo.repositories;

import com.audiospace.demo.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {

  Genre findGenreByGenreName(String genreName);
}
