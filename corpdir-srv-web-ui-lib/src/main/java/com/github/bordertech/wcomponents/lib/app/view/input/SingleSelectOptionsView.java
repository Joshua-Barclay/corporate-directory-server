package com.github.bordertech.wcomponents.lib.app.view.input;

import com.github.bordertech.wcomponents.AbstractWSingleSelectList;

/**
 * Single select options view.
 *
 * @param <T> the option type
 * @author Jonathan Austin
 * @since 1.0.0
 *
 */
public interface SingleSelectOptionsView<T> extends SingleSelectable<T>, InputOptionsView<T> {

	@Override
	AbstractWSingleSelectList getSelectInput();

}
