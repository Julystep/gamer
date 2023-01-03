package com.boomshair.gamer.controller;

import com.boomshair.gamer.domain.pojo.Game;
import com.boomshair.gamer.domain.res.ResultBody;
import com.boomshair.gamer.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.InetAddress;
import java.net.UnknownHostException;

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
    public ResultBody updateGame(@RequestParam(value = "file", required = false) MultipartFile file, Game game) {
        gameService.updateGame(file, game);
        return ResultBody.success("更新成功");
    }

    @GetMapping(value = {"/query/{year}", "/query"})
    public ResultBody findGame(@PathVariable(value = "year", required = false) Integer year) {
        return gameService.findGame(year);
    }

    @PostMapping("/delete/{id}")
    public ResultBody deleteGame(@PathVariable(value = "id") Integer id) {
        gameService.deleteGame(id);
        return ResultBody.success("删除成功");
    }

    @GetMapping("/detail/{id}")
    public ResultBody gameDetail(@PathVariable(value = "id") Integer id) {
        return gameService.gameDetail(id);
    }

    @GetMapping("/query/years")
    public ResultBody queryYears() {
        return gameService.queryYears();
    }



}
