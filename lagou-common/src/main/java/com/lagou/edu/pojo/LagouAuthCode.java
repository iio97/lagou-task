package com.lagou.edu.pojo;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data                           //自动生成get/set
@Entity                         //实体类
@Table(name="lagou_auth_code")  //对应的表
@AllArgsConstructor             //使用后添加一个构造函数，该构造函数含有所有已声明字段属性参数
@NoArgsConstructor              //使用后创建一个无参构造函数
public class LagouAuthCode implements Serializable {

    //ID主键
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//自增主键,
    private String email;//邮箱地址,
    private String code;//验证码,
    private Date createtime;//创建时间,
    private Date expiretime;//过期时间,

}
