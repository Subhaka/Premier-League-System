package com.company;

import java.util.Objects;

public class UniversityFootballClub extends FootballClub {

    private final String uni_name;

    public UniversityFootballClub(String club_name, String location, String uni_name) {
        super(club_name, location);
        this.uni_name = uni_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UniversityFootballClub that = (UniversityFootballClub) o;
        return Objects.equals(uni_name, that.uni_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), uni_name);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

