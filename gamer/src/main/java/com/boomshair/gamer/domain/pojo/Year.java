package com.boomshair.gamer.domain.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 年份表
 * @since v
 * @since 2023/1/5
 */
@Entity
@Getter
@Setter
public class Year {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "year", nullable = false)
    private Integer year;

}
