package com.sunzequn.search.data.entity;

import com.sunzequn.search.data.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sloriac on 15/11/25.
 * <p>
 * The entity class for movies.
 */
public class YouKuMovie {

    private String name;
    private String rating;
    private String duration;
    private String date;
    private List<String> alias = new ArrayList<>();
    private List<String> directors = new ArrayList<>();
    private List<String> actors = new ArrayList<>();
    private List<String> type = new ArrayList<>();
    private List<String> area = new ArrayList<>();

    public YouKuMovie() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.trim();
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating.trim();
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        duration = StringUtil.removePrefix(duration.trim(), "时长:");
        duration = StringUtil.removeSuffix(duration, "分钟");
        this.duration = duration.trim();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date.trim();
    }

    public List<String> getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = StringUtil.split(alias.trim(), "/");
    }

    public List<String> getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = StringUtil.split(directors.trim(), "/");
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = StringUtil.split(actors.trim(), "/");
    }

    public List<String> getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = StringUtil.split(area.trim(), "/");
    }

    public List<String> getType() {
        return type;
    }

    public void setType(String type) {
        this.type = StringUtil.split(type.trim(), "/");
    }

    @Override
    public String toString() {
        return "YouKuMovie{" +
                "name='" + name + '\'' +
                ", rating='" + rating + '\'' +
                ", duration='" + duration + '\'' +
                ", date='" + date + '\'' +
                ", alias=" + alias +
                ", directors=" + directors +
                ", actors=" + actors +
                ", type=" + type +
                ", area=" + area +
                '}';
    }
}
