package com.urise.webapp.model;

import com.urise.webapp.util.DateUtil;
import com.urise.webapp.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;

import static com.urise.webapp.util.DateUtil.NOW;
import static com.urise.webapp.util.DateUtil.of;

/**
 * Created by viktoriyasidenko on 2/28/17.
 */
@XmlAccessorType(XmlAccessType.FIELD)

public class Organization implements Serializable {

    private Link homePage;
    private List<DescriptTypeForOrganisation> positions = new ArrayList<>();

    public Organization() {
    }

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
        return Objects.equals(homePage, that.homePage) &&
                Objects.equals(positions, that.positions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, positions);
    }

    @Override
    public String toString() {
        return "Organization{" + homePage + "," + positions + ')';
    }

    /**
     * Created by viktoriyasidenko on 3/1/17.
     */
    @XmlAccessorType(XmlAccessType.FIELD)

    public static class DescriptTypeForOrganisation implements Serializable {
        private static final long serialVersionUID = 1L;

        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate startDate;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate endDate;
        private String title;
        private String description;

        public DescriptTypeForOrganisation() {
        }

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
            return Objects.equals(startDate, that.startDate) &&
                    Objects.equals(endDate, that.endDate) &&
                    Objects.equals(title, that.title) &&
                    Objects.equals(description, that.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(startDate, endDate, title, description);
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
