package com.ucb.medicalnow.Model;

public class TokenRefreshModel {

    private String refreshToken;

    public TokenRefreshModel() {
    }

    public TokenRefreshModel(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public String toString() {
        return "TokenRefreshModel{" +
                "refreshToken='" + refreshToken + '\'' +
                '}';
    }
}
