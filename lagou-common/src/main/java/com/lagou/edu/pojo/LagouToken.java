package com.lagou.edu.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data                           //自动生成get/set
@Entity                         //实体类
@Table(name="lagou_token")      //对应的表
@AllArgsConstructor             //使用后添加一个构造函数，该构造函数含有所有已声明字段属性参数
@NoArgsConstructor              //使用后创建一个无参构造函数
public class LagouToken implements Serializable {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//自增主键,
    private String email;//邮箱地址,
    private String token;//令牌,

}
