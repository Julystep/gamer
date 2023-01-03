package com.boomshair.gamer.service.impl;

import com.boomshair.gamer.dao.GameRepository;
import com.boomshair.gamer.domain.pojo.Game;
import com.boomshair.gamer.domain.res.ResultBody;
import com.boomshair.gamer.exception.GameException;
import com.boomshair.gamer.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 游戏服务实现
 * @since v1.0.0
 * @since 2022/12/27
 */
@Service
@Slf4j
public class GameServiceImpl implements GameService {

    @Value("${picture.path}")
    private String picturePath;

    @Value("${static.path}")
    private String staticPath;

    GameRepository gameRepository;

    private static final String UPDATE = "update";
    private static final String INSERT = "insert";

    @Autowired
    public void setGameRepository(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }


    @Override
    public void saveNewGame(MultipartFile file, Game game) {
        saveGame(file, game, INSERT);
    }

    @Override
    public void updateGame(MultipartFile file, Game game) {
        saveGame(file, game, UPDATE);
    }

    private void saveGame(MultipartFile file, Game game, String operate) {
        if (Objects.equals(operate, UPDATE) && Objects.isNull(file)) {
            saveWithoutFile(game);
        } else {
            saveWithFile(file, game, operate);
        }
    }

    private void saveWithFile(MultipartFile file, Game game, String operate) {
        if (Objects.equals(operate, UPDATE)) {
            Game gameInDb = gameRepository.findGameById(game.getId());
            File currentFile = new File(gameInDb.getDiskPath());
            if (!currentFile.delete()) log.warn("历史文件{}未成功删除", gameInDb.getDiskPath());
        }
        String absoluteDiskFilePath = saveDiskFile(game, file);
        String httpPath = MessageFormat.format("{0}/{1}/{2}", staticPath, game.getGameName(), file.getOriginalFilename());
        game.setCreateTime(new Date());
        game.setPicturePath(httpPath);
        game.setDiskPath(absoluteDiskFilePath);
        gameRepository.saveAndFlush(game);
    }

    private void saveWithoutFile(Game game) {
        Game gameInDb = gameRepository.findGameById(game.getId());
        game.setCreateTime(gameInDb.getCreateTime());
        game.setPicturePath(gameInDb.getPicturePath());
        game.setDiskPath(gameInDb.getDiskPath());
        gameRepository.saveAndFlush(game);
    }

    private String saveDiskFile(Game game, MultipartFile file) {
        String diskPath = MessageFormat.format("{0}/{1}/{2}", picturePath, game.getGameName(), file.getOriginalFilename());
        File diskFile = new File(diskPath);
        if (diskFile.exists() && !diskFile.delete()) throw new GameException("文件已存在");
        File parentFile = diskFile.getParentFile();
        if (!parentFile.exists() && !parentFile.mkdirs()) {
            throw new GameException("初始化存储文件夹失败");
        }
        try {
            if (!diskFile.createNewFile()) throw new GameException("文件创建失败");
        } catch (IOException e) {
            log.error("创建文件失败", e);
            throw new GameException("文件创建失败");
        }
        try (InputStream is = file.getInputStream();
             OutputStream os = new FileOutputStream(diskFile)) {
            int len;
            byte[] arr = new byte[1024];
            while ((len = is.read(arr)) != -1){
                os.write(arr,0,len);
            }
        } catch (IOException e) {
            log.error("文件内容写入文件系统失败", e);
            throw new GameException("将文件存入硬盘失败");
        }
        return diskFile.getAbsolutePath();
    }

    @Override
    public void deleteGame(Integer id) {
        Game gameInDb = gameRepository.findGameById(id);
        if (Objects.isNull(gameInDb)) throw new GameException("记录已被删除");
        gameRepository.deleteById(id);
    }

    @Override
    public ResultBody findGame(Integer year) {
        List<Game> result;
        if (Objects.isNull(year)) {
            result = gameRepository.findAll();
        } else {
            result = gameRepository.findGamesByYear(year);
        }
        return ResultBody.successWithData(result);
    }

    @Override
    public ResultBody gameDetail(Integer id) {
        Game game = gameRepository.findGameById(id);
        return ResultBody.successWithData(game);
    }

    @Override
    public ResultBody queryYears() {
        return ResultBody.successWithData(gameRepository.findAllYear());
    }
}
