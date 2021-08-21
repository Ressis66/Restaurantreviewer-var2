package com.ressis.repository;

import com.vahundos.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudMenuRepository extends JpaRepository<Menu, Integer> {

    @Override
    Optional<Menu> findById(Integer integer);

    @Override
    @Transactional
    Menu save(Menu menu);

    @Transactional
    @Modifying
    @Query(value = "MERGE INTO VOTES USING (VALUES (:menuId, :userId)) V (MENU_ID, USER_ID) " +
            "ON (VOTES.USER_ID = V.USER_ID AND (SELECT DATE FROM MENUS WHERE MENUS.ID = VOTES.MENU_ID) = :date) " +
            "WHEN MATCHED THEN UPDATE SET VOTES.MENU_ID=V.MENU_ID " +
            "WHEN NOT MATCHED THEN INSERT (MENU_ID, USER_ID) VALUES (V.MENU_ID, V.USER_ID)",
            nativeQuery = true)
    void makeVote(@Param("menuId") int menuId, @Param("userId") int userId, @Param("date") LocalDate date);
}
