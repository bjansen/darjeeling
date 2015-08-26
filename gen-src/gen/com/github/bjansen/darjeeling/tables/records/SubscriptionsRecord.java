/**
 * This class is generated by jOOQ
 */
package gen.com.github.bjansen.darjeeling.tables.records;


import ceylon.language.Integer;
import ceylon.time.DateTime;

import gen.com.github.bjansen.darjeeling.tables.Subscriptions;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.7.0"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class SubscriptionsRecord extends UpdatableRecordImpl<SubscriptionsRecord> implements Record6<Integer, Integer, Integer, Integer, DateTime, DateTime> {

	private static final long serialVersionUID = -858864423;

	/**
	 * Setter for <code>feedzee2.subscriptions.id</code>.
	 */
	public void setId(Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>feedzee2.subscriptions.id</code>.
	 */
	public Integer getId() {
		return (Integer) getValue(0);
	}

	/**
	 * Setter for <code>feedzee2.subscriptions.feed_id</code>.
	 */
	public void setFeedId(Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>feedzee2.subscriptions.feed_id</code>.
	 */
	public Integer getFeedId() {
		return (Integer) getValue(1);
	}

	/**
	 * Setter for <code>feedzee2.subscriptions.folder_id</code>.
	 */
	public void setFolderId(Integer value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>feedzee2.subscriptions.folder_id</code>.
	 */
	public Integer getFolderId() {
		return (Integer) getValue(2);
	}

	/**
	 * Setter for <code>feedzee2.subscriptions.user_id</code>.
	 */
	public void setUserId(Integer value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>feedzee2.subscriptions.user_id</code>.
	 */
	public Integer getUserId() {
		return (Integer) getValue(3);
	}

	/**
	 * Setter for <code>feedzee2.subscriptions.created_at</code>.
	 */
	public void setCreatedAt(DateTime value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>feedzee2.subscriptions.created_at</code>.
	 */
	public DateTime getCreatedAt() {
		return (DateTime) getValue(4);
	}

	/**
	 * Setter for <code>feedzee2.subscriptions.updated_at</code>.
	 */
	public void setUpdatedAt(DateTime value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>feedzee2.subscriptions.updated_at</code>.
	 */
	public DateTime getUpdatedAt() {
		return (DateTime) getValue(5);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Record1<Integer> key() {
		return (Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record6 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row6<Integer, Integer, Integer, Integer, DateTime, DateTime> fieldsRow() {
		return (Row6) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row6<Integer, Integer, Integer, Integer, DateTime, DateTime> valuesRow() {
		return (Row6) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field1() {
		return Subscriptions.subscriptions.id;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field2() {
		return Subscriptions.subscriptions.feedId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field3() {
		return Subscriptions.subscriptions.folderId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field4() {
		return Subscriptions.subscriptions.userId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<DateTime> field5() {
		return Subscriptions.subscriptions.createdAt;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<DateTime> field6() {
		return Subscriptions.subscriptions.updatedAt;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value1() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value2() {
		return getFeedId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value3() {
		return getFolderId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value4() {
		return getUserId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DateTime value5() {
		return getCreatedAt();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DateTime value6() {
		return getUpdatedAt();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SubscriptionsRecord value1(Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SubscriptionsRecord value2(Integer value) {
		setFeedId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SubscriptionsRecord value3(Integer value) {
		setFolderId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SubscriptionsRecord value4(Integer value) {
		setUserId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SubscriptionsRecord value5(DateTime value) {
		setCreatedAt(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SubscriptionsRecord value6(DateTime value) {
		setUpdatedAt(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SubscriptionsRecord values(Integer value1, Integer value2, Integer value3, Integer value4, DateTime value5, DateTime value6) {
		value1(value1);
		value2(value2);
		value3(value3);
		value4(value4);
		value5(value5);
		value6(value6);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached SubscriptionsRecord
	 */
	public SubscriptionsRecord() {
		super(Subscriptions.subscriptions);
	}

	/**
	 * Create a detached, initialised SubscriptionsRecord
	 */
	public SubscriptionsRecord(Integer id, Integer feedId, Integer folderId, Integer userId, DateTime createdAt, DateTime updatedAt) {
		super(Subscriptions.subscriptions);

		setValue(0, id);
		setValue(1, feedId);
		setValue(2, folderId);
		setValue(3, userId);
		setValue(4, createdAt);
		setValue(5, updatedAt);
	}
}
