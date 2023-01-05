package com.boomshair.gamer.domain.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * 游戏类
 * @since 1.0.0
 * @since 2022/12/27
 */
@Entity
@Getter
@Setter
public class Game {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "game_name", nullable = false)
    private String gameName;

    @Column(name = "chinese_name", nullable = false)
    private String chineseName;

    @Column(name = "platform", nullable = false)
    private String platform;

    @Column(name = "played_time", nullable = false)
    private Integer playedTime;

    @Column(name = "stars", nullable = false)
    private Double stars;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "picture_path", nullable = false)
    private String picturePath;

    @Column(name = "disk_path", nullable = false)
    private String diskPath;

    @Column(name = "comments")
    private String comments;

    @Column(name = "create_time", nullable = false)
    private Date createTime;

}
