package org.motechproject.carereporting.domain.forms;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

public class IndicatorFormObject implements Serializable {

    protected static final long serialVersionUID = 0L;

    private Integer id;

    @NotNull
    @Min(1)
    private Integer indicatorType;

    @NotNull
    private Set<Integer> categories;

    @NotNull
    @Min(1)
    private Integer level;

    @NotNull
    private Set<Integer> owners;

    private Set<Integer> complexConditions;

    private Set<Integer> values;

    @NotNull
    @Min(1)
    private Integer frequency;

    @NotNull
    @NotEmpty
    private String name;

    public IndicatorFormObject() {

    }

    public IndicatorFormObject(final Integer indicatorType, final Set<Integer> categories,
            final Integer level, final Set<Integer> owners, final Set<Integer> complexConditions,
            final Set<Integer> values, final Integer frequency,
            final String name) {
        this.indicatorType = indicatorType;
        this.categories = categories;
        this.level = level;
        this.owners = owners;
        this.complexConditions = complexConditions;
        this.values = values;
        this.frequency = frequency;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIndicatorType() {
        return indicatorType;
    }

    public Set<Integer> getCategories() {
        return categories;
    }

    public Integer getLevel() {
        return level;
    }

    public Set<Integer> getOwners() {
        return owners;
    }

    public Set<Integer> getComplexConditions() {
        return complexConditions;
    }

    public Set<Integer> getValues() {
        return values;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public String getName() {
        return name;
    }
}