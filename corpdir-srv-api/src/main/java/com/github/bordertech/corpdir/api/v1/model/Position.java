package com.github.bordertech.corpdir.api.v1.model;

import com.github.bordertech.corpdir.api.common.DefaultVersionTreeObject;
import com.github.bordertech.corpdir.api.v1.model.links.PositionLinks;
import java.util.ArrayList;
import java.util.List;

/**
 * Position in organization.
 *
 * @author Jonathan Austin
 * @since 1.0.0
 */
public class Position extends DefaultVersionTreeObject implements PositionLinks {

	private String typeId;
	private String ouId;
	private List<String> manageOuIds;
	private List<String> contactIds;

	protected Position() {
	}

	public Position(final String id) {
		super(id);
	}

	/**
	 *
	 * @return the position type key
	 */
	public String getTypeId() {
		return typeId;
	}

	/**
	 *
	 * @param typeId the position type key
	 */
	public void setTypeId(final String typeId) {
		this.typeId = typeId;
	}

	/**
	 *
	 * @return the org unit key the position belongs to
	 */
	@Override
	public String getOuId() {
		return ouId;
	}

	/**
	 *
	 * @param ouId the org unit key the position belongs to
	 */
	@Override
	public void setOuId(final String ouId) {
		this.ouId = ouId;
	}

	/**
	 *
	 * @return the org unit keys this position manages
	 */
	@Override
	public List<String> getManageOuIds() {
		if (manageOuIds == null) {
			manageOuIds = new ArrayList<>();
		}
		return manageOuIds;
	}

	/**
	 *
	 * @param manageOuIds the org unit keys this position manages
	 */
	@Override
	public void setManageOuIds(final List<String> manageOuIds) {
		this.manageOuIds = manageOuIds;
	}

	/**
	 *
	 * @return the contact keys for this position
	 */
	@Override
	public List<String> getContactIds() {
		if (contactIds == null) {
			contactIds = new ArrayList<>();
		}
		return contactIds;
	}

	/**
	 *
	 * @param contactIds the contact keys for this position
	 */
	@Override
	public void setContactIds(final List<String> contactIds) {
		this.contactIds = contactIds;
	}

}
