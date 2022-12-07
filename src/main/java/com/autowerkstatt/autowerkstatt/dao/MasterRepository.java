package com.autowerkstatt.autowerkstatt.dao;

import com.autowerkstatt.autowerkstatt.entity.Master;
import com.autowerkstatt.autowerkstatt.enums.Faults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MasterRepository extends JpaRepository<Master, Long> {

    @Query("select m from Master m where m.firstName = :firstName")
    Master findByFirstName(@Param("firstName") String firstName);

}
