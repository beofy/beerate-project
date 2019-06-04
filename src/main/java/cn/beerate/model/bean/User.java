package cn.beerate.model.bean;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class User {

    /**
     * 用户id
     */
    private long id;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 用户头像
     */
    private String photo;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 是否认证
     */
    private boolean isApprove = false;


    public User(long id, String name, String photo, String mobile, String email, boolean isApprove) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.mobile = mobile;
        this.email = email;
        this.isApprove = isApprove;
    }
}
