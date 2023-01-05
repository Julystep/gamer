package com.boomshair.gamer.service;

import com.boomshair.gamer.domain.pojo.Game;
import com.boomshair.gamer.domain.res.ResultBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 游戏服务
 * @since v1.0.0
 * @since 2022/12/27
 */
public interface GameService {

    void saveNewGame(MultipartFile file, Game game);

    void updateGame(MultipartFile file, Game game);

    void deleteGame(Integer id);

    ResultBody findGame(Integer yearId);

    ResultBody gameDetail(Integer id);

    ResultBody queryYears();
}
