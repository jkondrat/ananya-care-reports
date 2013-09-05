package org.motechproject.carereporting.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonView;
import org.motechproject.carereporting.domain.views.ComplexConditionJsonView;
import org.motechproject.carereporting.domain.views.IndicatorJsonView;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "field_comparison")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "field_comparison_id"))
})
@JsonTypeName(value = "field")
public class FieldComparisonConditionEntity extends ConditionEntity {

    @ManyToOne
    @JoinColumn(name = "comparison_symbol_id", nullable = false)
    @JsonView({ IndicatorJsonView.IndicatorDetails.class, ComplexConditionJsonView.ComplexConditionDetails.class })
    private ComparisonSymbolEntity operator;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "field_2_id")
    @JsonView({ IndicatorJsonView.IndicatorDetails.class, ComplexConditionJsonView.ComplexConditionDetails.class })
    private ComputedFieldEntity field2;

    @Column(name = "offset_1")
    @JsonView({ ComplexConditionJsonView.ListComplexConditions.class })
    private String offset1;

    @Column(name = "offset_2")
    @JsonView({ ComplexConditionJsonView.ListComplexConditions.class })
    private String offset2;

    public FieldComparisonConditionEntity() {
        super();
    }

    public FieldComparisonConditionEntity(FieldComparisonConditionEntity conditionEntity) {
        super(conditionEntity);

        this.operator = conditionEntity.getOperator();
        this.field2 = conditionEntity.getField2();
        this.offset1 = conditionEntity.getOffset1();
        this.offset2 = conditionEntity.getOffset2();
    }

    public ComparisonSymbolEntity getOperator() {
        return operator;
    }

    public void setOperator(ComparisonSymbolEntity operator) {
        this.operator = operator;
    }

    public ComputedFieldEntity getField2() {
        return field2;
    }

    public void setField2(ComputedFieldEntity field2) {
        this.field2 = field2;
    }

    public String getOffset1() {
        return offset1;
    }

    public void setOffset1(String offset1) {
        this.offset1 = offset1;
    }

    public String getOffset2() {
        return offset2;
    }

    public void setOffset2(String offset2) {
        this.offset2 = offset2;
    }
}
