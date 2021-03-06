/**
 * This class is generated by jOOQ
 */
package gen.com.github.bjansen.darjeeling.tables;


import ceylon.language.Integer;
import ceylon.language.String;
import ceylon.time.DateTime;

import com.github.bjansen.ceylon.jooqadapter.DateTimeConverter;
import com.github.bjansen.ceylon.jooqadapter.IntegerConverter;
import com.github.bjansen.ceylon.jooqadapter.StringConverter;

import gen.com.github.bjansen.darjeeling.Feedzee2;
import gen.com.github.bjansen.darjeeling.Keys;
import gen.com.github.bjansen.darjeeling.tables.records.FeedsRecord;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Identity;
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
public class Feeds extends TableImpl<FeedsRecord> {

	private static final long serialVersionUID = 375223059;

	/**
	 * The reference instance of <code>feedzee2.feeds</code>
	 */
	public static final Feeds feeds = new Feeds();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<FeedsRecord> getRecordType() {
		return FeedsRecord.class;
	}

	/**
	 * The column <code>feedzee2.feeds.id</code>.
	 */
	public final TableField<FeedsRecord, Integer> id = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "", new IntegerConverter());

	/**
	 * The column <code>feedzee2.feeds.url</code>.
	 */
	public final TableField<FeedsRecord, String> url = createField("url", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "", new StringConverter());

	/**
	 * The column <code>feedzee2.feeds.last_checked_date</code>.
	 */
	public final TableField<FeedsRecord, DateTime> lastCheckedDate = createField("last_checked_date", org.jooq.impl.SQLDataType.TIMESTAMP, this, "", new DateTimeConverter());

	/**
	 * The column <code>feedzee2.feeds.created_at</code>.
	 */
	public final TableField<FeedsRecord, DateTime> createdAt = createField("created_at", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false), this, "", new DateTimeConverter());

	/**
	 * The column <code>feedzee2.feeds.updated_at</code>.
	 */
	public final TableField<FeedsRecord, DateTime> updatedAt = createField("updated_at", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false), this, "", new DateTimeConverter());

	/**
	 * The column <code>feedzee2.feeds.name</code>.
	 */
	public final TableField<FeedsRecord, String> name = createField("name", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "", new StringConverter());

	/**
	 * The column <code>feedzee2.feeds.last_modified_at</code>.
	 */
	public final TableField<FeedsRecord, Date> lastModifiedAt = createField("last_modified_at", org.jooq.impl.SQLDataType.DATE, this, "");

	/**
	 * The column <code>feedzee2.feeds.etag</code>.
	 */
	public final TableField<FeedsRecord, String> etag = createField("etag", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "", new StringConverter());

	/**
	 * The column <code>feedzee2.feeds.last_url</code>.
	 */
	public final TableField<FeedsRecord, String> lastUrl = createField("last_url", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "", new StringConverter());

	/**
	 * The column <code>feedzee2.feeds.site_url</code>.
	 */
	public final TableField<FeedsRecord, String> siteUrl = createField("site_url", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "", new StringConverter());

	/**
	 * Create a <code>feedzee2.feeds</code> table reference
	 */
	public Feeds() {
		this("feeds", null);
	}

	/**
	 * Create an aliased <code>feedzee2.feeds</code> table reference
	 */
	public Feeds(java.lang.String alias) {
		this(alias, feeds);
	}

	private Feeds(java.lang.String alias, Table<FeedsRecord> aliased) {
		this(alias, aliased, null);
	}

	private Feeds(java.lang.String alias, Table<FeedsRecord> aliased, Field<?>[] parameters) {
		super(alias, Feedzee2.feedzee2, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Identity<FeedsRecord, Integer> getIdentity() {
		return Keys.IDENTITY_feeds;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<FeedsRecord> getPrimaryKey() {
		return Keys.keyFeedsPrimary;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<FeedsRecord>> getKeys() {
		return Arrays.<UniqueKey<FeedsRecord>>asList(Keys.keyFeedsPrimary);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Feeds as(java.lang.String alias) {
		return new Feeds(alias, this);
	}

	/**
	 * Rename this table
	 */
	public Feeds rename(java.lang.String name) {
		return new Feeds(name, null);
	}
}
