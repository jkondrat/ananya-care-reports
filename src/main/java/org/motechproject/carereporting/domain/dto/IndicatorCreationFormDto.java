package org.motechproject.carereporting.domain.dto;

import com.fasterxml.jackson.annotation.JsonView;
import org.motechproject.carereporting.domain.DwQueryEntity;
import org.motechproject.carereporting.domain.FrequencyEntity;
import org.motechproject.carereporting.domain.IndicatorClassificationEntity;
import org.motechproject.carereporting.domain.LevelEntity;
import org.motechproject.carereporting.domain.ReportTypeEntity;
import org.motechproject.carereporting.domain.RoleEntity;
import org.motechproject.carereporting.domain.views.IndicatorJsonView;

import java.util.Set;

public class IndicatorCreationFormDto {

    @JsonView({ IndicatorJsonView.CreationForm.class })
    private Set<IndicatorClassificationEntity> classifications;

    @JsonView({ IndicatorJsonView.CreationForm.class })
    private Set<RoleEntity> roles;

    @JsonView({ IndicatorJsonView.CreationForm.class })
    private Set<LevelEntity> levels;

    @JsonView({ IndicatorJsonView.CreationForm.class })
    private Set<FrequencyEntity> frequencies;

    @JsonView({ IndicatorJsonView.CreationForm.class })
    private Set<ReportTypeEntity> reportTypes;

    @JsonView({ IndicatorJsonView.CreationForm.class })
    private Set<DwQueryEntity> dwQueries;

    public IndicatorCreationFormDto() {

    }

    public IndicatorCreationFormDto(Set<IndicatorClassificationEntity> classifications, Set<RoleEntity> roles,
                                    Set<LevelEntity> levels, Set<FrequencyEntity> frequencies, Set<ReportTypeEntity> reportTypes,
                                    Set<DwQueryEntity> dwQueries) {
        this.classifications = classifications;
        this.roles = roles;
        this.levels = levels;
        this.frequencies = frequencies;
        this.reportTypes = reportTypes;
        this.dwQueries = dwQueries;
    }

    public Set<LevelEntity> getLevels() {
        return levels;
    }

    public void setLevels(Set<LevelEntity> levels) {
        this.levels = levels;
    }

    public Set<FrequencyEntity> getFrequencies() {
        return frequencies;
    }

    public void setFrequencies(Set<FrequencyEntity> frequencies) {
        this.frequencies = frequencies;
    }

    public Set<IndicatorClassificationEntity> getClassifications() {
        return classifications;
    }

    public void setClassifications(Set<IndicatorClassificationEntity> classifications) {
        this.classifications = classifications;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    public Set<ReportTypeEntity> getReportTypes() {
        return reportTypes;
    }

    public void setReportTypes(Set<ReportTypeEntity> reportTypes) {
        this.reportTypes = reportTypes;
    }

    public Set<DwQueryEntity> getDwQueries() {
        return dwQueries;
    }

    public void setDwQueries(Set<DwQueryEntity> dwQueries) {
        this.dwQueries = dwQueries;
    }
}
