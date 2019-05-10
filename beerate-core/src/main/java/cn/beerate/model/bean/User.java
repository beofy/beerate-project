package cn.beerate.model.bean;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class User {

    /**
     * 授权令牌
     */
    private String token;

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

    public User(String token, String name, String photo, String mobile, String email, boolean isApprove) {
        this.token = token;
        this.name = name;
        this.photo = photo;
        this.mobile = mobile;
        this.email = email;
        this.isApprove = isApprove;
    }


}
