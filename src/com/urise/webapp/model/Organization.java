package com.urise.webapp.model;

import com.sun.xml.internal.ws.api.ha.StickyFeature;

import java.time.LocalDate;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by viktoriyasidenko on 2/28/17.
 */
public class Organization {

    private final Link homePage;
    private final Map<DescriptTypeForOrganisation, String> terms = new EnumMap<>(DescriptTypeForOrganisation.class);

    public Organization(String name, String url) {

        this.homePage = new Link(name, url);

    }

    public String getTerm(DescriptTypeForOrganisation type) {
        return terms.get(type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (homePage != null ? !homePage.equals(that.homePage) : that.homePage != null) return false;
        return terms.equals(that.terms);
    }

    @Override
    public int hashCode() {
        int result = homePage != null ? homePage.hashCode() : 0;
        result = 31 * result + terms.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "homePage=" + homePage +
                ", terms=" + terms +
                '}';
    }
}
