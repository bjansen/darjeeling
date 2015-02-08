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
public class Storageversion extends org.jooq.impl.TableImpl<gen.com.github.bjansen.darjeeling.tables.records.StorageversionRecord> {

	private static final long serialVersionUID = -144669081;

	/**
	 * The reference instance of <code>feedzee2.storageversion</code>
	 */
	public static final gen.com.github.bjansen.darjeeling.tables.Storageversion STORAGEVERSION = new gen.com.github.bjansen.darjeeling.tables.Storageversion();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<gen.com.github.bjansen.darjeeling.tables.records.StorageversionRecord> getRecordType() {
		return gen.com.github.bjansen.darjeeling.tables.records.StorageversionRecord.class;
	}

	/**
	 * The column <code>feedzee2.storageversion.id</code>.
	 */
	public final org.jooq.TableField<gen.com.github.bjansen.darjeeling.tables.records.StorageversionRecord, java.lang.String> ID = createField("id", org.jooq.impl.SQLDataType.VARCHAR.length(200).nullable(false), this, "");

	/**
	 * The column <code>feedzee2.storageversion.contextName</code>.
	 */
	public final org.jooq.TableField<gen.com.github.bjansen.darjeeling.tables.records.StorageversionRecord, java.lang.String> CONTEXTNAME = createField("contextName", org.jooq.impl.SQLDataType.VARCHAR.length(200), this, "");

	/**
	 * The column <code>feedzee2.storageversion.lastAction</code>.
	 */
	public final org.jooq.TableField<gen.com.github.bjansen.darjeeling.tables.records.StorageversionRecord, java.lang.Integer> LASTACTION = createField("lastAction", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>feedzee2.storageversion.lastScript</code>.
	 */
	public final org.jooq.TableField<gen.com.github.bjansen.darjeeling.tables.records.StorageversionRecord, java.lang.Long> LASTSCRIPT = createField("lastScript", org.jooq.impl.SQLDataType.BIGINT, this, "");

	/**
	 * Create a <code>feedzee2.storageversion</code> table reference
	 */
	public Storageversion() {
		this("storageversion", null);
	}

	/**
	 * Create an aliased <code>feedzee2.storageversion</code> table reference
	 */
	public Storageversion(java.lang.String alias) {
		this(alias, gen.com.github.bjansen.darjeeling.tables.Storageversion.STORAGEVERSION);
	}

	private Storageversion(java.lang.String alias, org.jooq.Table<gen.com.github.bjansen.darjeeling.tables.records.StorageversionRecord> aliased) {
		this(alias, aliased, null);
	}

	private Storageversion(java.lang.String alias, org.jooq.Table<gen.com.github.bjansen.darjeeling.tables.records.StorageversionRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, gen.com.github.bjansen.darjeeling.Feedzee2.FEEDZEE2, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public org.jooq.UniqueKey<gen.com.github.bjansen.darjeeling.tables.records.StorageversionRecord> getPrimaryKey() {
		return gen.com.github.bjansen.darjeeling.Keys.KEY_STORAGEVERSION_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<gen.com.github.bjansen.darjeeling.tables.records.StorageversionRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<gen.com.github.bjansen.darjeeling.tables.records.StorageversionRecord>>asList(gen.com.github.bjansen.darjeeling.Keys.KEY_STORAGEVERSION_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public gen.com.github.bjansen.darjeeling.tables.Storageversion as(java.lang.String alias) {
		return new gen.com.github.bjansen.darjeeling.tables.Storageversion(alias, this);
	}

	/**
	 * Rename this table
	 */
	public gen.com.github.bjansen.darjeeling.tables.Storageversion rename(java.lang.String name) {
		return new gen.com.github.bjansen.darjeeling.tables.Storageversion(name, null);
	}
}
