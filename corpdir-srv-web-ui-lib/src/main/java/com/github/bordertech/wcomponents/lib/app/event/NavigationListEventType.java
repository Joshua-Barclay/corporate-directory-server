package com.github.bordertech.wcomponents.lib.app.event;

import com.github.bordertech.wcomponents.lib.flux.EventType;

/**
 * Navigation for list items.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
public enum NavigationListEventType implements EventType {
	FIRST,
	PREV,
	NEXT,
	LAST
}
