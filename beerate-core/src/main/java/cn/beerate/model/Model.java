package cn.beerate.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@NoArgsConstructor
@Setter
@Getter
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "timestamp NULL DEFAULT CURRENT_TIMESTAMP")
    private Date createTime;

    private Date updateTime;

}
