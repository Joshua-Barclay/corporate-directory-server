package com.github.bordertech.wcomponents.lib.table;

import com.github.bordertech.wcomponents.AbstractBeanBoundTableModel;
import com.github.bordertech.wcomponents.WComponent;
import com.github.bordertech.wcomponents.WTable;
import com.github.bordertech.wcomponents.WTableColumn;
import com.github.bordertech.wcomponents.util.SystemException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Bean bound table model with column definitions.
 *
 * @param <T> the bean class used by this model
 * @param <U> the table column definition
 * @author Jonathan Austin
 */
public class TableBeanModel<T, U extends TableColumn<?, T>> extends AbstractBeanBoundTableModel {

	/**
	 * Indicates whether rows are globally selectable.
	 */
	private boolean selectable;

	/**
	 * Columns for the table.
	 */
	private final List<U> columns;

	/**
	 * @param columns the columns for this table
	 */
	public TableBeanModel(final List<U> columns) {
		this.columns = columns;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getValueAt(final List<Integer> row, final int col) {
		// Get the bean for the row
		T bean = getRowBean(row);

		// Get the value for the column
		TableColumn<?, T> column = getColumns().get(col);

		return column.getValue(bean);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getRowCount() {
		return getBeanList().size();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getChildCount(final List<Integer> row) {
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isCellEditable(final List<Integer> row, final int col) {
		TableColumn<?, T> column = getColumn(col);
		return column.isEditable();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSortable(final int col) {
		TableColumn<?, T> column = getColumn(col);
		return column.getComparator() != null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getRowKey(final List<Integer> row) {
		return getRowBean(row);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int[] sort(final int col, final boolean ascending) {
		TableColumn<?, T> column = getColumn(col);
		Comparator<Object> comp = (Comparator<Object>) column.getComparator();
		if (comp == null) {
			return null;
		}
		return sort(comp, col, ascending);
	}

	/**
	 * @return the columns for this table.
	 */
	public List<U> getColumns() {
		return columns;
	}

	/**
	 * @param col the column index
	 * @return the column details for this column index
	 */
	public TableColumn<?, T> getColumn(final int col) {
		return getColumns().get(col);
	}

	/**
	 * @param columnId the column id
	 * @return the index of this column id, or -1 if not found
	 */
	public int getColumnIdx(final String columnId) {
		List<U> cols = getColumns();
		for (int i = 0; i < cols.size(); i++) {
			if (cols.get(i).getColumnId().equals(columnId)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * @return the list of beans for the table
	 */
	public List<T> getBeanList() {
		List<T> beans = (List<T>) super.getBean();
		return beans == null ? Collections.EMPTY_LIST : beans;
	}

	/**
	 * @param row the row index
	 * @return the bean for the row
	 */
	public T getRowBean(final List<Integer> row) {
		int idx = row.get(0);
		return getBeanList().get(idx);
	}

	/**
	 * Helper method to add the columns to the table.
	 *
	 * @param table the table to configure
	 */
	public static void configTable(final WTable table) {
		configTable(table, false);
	}

	/**
	 * Helper method to add the columns to the table.
	 *
	 * @param table the table to configure
	 * @param instance true if create instance of render class on table column
	 */
	public static void configTable(final WTable table, final boolean instance) {
		TableBeanModel<?, ?> beanModel = (TableBeanModel<?, ?>) table.getTableModel();
		for (TableColumn<?, ?> col : beanModel.getColumns()) {
			// Create column
			WTableColumn tblCol;
			if (col.getRenderer() != null) {
				tblCol = new WTableColumn(col.getColumnLabel(), col.getRenderer());
			} else if (instance) {
				try {
					WComponent rendererComponent = col.getRendererClass().newInstance();
					tblCol = new WTableColumn(col.getColumnLabel(), rendererComponent);
				} catch (Exception e) {
					throw new SystemException("Could not create table column for " + col.getColumnId(), e);
				}
			} else {
				tblCol = new WTableColumn(col.getColumnLabel(), col.getRendererClass());
			}
			table.addColumn(tblCol);
		}
	}

	/**
	 * @param row ignored
	 * @return true if the model is globally selectable, otherwise false
	 */
	@Override
	public boolean isSelectable(final List<Integer> row) {
		return selectable;
	}

	/**
	 * Sets whether the rows are globally selectable.
	 *
	 * @param selectable true if the rows are globally selectable, otherwise false
	 */
	public void setSelectable(final boolean selectable) {
		this.selectable = selectable;
	}

}
