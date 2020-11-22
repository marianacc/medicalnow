package com.ucb.medicalnow.Interfaces;

import java.util.Optional;

import com.ucb.medicalnow.Model.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ImageRepository extends JpaRepository<ImageModel, Long> {
    Optional<ImageModel> findByName(String name);
}
