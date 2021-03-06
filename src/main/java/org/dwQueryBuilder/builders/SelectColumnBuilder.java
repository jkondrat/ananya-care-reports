package org.dwQueryBuilder.builders;

import org.dwQueryBuilder.builders.steps.ComputedColumnBuilderOperationStep;
import org.dwQueryBuilder.data.ComputedColumn;
import org.dwQueryBuilder.data.SelectColumn;
import org.dwQueryBuilder.data.enums.SelectColumnFunctionType;

public class SelectColumnBuilder {
    private ComputedColumn computedColumn;
    private SelectColumnFunctionType function;
    private String alias;
    private String nullValue;
    private boolean valueToLowerCase;

    public SelectColumnBuilder withColumn(String fieldName) {
        this.computedColumn = new ComputedColumn(null, fieldName);
        return this;
    }

    public SelectColumnBuilder withColumn(String tableName, String fieldName) {
        this.computedColumn = new ComputedColumn(tableName, fieldName);
        return this;
    }

    public SelectColumnBuilder withColumn(ComputedColumn computedColumn) {
        this.computedColumn = computedColumn;
        return this;
    }

    public SelectColumnBuilder withColumn(ComputedColumnBuilderOperationStep computedColumnBuilder) {
        this.computedColumn = computedColumnBuilder.build();
        return this;
    }

    public SelectColumnBuilder withFunction(SelectColumnFunctionType function) {
        this.function = function;
        return this;
    }

    public SelectColumnBuilder withColumnAndFunction(String fieldName,
                                                     SelectColumnFunctionType function) {
        this.computedColumn = new ComputedColumn(null, fieldName);
        this.function = function;
        return this;
    }

    public SelectColumnBuilder withColumnAndFunction(String tableName, String fieldName,
                                                     SelectColumnFunctionType function) {
        this.computedColumn = new ComputedColumn(tableName, fieldName);
        this.function = function;
        return this;
    }

    public SelectColumnBuilder withNullValue(String nullValue) {
        this.nullValue = nullValue;
        return this;
    }

    public SelectColumnBuilder withAlias(String alias) {
        this.alias = alias;
        return this;
    }

    public SelectColumnBuilder withValueToLowerCase(boolean valueToLowerCase) {
        this.valueToLowerCase = valueToLowerCase;
        return this;
    }

    public SelectColumn build() {
        return new SelectColumn(computedColumn, alias, function, nullValue, valueToLowerCase);
    }
}
