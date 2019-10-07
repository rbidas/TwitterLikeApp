package com.esencjal.twitter.like.tags;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TagsRepository extends CrudRepository<TagEntity, Long> {

    @Query(value = "SELECT COUNT(CREATE_TIMESTAMP) as count, TO_CHAR(\"CREATE_TIMESTAMP\",'YYYY-MM-DD') as date, TAG as tag\n" +
            "FROM \"TAGS\"\n" +
            "where TAG=:tagName and CREATE_TIMESTAMP BETWEEN :startDate AND :endDate\n" +
            "GROUP BY TO_CHAR(\"CREATE_TIMESTAMP\",'YYYY-MM-DD'), TAG\n" +
            "ORDER BY TO_CHAR(\"CREATE_TIMESTAMP\",'YYYY-MM-DD'), TAG desc",
            nativeQuery = true)
    List<Object[]> findCountPerDay(@Param("tagName") String tagName, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    Page<TagEntity> findAllByTag(Pageable var1, String name);

}
