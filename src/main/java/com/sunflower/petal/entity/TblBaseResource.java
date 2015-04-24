

package com.sunflower.petal.entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TblBaseResource implements Serializable{


    /**
     * id *
     */
    private String id;


    /**
     * gId *
     */
    private Integer gId;


    /**
     * pId *
     */
    private Integer pId;


    /**
     * appResourceId *
     */
    private String appResourceId;


    /**
     * name *
     */
    private String name;


    /**
     * value *
     */
    private String value;


    /**
     * type *
     */
    private String type;


    /**
     * appRId *
     */
    private String appRId;


    /**
     * appRPid *
     */
    private String appRPid;


    /**
     * creator *
     */
    private String creator;


    /**
     * created *
     */
    private java.util.Date created;


    /**
     * mender *
     */
    private String mender;


    /**
     * modified *
     */
    private java.util.Date modified;


    /**
     * deleted *
     */
    private Integer deleted;


    /**
     * ext1 *
     */
    private String ext1;


    /**
     * ext2 *
     */
    private String ext2;


    /**
     * ext3 *
     */
    private String ext3;


    /**
     * ext4 *
     */
    private String ext4;


    /**
     * ext5 *
     */
    private String ext5;


    /**
     * orders *
     */
    private Integer orders;

    private String[] ids;

    private boolean check = false;
    private boolean leaf = true;

    private boolean open = false;
    private Integer roleId;
    private String flag = "resource";
    private boolean isParent;
    private List<TblBaseResource> children = new ArrayList<TblBaseResource>();
    private String groupName;

    private Integer userId;
    private Integer limit;
    private Integer start;

    public TblBaseResource() {

    }
    public TblBaseResource(ResourceItemDto resourceItemDto){
        this.setId(resourceItemDto.getId().toString());
        this.setgId(resourceItemDto.getgId());
        this.setpId(resourceItemDto.getpId());
        this.setName(resourceItemDto.getName());
        this.setValue(resourceItemDto.getValue());
        this.setType(resourceItemDto.getType());
        this.setAppResourceId(resourceItemDto.getAppResourceId());
        this.setAppRId(resourceItemDto.getAppRId());
        this.setAppRPid(resourceItemDto.getAppRPid());
        this.setExt1(resourceItemDto.getExt1());
        this.setExt2(resourceItemDto.getExt2());
        this.setExt3(resourceItemDto.getExt3());
        this.setExt4(resourceItemDto.getExt4());
        this.setExt5(resourceItemDto.getExt5());
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<TblBaseResource> getChildren() {
        return children;
    }

    public void setChildren(List<TblBaseResource> children) {
        this.children = children;
    }

    public boolean isParent() {
        return isParent;
    }

    public boolean getIsParent() {
        return isParent;
    }

    public void setParent(boolean parent) {
        isParent = parent;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }


    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getgId() {
        return gId;
    }

    public void setgId(Integer gId) {
        this.gId = gId;
    }

    public Integer getGId() {
        return gId;
    }

    public void setGId(Integer gId) {
        this.gId = gId;
    }


    public Integer getPId() {
        return pId;
    }

    public void setPId(Integer pId) {
        this.pId = pId;
    }


    public String getAppResourceId() {
        return appResourceId;
    }

    public void setAppResourceId(String appResourceId) {
        this.appResourceId = appResourceId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getAppRId() {
        return appRId;
    }

    public void setAppRId(String appRId) {
        this.appRId = appRId;
    }


    public String getAppRPid() {
        return appRPid;
    }

    public void setAppRPid(String appRPid) {
        this.appRPid = appRPid;
    }


    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }


    public java.util.Date getCreated() {
        return created;
    }

    public void setCreated(java.util.Date created) {
        this.created = created;
    }


    public String getMender() {
        return mender;
    }

    public void setMender(String mender) {
        this.mender = mender;
    }


    public java.util.Date getModified() {
        return modified;
    }

    public void setModified(java.util.Date modified) {
        this.modified = modified;
    }


    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }


    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }


    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }


    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }


    public String getExt4() {
        return ext4;
    }

    public void setExt4(String ext4) {
        this.ext4 = ext4;
    }


    public String getExt5() {
        return ext5;
    }

    public void setExt5(String ext5) {
        this.ext5 = ext5;
    }

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }
}
