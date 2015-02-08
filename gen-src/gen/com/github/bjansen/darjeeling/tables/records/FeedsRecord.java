/**
 * This class is generated by jOOQ
 */
package gen.com.github.bjansen.darjeeling.tables.records;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.5.0"
	},
	comments = "This class is generated by jOOQ"
)
@java.lang.SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class FeedsRecord extends org.jooq.impl.UpdatableRecordImpl<gen.com.github.bjansen.darjeeling.tables.records.FeedsRecord> implements org.jooq.Record10<java.lang.Integer, java.lang.String, ceylon.time.DateTime, ceylon.time.DateTime, ceylon.time.DateTime, java.lang.String, java.sql.Date, java.lang.String, java.lang.String, java.lang.String> {

	private static final long serialVersionUID = -2035176648;

	/**
	 * Setter for <code>feedzee2.feeds.id</code>.
	 */
	public void setId(java.lang.Integer value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>feedzee2.feeds.id</code>.
	 */
	public java.lang.Integer getId() {
		return (java.lang.Integer) getValue(0);
	}

	/**
	 * Setter for <code>feedzee2.feeds.url</code>.
	 */
	public void setUrl(java.lang.String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>feedzee2.feeds.url</code>.
	 */
	public java.lang.String getUrl() {
		return (java.lang.String) getValue(1);
	}

	/**
	 * Setter for <code>feedzee2.feeds.last_checked_date</code>.
	 */
	public void setLastCheckedDate(ceylon.time.DateTime value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>feedzee2.feeds.last_checked_date</code>.
	 */
	public ceylon.time.DateTime getLastCheckedDate() {
		return (ceylon.time.DateTime) getValue(2);
	}

	/**
	 * Setter for <code>feedzee2.feeds.created_at</code>.
	 */
	public void setCreatedAt(ceylon.time.DateTime value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>feedzee2.feeds.created_at</code>.
	 */
	public ceylon.time.DateTime getCreatedAt() {
		return (ceylon.time.DateTime) getValue(3);
	}

	/**
	 * Setter for <code>feedzee2.feeds.updated_at</code>.
	 */
	public void setUpdatedAt(ceylon.time.DateTime value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>feedzee2.feeds.updated_at</code>.
	 */
	public ceylon.time.DateTime getUpdatedAt() {
		return (ceylon.time.DateTime) getValue(4);
	}

	/**
	 * Setter for <code>feedzee2.feeds.name</code>.
	 */
	public void setName(java.lang.String value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>feedzee2.feeds.name</code>.
	 */
	public java.lang.String getName() {
		return (java.lang.String) getValue(5);
	}

	/**
	 * Setter for <code>feedzee2.feeds.last_modified_at</code>.
	 */
	public void setLastModifiedAt(java.sql.Date value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>feedzee2.feeds.last_modified_at</code>.
	 */
	public java.sql.Date getLastModifiedAt() {
		return (java.sql.Date) getValue(6);
	}

	/**
	 * Setter for <code>feedzee2.feeds.etag</code>.
	 */
	public void setEtag(java.lang.String value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>feedzee2.feeds.etag</code>.
	 */
	public java.lang.String getEtag() {
		return (java.lang.String) getValue(7);
	}

	/**
	 * Setter for <code>feedzee2.feeds.last_url</code>.
	 */
	public void setLastUrl(java.lang.String value) {
		setValue(8, value);
	}

	/**
	 * Getter for <code>feedzee2.feeds.last_url</code>.
	 */
	public java.lang.String getLastUrl() {
		return (java.lang.String) getValue(8);
	}

	/**
	 * Setter for <code>feedzee2.feeds.site_url</code>.
	 */
	public void setSiteUrl(java.lang.String value) {
		setValue(9, value);
	}

	/**
	 * Getter for <code>feedzee2.feeds.site_url</code>.
	 */
	public java.lang.String getSiteUrl() {
		return (java.lang.String) getValue(9);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Record1<java.lang.Integer> key() {
		return (org.jooq.Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record10 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row10<java.lang.Integer, java.lang.String, ceylon.time.DateTime, ceylon.time.DateTime, ceylon.time.DateTime, java.lang.String, java.sql.Date, java.lang.String, java.lang.String, java.lang.String> fieldsRow() {
		return (org.jooq.Row10) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Row10<java.lang.Integer, java.lang.String, ceylon.time.DateTime, ceylon.time.DateTime, ceylon.time.DateTime, java.lang.String, java.sql.Date, java.lang.String, java.lang.String, java.lang.String> valuesRow() {
		return (org.jooq.Row10) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.Integer> field1() {
		return gen.com.github.bjansen.darjeeling.tables.Feeds.FEEDS.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field2() {
		return gen.com.github.bjansen.darjeeling.tables.Feeds.FEEDS.URL;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<ceylon.time.DateTime> field3() {
		return gen.com.github.bjansen.darjeeling.tables.Feeds.FEEDS.LAST_CHECKED_DATE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<ceylon.time.DateTime> field4() {
		return gen.com.github.bjansen.darjeeling.tables.Feeds.FEEDS.CREATED_AT;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<ceylon.time.DateTime> field5() {
		return gen.com.github.bjansen.darjeeling.tables.Feeds.FEEDS.UPDATED_AT;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field6() {
		return gen.com.github.bjansen.darjeeling.tables.Feeds.FEEDS.NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.sql.Date> field7() {
		return gen.com.github.bjansen.darjeeling.tables.Feeds.FEEDS.LAST_MODIFIED_AT;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field8() {
		return gen.com.github.bjansen.darjeeling.tables.Feeds.FEEDS.ETAG;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field9() {
		return gen.com.github.bjansen.darjeeling.tables.Feeds.FEEDS.LAST_URL;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Field<java.lang.String> field10() {
		return gen.com.github.bjansen.darjeeling.tables.Feeds.FEEDS.SITE_URL;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.Integer value1() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value2() {
		return getUrl();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ceylon.time.DateTime value3() {
		return getLastCheckedDate();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ceylon.time.DateTime value4() {
		return getCreatedAt();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ceylon.time.DateTime value5() {
		return getUpdatedAt();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value6() {
		return getName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.sql.Date value7() {
		return getLastModifiedAt();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value8() {
		return getEtag();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value9() {
		return getLastUrl();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.lang.String value10() {
		return getSiteUrl();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FeedsRecord value1(java.lang.Integer value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FeedsRecord value2(java.lang.String value) {
		setUrl(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FeedsRecord value3(ceylon.time.DateTime value) {
		setLastCheckedDate(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FeedsRecord value4(ceylon.time.DateTime value) {
		setCreatedAt(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FeedsRecord value5(ceylon.time.DateTime value) {
		setUpdatedAt(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FeedsRecord value6(java.lang.String value) {
		setName(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FeedsRecord value7(java.sql.Date value) {
		setLastModifiedAt(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FeedsRecord value8(java.lang.String value) {
		setEtag(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FeedsRecord value9(java.lang.String value) {
		setLastUrl(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FeedsRecord value10(java.lang.String value) {
		setSiteUrl(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FeedsRecord values(java.lang.Integer value1, java.lang.String value2, ceylon.time.DateTime value3, ceylon.time.DateTime value4, ceylon.time.DateTime value5, java.lang.String value6, java.sql.Date value7, java.lang.String value8, java.lang.String value9, java.lang.String value10) {
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached FeedsRecord
	 */
	public FeedsRecord() {
		super(gen.com.github.bjansen.darjeeling.tables.Feeds.FEEDS);
	}

	/**
	 * Create a detached, initialised FeedsRecord
	 */
	public FeedsRecord(java.lang.Integer id, java.lang.String url, ceylon.time.DateTime lastCheckedDate, ceylon.time.DateTime createdAt, ceylon.time.DateTime updatedAt, java.lang.String name, java.sql.Date lastModifiedAt, java.lang.String etag, java.lang.String lastUrl, java.lang.String siteUrl) {
		super(gen.com.github.bjansen.darjeeling.tables.Feeds.FEEDS);

		setValue(0, id);
		setValue(1, url);
		setValue(2, lastCheckedDate);
		setValue(3, createdAt);
		setValue(4, updatedAt);
		setValue(5, name);
		setValue(6, lastModifiedAt);
		setValue(7, etag);
		setValue(8, lastUrl);
		setValue(9, siteUrl);
	}
}
