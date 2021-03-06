/**
 * This class is generated by jOOQ
 */
package gen.com.github.bjansen.darjeeling.tables;


import ceylon.language.String;

import com.github.bjansen.ceylon.jooqadapter.StringConverter;

import gen.com.github.bjansen.darjeeling.Feedzee2;
import gen.com.github.bjansen.darjeeling.Keys;
import gen.com.github.bjansen.darjeeling.tables.records.SchemaMigrationsRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


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
public class SchemaMigrations extends TableImpl<SchemaMigrationsRecord> {

	private static final long serialVersionUID = -909323041;

	/**
	 * The reference instance of <code>feedzee2.schema_migrations</code>
	 */
	public static final SchemaMigrations schemaMigrations = new SchemaMigrations();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<SchemaMigrationsRecord> getRecordType() {
		return SchemaMigrationsRecord.class;
	}

	/**
	 * The column <code>feedzee2.schema_migrations.version</code>.
	 */
	public final TableField<SchemaMigrationsRecord, String> version = createField("version", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "", new StringConverter());

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
		this(alias, schemaMigrations);
	}

	private SchemaMigrations(java.lang.String alias, Table<SchemaMigrationsRecord> aliased) {
		this(alias, aliased, null);
	}

	private SchemaMigrations(java.lang.String alias, Table<SchemaMigrationsRecord> aliased, Field<?>[] parameters) {
		super(alias, Feedzee2.feedzee2, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<SchemaMigrationsRecord>> getKeys() {
		return Arrays.<UniqueKey<SchemaMigrationsRecord>>asList(Keys.keySchemaMigrationsUniqueSchemaMigrations);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SchemaMigrations as(java.lang.String alias) {
		return new SchemaMigrations(alias, this);
	}

	/**
	 * Rename this table
	 */
	public SchemaMigrations rename(java.lang.String name) {
		return new SchemaMigrations(name, null);
	}
}
