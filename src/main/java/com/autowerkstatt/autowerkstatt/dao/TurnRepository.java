package com.autowerkstatt.autowerkstatt.dao;

import com.autowerkstatt.autowerkstatt.entity.Turn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurnRepository extends JpaRepository<Turn, Long> {

    @Query(value = "select * from turn " +
            "inner join notification n on n.id = turn.notification_id " +
            "where faults = 'HODOVKA' and n.status <> 'DENIED'", nativeQuery = true)
    List<Turn> findTurnByStatus();

    @Query(value = "select * from turn " +
            "inner join notification n on n.id = turn.notification_id " +
            "where faults = 'ELECTRICIAN' and n.status <> 'DENIED'", nativeQuery = true)
    List<Turn> findTurnsBy();

    @Query(value = "select * from turn " +
            "inner join notification n on n.id = turn.notification_id " +
            "where faults = 'INTERNAL_COMBUSTION_ENGINE' and n.status <> 'DENIED'", nativeQuery = true)
    List<Turn> findTurnAndStatus();

    @Query(value = "select * from turn " +
            "inner join notification n on n.id = turn.notification_id " +
            "where faults = 'MORE' and n.status <> 'DENIED'", nativeQuery = true)
    List<Turn> findTurnByStatusAndNotification();

    @Query(value = "select * from turn " +
            "inner join notification n on n.id = turn.notification_id " +
            "where n.status in ('QUEUE', 'WORKING', 'DONE')", nativeQuery = true)
    List<Turn> findTurnByStatusAndNotificationStatus();
}
