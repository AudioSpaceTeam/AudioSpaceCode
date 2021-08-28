package com.audiospace.demo.repositories;

import com.audiospace.demo.models.Genre;
import com.audiospace.demo.models.Genre2;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Genre2Repository extends JpaRepository <Genre2, Long> {
    Genre2 findGenre2ByGenreName(String genreName);
}
