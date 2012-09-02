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

package com.dharma.service.persistence;

import com.dharma.NoSuchPMReadMessageException;

import com.dharma.model.PMReadMessage;
import com.dharma.model.impl.PMReadMessageImpl;
import com.dharma.model.impl.PMReadMessageModelImpl;

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the p m read message service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PMReadMessagePersistence
 * @see PMReadMessageUtil
 * @generated
 */
public class PMReadMessagePersistenceImpl extends BasePersistenceImpl<PMReadMessage>
	implements PMReadMessagePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PMReadMessageUtil} to access the p m read message persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PMReadMessageImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_MESSAGEID =
		new FinderPath(PMReadMessageModelImpl.ENTITY_CACHE_ENABLED,
			PMReadMessageModelImpl.FINDER_CACHE_ENABLED,
			PMReadMessageImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByMessageId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MESSAGEID =
		new FinderPath(PMReadMessageModelImpl.ENTITY_CACHE_ENABLED,
			PMReadMessageModelImpl.FINDER_CACHE_ENABLED,
			PMReadMessageImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByMessageId", new String[] { Long.class.getName() },
			PMReadMessageModelImpl.MESSAGEID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_MESSAGEID = new FinderPath(PMReadMessageModelImpl.ENTITY_CACHE_ENABLED,
			PMReadMessageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByMessageId",
			new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PMReadMessageModelImpl.ENTITY_CACHE_ENABLED,
			PMReadMessageModelImpl.FINDER_CACHE_ENABLED,
			PMReadMessageImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PMReadMessageModelImpl.ENTITY_CACHE_ENABLED,
			PMReadMessageModelImpl.FINDER_CACHE_ENABLED,
			PMReadMessageImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PMReadMessageModelImpl.ENTITY_CACHE_ENABLED,
			PMReadMessageModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the p m read message in the entity cache if it is enabled.
	 *
	 * @param pmReadMessage the p m read message
	 */
	public void cacheResult(PMReadMessage pmReadMessage) {
		EntityCacheUtil.putResult(PMReadMessageModelImpl.ENTITY_CACHE_ENABLED,
			PMReadMessageImpl.class, pmReadMessage.getPrimaryKey(),
			pmReadMessage);

		pmReadMessage.resetOriginalValues();
	}

	/**
	 * Caches the p m read messages in the entity cache if it is enabled.
	 *
	 * @param pmReadMessages the p m read messages
	 */
	public void cacheResult(List<PMReadMessage> pmReadMessages) {
		for (PMReadMessage pmReadMessage : pmReadMessages) {
			if (EntityCacheUtil.getResult(
						PMReadMessageModelImpl.ENTITY_CACHE_ENABLED,
						PMReadMessageImpl.class, pmReadMessage.getPrimaryKey()) == null) {
				cacheResult(pmReadMessage);
			}
			else {
				pmReadMessage.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all p m read messages.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(PMReadMessageImpl.class.getName());
		}

		EntityCacheUtil.clearCache(PMReadMessageImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the p m read message.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(PMReadMessage pmReadMessage) {
		EntityCacheUtil.removeResult(PMReadMessageModelImpl.ENTITY_CACHE_ENABLED,
			PMReadMessageImpl.class, pmReadMessage.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<PMReadMessage> pmReadMessages) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (PMReadMessage pmReadMessage : pmReadMessages) {
			EntityCacheUtil.removeResult(PMReadMessageModelImpl.ENTITY_CACHE_ENABLED,
				PMReadMessageImpl.class, pmReadMessage.getPrimaryKey());
		}
	}

	/**
	 * Creates a new p m read message with the primary key. Does not add the p m read message to the database.
	 *
	 * @param readMessageId the primary key for the new p m read message
	 * @return the new p m read message
	 */
	public PMReadMessage create(long readMessageId) {
		PMReadMessage pmReadMessage = new PMReadMessageImpl();

		pmReadMessage.setNew(true);
		pmReadMessage.setPrimaryKey(readMessageId);

		return pmReadMessage;
	}

	/**
	 * Removes the p m read message with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param readMessageId the primary key of the p m read message
	 * @return the p m read message that was removed
	 * @throws com.dharma.NoSuchPMReadMessageException if a p m read message with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PMReadMessage remove(long readMessageId)
		throws NoSuchPMReadMessageException, SystemException {
		return remove(Long.valueOf(readMessageId));
	}

	/**
	 * Removes the p m read message with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the p m read message
	 * @return the p m read message that was removed
	 * @throws com.dharma.NoSuchPMReadMessageException if a p m read message with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PMReadMessage remove(Serializable primaryKey)
		throws NoSuchPMReadMessageException, SystemException {
		Session session = null;

		try {
			session = openSession();

			PMReadMessage pmReadMessage = (PMReadMessage)session.get(PMReadMessageImpl.class,
					primaryKey);

			if (pmReadMessage == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPMReadMessageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(pmReadMessage);
		}
		catch (NoSuchPMReadMessageException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected PMReadMessage removeImpl(PMReadMessage pmReadMessage)
		throws SystemException {
		pmReadMessage = toUnwrappedModel(pmReadMessage);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, pmReadMessage);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(pmReadMessage);

		return pmReadMessage;
	}

	@Override
	public PMReadMessage updateImpl(
		com.dharma.model.PMReadMessage pmReadMessage, boolean merge)
		throws SystemException {
		pmReadMessage = toUnwrappedModel(pmReadMessage);

		boolean isNew = pmReadMessage.isNew();

		PMReadMessageModelImpl pmReadMessageModelImpl = (PMReadMessageModelImpl)pmReadMessage;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, pmReadMessage, merge);

			pmReadMessage.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !PMReadMessageModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((pmReadMessageModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MESSAGEID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(pmReadMessageModelImpl.getOriginalMessageId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MESSAGEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MESSAGEID,
					args);

				args = new Object[] {
						Long.valueOf(pmReadMessageModelImpl.getMessageId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_MESSAGEID,
					args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MESSAGEID,
					args);
			}
		}

		EntityCacheUtil.putResult(PMReadMessageModelImpl.ENTITY_CACHE_ENABLED,
			PMReadMessageImpl.class, pmReadMessage.getPrimaryKey(),
			pmReadMessage);

		return pmReadMessage;
	}

	protected PMReadMessage toUnwrappedModel(PMReadMessage pmReadMessage) {
		if (pmReadMessage instanceof PMReadMessageImpl) {
			return pmReadMessage;
		}

		PMReadMessageImpl pmReadMessageImpl = new PMReadMessageImpl();

		pmReadMessageImpl.setNew(pmReadMessage.isNew());
		pmReadMessageImpl.setPrimaryKey(pmReadMessage.getPrimaryKey());

		pmReadMessageImpl.setReadMessageId(pmReadMessage.getReadMessageId());
		pmReadMessageImpl.setMessageId(pmReadMessage.getMessageId());
		pmReadMessageImpl.setReadDate(pmReadMessage.getReadDate());

		return pmReadMessageImpl;
	}

	/**
	 * Returns the p m read message with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the p m read message
	 * @return the p m read message
	 * @throws com.liferay.portal.NoSuchModelException if a p m read message with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PMReadMessage findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the p m read message with the primary key or throws a {@link com.dharma.NoSuchPMReadMessageException} if it could not be found.
	 *
	 * @param readMessageId the primary key of the p m read message
	 * @return the p m read message
	 * @throws com.dharma.NoSuchPMReadMessageException if a p m read message with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PMReadMessage findByPrimaryKey(long readMessageId)
		throws NoSuchPMReadMessageException, SystemException {
		PMReadMessage pmReadMessage = fetchByPrimaryKey(readMessageId);

		if (pmReadMessage == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + readMessageId);
			}

			throw new NoSuchPMReadMessageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				readMessageId);
		}

		return pmReadMessage;
	}

	/**
	 * Returns the p m read message with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the p m read message
	 * @return the p m read message, or <code>null</code> if a p m read message with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public PMReadMessage fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the p m read message with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param readMessageId the primary key of the p m read message
	 * @return the p m read message, or <code>null</code> if a p m read message with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PMReadMessage fetchByPrimaryKey(long readMessageId)
		throws SystemException {
		PMReadMessage pmReadMessage = (PMReadMessage)EntityCacheUtil.getResult(PMReadMessageModelImpl.ENTITY_CACHE_ENABLED,
				PMReadMessageImpl.class, readMessageId);

		if (pmReadMessage == _nullPMReadMessage) {
			return null;
		}

		if (pmReadMessage == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				pmReadMessage = (PMReadMessage)session.get(PMReadMessageImpl.class,
						Long.valueOf(readMessageId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (pmReadMessage != null) {
					cacheResult(pmReadMessage);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(PMReadMessageModelImpl.ENTITY_CACHE_ENABLED,
						PMReadMessageImpl.class, readMessageId,
						_nullPMReadMessage);
				}

				closeSession(session);
			}
		}

		return pmReadMessage;
	}

	/**
	 * Returns all the p m read messages where messageId = &#63;.
	 *
	 * @param messageId the message ID
	 * @return the matching p m read messages
	 * @throws SystemException if a system exception occurred
	 */
	public List<PMReadMessage> findByMessageId(long messageId)
		throws SystemException {
		return findByMessageId(messageId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the p m read messages where messageId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param messageId the message ID
	 * @param start the lower bound of the range of p m read messages
	 * @param end the upper bound of the range of p m read messages (not inclusive)
	 * @return the range of matching p m read messages
	 * @throws SystemException if a system exception occurred
	 */
	public List<PMReadMessage> findByMessageId(long messageId, int start,
		int end) throws SystemException {
		return findByMessageId(messageId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the p m read messages where messageId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param messageId the message ID
	 * @param start the lower bound of the range of p m read messages
	 * @param end the upper bound of the range of p m read messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching p m read messages
	 * @throws SystemException if a system exception occurred
	 */
	public List<PMReadMessage> findByMessageId(long messageId, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_MESSAGEID;
			finderArgs = new Object[] { messageId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_MESSAGEID;
			finderArgs = new Object[] { messageId, start, end, orderByComparator };
		}

		List<PMReadMessage> list = (List<PMReadMessage>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (PMReadMessage pmReadMessage : list) {
				if ((messageId != pmReadMessage.getMessageId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_PMREADMESSAGE_WHERE);

			query.append(_FINDER_COLUMN_MESSAGEID_MESSAGEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(PMReadMessageModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(messageId);

				list = (List<PMReadMessage>)QueryUtil.list(q, getDialect(),
						start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first p m read message in the ordered set where messageId = &#63;.
	 *
	 * @param messageId the message ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p m read message
	 * @throws com.dharma.NoSuchPMReadMessageException if a matching p m read message could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PMReadMessage findByMessageId_First(long messageId,
		OrderByComparator orderByComparator)
		throws NoSuchPMReadMessageException, SystemException {
		PMReadMessage pmReadMessage = fetchByMessageId_First(messageId,
				orderByComparator);

		if (pmReadMessage != null) {
			return pmReadMessage;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("messageId=");
		msg.append(messageId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPMReadMessageException(msg.toString());
	}

	/**
	 * Returns the first p m read message in the ordered set where messageId = &#63;.
	 *
	 * @param messageId the message ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching p m read message, or <code>null</code> if a matching p m read message could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PMReadMessage fetchByMessageId_First(long messageId,
		OrderByComparator orderByComparator) throws SystemException {
		List<PMReadMessage> list = findByMessageId(messageId, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last p m read message in the ordered set where messageId = &#63;.
	 *
	 * @param messageId the message ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p m read message
	 * @throws com.dharma.NoSuchPMReadMessageException if a matching p m read message could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PMReadMessage findByMessageId_Last(long messageId,
		OrderByComparator orderByComparator)
		throws NoSuchPMReadMessageException, SystemException {
		PMReadMessage pmReadMessage = fetchByMessageId_Last(messageId,
				orderByComparator);

		if (pmReadMessage != null) {
			return pmReadMessage;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("messageId=");
		msg.append(messageId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchPMReadMessageException(msg.toString());
	}

	/**
	 * Returns the last p m read message in the ordered set where messageId = &#63;.
	 *
	 * @param messageId the message ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching p m read message, or <code>null</code> if a matching p m read message could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PMReadMessage fetchByMessageId_Last(long messageId,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByMessageId(messageId);

		List<PMReadMessage> list = findByMessageId(messageId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the p m read messages before and after the current p m read message in the ordered set where messageId = &#63;.
	 *
	 * @param readMessageId the primary key of the current p m read message
	 * @param messageId the message ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next p m read message
	 * @throws com.dharma.NoSuchPMReadMessageException if a p m read message with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public PMReadMessage[] findByMessageId_PrevAndNext(long readMessageId,
		long messageId, OrderByComparator orderByComparator)
		throws NoSuchPMReadMessageException, SystemException {
		PMReadMessage pmReadMessage = findByPrimaryKey(readMessageId);

		Session session = null;

		try {
			session = openSession();

			PMReadMessage[] array = new PMReadMessageImpl[3];

			array[0] = getByMessageId_PrevAndNext(session, pmReadMessage,
					messageId, orderByComparator, true);

			array[1] = pmReadMessage;

			array[2] = getByMessageId_PrevAndNext(session, pmReadMessage,
					messageId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected PMReadMessage getByMessageId_PrevAndNext(Session session,
		PMReadMessage pmReadMessage, long messageId,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_PMREADMESSAGE_WHERE);

		query.append(_FINDER_COLUMN_MESSAGEID_MESSAGEID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(PMReadMessageModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(messageId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(pmReadMessage);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<PMReadMessage> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the p m read messages.
	 *
	 * @return the p m read messages
	 * @throws SystemException if a system exception occurred
	 */
	public List<PMReadMessage> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the p m read messages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of p m read messages
	 * @param end the upper bound of the range of p m read messages (not inclusive)
	 * @return the range of p m read messages
	 * @throws SystemException if a system exception occurred
	 */
	public List<PMReadMessage> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the p m read messages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of p m read messages
	 * @param end the upper bound of the range of p m read messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of p m read messages
	 * @throws SystemException if a system exception occurred
	 */
	public List<PMReadMessage> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = new Object[] { start, end, orderByComparator };

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<PMReadMessage> list = (List<PMReadMessage>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_PMREADMESSAGE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PMREADMESSAGE.concat(PMReadMessageModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<PMReadMessage>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<PMReadMessage>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the p m read messages where messageId = &#63; from the database.
	 *
	 * @param messageId the message ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByMessageId(long messageId) throws SystemException {
		for (PMReadMessage pmReadMessage : findByMessageId(messageId)) {
			remove(pmReadMessage);
		}
	}

	/**
	 * Removes all the p m read messages from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (PMReadMessage pmReadMessage : findAll()) {
			remove(pmReadMessage);
		}
	}

	/**
	 * Returns the number of p m read messages where messageId = &#63;.
	 *
	 * @param messageId the message ID
	 * @return the number of matching p m read messages
	 * @throws SystemException if a system exception occurred
	 */
	public int countByMessageId(long messageId) throws SystemException {
		Object[] finderArgs = new Object[] { messageId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_MESSAGEID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_PMREADMESSAGE_WHERE);

			query.append(_FINDER_COLUMN_MESSAGEID_MESSAGEID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(messageId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_MESSAGEID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of p m read messages.
	 *
	 * @return the number of p m read messages
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PMREADMESSAGE);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the p m read message persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.dharma.model.PMReadMessage")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<PMReadMessage>> listenersList = new ArrayList<ModelListener<PMReadMessage>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<PMReadMessage>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(PMReadMessageImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = PMBlockedUserPersistence.class)
	protected PMBlockedUserPersistence pmBlockedUserPersistence;
	@BeanReference(type = PMDeletedMessagePersistence.class)
	protected PMDeletedMessagePersistence pmDeletedMessagePersistence;
	@BeanReference(type = PMMessagePersistence.class)
	protected PMMessagePersistence pmMessagePersistence;
	@BeanReference(type = PMReadMessagePersistence.class)
	protected PMReadMessagePersistence pmReadMessagePersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	private static final String _SQL_SELECT_PMREADMESSAGE = "SELECT pmReadMessage FROM PMReadMessage pmReadMessage";
	private static final String _SQL_SELECT_PMREADMESSAGE_WHERE = "SELECT pmReadMessage FROM PMReadMessage pmReadMessage WHERE ";
	private static final String _SQL_COUNT_PMREADMESSAGE = "SELECT COUNT(pmReadMessage) FROM PMReadMessage pmReadMessage";
	private static final String _SQL_COUNT_PMREADMESSAGE_WHERE = "SELECT COUNT(pmReadMessage) FROM PMReadMessage pmReadMessage WHERE ";
	private static final String _FINDER_COLUMN_MESSAGEID_MESSAGEID_2 = "pmReadMessage.messageId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "pmReadMessage.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No PMReadMessage exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No PMReadMessage exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(PMReadMessagePersistenceImpl.class);
	private static PMReadMessage _nullPMReadMessage = new PMReadMessageImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<PMReadMessage> toCacheModel() {
				return _nullPMReadMessageCacheModel;
			}
		};

	private static CacheModel<PMReadMessage> _nullPMReadMessageCacheModel = new CacheModel<PMReadMessage>() {
			public PMReadMessage toEntityModel() {
				return _nullPMReadMessage;
			}
		};
}