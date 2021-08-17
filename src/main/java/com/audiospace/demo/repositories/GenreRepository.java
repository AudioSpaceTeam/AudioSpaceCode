package com.audiospace.demo.repositories;

import com.audiospace.demo.models.Event;
import com.audiospace.demo.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {

  Genre findGenreByGenreName(String genreName);

}
