package cn.beerate.model;

/**
 * 后台栏目
 */
public enum RightColumn {
    MAIN("主页"),
    USER("会员"),
    ITEM("项目"),
    MANAGER("管理"),
    SETTING("设置"),
    ;

    private String value;

    RightColumn(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
