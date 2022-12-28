package com.boomshair.gamer.service.impl;

import com.boomshair.gamer.dao.GameRepository;
import com.boomshair.gamer.domain.pojo.Game;
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
import java.util.Date;

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

    GameRepository gameRepository;

    @Autowired
    public void setGameRepository(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }


    @Override
    public void saveNewGame(MultipartFile file, Game game) {
        String name = file.getOriginalFilename();
        String diskPath = MessageFormat.format("{0}/{1}/{2}", picturePath, game.getGameName(),name);
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
        game.setCreateTime(new Date());
        game.setPicturePath(diskFile.getAbsolutePath());
        gameRepository.saveAndFlush(game);
    }

    @Override
    public void updateGame(Game game) {

    }

    @Override
    public void deleteGame(Game game) {

    }

    @Override
    public void findGame(Game game) {

    }
}
