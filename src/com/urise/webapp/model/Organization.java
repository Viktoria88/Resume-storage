package com.urise.webapp.model;

import com.urise.webapp.util.DateUtil;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

import static com.urise.webapp.util.DateUtil.NOW;
import static com.urise.webapp.util.DateUtil.of;

/**
 * Created by viktoriyasidenko on 2/28/17.
 */
public class Organization {

    private final Link homePage;
    private List<DescriptTypeForOrganisation> positions = new ArrayList<>();



    public Organization(String name, String url, DescriptTypeForOrganisation... positions) {
        this(new Link(name, url), Arrays.asList(positions));

    }

    public Organization(Link homePage, List<DescriptTypeForOrganisation> positions) {
        this.homePage = homePage;
        this.positions = positions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (homePage != null ? !homePage.equals(that.homePage) : that.homePage != null) return false;
        return positions.equals(that.positions);
    }

    @Override
    public int hashCode() {
        int result = homePage != null ? homePage.hashCode() : 0;
        result = 31 * result + positions.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Organization{" + homePage + "," + positions + ')';
    }

    /**
     * Created by viktoriyasidenko on 3/1/17.
     */
    public static class DescriptTypeForOrganisation {

        private final LocalDate startDate;
        private final LocalDate endDate;
        private final String title;
        private final String description;

        public DescriptTypeForOrganisation(int startYear, Month startMonth, String title, String description){
            this(of(startYear, startMonth), NOW, title, description);
        }

        public DescriptTypeForOrganisation(int startYear, Month startMonth, int endYear, Month endMonth, String title, String description){
            this(of(startYear, startMonth), of(endYear, endMonth), title, description);
        }
        public DescriptTypeForOrganisation(LocalDate startDate, LocalDate endDate, String title, String description) {
            Objects.requireNonNull(startDate, "startDate must not be null");
            Objects.requireNonNull(endDate, "endDate must not be null");
            Objects.requireNonNull(title, "title must not be null");
            this.startDate = startDate;
            this.endDate = endDate;
            this.title = title;
            this.description = description;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            DescriptTypeForOrganisation that = (DescriptTypeForOrganisation) o;

            if (!startDate.equals(that.startDate)) return false;
            if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
            if (!title.equals(that.title)) return false;
            return description.equals(that.description);
        }

        @Override
        public int hashCode() {
            int result = startDate.hashCode();
            result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
            result = 31 * result + title.hashCode();
            result = 31 * result + description.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return "DescriptTypeForOrganisation{" +
                    "startDate=" + startDate +
                    ", endDate=" + endDate +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }
    }
}
