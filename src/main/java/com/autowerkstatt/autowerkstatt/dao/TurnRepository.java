package com.autowerkstatt.autowerkstatt.dao;

import com.autowerkstatt.autowerkstatt.entity.Turn;
import com.autowerkstatt.autowerkstatt.enums.Faults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurnRepository extends JpaRepository<Turn, Long> {

    @Query(value = "select * from turn " +
            "inner join notification n on n.id = turn.notification_id " +
            "where faults = 'HODOVKA' and turn.status = 'QUEUE'", nativeQuery = true)
    List<Turn> findTurnByStatus();

    @Query(value = "select * from turn " +
            "inner join notification n on n.id = turn.notification_id " +
            "where faults = 'ELECTRICIAN' and turn.status = 'QUEUE'", nativeQuery = true)
    List<Turn> findTurnsBy();

    @Query(value = "select * from turn " +
            "inner join notification n on n.id = turn.notification_id " +
            "where faults = 'INTERNAL_COMBUSTION_ENGINE' and turn.status = 'QUEUE'", nativeQuery = true)
    List<Turn> findTurnAndStatus();

    @Query(value = "select * from turn " +
            "inner join notification n on n.id = turn.notification_id " +
            "where faults = 'MORE' and turn.status = 'QUEUE'", nativeQuery = true)
    List<Turn> findTurnByStatusAndNotification();

    @Query(value = "select * from turn " +
            "inner join notification n on n.id = turn.notification_id " +
            "where n.status in ('QUEUE', 'WORKING', 'DONE') and n.user_id = :user_id", nativeQuery = true)
    List<Turn> findTurnByStatusAndNotificationStatus(@Param(value = "user_id") Long userId);

    @Query(value = "select * from turn t " +
            "inner join notification n on n.id = t.notification_id " +
            "where t.status = 'WORKING'", nativeQuery = true)
    List<Turn> findTurnsByStatus();
}
