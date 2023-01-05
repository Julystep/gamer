package com.boomshair.gamer.dao;

import com.boomshair.gamer.domain.pojo.Year;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * 年份的数据库操作
 * @since v
 * @since 2023/1/5
 */
public interface YearRepository extends JpaRepository<Year, Integer> {

    Optional<Year> findById(Integer id);

    List<Year> findAllByOrderByYearDesc();

}

