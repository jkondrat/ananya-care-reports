package org.motechproject.carereporting.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "dw_query")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "dw_query_id"))
})
@Inheritance(strategy = InheritanceType.JOINED)
public class DwQueryEntity extends AbstractEntity {

    @ManyToMany
    @JoinTable(name = "dw_query_select_column", joinColumns = { @JoinColumn(name = "dw_query_id") },
            inverseJoinColumns = { @JoinColumn(name = "select_column_id") })
    private Set<SelectColumnEntity> selectColumns;

    @ManyToOne
    @JoinColumn(name = "combination_id", referencedColumnName = "combination_id")
    private CombinationEntity combination;

    public Set<SelectColumnEntity> getSelectColumns() {
        return selectColumns;
    }

    public void setSelectColumns(Set<SelectColumnEntity> selectColumns) {
        this.selectColumns = selectColumns;
    }

    public CombinationEntity getCombination() {
        return combination;
    }

    public void setCombination(CombinationEntity combination) {
        this.combination = combination;
    }
}
