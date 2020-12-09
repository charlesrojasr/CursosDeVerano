package com.bytecode.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bytecode.core.models.entity.Profesor;

@Repository("profesordao")
public interface ProfesorDao  extends JpaRepository<Profesor, Integer>{

}
