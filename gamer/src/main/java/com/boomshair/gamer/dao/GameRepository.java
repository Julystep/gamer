package com.boomshair.gamer.dao;

import com.boomshair.gamer.domain.pojo.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 查询
 * @since v1.0.0
 * @since 2022/12/27
 */
public interface GameRepository extends JpaRepository<Game, Integer> {

    Game findGameById(Integer id);

    List<Game> findGamesByYear(Integer year);

}
