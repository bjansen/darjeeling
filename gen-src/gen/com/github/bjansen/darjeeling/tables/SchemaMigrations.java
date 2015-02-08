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
public class SchemaMigrations extends org.jooq.impl.TableImpl<gen.com.github.bjansen.darjeeling.tables.records.SchemaMigrationsRecord> {

	private static final long serialVersionUID = 1622390677;

	/**
	 * The reference instance of <code>feedzee2.schema_migrations</code>
	 */
	public static final gen.com.github.bjansen.darjeeling.tables.SchemaMigrations SCHEMA_MIGRATIONS = new gen.com.github.bjansen.darjeeling.tables.SchemaMigrations();

	/**
	 * The class holding records for this type
	 */
	@Override
	public java.lang.Class<gen.com.github.bjansen.darjeeling.tables.records.SchemaMigrationsRecord> getRecordType() {
		return gen.com.github.bjansen.darjeeling.tables.records.SchemaMigrationsRecord.class;
	}

	/**
	 * The column <code>feedzee2.schema_migrations.version</code>.
	 */
	public final org.jooq.TableField<gen.com.github.bjansen.darjeeling.tables.records.SchemaMigrationsRecord, java.lang.String> VERSION = createField("version", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

	/**
	 * Create a <code>feedzee2.schema_migrations</code> table reference
	 */
	public SchemaMigrations() {
		this("schema_migrations", null);
	}

	/**
	 * Create an aliased <code>feedzee2.schema_migrations</code> table reference
	 */
	public SchemaMigrations(java.lang.String alias) {
		this(alias, gen.com.github.bjansen.darjeeling.tables.SchemaMigrations.SCHEMA_MIGRATIONS);
	}

	private SchemaMigrations(java.lang.String alias, org.jooq.Table<gen.com.github.bjansen.darjeeling.tables.records.SchemaMigrationsRecord> aliased) {
		this(alias, aliased, null);
	}

	private SchemaMigrations(java.lang.String alias, org.jooq.Table<gen.com.github.bjansen.darjeeling.tables.records.SchemaMigrationsRecord> aliased, org.jooq.Field<?>[] parameters) {
		super(alias, gen.com.github.bjansen.darjeeling.Feedzee2.FEEDZEE2, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.List<org.jooq.UniqueKey<gen.com.github.bjansen.darjeeling.tables.records.SchemaMigrationsRecord>> getKeys() {
		return java.util.Arrays.<org.jooq.UniqueKey<gen.com.github.bjansen.darjeeling.tables.records.SchemaMigrationsRecord>>asList(gen.com.github.bjansen.darjeeling.Keys.KEY_SCHEMA_MIGRATIONS_UNIQUE_SCHEMA_MIGRATIONS);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public gen.com.github.bjansen.darjeeling.tables.SchemaMigrations as(java.lang.String alias) {
		return new gen.com.github.bjansen.darjeeling.tables.SchemaMigrations(alias, this);
	}

	/**
	 * Rename this table
	 */
	public gen.com.github.bjansen.darjeeling.tables.SchemaMigrations rename(java.lang.String name) {
		return new gen.com.github.bjansen.darjeeling.tables.SchemaMigrations(name, null);
	}
}
