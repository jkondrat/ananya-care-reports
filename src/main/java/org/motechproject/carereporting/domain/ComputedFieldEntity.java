package org.motechproject.carereporting.domain;

import org.codehaus.jackson.map.annotate.JsonView;
import org.hibernate.validator.constraints.NotEmpty;
import org.motechproject.carereporting.domain.views.IndicatorJsonView;
import org.motechproject.carereporting.enums.FieldType;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "computed_field")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "computed_field_id"))
})
public class ComputedFieldEntity extends AbstractEntity {

    @NotNull
    @NotEmpty
    @Column(name = "name")
    @JsonView(IndicatorJsonView.IndicatorMainForm.class)
    private String name;

    @NotNull
    @Column(name = "type", columnDefinition = "character varying", length = 100)
    @Enumerated(value = EnumType.STRING)
    @JsonView({ IndicatorJsonView.IndicatorMainForm.class, IndicatorJsonView.ListComputedFieldNames.class })
    private FieldType type;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "form_id")
    private FormEntity form;

    @NotNull
    @OneToMany(mappedBy = "computedField", cascade = CascadeType.ALL)
    private Set<FieldOperationEntity> fieldOperations;

    @OneToMany(mappedBy = "computedField")
    private Set<ConditionEntity> conditions;

    @OneToMany(mappedBy = "computedField")
    private Set<IndicatorEntity> indicators;

    public ComputedFieldEntity() {

    }

    public ComputedFieldEntity(final String name, final FieldType type, final FormEntity form,
            final Set<FieldOperationEntity> fieldOperations) {
        this.name = name;
        this.type = type;
        this.form = form;
        this.fieldOperations = fieldOperations;

        for (FieldOperationEntity fieldOperationEntity : fieldOperations) {
            if (fieldOperationEntity.getComputedField() == null) {
                fieldOperationEntity.setComputedField(this);
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FieldType getType() {
        return type;
    }

    public void setType(FieldType type) {
        this.type = type;
    }

    public FormEntity getForm() {
        return form;
    }

    public void setForm(FormEntity form) {
        this.form = form;
    }

    public Set<FieldOperationEntity> getFieldOperations() {
        return fieldOperations;
    }

    public void setFieldOperations(Set<FieldOperationEntity> fieldOperations) {
        this.fieldOperations = fieldOperations;
    }

    public Set<ConditionEntity> getConditions() {
        return conditions;
    }

    public void setConditions(Set<ConditionEntity> conditions) {
        this.conditions = conditions;
    }

    public Set<IndicatorEntity> getIndicators() {
        return indicators;
    }

    public void setIndicators(Set<IndicatorEntity> indicators) {
        this.indicators = indicators;
    }
}