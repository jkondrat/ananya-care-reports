package org.motechproject.carereporting.indicator.condition;

import org.motechproject.carereporting.domain.ComputedFieldEntity;
import org.motechproject.carereporting.domain.DateDiffComparisonConditionEntity;

import java.util.LinkedHashMap;
import java.util.Map;

public class DateDiffComparisonWhereCondition extends AbstractWhereCondition<DateDiffComparisonConditionEntity> {

    private static final String DATE_DIFF_COMPARISON_CONDITION_WHERE_CLAUSE = "((%(firstDate) - %(secondDate)) %(comparisonSymbol) %(value))";

    public DateDiffComparisonWhereCondition(ComputedFieldEntity computedField,
                                            DateDiffComparisonConditionEntity condition) {
        super(computedField, condition);
    }

    @Override
    public String getSqlQuery() {
        return DATE_DIFF_COMPARISON_CONDITION_WHERE_CLAUSE;
    }

    @Override
    protected Map<String, String> getSqlQueryParams() {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("firstDate", field.getFieldSql());
        params.put("secondDate", condition.getField2().getFieldSql());
        params.put("comparisonSymbol", condition.getComparisonSymbol().getName());
        params.put("value", condition.getValue().toString());
        return params;
    }

}
