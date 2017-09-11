package com.github.bordertech.wcomponents.lib.app.view.bar;

import com.github.bordertech.wcomponents.WMenu;
import com.github.bordertech.wcomponents.WMenuItem;
import com.github.bordertech.wcomponents.lib.app.event.ToolbarEventType;

/**
 * Toolbar default implementation.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
public class DefaultToolbarView<T> extends AbstractMenuToolbar<T> {

	private final WMenuItem itemBack = new ToolbarMenuItem("Back", ToolbarEventType.BACK) {
		@Override
		public boolean isVisible() {
			return isUseToolbarType(ToolbarItem.BACK);
		}
	};

	private final WMenuItem itemAdd = new ToolbarMenuItem("Add", ToolbarEventType.ADD) {
		@Override
		public boolean isVisible() {
			return isUseToolbarType(ToolbarItem.ADD);
		}
	};

	private final WMenuItem itemReset = new ToolbarMenuItem("Reset", ToolbarEventType.RESET_VIEW) {
		@Override
		public boolean isVisible() {
			return isUseToolbarType(ToolbarItem.RESET);
		}
	};

	public DefaultToolbarView() {
		WMenu menu = getMenu();
		menu.add(itemBack);
		menu.add(itemAdd);
		menu.add(itemReset);
		menu.addHtmlClass("wc-neg-margin");

		// Default to use RESET
		addToolbarType(ToolbarItem.RESET);
	}

	public final WMenuItem getItemBack() {
		return itemBack;
	}

	public final WMenuItem getItemAdd() {
		return itemAdd;
	}

	public final WMenuItem getItemReset() {
		return itemReset;
	}

}