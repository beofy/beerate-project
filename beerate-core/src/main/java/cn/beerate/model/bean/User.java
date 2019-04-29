package cn.beerate.model.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
public class User {
    /** 用户ID */
    private long id;

    /** 用户名称 */
    private String name;

    /** 用户头像 */
    private String photo;
    
    /** 是否认证 */
    private boolean isApprove = false;

    public User(long id, String name, String photo, boolean isApprove) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.isApprove = isApprove;
    }
}
