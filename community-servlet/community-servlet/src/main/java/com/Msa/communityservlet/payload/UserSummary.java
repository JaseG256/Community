package com.Msa.communityservlet.payload;

public class UserSummary {

    private Long id;
    private String username;
    private byte[] avatarData;

    public UserSummary(Long id, String username, byte[] avatarData) {
        this.id = id;
        this.username = username;
        this.avatarData = avatarData;
    }

    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte[] getAvatarData() {
        return avatarData;
    }

    public void setAvatarData(byte[] avatarData) {
        this.avatarData = avatarData;
    }
}
