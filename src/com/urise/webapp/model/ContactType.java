package com.urise.webapp.model;

/**
 * Created by viktoriyasidenko on 2/28/17.
 */
public enum ContactType {
    PHONE("Teл."),
    MOBILE("Мобилный"),
    HOME_PHONE("Домашний телю"),
    SKYPE("Skype"),
    MAIL("Почта"),
    LINKEDIN("Профиль LinkedIn"),
    GITHUB("Профиль GitHub"),
    STATCKOVERFLOW("Профиль Statckoverflow"),
    HOME_PAGE("Домашняя страница");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
