/**
 * This class is generated by jOOQ
 */
package gen.com.github.bjansen.darjeeling;

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
public class Feedzee2 extends org.jooq.impl.SchemaImpl {

	private static final long serialVersionUID = 343672221;

	/**
	 * The reference instance of <code>feedzee2</code>
	 */
	public static final Feedzee2 FEEDZEE2 = new Feedzee2();

	/**
	 * No further instances allowed
	 */
	private Feedzee2() {
		super("feedzee2");
	}

	@Override
	public final java.util.List<org.jooq.Table<?>> getTables() {
		java.util.List result = new java.util.ArrayList();
		result.addAll(getTables0());
		return result;
	}

	private final java.util.List<org.jooq.Table<?>> getTables0() {
		return java.util.Arrays.<org.jooq.Table<?>>asList(
			gen.com.github.bjansen.darjeeling.tables.Feeds.FEEDS,
			gen.com.github.bjansen.darjeeling.tables.Folders.FOLDERS,
			gen.com.github.bjansen.darjeeling.tables.Items.ITEMS,
			gen.com.github.bjansen.darjeeling.tables.ItemsToRead.ITEMS_TO_READ,
			gen.com.github.bjansen.darjeeling.tables.SchemaMigrations.SCHEMA_MIGRATIONS,
			gen.com.github.bjansen.darjeeling.tables.Storageversion.STORAGEVERSION,
			gen.com.github.bjansen.darjeeling.tables.Subscriptions.SUBSCRIPTIONS,
			gen.com.github.bjansen.darjeeling.tables.Users.USERS);
	}
}
