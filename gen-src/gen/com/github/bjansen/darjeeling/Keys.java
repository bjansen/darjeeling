/**
 * This class is generated by jOOQ
 */
package gen.com.github.bjansen.darjeeling;


import ceylon.language.Integer;

import gen.com.github.bjansen.darjeeling.tables.Feeds;
import gen.com.github.bjansen.darjeeling.tables.Folders;
import gen.com.github.bjansen.darjeeling.tables.Items;
import gen.com.github.bjansen.darjeeling.tables.ItemsToRead;
import gen.com.github.bjansen.darjeeling.tables.SchemaMigrations;
import gen.com.github.bjansen.darjeeling.tables.Subscriptions;
import gen.com.github.bjansen.darjeeling.tables.Users;
import gen.com.github.bjansen.darjeeling.tables.records.FeedsRecord;
import gen.com.github.bjansen.darjeeling.tables.records.FoldersRecord;
import gen.com.github.bjansen.darjeeling.tables.records.ItemsRecord;
import gen.com.github.bjansen.darjeeling.tables.records.ItemsToReadRecord;
import gen.com.github.bjansen.darjeeling.tables.records.SchemaMigrationsRecord;
import gen.com.github.bjansen.darjeeling.tables.records.SubscriptionsRecord;
import gen.com.github.bjansen.darjeeling.tables.records.UsersRecord;

import javax.annotation.Generated;

import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.AbstractKeys;


/**
 * A class modelling foreign key relationships between tables of the <code>feedzee2</code> 
 * schema
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.7.0"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

	// -------------------------------------------------------------------------
	// IDENTITY definitions
	// -------------------------------------------------------------------------

	public static final Identity<FeedsRecord, Integer> IDENTITY_feeds = Identities0.IDENTITY_feeds;
	public static final Identity<FoldersRecord, Integer> IDENTITY_folders = Identities0.IDENTITY_folders;
	public static final Identity<ItemsRecord, Integer> IDENTITY_items = Identities0.IDENTITY_items;
	public static final Identity<ItemsToReadRecord, Integer> IDENTITY_itemsToRead = Identities0.IDENTITY_itemsToRead;
	public static final Identity<SubscriptionsRecord, Integer> IDENTITY_subscriptions = Identities0.IDENTITY_subscriptions;
	public static final Identity<UsersRecord, Integer> IDENTITY_users = Identities0.IDENTITY_users;

	// -------------------------------------------------------------------------
	// UNIQUE and PRIMARY KEY definitions
	// -------------------------------------------------------------------------

	public static final UniqueKey<FeedsRecord> keyFeedsPrimary = UniqueKeys0.keyFeedsPrimary;
	public static final UniqueKey<FoldersRecord> keyFoldersPrimary = UniqueKeys0.keyFoldersPrimary;
	public static final UniqueKey<ItemsRecord> keyItemsPrimary = UniqueKeys0.keyItemsPrimary;
	public static final UniqueKey<ItemsToReadRecord> keyItemsToReadPrimary = UniqueKeys0.keyItemsToReadPrimary;
	public static final UniqueKey<SchemaMigrationsRecord> keySchemaMigrationsUniqueSchemaMigrations = UniqueKeys0.keySchemaMigrationsUniqueSchemaMigrations;
	public static final UniqueKey<SubscriptionsRecord> keySubscriptionsPrimary = UniqueKeys0.keySubscriptionsPrimary;
	public static final UniqueKey<UsersRecord> keyUsersPrimary = UniqueKeys0.keyUsersPrimary;

	// -------------------------------------------------------------------------
	// FOREIGN KEY definitions
	// -------------------------------------------------------------------------


	// -------------------------------------------------------------------------
	// [#1459] distribute members to avoid static initialisers > 64kb
	// -------------------------------------------------------------------------

	private static class Identities0 extends AbstractKeys {
		public static Identity<FeedsRecord, Integer> IDENTITY_feeds = createIdentity(Feeds.feeds, Feeds.feeds.id);
		public static Identity<FoldersRecord, Integer> IDENTITY_folders = createIdentity(Folders.folders, Folders.folders.id);
		public static Identity<ItemsRecord, Integer> IDENTITY_items = createIdentity(Items.items, Items.items.id);
		public static Identity<ItemsToReadRecord, Integer> IDENTITY_itemsToRead = createIdentity(ItemsToRead.itemsToRead, ItemsToRead.itemsToRead.id);
		public static Identity<SubscriptionsRecord, Integer> IDENTITY_subscriptions = createIdentity(Subscriptions.subscriptions, Subscriptions.subscriptions.id);
		public static Identity<UsersRecord, Integer> IDENTITY_users = createIdentity(Users.users, Users.users.id);
	}

	private static class UniqueKeys0 extends AbstractKeys {
		public static final UniqueKey<FeedsRecord> keyFeedsPrimary = createUniqueKey(Feeds.feeds, Feeds.feeds.id);
		public static final UniqueKey<FoldersRecord> keyFoldersPrimary = createUniqueKey(Folders.folders, Folders.folders.id);
		public static final UniqueKey<ItemsRecord> keyItemsPrimary = createUniqueKey(Items.items, Items.items.id);
		public static final UniqueKey<ItemsToReadRecord> keyItemsToReadPrimary = createUniqueKey(ItemsToRead.itemsToRead, ItemsToRead.itemsToRead.id);
		public static final UniqueKey<SchemaMigrationsRecord> keySchemaMigrationsUniqueSchemaMigrations = createUniqueKey(SchemaMigrations.schemaMigrations, SchemaMigrations.schemaMigrations.version);
		public static final UniqueKey<SubscriptionsRecord> keySubscriptionsPrimary = createUniqueKey(Subscriptions.subscriptions, Subscriptions.subscriptions.id);
		public static final UniqueKey<UsersRecord> keyUsersPrimary = createUniqueKey(Users.users, Users.users.id);
	}
}
