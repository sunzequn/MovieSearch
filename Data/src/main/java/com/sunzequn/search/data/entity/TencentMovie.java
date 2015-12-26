package com.sunzequn.search.data.entity;

import com.sunzequn.search.data.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sloriac on 15/12/23.
 */
public class TencentMovie {

    private String name;
    private String url;
    private String general;
    private List<String> directors = new ArrayList<>();
    private List<String> actors = new ArrayList<>();

    public TencentMovie() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getGeneral() {
        return general;
    }

    public void setGeneral(String general) {
        this.general = general;
    }

    public List<String> getDirectors() {
        return directors;
    }

    public void setDirectors(List<String> directors) {
        this.directors = directors;
    }

    public void setDirectors(String directors) {
        this.directors = StringUtil.split(directors, " ");
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public void setActors(String actors) {
        this.actors = StringUtil.split(actors, " ");
    }

    public String directors() {
        String string = "";
        for (String director : directors) {
            string += director;
        }
        return string;
    }

    public String[] actors() {
        String[] strings = actors.toArray(new String[actors.size()]);
        return strings;
    }

    @Override
    public String toString() {
        return "TencentMovie{" +
                "name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", general='" + general + '\'' +
                ", directors=" + directors +
                ", actors=" + actors +
                '}';
    }
}
