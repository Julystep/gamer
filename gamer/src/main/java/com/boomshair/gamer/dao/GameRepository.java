package com.boomshair.gamer.dao;

import com.boomshair.gamer.domain.pojo.Game;
import com.boomshair.gamer.domain.pojo.Year;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 查询
 * @since v1.0.0
 * @since 2022/12/27
 */
public interface GameRepository extends JpaRepository<Game, Integer> {


    @Query(nativeQuery = true, value = "select a.id, a.game_name, a.chinese_name, a.picture_path, a.disk_path, a.platform, b.year, a.played_time, a.comments, a.stars, a.create_time from game a, year b where a.year = b.id")
    List<Game> findAll();

    @Query(nativeQuery = true, value = "select a.id, a.game_name, a.chinese_name, a.picture_path, a.disk_path, a.platform, b.year, a.played_time, a.comments, a.stars, a.create_time from game a, year b where a.year = b.id and a.id=(?1)")
    Game findGameById(Integer id);

    @Query(nativeQuery = true, value = "select a.id, a.game_name, a.chinese_name, a.picture_path, a.disk_path, a.platform, b.year, a.played_time, a.comments, a.stars, a.create_time from game a, year b where a.year = b.id and b.id=(?1)")
    List<Game> findGamesByYearOrderByYearDesc(Integer year);

    @Override
    @Modifying
    void deleteById(Integer integer);
}
