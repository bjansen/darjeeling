/**
 * This class is generated by jOOQ
 */
package gen.com.github.bjansen.darjeeling.tables.records;


import ceylon.language.String;

import gen.com.github.bjansen.darjeeling.tables.SchemaMigrations;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Row1;
import org.jooq.impl.TableRecordImpl;


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
public class SchemaMigrationsRecord extends TableRecordImpl<SchemaMigrationsRecord> implements Record1<String> {

	private static final long serialVersionUID = -1486183023;

	/**
	 * Setter for <code>feedzee2.schema_migrations.version</code>.
	 */
	public void setVersion(String value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>feedzee2.schema_migrations.version</code>.
	 */
	public String getVersion() {
		return (String) getValue(0);
	}

	// -------------------------------------------------------------------------
	// Record1 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row1<String> fieldsRow() {
		return (Row1) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row1<String> valuesRow() {
		return (Row1) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field1() {
		return SchemaMigrations.schemaMigrations.version;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value1() {
		return getVersion();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SchemaMigrationsRecord value1(String value) {
		setVersion(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SchemaMigrationsRecord values(String value1) {
		value1(value1);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached SchemaMigrationsRecord
	 */
	public SchemaMigrationsRecord() {
		super(SchemaMigrations.schemaMigrations);
	}

	/**
	 * Create a detached, initialised SchemaMigrationsRecord
	 */
	public SchemaMigrationsRecord(String version) {
		super(SchemaMigrations.schemaMigrations);

		setValue(0, version);
	}
}
