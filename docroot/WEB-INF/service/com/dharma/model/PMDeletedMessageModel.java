/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.dharma.model;

import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the PMDeletedMessage service. Represents a row in the &quot;deleted_message&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.dharma.model.impl.PMDeletedMessageModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.dharma.model.impl.PMDeletedMessageImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PMDeletedMessage
 * @see com.dharma.model.impl.PMDeletedMessageImpl
 * @see com.dharma.model.impl.PMDeletedMessageModelImpl
 * @generated
 */
public interface PMDeletedMessageModel extends BaseModel<PMDeletedMessage> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a p m deleted message model instance should use the {@link PMDeletedMessage} interface instead.
	 */

	/**
	 * Returns the primary key of this p m deleted message.
	 *
	 * @return the primary key of this p m deleted message
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this p m deleted message.
	 *
	 * @param primaryKey the primary key of this p m deleted message
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the deleted message ID of this p m deleted message.
	 *
	 * @return the deleted message ID of this p m deleted message
	 */
	public long getDeletedMessageId();

	/**
	 * Sets the deleted message ID of this p m deleted message.
	 *
	 * @param deletedMessageId the deleted message ID of this p m deleted message
	 */
	public void setDeletedMessageId(long deletedMessageId);

	/**
	 * Returns the message ID of this p m deleted message.
	 *
	 * @return the message ID of this p m deleted message
	 */
	public long getMessageId();

	/**
	 * Sets the message ID of this p m deleted message.
	 *
	 * @param messageId the message ID of this p m deleted message
	 */
	public void setMessageId(long messageId);

	/**
	 * Returns the owner ID of this p m deleted message.
	 *
	 * @return the owner ID of this p m deleted message
	 */
	public long getOwnerId();

	/**
	 * Sets the owner ID of this p m deleted message.
	 *
	 * @param ownerId the owner ID of this p m deleted message
	 */
	public void setOwnerId(long ownerId);

	/**
	 * Returns the deleted date of this p m deleted message.
	 *
	 * @return the deleted date of this p m deleted message
	 */
	public Date getDeletedDate();

	/**
	 * Sets the deleted date of this p m deleted message.
	 *
	 * @param deletedDate the deleted date of this p m deleted message
	 */
	public void setDeletedDate(Date deletedDate);

	public boolean isNew();

	public void setNew(boolean n);

	public boolean isCachedModel();

	public void setCachedModel(boolean cachedModel);

	public boolean isEscapedModel();

	public Serializable getPrimaryKeyObj();

	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	public ExpandoBridge getExpandoBridge();

	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	public Object clone();

	public int compareTo(PMDeletedMessage pmDeletedMessage);

	public int hashCode();

	public CacheModel<PMDeletedMessage> toCacheModel();

	public PMDeletedMessage toEscapedModel();

	public String toString();

	public String toXmlString();
}