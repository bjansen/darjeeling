/**
 * This class is generated by jOOQ
 */
package gen.com.github.bjansen.darjeeling.tables;

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
public class Items extends org.jooq.impl.TableImpl<gen.com.github.bjansen.darjeeling.tables.records.ItemsRecord> {

	private static final long serialVersionUID = -932280135;

	/**
	 * The reference instance of <code>feedzee2.items</code>
	 */
	public static final gen.com.github.bjansen.darjeeling.tables.Items ITEMS = new gen.com.github.bjansen.darjeeling.tables.Items();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<gen.com.github.bjansen.darjeeling.tables.records.ItemsRecord> getRecordType() {
		return gen.com.github.bjansen.darjeeling.tables.records.ItemsRecord.class;
	}

	/**
	 * The column <code>feedzee2.items.id</code>.
	 */
	public final org.jooq.TableField<gen.com.github.bjansen.darjeeling.tables.records.ItemsRecord, java.lang.Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

	/**
	 * The column <code>feedzee2.items.feed_id</code>.
	 */
	public final org.jooq.TableField<gen.com.github.bjansen.darjeeling.tables.records.ItemsRecord, java.lang.Integer> FEED_ID = createField("feed_id", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>feedzee2.items.title</code>.
	 */
	public final org.jooq.TableField<gen.com.github.bjansen.darjeeling.tables.records.ItemsRecord, java.lang.String> TITLE = createField("title", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

	/**
	 * The column <code>feedzee2.items.description</code>.
	 */
	public final org.jooq.TableField<gen.com.github.bjansen.darjeeling.tables.records.ItemsRecord, java.lang.String> DESCRIPTION = createField("description", org.jooq.impl.SQLDataType.CLOB.length(65535), this, "");

	/**
	 * The column <code>feedzee2.items.publication_date</code>.
	 */
	public final org.jooq.TableField<gen.com.github.bjansen.darjeeling.tables.records.ItemsRecord, ceylon.time.DateTime> PUBLICATION_DATE = createField("publication_date", org.jooq.impl.SQLDataType.TIMESTAMP, this, "", new gen.com.github.bjansen.darjeeling.jooq.DateTimeConverter());

	/**
	 * The column <code>feedzee2.items.url</code>.
	 */
	public final org.jooq.TableField<gen.com.github.bjansen.darjeeling.tables.records.ItemsRecord, java.lang.String> URL = createField("url", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

	/**
	 * The column <code>feedzee2.items.created_at</code>.
	 */
	public final org.jooq.TableField<gen.com.github.bjansen.darjeeling.tables.records.ItemsRecord, ceylon.time.DateTime> CREATED_AT = createField("created_at", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false), this, "", new gen.com.github.bjansen.darjeeling.jooq.DateTimeConverter());

	/**
	 * The column <code>feedzee2.items.updated_at</code>.
	 */
	public final org.jooq.TableField<gen.com.github.bjansen.darjeeling.tables.records.ItemsRecord, ceylon.time.DateTime> UPDATED_AT = createField("updated_at", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false), this, "", new gen.com.github.bjansen.darjeeling.jooq.DateTimeConverter());

	/**
	 * The column <code>feedzee2.items.guid</code>.
	 */
	public final org.jooq.TableField<gen.com.github.bjansen.darjeeling.tables.records.ItemsRecord, java.lang.String> GUID = createField("guid", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

	/**
	 * The column <code>feedzee2.items.subscription_pushed</code>.
	 */
	public final org.jooq.TableField<gen.com.github.bjansen.darjeeling.tables.records.ItemsRecord, java.lang.Byte> SUBSCRIPTION_PUSHED = createField("subscription_pushed", org.jooq.impl.SQLDataType.TINYINT, this, "");

	/**
	 * Create a <code>feedzee2.items</code> table reference
	 */
	public Items() {
		this("items", null);
	}

	/**
	 * Create an aliased <code>feedzee2.items</code> table reference
	 */
	public Items(java.lang.String alias) {
		this(alias, gen.com.github.bjansen.darjeeling.tables.Items.ITEMS);
	}

	private Items(java.lang.String alias, org.jooq.Table<gen.com.github.bjansen.darjeeling.tables.records.ItemsRecord> aliased) {
		this(alias, aliased, null);
	}

	private Items(java.lang.String alias, org.jooq.Table<gen.com.github.bjansen.darjeeling.tables.records.ItemsRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, gen.com.github.bjansen.darjeeling.Feedzee2.FEEDZEE2, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.Identity<gen.com.github.bjansen.darjeeling.tables.records.ItemsRecord, java.lang.Integer> getIdentity() {
		return gen.com.github.bjansen.darjeeling.Keys.IDENTITY_ITEMS;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<gen.com.github.bjansen.darjeeling.tables.records.ItemsRecord> getPrimaryKey() {
		return gen.com.github.bjansen.darjeeling.Keys.KEY_ITEMS_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<gen.com.github.bjansen.darjeeling.tables.records.ItemsRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<gen.com.github.bjansen.darjeeling.tables.records.ItemsRecord>>asList(gen.com.github.bjansen.darjeeling.Keys.KEY_ITEMS_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public gen.com.github.bjansen.darjeeling.tables.Items as(java.lang.String alias) {
		return new gen.com.github.bjansen.darjeeling.tables.Items(alias, this);
	}

	/**
	 * Rename this table
	 */
	public gen.com.github.bjansen.darjeeling.tables.Items rename(java.lang.String name) {
		return new gen.com.github.bjansen.darjeeling.tables.Items(name, null);
	}
}
