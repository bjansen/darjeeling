/**
 * This class is generated by jOOQ
 */
package gen.com.github.bjansen.darjeeling.tables.records;


import ceylon.language.Integer;
import ceylon.time.DateTime;

import gen.com.github.bjansen.darjeeling.tables.ItemsToRead;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
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
public class ItemsToReadRecord extends UpdatableRecordImpl<ItemsToReadRecord> implements Record5<Integer, Integer, Integer, DateTime, DateTime> {

	private static final long serialVersionUID = -408597806;

	/**
	 * Setter for <code>feedzee2.items_to_read.id</code>.
	 */
	public void setId(Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>feedzee2.items_to_read.id</code>.
	 */
	public Integer getId() {
		return (Integer) getValue(0);
	}

	/**
	 * Setter for <code>feedzee2.items_to_read.item_id</code>.
	 */
	public void setItemId(Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>feedzee2.items_to_read.item_id</code>.
	 */
	public Integer getItemId() {
		return (Integer) getValue(1);
	}

	/**
	 * Setter for <code>feedzee2.items_to_read.user_id</code>.
	 */
	public void setUserId(Integer value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>feedzee2.items_to_read.user_id</code>.
	 */
	public Integer getUserId() {
		return (Integer) getValue(2);
	}

	/**
	 * Setter for <code>feedzee2.items_to_read.created_at</code>.
	 */
	public void setCreatedAt(DateTime value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>feedzee2.items_to_read.created_at</code>.
	 */
	public DateTime getCreatedAt() {
		return (DateTime) getValue(3);
	}

	/**
	 * Setter for <code>feedzee2.items_to_read.updated_at</code>.
	 */
	public void setUpdatedAt(DateTime value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>feedzee2.items_to_read.updated_at</code>.
	 */
	public DateTime getUpdatedAt() {
		return (DateTime) getValue(4);
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
	// Record5 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row5<Integer, Integer, Integer, DateTime, DateTime> fieldsRow() {
		return (Row5) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row5<Integer, Integer, Integer, DateTime, DateTime> valuesRow() {
		return (Row5) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field1() {
		return ItemsToRead.itemsToRead.id;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field2() {
		return ItemsToRead.itemsToRead.itemId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field3() {
		return ItemsToRead.itemsToRead.userId;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<DateTime> field4() {
		return ItemsToRead.itemsToRead.createdAt;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<DateTime> field5() {
		return ItemsToRead.itemsToRead.updatedAt;
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
		return getItemId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value3() {
		return getUserId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DateTime value4() {
		return getCreatedAt();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DateTime value5() {
		return getUpdatedAt();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ItemsToReadRecord value1(Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ItemsToReadRecord value2(Integer value) {
		setItemId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ItemsToReadRecord value3(Integer value) {
		setUserId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ItemsToReadRecord value4(DateTime value) {
		setCreatedAt(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ItemsToReadRecord value5(DateTime value) {
		setUpdatedAt(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ItemsToReadRecord values(Integer value1, Integer value2, Integer value3, DateTime value4, DateTime value5) {
		value1(value1);
		value2(value2);
		value3(value3);
		value4(value4);
		value5(value5);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached ItemsToReadRecord
	 */
	public ItemsToReadRecord() {
		super(ItemsToRead.itemsToRead);
	}

	/**
	 * Create a detached, initialised ItemsToReadRecord
	 */
	public ItemsToReadRecord(Integer id, Integer itemId, Integer userId, DateTime createdAt, DateTime updatedAt) {
		super(ItemsToRead.itemsToRead);

		setValue(0, id);
		setValue(1, itemId);
		setValue(2, userId);
		setValue(3, createdAt);
		setValue(4, updatedAt);
	}
}
