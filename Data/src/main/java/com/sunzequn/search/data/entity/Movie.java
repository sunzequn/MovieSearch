package com.sunzequn.search.data.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sloriac on 15/12/26.
 */
public class Movie {

    private String name;
    private String rating;
    private String duration;
    private String date;
    private String general;
    private String youkuUrl;
    private String tencentUrl;
    private List<String> alias = new ArrayList<>();
    private List<String> directors = new ArrayList<>();
    private List<String> actors = new ArrayList<>();
    private List<String> type = new ArrayList<>();
    private List<String> area = new ArrayList<>();

    public Movie() {
    }

    public Movie(YouKuMovie movie) {
        this.name = movie.getName();
        this.rating = movie.getRating();
        this.duration = movie.getDuration();
        this.date = movie.getDate();
        this.general = movie.getGeneral();
        this.youkuUrl = movie.getUrl();
        this.alias = movie.getAlias();
        this.directors = movie.getDirectors();
        this.actors = movie.getActors();
        this.type = movie.getType();
        this.area = movie.getArea();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGeneral() {
        return general;
    }

    public void setGeneral(String general) {
        this.general = general;
    }

    public String getYoukuUrl() {
        return youkuUrl;
    }

    public void setYoukuUrl(String youkuUrl) {
        this.youkuUrl = youkuUrl;
    }

    public String getTencentUrl() {
        return tencentUrl;
    }

    public void setTencentUrl(String tencentUrl) {
        this.tencentUrl = tencentUrl;
    }

    public List<String> getAlias() {
        return alias;
    }

    public void setAlias(List<String> alias) {
        this.alias = alias;
    }

    public List<String> getDirectors() {
        return directors;
    }

    public void setDirectors(List<String> directors) {
        this.directors = directors;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public List<String> getArea() {
        return area;
    }

    public void setArea(List<String> area) {
        this.area = area;
    }
}
