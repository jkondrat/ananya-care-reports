package org.motechproject.carereporting.domain.forms;

import org.hibernate.validator.constraints.NotEmpty;
import org.motechproject.carereporting.domain.FieldOperationEntity;
import org.motechproject.carereporting.enums.FieldType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class ComputedFieldFormObject implements Serializable {

    protected static final long serialVersionUID = 0L;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private FieldType type;

    @NotNull
    @Min(1)
    private Integer form;

    @NotNull
    private List<FieldOperationEntity> fieldOperations;

    public ComputedFieldFormObject() {

    }

    public ComputedFieldFormObject(final String name, final FieldType type,
            final Integer form, final List<FieldOperationEntity> fieldOperations) {
        this.name = name;
        this.type = type;
        this.form = form;
        this.fieldOperations = fieldOperations;
    }

    public String getName() {
        return name;
    }

    public FieldType getType() {
        return type;
    }

    public Integer getForm() {
        return form;
    }

    public List<FieldOperationEntity> getFieldOperations() {
        return fieldOperations;
    }
}
