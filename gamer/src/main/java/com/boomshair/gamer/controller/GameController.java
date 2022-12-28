package com.boomshair.gamer.controller;

import com.boomshair.gamer.domain.pojo.Game;
import com.boomshair.gamer.domain.res.ResultBody;
import com.boomshair.gamer.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 接口定义
 * @since v1.0.0
 * @since 2022/12/27
 */
@RestController
public class GameController {

    GameService gameService;

    @Autowired
    public void setGameService(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/save")
    public ResultBody saveGame(@RequestParam("file") MultipartFile file, Game game) {
        gameService.saveNewGame(file, game);
        return ResultBody.success("新增成功");
    }

    @PostMapping("/update")
    public ResultBody updateGame(Game game) {
        gameService.updateGame(game);
        return ResultBody.success("更新成功");
    }

    @PostMapping("/query/{year}")
    public ResultBody findGame(@RequestBody Game game, @PathVariable String year) {
        gameService.updateGame(game);
        return ResultBody.success("更新成功");
    }

    @PostMapping("/delete")
    public ResultBody deleteGame(@RequestBody Game game) {
        gameService.updateGame(game);
        return ResultBody.success("更新成功");
    }



}
