package com.ucb.medicalnow.Model;

public class ChatModel {

    private Integer roleId;
    private String message;

    public ChatModel() {
    }

    public ChatModel(Integer roleId, String message) {
        this.roleId = roleId;
        this.message = message;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ConversationModel{" +
                "roleId=" + roleId +
                ", message='" + message + '\'' +
                '}';
    }
}
