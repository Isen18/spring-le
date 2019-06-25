package spring.sercurity.le.auth;

/**
 * 方法权限
 *
 * @author Isen
 * @date 19-6-25 下午10:37
 * @since 1.0
 */
public enum MethodPermissionEn {
    //商品类权限
    ITEM_ADD("item_add")
    ;
    /**
     * 权限标识
     */
    private String permissionMark;

    MethodPermissionEn(String permissionMark){
        this.permissionMark = permissionMark;
    }

    public String getPermissionMark() {
        return permissionMark;
    }
}
